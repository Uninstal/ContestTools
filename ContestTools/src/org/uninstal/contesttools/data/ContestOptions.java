package org.uninstal.contesttools.data;

public abstract class ContestOptions {
	
	private ContestType type;
	
	public ContestOptions(ContestType type) {
		this.type = type;
	}
	
	public abstract boolean checkTarget(Object target);
	public abstract int scoreFor(Object target);
	
	public ContestType getType() {
		return type;
	}
}
