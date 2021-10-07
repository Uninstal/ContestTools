package org.uninstal.contesttools.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public void setValue(String player, int value) {
		this.players.put(player, value);
	}
	
	public void setValue(Player player, int value) {
		this.players.put(player.getName(), value);
	}
	
	public String getFirstPlace() {
		String player = new String();
		
		int temp = 0;
		for(Entry<String, Integer> set : players.entrySet()) {
			if(set.getValue() > temp) {
				temp = set.getValue();
				player = set.getKey();
			}
		}
		
		return player;
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