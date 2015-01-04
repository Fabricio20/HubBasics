package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class RainListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void RainStart(WeatherChangeEvent e) {
		if(Main.getPlugin().getConfig().getBoolean("Others.DisableRain") == true) {
			List<String> worlds = Main.getPlugin().getConfig().getStringList("Worlds");
			if(worlds.contains(e.getWorld().getName())) {
				if(!e.isCancelled()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
