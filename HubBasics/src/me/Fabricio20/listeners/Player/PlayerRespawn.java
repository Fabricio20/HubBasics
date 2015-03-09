package me.Fabricio20.listeners.Player;

import me.Fabricio20.Main;
import me.Fabricio20.methods.JoinItems;
import me.Fabricio20.methods.ModuleManager;

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
	}
	
}
