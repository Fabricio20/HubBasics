package me.Fabricio20.listeners.Item;

import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.methods.Items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener {
	
	@EventHandler
	public void Drop(PlayerDropItemEvent e) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if (Main.theClass.config.getBoolean("Others.NoDrops") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
		if(Main.theClass.config.getBoolean("MagicClock.NoDrop") == true) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				if(e.getItemDrop().getItemStack().equals(Items.MagicClock(e.getPlayer().getName()))) {
					e.setCancelled(true);
				}
			}
		}
		if(Main.theClass.config.getBoolean("HatSystem.AllowDrops") == false) {
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				if(e.getItemDrop().getItemStack().hasItemMeta()) {
					if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().contains(Main.theClass.config.getString("HatSystem.Name").substring(3))) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
