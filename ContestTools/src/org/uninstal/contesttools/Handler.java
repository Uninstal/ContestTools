package org.uninstal.contesttools;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.uninstal.contesttools.data.Contest;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;

public class Handler implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if(Contest.isRunning()) {
			ContestOptions options = Contest.getCurrentContest();
			
			if(options.getType() == ContestType.MINE_BLOCKS) {
				
				Player player = e.getPlayer();
				Block block = e.getBlock();
				
				if(options.checkTarget(block)) {
					
					int score = options.scoreFor(block);
					Contest.getPlayersData().reward(player, score);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityKill(EntityDeathEvent e) {
		
		if(Contest.isRunning()) {
			ContestOptions options = Contest.getCurrentContest();
			
			if(options.getType() == ContestType.KILL_ENTITY) {
				
				LivingEntity entity = e.getEntity();
				Player killer = entity.getKiller();
				
				if(killer != null && options.checkTarget(entity)) {
					
					int score = options.scoreFor(entity);
					Contest.getPlayersData().reward(killer, score);
					return;
				}
			}
		}
	}
}
