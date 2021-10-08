package org.uninstal.contesttools.data.rewards;

import org.bukkit.entity.Player;
import org.uninstal.contesttools.data.rewards.types.RewardType;

public abstract class ContestReward {
	
	private RewardType type;
	private Object reward;
	private String name;
	private int chance;

	public ContestReward(RewardType type, String name, int chance, Object reward) {
		this.type = type;
		this.name = name;
		this.reward = reward;
		this.chance = chance;
	}

	public abstract void transfer(Player player);
	
	public String getName() {
		return name;
	}
	
	public int getChance() {
		return chance;
	}
	
	public Object getReward() {
		return reward;
	}
	
	public RewardType getType() {
		return type;
	}
}