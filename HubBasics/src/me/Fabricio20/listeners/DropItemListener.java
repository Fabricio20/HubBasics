package me.Fabricio20.listeners;

import java.util.List;

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
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if (plugin.getConfig().getBoolean("Others.NoDrops") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
		if(plugin.getConfig().getBoolean("MagicClock.NoDrop") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
		if(plugin.getConfig().getBoolean("Others.HatAllowMove") == false) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
