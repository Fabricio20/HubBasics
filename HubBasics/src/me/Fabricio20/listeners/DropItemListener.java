package me.Fabricio20.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DropItemListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public DropItemListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@EventHandler
	public void Drop(PlayerDropItemEvent e) {
		if (plugin.getConfig().getBoolean("NoDrops") == true) {
			e.setCancelled(true);
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
