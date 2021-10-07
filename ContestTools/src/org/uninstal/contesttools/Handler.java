package org.uninstal.contesttools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.uninstal.contesttools.data.Contest;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;

public class Handler implements Listener {
	
	private static List<Object> memory = new ArrayList<>();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if(Contest.isRunning()) {
			ContestOptions options = Contest.getOptions();
			
			if(options.getType() == ContestType.MINE_BLOCKS) {
				
				Player player = e.getPlayer();
				Block block = e.getBlock();
				
				if(options.checkTarget(block) && 
					!memory.contains(block)) {
					
					int score = options.scoreOf(block);
					Contest.getPlayersData().reward(player, score);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		if(Contest.isRunning()) {
			
			Block b = e.getBlock();
			if(Contest.getOptions().checkTarget(b))
				memory.add(b);
			
			return;
		}
	}
	
	@EventHandler
	public void onEntityKill(EntityDeathEvent e) {
		
		if(Contest.isRunning()) {
			ContestOptions options = Contest.getOptions();
			
			if(options.getType() == ContestType.KILL_ENTITY) {
				
				LivingEntity entity = e.getEntity();
				Player killer = entity.getKiller();
				
				if(killer != null && options.checkTarget(entity)) {
					
					int score = options.scoreOf(entity);
					Contest.getPlayersData().reward(killer, score);
					return;
				}
			}
		}
	}
}
