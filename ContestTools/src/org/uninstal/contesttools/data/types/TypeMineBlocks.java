package org.uninstal.contesttools.data.types;

import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;
import org.uninstal.contesttools.data.rewards.ContestRewards;

public class TypeMineBlocks extends ContestOptions {
	
	private Map<Material, Integer> scores;

	public TypeMineBlocks(String id, String name, 
			String description, int duration, 
			Map<Material, Integer> scores,
			ContestRewards rewards) {
		
		super(id, name, description, 
			ContestType.MINE_BLOCKS, duration, 
			rewards);
		
		this.scores = scores;
	}
	
	public Set<Material> getTargetBlocks() {
		return scores.keySet();
	}
	
	@Override
	public boolean checkTarget(Object... target) {
		Object type = target[0];
		
		if(type instanceof Material) {
			return scores.containsKey(type);
		}
		
		if(type instanceof Block) {
			return scores.containsKey(((Block) type).getType());
		}
		
		return false;
	}
	
	@Override
	public int scoreOf(Object... target) {
		Object material = target[0];
		
		if(scores != null && checkTarget(material)) {
			return scores.get(material(material));
		}
		
		return 1;
	}
	
	private Material material(Object target) {
		
		if(target instanceof Material) {
			return (Material) target;
		}
		
		if(target instanceof Block) {
			return ((Block) target).getType();
		}
		
		return null;
	}
}
