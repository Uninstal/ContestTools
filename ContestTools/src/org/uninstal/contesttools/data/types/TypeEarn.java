package org.uninstal.contesttools.data.types;

import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;
import org.uninstal.contesttools.data.rewards.ContestRewards;

public class TypeEarn extends ContestOptions {

	private int cost;

	public TypeEarn(String id, String name, 
		String description, int duration,
		int cost, ContestRewards rewards) {
		
		super(id, name, description, ContestType.EARN, duration, rewards);
		this.cost = cost;
	}

	@Override
	public boolean checkTarget(Object... target) {
		
		if(target[0].getClass().equals(int.class))
			return true;
		
		return false;
	}

	@Override
	public int scoreOf(Object... target) {
		return ((int) target[0]) / cost;
	}
}
