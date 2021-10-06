package org.uninstal.contesttools.util;

import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;

public class Values {
	
	private static YamlConfiguration config;

	public static void setConfig(YamlConfiguration config) {
		Values.config = config;
	}

	public static void read() {
		
		// Read all the contests from config.
		for(String id : config.getConfigurationSection("contests")
				.getKeys(false)) {
			
			String contest = config.getString("contests." + id + ".type");
			ContestType type = ContestType.of(contest);
			
			if(type == null) {
				
				Messenger.console("§c[Config] Type " + contest+ " is non-existent.");
				Messenger.console("§c[Config] Contest '" + id + "' will not be uploaded.");
				continue;
			}
			
			switch (type) {
			
				case MINE_BLOCKS:
					
					
					
					break;
					
				case KILL_ENTITY:
					
					
					
					break;

				default:
					break;
			}
		}
	}
	
	public static Map<String, ContestOptions> CONTESTS;
	
	public static String COMMAND_HELP;
	public static String CONTEST_NOTIFICATION;
	public static String CONTEST_START;
	public static String CONTEST_END;
}
