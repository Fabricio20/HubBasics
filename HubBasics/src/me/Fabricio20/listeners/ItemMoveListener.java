package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class ItemMoveListener implements Listener {
	
	@EventHandler
	public void onItemMove(InventoryClickEvent e) {
		List<String> worlds = Main.getPlugin().getConfig().getStringList("Worlds");
		if(Main.getPlugin().getConfig().getBoolean("Others.AllowItemMove") == false) {
			if (e.getClickedInventory() != null) {
				if(worlds.contains(e.getWhoClicked().getWorld().getName())) {
					e.setCancelled(true);
				}
			}
		}
		if(e.getClickedInventory() != null) {
			if(worlds.contains(e.getWhoClicked().getWorld().getName())) {
				if (Main.getPlugin().getConfig().getBoolean("MagicClock.AllowMove") == false) {
					if (e.getSlot() == Main.getPlugin().getConfig().getInt("MagicClock.Slot")) {
						e.setCancelled(true);
					}
				}
				if(Main.getPlugin().getConfig().getBoolean("Others.HatAllowMove") == false) {
					if(e.getSlotType().equals(SlotType.ARMOR) && e.getRawSlot() == 5) {
						e.setCancelled(true);
						Player player = (Player) e.getWhoClicked();
						player.closeInventory();
					}
				}
			}
		}
	}
	
}
