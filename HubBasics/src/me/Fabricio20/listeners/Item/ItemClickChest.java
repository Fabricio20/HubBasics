package me.Fabricio20.listeners.Item;

import java.util.Set;

import me.Fabricio20.Main;
import me.Fabricio20.API.LanguageAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemClickChest implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getInventory() != null) {
			if(e.getClickedInventory().getName().equals(LanguageAPI.theClass.QWarpChestName(player.getName(), player.getWorld().getName()))) {
				if(e.getCurrentItem().hasItemMeta()) {
					Set<String> s = Main.theClass.QuickWarpChest.getConfigurationSection("Items").getKeys(false);
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if(meta.hasDisplayName()) {
						for(String st : s) {
							if(Main.theClass.QuickWarpChest.getString("Items." + st + ".Name").equals(meta.getDisplayName().replace("§", "&"))) {
								if(Main.theClass.QuickWarpChest.getBoolean("Items." + st + ".UsePerm")) {
									if(player.hasPermission(Main.theClass.QuickWarpChest.getString("Items." + st + ".Permission"))) {
										if(Main.theClass.QuickWarpChest.getBoolean("Items." + st + ".Console")) {
											Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").replace("/", ""));
										} else {
											player.chat(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command"));
										}
									} else {
										player.sendMessage(LanguageAPI.theClass.ServerSelectorNoPerm(player.getName(), player.getWorld().getName()));
									}
								} else {
									if(Main.theClass.QuickWarpChest.getBoolean("Items." + st + ".Console")) {
										Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").replace("/", ""));
									} else {
										player.chat(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command"));
									}
								}
							}
						}
					}
				}
				e.setCancelled(true);
			}
		}
	}
	
}
