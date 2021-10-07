package org.uninstal.contesttools.data.rewards;

import java.util.ArrayList;
import java.util.List;

import org.uninstal.contesttools.util.Utils;

public class ContestRewards {

	private List<ContestReward> rewards = new ArrayList<>();
	
	public ContestRewards(List<ContestReward> rewards) {
		this.rewards = rewards;
	}
	
	public ContestReward random() {
		ContestReward reward = null;
		
		while(reward == null) {
			for(ContestReward r : rewards) {
				
				int chance = r.getChance();
				int check = Utils.random(1, 100);
				
				if(check > chance)
					reward = r;
				break;
			}
		}
		
		return reward;
	}
	
	public List<ContestReward> get() {
		return rewards;
	}
}
