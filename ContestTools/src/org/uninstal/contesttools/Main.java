package org.uninstal.contesttools;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.uninstal.contesttools.commands.ContestCommand;
import org.uninstal.contesttools.data.Contest;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestPlayersData;
import org.uninstal.contesttools.util.Messenger;
import org.uninstal.contesttools.util.Values;

public class Main extends JavaPlugin {
	
	private static Map<String, ContestCommand> commands;
	private static Main instance;
	private static Files files;

	@Override
	public void onEnable() {
		
		instance = this;
		files = new Files(this);
		
		// Registering and reading files.
		YamlConfiguration config = files.registerNewFile("config");
		YamlConfiguration contest = files.load("contest", true);
		Values.setConfig(config);
		Values.read();
		
		// If exists contest data-file.
		if(contest != null && Values.CONTEST_RESTART_ENABLE) {
			
			try {
				String id = contest.getString("contest.id");
				int time = contest.getInt("contest.time");
				
				if(!Values.CONTESTS.containsKey(id)) {
					Messenger.console("[ContestTools] &cType \"" + id + "\" is no longer functioning, "
							+ "the contest has been canceled.");
				}
				
				else {
					
					ContestPlayersData data = new ContestPlayersData();
					if(Values.CONTEST_SAVE_PROGRESS && contest.contains("contest.data")) {
						
						for(String player : contest
							.getConfigurationSection("contest.data")
							.getKeys(false)) {
							
							int value = contest.getInt("contest.data." + player);
							data.setValue(player, value);
							continue;
						}
					}
					
					ContestOptions options = Values.CONTESTS.get(id);
					Contest.restart(options, data, time);
				}
				
			} catch(Exception e) {
				Messenger.console("§cBad contest.yml file, ignore it.");
				files.getFile("contest").delete();
			}
		}
		
		// Registering handler.
		Bukkit.getPluginManager().registerEvents(new Handler(), this);
	}
	
	@Override
	public void onDisable() {
		
		if(Contest.isRunning() && Values.CONTEST_RESTART_ENABLE) {
			
			YamlConfiguration contest = new YamlConfiguration();
			contest.set("contest.id", Contest.getOptions().getId());
			contest.set("contest.time", Contest.getTime());
			
			if(Values.CONTEST_SAVE_PROGRESS) {
				
				ContestPlayersData data = Contest.getPlayersData();
				Map<String, Integer> map = data.get();
				
				for(Entry<String, Integer> set : map.entrySet()) {
					
					String player = set.getKey();
					int value = set.getValue();
					
					contest.set("contest.data." + player, value);
					continue;
				}
			}
			
			files.createFile("contest", contest);
			return;
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("contest")) {
			
			if(args.length == 0) {
				
				sender.sendMessage(
				Values.COMMAND_HELP);
				return false;
			}
			
			String arg0 = args[0];
			ContestCommand cmd = commands.get(arg0);
			
			if(cmd != null && args.length >= cmd.getLenght()) {
				cmd.run(sender, args);
				return false;
			}
			
			else {
				
				sender.sendMessage(
				Values.COMMAND_HELP);
				return false;
			}
		}
		
		return false;
	}
	
	public static Main getInstance() {
		return instance;
	}
}
