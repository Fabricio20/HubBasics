package me.Fabricio20.listeners.Item;

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
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if(e.getClickedInventory() != null) {
			if(worlds.contains(e.getWhoClicked().getWorld().getName())) {
				if(Main.theClass.config.getBoolean("Others.AllowItemMove") == false) {
					e.setCancelled(true);
				}
				if(Main.theClass.config.getBoolean("MagicClock.Enabled") == true) {
					if(Main.theClass.config.getBoolean("MagicClock.AllowMove") == false) {
						if(e.getSlot() == Main.theClass.config.getInt("MagicClock.Slot")) {
							e.setCancelled(true);
						}
					}
				}
				if(Main.theClass.config.getBoolean("HatSystem.HatAllowMove") == false) {
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
