package me.Fabricio20.listeners;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public HealthListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void Health(EntityDamageEvent e) {
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if (plugin.getConfig().getBoolean("Others.KeepHealth") == true) {
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
