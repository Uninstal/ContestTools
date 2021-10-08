package org.uninstal.contesttools.data.rewards.types;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.uninstal.contesttools.data.rewards.ContestReward;

public class RewardItem extends ContestReward {
	
	private List<ItemStack> rewards;

	public RewardItem(String name, int chance, List<ItemStack> items) {
		super(RewardType.ITEM, name, chance, items);
		this.rewards = items;
	}
	
	@Override
	public void transfer(Player player) {
		
		PlayerInventory inventory = player.getInventory();
		
		for(ItemStack reward : this.rewards) {
			
			// Checking for an empty slot in the inventory to see 
			// if it is possible to give the item to the player.
			if(!inventory.contains(Material.AIR)) {
				
				Location playerLocation = player.getLocation();
				playerLocation.getWorld().dropItem(playerLocation, reward);
				return;
			}
			
			inventory.addItem(reward);
		}
		return;
	}
}
