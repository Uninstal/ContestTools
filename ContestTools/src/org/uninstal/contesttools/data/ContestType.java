package org.uninstal.contesttools.data;

public enum ContestType {

	MINE_BLOCKS("mine_blocks"), 
	EARN_MONEY("earn_money"), 
	KILL_ENTITY("kill_entity");
	
	private String name;

	private ContestType(String name) {
		this.name = name;
	}
	
	public static ContestType of(String index) {
		
		for(ContestType type : ContestType.values())
			if(type.toString().equalsIgnoreCase(index))
				return type;
		
		return null;
	}
	
	public static String names() {
		
		StringBuilder sb = new StringBuilder();
		for(ContestType type : ContestType.values())
			sb.append(", " + type.toString());
		
		return sb.toString().substring(2);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
