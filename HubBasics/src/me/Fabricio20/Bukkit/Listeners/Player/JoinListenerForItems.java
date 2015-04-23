package me.Fabricio20.Bukkit.Listeners.Player;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.API.ItemsAPI;
import me.Fabricio20.Bukkit.Methods.JoinItems;
import me.Fabricio20.Bukkit.Methods.ModuleManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListenerForItems implements Listener {
	
	@EventHandler
	public void PlayerJoinGetItem(PlayerJoinEvent e) {
		 Player player = e.getPlayer();
		 if(ModuleManager.theClass.isInWorld(player)) {
			 if(Main.theClass.config.getBoolean("Others.ClearInventory") == true) {
				 e.getPlayer().getInventory().clear();
			 }
			 if(Main.theClass.config.getBoolean("Others.JoinItems") == true) {
				if(Main.theClass.config.getBoolean("Others.FirstJoinItemsOnly") == false) {
					 
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
					
				} else {
					if(ItemsAPI.shouldGive(e.getPlayer().getName())) {
						
						for(ItemStack stack: JoinItems.theClass.getItems(player).keySet()) {
							if(JoinItems.theClass.getPermission(stack, player) != null){
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
						
						ItemsAPI.give(e.getPlayer().getName());
					}
				}
			}
		 }
	}
}
