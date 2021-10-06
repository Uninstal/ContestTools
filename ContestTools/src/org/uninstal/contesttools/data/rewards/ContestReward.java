package org.uninstal.contesttools.data.rewards;

import org.bukkit.entity.Player;

public abstract class ContestReward {
	
	private Object reward;
	private String name;
	private int chance;

	public ContestReward(String name, int chance, Object reward) {
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
}