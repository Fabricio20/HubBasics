package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.methods.CustomConfigs;
import me.Fabricio20.methods.Items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListenerForItems implements Listener {
	
	@EventHandler
	public void PlayerJoinGetItem(PlayerJoinEvent e) {
		 List<String> worlds = Main.theClass.config.getStringList("Worlds");
		 Player player = e.getPlayer();
		 String name = player.getName();
		 if(worlds.contains(e.getPlayer().getWorld().getName())) {
			 if(Main.theClass.config.getBoolean("Others.ClearInventory") == true) {
				 e.getPlayer().getInventory().clear();
			 }
			 if(Main.theClass.config.getBoolean("Others.JoinItems") == true) {
				 
				if(CustomConfigs.getItemConfig().getBoolean("Item1.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item1(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item1.Slot"), Items.Item1(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item1(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item2.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item2(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item2.Slot"), Items.Item2(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item2(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item3.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item3(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item3.Slot"), Items.Item3(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item3(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item4.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item4(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item4.Slot"), Items.Item4(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item4(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item5.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item5(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item5.Slot"), Items.Item5(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item5(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item6.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item6(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item6.Slot"), Items.Item6(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item6(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item7.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item7(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item7.Slot"), Items.Item7(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item7(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item8.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item8(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item8.Slot"), Items.Item8(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item8(name));
						}
					}
				}
				
				if(CustomConfigs.getItemConfig().getBoolean("Item9.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item9(name))) {
						if(Main.theClass.config.getBoolean("Others.GiveItems") == false) {
							e.getPlayer().getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item9.Slot"), Items.Item9(name));
						} else {
							e.getPlayer().getInventory().addItem(Items.Item9(name));
						}
					}
				}
				
			}
		 }
	}	
}
