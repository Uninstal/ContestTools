package org.uninstal.contesttools.data;

public abstract class ContestOptions {
	
	private ContestType type;
	private int duration;
	private String id;
	private String name;
	private String description;
	
	public ContestOptions(String id, String name, String description, ContestType type, int duration) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.type = type;
	}
	
	public abstract boolean checkTarget(Object target);
	public abstract int scoreFor(Object target);
	
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
}
