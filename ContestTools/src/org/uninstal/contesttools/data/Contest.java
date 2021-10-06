package org.uninstal.contesttools.data;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.uninstal.contesttools.Main;
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
		ContestPlayersData playersData, 
		int time, boolean notification) {
		if(isRunning()) return false;
		
		
		
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
		data = new ContestPlayersData();
		
		task = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				if(currentContest.getType() == ContestType.EARN_MONEY) {
					//Code with temp data (TemporaryData class).
				}
				
				if(time == options.getDuration()) {
					
					//Rewards and other...
					
					end();
					return;
				}
				
				time++;
				return;
			}
			
		}.runTaskTimerAsynchronously(Main.getInstance(), 20, 20);
		return true;
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
	}
}
