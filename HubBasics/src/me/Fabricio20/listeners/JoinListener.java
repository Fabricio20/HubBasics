package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.API.ActionAPI;
import me.Fabricio20.methods.Book;
import me.Fabricio20.methods.CustomConfigs;
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
		 if(Main.getPlugin().getConfig().getBoolean("JoinEvents.DisableMessage") == true) {
			 e.setJoinMessage(null);
		 } else {
			 e.setJoinMessage(Main.getPlugin().getConfig().getString("JoinEvents.Message").replace("&", "§").replace("%p", e.getPlayer().getName()));
		 }
		 if(Main.getPlugin().getConfig().getBoolean("JoinEvents.SilentOpJoin") == true) {
			 if(e.getPlayer().isOp()) {
				 e.setJoinMessage(null);
			 }
		 }
		if(Main.getPlugin().getConfig().getBoolean("JoinEvents.HubAtLogin") == true) {
			if(CustomConfigs.getStorageConfig().contains("Hub.World")) {
				Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
				if(Bukkit.getWorld(CustomConfigs.getStorageConfig().getString("Hub.World")) != null) {
					loc.setWorld(Bukkit.getWorld(CustomConfigs.getStorageConfig().getString("Hub.World")));
					loc.setX(CustomConfigs.getStorageConfig().getInt("Hub.X"));
					loc.setY(CustomConfigs.getStorageConfig().getInt("Hub.Y"));
					loc.setZ(CustomConfigs.getStorageConfig().getInt("Hub.Z"));
					loc.setYaw(CustomConfigs.getStorageConfig().getInt("Hub.Yaw"));
					loc.setPitch(CustomConfigs.getStorageConfig().getInt("Hub.Pitch"));
					e.getPlayer().teleport(loc);
				}
			}
		}
		if(Main.getPlugin().getConfig().getBoolean("BookSystem.Enabled") == true) {
			List<String> worlds = Main.getPlugin().getConfig().getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.getPlayer().getInventory().setItem(Main.getPlugin().getConfig().getInt("BookSystem.Slot"), Book.build());
			}
		}
		if(Main.getPlugin().getConfig().getBoolean("BossAnnouncer.Enabled") == true && Main.getPlugin().getConfig().getBoolean("JoinEvents.BossBarOnJoin") == true) {
			List<String> Announces = new ArrayList<String>();
			Announces = Main.getPlugin().getConfig().getStringList("BossAnnouncer.Msgs");
			BarAPI.setMessage(e.getPlayer(), Announces.get(BossAnnouncer.Stamp));
		}
		if(Main.getPlugin().getConfig().getBoolean("MagicClock.Enabled") == true) {
			List<String> worlds = Main.getPlugin().getConfig().getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				if(!e.getPlayer().getInventory().contains(Items.MagicClock(e.getPlayer().getName()))) {
					e.getPlayer().getInventory().setItem(Main.getPlugin().getConfig().getInt("MagicClock.Slot"), Items.MagicClock(e.getPlayer().getName()));
				}
			}
		}
		if(Main.getPlugin().getConfig().getBoolean("TitleSystem.Enabled") == true) {
			String title = " ";
			String subtitle = " ";
			if(Main.getPlugin().getConfig().getString("TitleSystem.Title") != "") {
				title = Main.getPlugin().getConfig().getString("TitleSystem.Title").replace("&", "§").replace("%p", e.getPlayer().getName());
			}
			subtitle = Main.getPlugin().getConfig().getString("TitleSystem.Subtitle").replace("&", "§").replace("%p", e.getPlayer().getName());
			ActionAPI.sendTitle(e.getPlayer(), title);
			ActionAPI.sendSubtitle(e.getPlayer(), subtitle);
		}
	}
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
}