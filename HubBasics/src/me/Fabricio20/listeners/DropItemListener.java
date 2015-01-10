package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.methods.Items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener {
	
	@EventHandler
	public void Drop(PlayerDropItemEvent e) {
		List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
		if (Main.theClass.getPlugin().getConfig().getBoolean("Others.NoDrops") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
		if(Main.theClass.getPlugin().getConfig().getBoolean("MagicClock.NoDrop") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				if(e.getItemDrop().getItemStack().equals(Items.MagicClock(e.getPlayer().getName()))) {
					e.setCancelled(true);
				}
			}
		}
		if(Main.theClass.getPlugin().getConfig().getBoolean("Others.HatDrop") == false) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
