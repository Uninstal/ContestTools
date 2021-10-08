package org.uninstal.contesttools.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Utils {
	
	private static Random random = new Random();
	
	public static int random(int start, int end) {
		return random.nextInt(end) + start;
	}
	
	public static ItemStack ofItemStack(String index) {
		return null;
	}
	
	public static Material ofMaterial(String index) {
		
		index = index.toUpperCase();
		Material material = Material.valueOf(index);
		return material;
	}
	
	public static EntityType ofEntity(String index) {
		
		index = index.toUpperCase();
		EntityType entityType = EntityType.valueOf(index);
		return entityType;
	}
	
	public static <T, K> List<T> transformCollect(List<K> list, Function<K, T> func){
		List<T> newList = new ArrayList<>();
		
		for(K param : list) {
			T value = func.apply(param);
			
			if(value == null) { // Zero-value protection.
				
				Messenger.console("§c[ContestTools] Param '" + param + "' is incorrect.");
				Messenger.console("§c[ContestTools] It won't be uploaded...");
				continue;
			}
			
			newList.add(value);
			continue;
		}
		
		return newList;
	}
	
	// The hashmap collector from the config section, 
	// which converts the data from there to the necessary.
	public static <T, V> Map<T, V> map(YamlConfiguration config, String path, 
		Function<String, T> keys,
		Function<String, V> values) {
		
		Map<T, V> map = new HashMap<>();
		ConfigurationSection cs = config.getConfigurationSection(path);
		
		for(String param : cs.getKeys(false)) {
			String value = cs.getString(param);
			
			T key = keys.apply(param);
			if(key == null) { // Zero-key protection.
				
				Messenger.console("§c[ContestTools] Param '" + param + "' is incorrect.");
				Messenger.console("§c[ContestTools] It won't be uploaded...");
				continue;
			}
			
			map.put(key, 
			values.apply(value));
			continue;
		}
		
		return map;
	}
}