package me.Fabricio20.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HungerListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public HungerListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void Hunger(FoodLevelChangeEvent e) {
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if (plugin.getConfig().getBoolean("Others.KeepFood") == true) {
			if(worlds.contains(e.getEntity().getWorld().getName())) {
				if (e.getFoodLevel() < 20) {
					e.setFoodLevel(20);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
