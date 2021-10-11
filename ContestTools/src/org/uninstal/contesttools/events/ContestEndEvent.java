package org.uninstal.contesttools.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.rewards.ContestReward;

public class ContestEndEvent extends Event {

	private static HandlerList handlerList = new HandlerList();
	private ContestOptions options;
	private String winner;
	private ContestReward reward;
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	public ContestEndEvent(ContestOptions options, String winner, ContestReward reward) {
		this.options = options;
		this.winner = winner;
		this.reward = reward;
	}
	
	public ContestOptions getOptions() {
		return options;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public ContestReward getWinnerReward() {
		return reward;
	}
}
