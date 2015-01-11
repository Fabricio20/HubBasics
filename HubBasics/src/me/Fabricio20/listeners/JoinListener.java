package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;
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
		if(Main.theClass.config.getBoolean("BookSystem.Enabled") == true) {
			List<String> worlds = Main.theClass.config.getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("BookSystem.Slot"), Items.Book17(e.getPlayer().getName()));
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