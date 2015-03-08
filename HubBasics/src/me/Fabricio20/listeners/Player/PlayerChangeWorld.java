package me.Fabricio20.listeners.Player;

import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.API.ItemsAPI;
import me.Fabricio20.methods.JoinItems;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerChangeWorld implements Listener {

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		if(Main.theClass.config.getBoolean("Others.ClearInventoryOnEveryWorld")) {
			e.getPlayer().getInventory().clear();
		}
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if(worlds.contains(e.getPlayer().getWorld())) {
			Player player = e.getPlayer();
			if(Main.theClass.config.getBoolean("Others.JoinItemsChangeWorld") == true) {
				for(ItemStack stack: JoinItems.theClass.getItems(player).keySet()) {
					if(JoinItems.theClass.getPermission(stack, player) != null) {
						if(player.hasPermission(JoinItems.theClass.getPermission(stack, player))) {
							if(JoinItems.theClass.getItems(player).get(stack) > 0) {
								int slot = JoinItems.theClass.getItems(player).get(stack) -1;
								player.getInventory().setItem(slot, stack);
							} else {
								player.getInventory().addItem(stack);
							}
						}
					} else {
						if(JoinItems.theClass.getItems(player).get(stack) > 0) {
							int slot = JoinItems.theClass.getItems(player).get(stack) -1;
							player.getInventory().setItem(slot, stack);
						} else {
							player.getInventory().addItem(stack);
						}
					}
				}
			}
			if(Main.theClass.config.getBoolean("MagicClock.GiveOnWorldChange") == true) {
				if(!e.getPlayer().getInventory().contains(ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(e.getPlayer().getName())))) {
					e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("MagicClock.Slot"), ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(e.getPlayer().getName())));
				}
			}
		}
		
	}

}
