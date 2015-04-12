package me.Fabricio20.Bukkit.Listeners.Player;

import java.util.List;

import me.Fabricio20.Bukkit.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {
	
	@EventHandler
	public void Hunger(FoodLevelChangeEvent e) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if (Main.theClass.config.getBoolean("Others.KeepFood") == true) {
			if(worlds.contains(e.getEntity().getWorld().getName())) {
				if (e.getFoodLevel() < 20) {
					e.setFoodLevel(20);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
