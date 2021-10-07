package org.uninstal.contesttools.data;

import org.uninstal.contesttools.data.rewards.ContestRewards;

public abstract class ContestOptions {
	
	private ContestType type;
	private int duration;
	private String id;
	private String name;
	private String description;
	private ContestRewards rewards;
	
	public ContestOptions(String id, String name, String description, 
		ContestType type, int duration, ContestRewards rewards) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.rewards = rewards;
		this.type = type;
	}
	
	public abstract boolean checkTarget(Object... target);
	public abstract int scoreOf(Object... target);
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public ContestType getType() {
		return type;
	}
	
	public ContestRewards getRewards() {
		return rewards;
	}
}
