package org.uninstal.contesttools.data.rewards.types;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.uninstal.contesttools.data.rewards.ContestReward;

public class RewardCommand extends ContestReward {

	private List<String> commands;

	public RewardCommand(String name, int chance, List<String> commands) {
		super(RewardType.COMMAND, name, chance, commands);
		this.commands = commands;
	}
	
	@Override
	public void transfer(Player player) {
		
		commands.forEach(command -> {
			
			command = command.replace("<player>", player.getName());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
		});
	}
}
