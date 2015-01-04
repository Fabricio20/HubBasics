package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener {
	
	@EventHandler
	public void Drop(PlayerDropItemEvent e) {
		List<String> worlds = Main.getPlugin().getConfig().getStringList("Worlds");
		if (Main.getPlugin().getConfig().getBoolean("Others.NoDrops") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
		if(Main.getPlugin().getConfig().getBoolean("MagicClock.NoDrop") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
		if(Main.getPlugin().getConfig().getBoolean("Others.HatAllowMove") == false) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
