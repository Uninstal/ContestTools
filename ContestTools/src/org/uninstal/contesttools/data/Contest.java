package org.uninstal.contesttools.data;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Contest {
	
	private static BukkitTask task;
	private static boolean running = false;
	private static ContestPlayersData data;
	private static ContestOptions currentContest;
	
	public static boolean isRunning() {
		return running;
	}
	
	public static ContestPlayersData getPlayersData() {
		return data;
	}
	
	public static ContestOptions getCurrentContest() {
		return currentContest;
	}
	
	public static boolean start(ContestOptions options) {
		if(isRunning()) return false;
		
		task = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				
			}
			
		}.runTaskTimerAsynchronously(null, 0, 0);
		return true;
	}
	
	public static void end() {
		task.cancel();
		
		running = false;
		data = null;
		currentContest = null;
	}
}
