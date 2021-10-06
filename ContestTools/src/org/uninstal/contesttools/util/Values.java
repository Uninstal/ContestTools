package org.uninstal.contesttools.util;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;
import org.uninstal.contesttools.data.types.TypeKillEntity;
import org.uninstal.contesttools.data.types.TypeMineBlocks;

public class Values {
	
	private static YamlConfiguration config;

	public static void setConfig(YamlConfiguration config) {
		Values.config = config;
	}

	public static void read() {
		
		// Read all the contests from config.
		for(String id : config.getConfigurationSection("contests")
				.getKeys(false)) {
			String path = "contests." + id;
			
			String contest = config.getString(path + ".type");
			ContestType type = ContestType.of(contest);
			
			if(type == null) {
				
				Messenger.console("§c[ContestTools] Type " + contest + " is non-existent.");
				Messenger.console("§c[ContestTools] Contest '" + id + "' will not be uploaded.");
				continue;
			}
			
			String name = config.getString(path + ".options.name");
			String desc = config.getString(path + ".options.desc");
			int duration = config.getInt(path + ".options.desc", 12000);
			
			ContestOptions options = null;
			
			if(type == ContestType.MINE_BLOCKS) {

				Map<Material, Integer> scores = Utils.map(config, path + ".targets", 
					k -> Utils.ofMaterial(k), 
					v -> Integer.valueOf(v));
				
				options = new TypeMineBlocks(
					id, name, desc, 
					duration, scores);
			}
			
			if(type == ContestType.KILL_ENTITY) {
				
				Map<EntityType, Integer> scores = Utils.map(config, path + ".targets", 
					k -> Utils.ofEntity(k), 
					v -> Integer.valueOf(v));
				
				options = new TypeKillEntity(
					id, name, desc, 
					duration, scores);
			}
			
			Values.CONTESTS.put(id, options);
			Messenger.console("§a[ContestTools] Contest '" + id + "' was uploaded.");
			
			continue;
		}
	}
	
	public static Map<String, ContestOptions> CONTESTS;
	
	public static String COMMAND_HELP;
	public static String CONTEST_NOTIFICATION;
	public static String CONTEST_START;
	public static String CONTEST_END;
}
