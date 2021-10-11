package org.uninstal.contesttools.data.types;

import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;
import org.uninstal.contesttools.data.rewards.ContestRewards;

public class TypeKill extends ContestOptions {

	private Map<EntityType, Integer> scores;

	public TypeKill(String id, String name, 
			String description, int duration, 
			Map<EntityType, Integer> scores,
			ContestRewards rewards) {
		
		super(id, name, description, 
			ContestType.KILL, duration, 
			rewards);
		
		this.scores = scores;
	}
	
	public Set<EntityType> getTargetEntities() {
		return scores.keySet();
	}

	@Override
	public boolean checkTarget(Object... target) {
		Object type = target[0];
		
		if(type instanceof EntityType) {
			return scores.containsKey(type);
		}
		
		if(type instanceof Entity) {
			return scores.containsKey(((Entity) type).getType());
		}
		
		return false;
	}

	@Override
	public int scoreOf(Object... target) {
		Object type = target[0];
		
		if(scores != null && checkTarget(type)) {
			return scores.get(entity(type));
		}
		
		return 1;
	}
	
	private EntityType entity(Object target) {
		
		if(target instanceof EntityType) {
			return (EntityType) target;
		}
		
		if(target instanceof Entity) {
			return ((Entity) target).getType();
		}
		
		return null;
	}
}
