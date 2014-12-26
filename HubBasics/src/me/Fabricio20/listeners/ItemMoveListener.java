package me.Fabricio20.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemMoveListener implements Listener {
	
	private final JavaPlugin plugin;

	public ItemMoveListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onItemMove(InventoryClickEvent e) {
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if (plugin.getConfig().getBoolean("Others.AllowItemMove") == false) {
			if (e.getClickedInventory() != null) {
				if(worlds.contains(e.getWhoClicked().getWorld().getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
