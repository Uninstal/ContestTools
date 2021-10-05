package org.uninstal.contesttools.data;

public enum ContestType {

	MINE_BLOCKS("mine_blocks"), 
	EARN_MONEY("earn_money"), 
	KILL_ENTITY("kill_entity");
	
	private String name;

	private ContestType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
