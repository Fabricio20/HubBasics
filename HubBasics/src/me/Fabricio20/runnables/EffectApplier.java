package me.Fabricio20.runnables;

import me.Fabricio20.Main;
import me.Fabricio20.methods.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EffectApplier extends BukkitRunnable {

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if(Main.theClass.config.getBoolean("JoinEvents.Effects.Enabled") == true) {
			if(Main.theClass.config.getBoolean("JoinEvents.Effects.SPEED") == true) {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(ModuleManager.theClass.isInWorld(players.getWorld().getName())) {
						PotionEffect potion = new PotionEffect(PotionEffectType.SPEED, 20 * 15, 2);
						players.addPotionEffect(potion);
					}
				}
			}
			if(Main.theClass.config.getBoolean("JoinEvents.Effects.JUMP") == true) {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(ModuleManager.theClass.isInWorld(players.getWorld().getName())) {
						PotionEffect potion = new PotionEffect(PotionEffectType.JUMP, 20 * 15, 2);
						players.addPotionEffect(potion);
					}
				}
			}
		}
	}
	
}
