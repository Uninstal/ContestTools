package org.uninstal.contesttools.data.types;

import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;

public class TypeMineBlocks extends ContestOptions {
	
	private Map<Material, Integer> scores;

	public TypeMineBlocks(String id, String name, 
			String description, int duration, 
			Map<Material, Integer> scores) {
		
		super(id, name, description, ContestType.MINE_BLOCKS, duration);
		this.scores = scores;
	}
	
	public Set<Material> getTargetBlocks() {
		return scores.keySet();
	}
	
	@Override
	public boolean checkTarget(Object target) {
		
		if(target instanceof Material) {
			return scores.containsKey(target);
		}
		
		if(target instanceof Block) {
			return scores.containsKey(((Block) target).getType());
		}
		
		return false;
	}
	
	@Override
	public int scoreOf(Object target) {
		
		if(scores != null && checkTarget(target)) {
			return scores.get(material(target));
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
