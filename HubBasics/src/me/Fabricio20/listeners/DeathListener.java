package me.Fabricio20.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public DeathListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void Die(PlayerDeathEvent e) {
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if(plugin.getConfig().getBoolean("Others.NoDeathDrops") == true) {
			if(worlds.contains(e.getEntity().getWorld().getName())) {
				e.getDrops().clear();
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
