package org.uninstal.contesttools.util;

import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;
import org.uninstal.contesttools.data.ContestOptions;

public class Values {
	
	private static YamlConfiguration config;

	public static void setConfig(YamlConfiguration config) {
		Values.config = config;
	}

	public static void read() {
		
		
	}
	
	public static Map<String, ContestOptions> CONTESTS;
	
	public static String COMMAND_HELP;
	public static String CONTEST_START;
}
