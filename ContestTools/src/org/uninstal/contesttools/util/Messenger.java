package org.uninstal.contesttools.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class Messenger {
	
	public static void send(Player player, String message) {
		String lower = message.toLowerCase();
		
		if(lower.startsWith("title:") && lower.contains("subtitle:")) {
			
			int index = lower.indexOf("subtitle:");
			String title = message.substring(6, index);
			String subtitle = message.substring(index + 9);
			
			player.sendTitle(title, subtitle, 10, 40, 10);
			return;
		}
		
		if(lower.startsWith("actionbar:")) {
			message = message.substring(10);
			
			BaseComponent component = new TextComponent(message);
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
			return;
		}
		
		player.sendMessage(message);
		return;
	}
	
	public static void announce(String message) {
		
		for(Player player : Bukkit.getOnlinePlayers())
			send(player, message);
	}
	
	public static void console(String message) {
		Bukkit.getConsoleSender().sendMessage(message.replace("&", "§"));
	}
}
