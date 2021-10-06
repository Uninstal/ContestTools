package org.uninstal.contesttools.data.types;

import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;

public class TypeKillEntity extends ContestOptions {

	private Map<EntityType, Integer> scores;

	public TypeKillEntity(String id, String name, 
			String description, int duration, 
			Map<EntityType, Integer> scores) {
		
		super(id, name, description, ContestType.KILL_ENTITY, duration);
		this.scores = scores;
	}
	
	public Set<EntityType> getTargetEntities() {
		return scores.keySet();
	}

	@Override
	public boolean checkTarget(Object target) {
		
		if(target instanceof EntityType) {
			return scores.containsKey(target);
		}
		
		if(target instanceof Entity) {
			return scores.containsKey(((Entity) target).getType());
		}
		
		return false;
	}

	@Override
	public int scoreOf(Object target) {
		
		if(scores != null && checkTarget(target)) {
			return scores.get(entity(target));
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
