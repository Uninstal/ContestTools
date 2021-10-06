package org.uninstal.contesttools.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

public class Utils {
	
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
	
	// The hashmap collector from the config section, 
	// which converts the data from there to the necessary.
	public static <T, V> Map<T, V> map(YamlConfiguration config, String path, 
		Function<String, ? extends T> keys,
		Function<String, ? extends V> values) {
		
		Map<T, V> map = new HashMap<>();
		ConfigurationSection cs = config.getConfigurationSection(path);
		
		for(String param : cs.getKeys(false)) {
			String value = cs.getString(param);
			
			T key = keys.apply(param);
			if(key == null) { // Zero-key protection.
				
				Messenger.console("§c[ContestTools] Param '" + param + "' is non-existent.");
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