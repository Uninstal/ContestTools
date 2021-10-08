package org.uninstal.contesttools.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.uninstal.contesttools.data.ContestOptions;
import org.uninstal.contesttools.data.ContestType;
import org.uninstal.contesttools.data.rewards.ContestReward;
import org.uninstal.contesttools.data.rewards.ContestRewards;
import org.uninstal.contesttools.data.rewards.types.RewardCommand;
import org.uninstal.contesttools.data.rewards.types.RewardItem;
import org.uninstal.contesttools.data.rewards.types.RewardType;
import org.uninstal.contesttools.data.types.TypeKillEntity;
import org.uninstal.contesttools.data.types.TypeMineBlocks;

public class Values {
	
	private static YamlConfiguration config;

	public static void setConfig(YamlConfiguration config) {
		Values.config = config;
	}

	public static void read() {
		long start = System.currentTimeMillis();
		
		// Read all the contests from config.
		for(String id : config.getConfigurationSection("contests")
				.getKeys(false)) {
			String path = "contests." + id;
			
			String contest = config.getString(path + ".type");
			ContestType type = ContestType.of(contest);
			
			if(type == null) {
				
				Messenger.console("§c[ContestTools] Type " + contest + " is non-existent.");
				Messenger.console("§c[ContestTools] Contest '" + id + "' will not be uploaded.");
				continue;
			}
			
			String name = config.getString(path + ".options.name");
			String desc = config.getString(path + ".options.desc");
			int duration = config.getInt(path + ".options.duration", 12000);
			List<ContestReward> rewards = new ArrayList<>();
			
			// Rewards load.
			for(String rewardId : config.getConfigurationSection(path + ".rewards")
				.getKeys(false)) {
				String path2 = path + ".rewards." + rewardId;
				
				String rewardName = config.getString(path2 + ".name");
				String reward = config.getString(path2 + ".type");
				int chance = config.getInt(path2 + ".chance");
				List<String> rewardValues = config.getStringList(path2 + ".values");
				RewardType rewardType = RewardType.of(reward);
				
				ContestReward r = null;
				
				if(rewardType == RewardType.COMMAND)
					r = new RewardCommand(rewardName, chance, rewardValues);
				
				else if(rewardType == RewardType.ITEM) {
					
					List<ItemStack> items = Utils
						.transformCollect(rewardValues,
						(t) -> Utils.ofItemStack(t));
					
					r = new RewardItem(rewardName, chance, items);
				}
				
				rewards.add(r);
				continue;
			}
			
			ContestOptions options = null;
			
			if(type == ContestType.MINE_BLOCKS) {

				Map<Material, Integer> scores = Utils.map(
					config, path + ".targets", 
					k -> Utils.ofMaterial(k), 
					v -> Integer.valueOf(v));
				
				options = new TypeMineBlocks(
					id, name, desc, 
					duration, scores,
					new ContestRewards(rewards));
			}
			
			else if(type == ContestType.KILL_ENTITY) {
				
				Map<EntityType, Integer> scores = Utils.map(
					config, path + ".targets", 
					k -> Utils.ofEntity(k), 
					v -> Integer.valueOf(v));
				
				options = new TypeKillEntity(
					id, name, desc, 
					duration, scores,
					new ContestRewards(rewards));
			}
			
			Values.CONTESTS.put(id, options);
			Messenger.console("§a[ContestTools] Contest '" + id + "' was uploaded.");
			
			continue;
		}
		
		Messenger.console("Config read in " + 
		(System.currentTimeMillis() - start) + "ms.");
	}
	
	// All types of contests and their settings.
	public static Map<String, ContestOptions> CONTESTS;
	
	// Values of command messages.
	public static String COMMAND_HELP;
	
	// The values of the messages about the beginning/end of the contest.
	public static String CONTEST_NOTIFICATION;
	public static String CONTEST_START;
	public static String CONTEST_END;
	
	// Values of restart contest.
	public static boolean CONTEST_RESTART_ENABLE;
	public static boolean CONTEST_SAVE_PROGRESS;
	public static int CONTEST_RESTART_DELAY;
	public static String CONTEST_RESTART;
}
