package org.uninstal.contesttools.data;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class ContestPlayersData {

	private Map<String, Integer> players = new HashMap<>();
	
	public void reward(String player) {
		put(player, 1);
	}
	
	public void reward(String player, int value) {
		put(player, value);
	}
	
	public void reward(Player player) {
		put(player.getName(), 1);
	}
	
	public void reward(Player player, int value) {
		put(player.getName(), value);
	}
	
	public Map<String, Integer> get() {
		return players;
	}
	
	private void put(String player, int value) {
		
		if(players.containsKey(player))
			players.put(player, players.get(player) + value);
		else players.put(player, value);
	}
}