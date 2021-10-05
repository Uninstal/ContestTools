package org.uninstal.contesttools.commands;

import org.bukkit.command.CommandSender;

public abstract class ContestCommand {
	
	private int lenght;
	
	public ContestCommand(int lenght) {
		this.lenght = lenght;
	}
	
	public int getLenght() {
		return lenght;
	}
	
	public abstract boolean run(CommandSender sender, String[] args);
}