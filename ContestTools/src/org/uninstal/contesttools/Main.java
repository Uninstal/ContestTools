package org.uninstal.contesttools;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.uninstal.contesttools.commands.ContestCommand;
import org.uninstal.contesttools.data.Contest;
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
		
		YamlConfiguration config = files.registerNewFile("config");
		YamlConfiguration contest = files.load("contest", false);
		Values.setConfig(config);
		Values.read();
		
		//If exists contest data-file.
		if(contest != null) {
			
			String type = contest.getString("contest.type");
			
			
			if(!Values.CONTESTS.containsKey(contest.getString("contest.type"))) {
				Messenger.console("&cType \"" + type + "\" is no longer functioning, "
						+ "the contest has been canceled.");
			}
			
			else {
				
			}
		}
	}
	
	@Override
	public void onDisable() {
		
		if(Contest.isRunning()) {
			
			YamlConfiguration contest = new YamlConfiguration();
			contest.set("contest.type", Contest.getOptions().getId());
			contest.set("contest.time", Contest.getTime());
			
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
