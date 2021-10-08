package org.uninstal.contesttools.data.rewards.types;

public enum RewardType {

	ITEM("item"), 
	COMMAND("command");
	
	private String id;

	private RewardType(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public static RewardType of(String index) {
		
		for(RewardType type : RewardType.values())
			if(type.toString().equalsIgnoreCase(index))
				return type;
		
		return null;
	}
}
