package me.Fabricio20.listeners.Player;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.API.BookAPI;
import me.Fabricio20.methods.Items;
import me.Fabricio20.runnables.BossAnnouncer;
import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {	
	 
	 @EventHandler(priority = EventPriority.HIGH)
	 public void Join(PlayerJoinEvent e) {
		 if(Main.theClass.config.getBoolean("JoinEvents.DisableMessage") == true) {
			 e.setJoinMessage(null);
		 } else {
			 e.setJoinMessage(Main.theClass.config.getString("JoinEvents.Message").replace("&", "§").replace("%p", e.getPlayer().getName()));
		 }
		 if(Main.theClass.config.getBoolean("JoinEvents.SilentOpJoin") == true) {
			 if(e.getPlayer().isOp()) {
				 e.setJoinMessage(null);
			 }
		 }
		if(Main.theClass.config.getBoolean("JoinEvents.HubAtLogin") == true) {
			if(Main.theClass.Storage.contains("Hub.World")) {
				Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
				if(Bukkit.getWorld(Main.theClass.Storage.getString("Hub.World")) != null) {
					loc.setWorld(Bukkit.getWorld(Main.theClass.Storage.getString("Hub.World")));
					loc.setX(Main.theClass.Storage.getDouble("Hub.X"));
					loc.setY(Main.theClass.Storage.getDouble("Hub.Y"));
					loc.setZ(Main.theClass.Storage.getDouble("Hub.Z"));
					loc.setYaw(Main.theClass.Storage.getInt("Hub.Yaw"));
					loc.setPitch(Main.theClass.Storage.getInt("Hub.Pitch"));
					e.getPlayer().teleport(loc);
				}
			}
		}
		if(Main.theClass.config.getBoolean("BookSystem.Enabled") == true) {
			List<String> worlds = Main.theClass.config.getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				if(!e.getPlayer().getInventory().contains(Items.Book(e.getPlayer().getName()))) {
					if(Main.theClass.config.getBoolean("BookSystem.FirstJoinOnly") == true) {
						if(BookAPI.shouldGive(e.getPlayer().getName()) == true) {
							if(Main.theClass.config.getBoolean("BookSystem.Give") == false) {
								e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("BookSystem.Slot"), Items.Book(e.getPlayer().getName()));
								BookAPI.give(e.getPlayer().getName());
							} else {
								e.getPlayer().getInventory().addItem(Items.Book(e.getPlayer().getName()));
								BookAPI.give(e.getPlayer().getName());
							}
						}
					} else {
						if(Main.theClass.config.getBoolean("BookSystem.Give") == false) {
							e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("BookSystem.Slot"), Items.Book(e.getPlayer().getName()));
						} else {
							e.getPlayer().getInventory().addItem(Items.Book(e.getPlayer().getName()));
						}
					}
				}
			}
		}
		if(Main.theClass.config.getBoolean("BossAnnouncer.Enabled") == true && Main.theClass.config.getBoolean("JoinEvents.BossBarOnJoin") == true) {
			List<String> Announces = new ArrayList<String>();
			Announces = Main.theClass.config.getStringList("BossAnnouncer.Msgs");
			BarAPI.setMessage(e.getPlayer(), Announces.get(BossAnnouncer.Stamp));
		}
		if(Main.theClass.config.getBoolean("MagicClock.Enabled") == true) {
			List<String> worlds = Main.theClass.config.getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				if(!e.getPlayer().getInventory().contains(Items.MagicClock(e.getPlayer().getName()))) {
					e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("MagicClock.Slot"), Items.MagicClock(e.getPlayer().getName()));
				}
			}
		}
	}
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
}