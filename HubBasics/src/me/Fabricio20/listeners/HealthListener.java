package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HealthListener implements Listener {
	
	@EventHandler
	public void Health(EntityDamageEvent e) {
		List<String> worlds = Main.getPlugin().getConfig().getStringList("Worlds");
		if (Main.getPlugin().getConfig().getBoolean("Others.KeepHealth") == true) {
			if(e.getEntityType() == EntityType.PLAYER) {
				if(worlds.contains(e.getEntity().getWorld().getName())) {
					e.setDamage(0.0);
					e.setCancelled(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
