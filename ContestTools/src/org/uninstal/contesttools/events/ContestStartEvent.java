package org.uninstal.contesttools.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.uninstal.contesttools.data.ContestOptions;

public class ContestStartEvent extends Event {
	
	private static HandlerList handlerList = new HandlerList();
	private ContestOptions options;
	private boolean restart;
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	public ContestStartEvent(ContestOptions options, boolean restart) {
		this.options = options;
		this.restart = restart;
	}
	
	public ContestOptions getOptions() {
		return options;
	}
	
	public boolean isRestart() {
		return restart;
	}
}
