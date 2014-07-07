package me.Fabricio20.listeners;

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
		if (plugin.getConfig().getBoolean("KeepHealth") == true) {
			if(e.getEntityType() == EntityType.PLAYER) {
				e.setDamage(0.0);
				e.setCancelled(true);				
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
