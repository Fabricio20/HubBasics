package me.Fabricio20.listeners;

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
		if(plugin.getConfig().getBoolean("NoDeathDrops") == true) {
			e.getDrops().clear();
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
