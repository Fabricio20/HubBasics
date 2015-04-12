package me.Fabricio20.Bukkit.Listeners.Others;

import java.util.List;

import me.Fabricio20.Bukkit.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class RainListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void RainStart(WeatherChangeEvent e) {
		if(Main.theClass.config.getBoolean("Others.DisableRain") == true) {
			List<String> worlds = Main.theClass.config.getStringList("Worlds");
			if(worlds.contains(e.getWorld().getName())) {
				if(!e.isCancelled()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
