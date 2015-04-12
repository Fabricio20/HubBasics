package me.Fabricio20.Bukkit.Listeners.Player;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.API.ItemsAPI;
import me.Fabricio20.Bukkit.Methods.JoinItems;
import me.Fabricio20.Bukkit.Methods.ModuleManager;
import me.Fabricio20.Bukkit.Methods.Managers.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerRespawn implements Listener {
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player player = e.getPlayer();
		if(ModuleManager.theClass.isInWorld(player)) {
			if(Main.theClass.config.getBoolean("Others.JoinItems") == true) {
				if(Main.theClass.config.getBoolean("Others.JoinItemsDeath")) {
					for(ItemStack stack: JoinItems.theClass.getItems(player).keySet()) {
						if(JoinItems.theClass.getPermission(stack, player) != null) {
							if(player.hasPermission(JoinItems.theClass.getPermission(stack, player))) {
								if(!player.getInventory().contains(stack)) {
									if(JoinItems.theClass.getItems(player).get(stack) > 0) {
										int slot = JoinItems.theClass.getItems(player).get(stack) -1;
										player.getInventory().setItem(slot, stack);
									} else {
										player.getInventory().addItem(stack);
									}
								}
							}
						} else {
							if(!player.getInventory().contains(stack)) {
								if(JoinItems.theClass.getItems(player).get(stack) > 0) {
									int slot = JoinItems.theClass.getItems(player).get(stack) -1;
									player.getInventory().setItem(slot, stack);
								} else {
									player.getInventory().addItem(stack);
								}
							}
						}
					}
				}
			}
			if(Main.theClass.config.getBoolean("MagicClock.Enabled") == true) {
				if(Main.theClass.config.getBoolean("MagicClock.OnDeath")) {
					if(ModuleManager.theClass.isInWorld(e.getPlayer())) {
						if(!e.getPlayer().getInventory().contains(ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(e.getPlayer().getName())))) {
							e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("MagicClock.Slot"), ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(e.getPlayer().getName())));
						}
					}
				}
			}
		}
	}
	
}
