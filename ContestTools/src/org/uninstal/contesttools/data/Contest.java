package org.uninstal.contesttools.data;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.uninstal.contesttools.Main;
import org.uninstal.contesttools.data.rewards.ContestReward;
import org.uninstal.contesttools.data.rewards.ContestRewards;
import org.uninstal.contesttools.util.Economy;
import org.uninstal.contesttools.util.Messenger;
import org.uninstal.contesttools.util.Values;

public class Contest {
	
	private static BukkitTask task;
	private static int time;
	private static boolean running = false;
	
	private static ContestPlayersData data;
	private static ContestOptions currentContest;
	
	public static boolean isRunning() {
		return running;
	}
	
	public static ContestPlayersData getPlayersData() {
		return data;
	}
	
	public static ContestOptions getOptions() {
		return currentContest;
	}
	
	public static boolean restart(ContestOptions options, 
		ContestPlayersData playersData, int time, boolean delayed) {
		if(isRunning()) return false;
		
		if(delayed) {
			
			task = Bukkit.getScheduler().runTaskLaterAsynchronously
				(Main.getInstance(), 
					() -> restart(options, playersData, time, false), 
					Values.CONTEST_RESTART_DELAY * 20);
			
			return true;
		}
		
		String message = Values.CONTEST_RESTART;
		message = message.replace("<name>", options.getName());
		message = message.replace("<time>", String.valueOf(options.getDuration()));
		message = message.replace("<desc>", options.getDescription());
		Messenger.announce(message);
		
		Contest.currentContest = options;
		Contest.data = playersData;
		Contest.time = time;
		
		runContestTask();
		return true;
	}
	
	public static boolean start(ContestOptions options, boolean notification) {
		if(isRunning()) return false;
		
		if(notification) {
			
			String message = Values.CONTEST_NOTIFICATION;
			message = message.replace("<name>", options.getName());
			message = message.replace("<time>", String.valueOf(options.getDuration()));
			message = message.replace("<desc>", options.getDescription());
			Messenger.announce(message);
			
			task = Bukkit.getScheduler().runTaskLaterAsynchronously
			(Main.getInstance(), 
					() -> Contest.start(options, false), 
					20 /* this time */);
			
			return true;
		}
		
		String message = Values.CONTEST_START;
		message = message.replace("<name>", options.getName());
		message = message.replace("<time>", String.valueOf(options.getDuration()));
		message = message.replace("<desc>", options.getDescription());
		Messenger.announce(message);
		
		running = true;
		currentContest = options;
		if(data == null)
			data = new ContestPlayersData();
		
		runContestTask();
		return true;
	}
	
	private static void runContestTask() {
		
		task = new BukkitRunnable() {
			
			private Map<String, Integer> balances = new HashMap<>();
			
			@Override
			public void run() {
				
				// For the balance contest.
				if(currentContest.getType() == ContestType.EARN_MONEY) {
					
					for(Player player : Bukkit.getOnlinePlayers()) {
						String name = player.getName();
						int cur = Economy.getMoney(player);
						
						if(!balances.containsKey(name)) {
							balances.put(name, Economy.getMoney(player));
							continue;
						}
						
						else {
							
							int old = balances.get(name);
							int reward = currentContest.scoreOf(old, cur);
							
							if(reward != 0)
								data.setValue(player, reward);
							continue;
						}
					}
				}
				
				if(time >= currentContest.getDuration()) {
					
					ContestRewards rewards = currentContest.getRewards();
					String winner = data.getFirstPlace();
					Player player = Bukkit.getPlayer(winner);
					
					if(player == null) {
						// I think...
						return;
					}
					
					ContestReward reward = rewards.random();
					reward.transfer(player);
					
					end();
					return;
				}
				
				time++;
				return;
			}
			
		}.runTaskTimerAsynchronously(Main.getInstance(), 20, 20);
	}
	
	public static int getTime() {
		return time;
	}
	
	public static void setTime(int time) {
		Contest.time = time;
	}
	
	public static int getRemainingTime() {
		return currentContest.getDuration() - time;
	}
	
	public static void end() {
		task.cancel();
		
		running = false;
		data = null;
		currentContest = null;
		time = 0;
	}
}
