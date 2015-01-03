package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

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
import org.bukkit.plugin.java.JavaPlugin;

public class JoinListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static JavaPlugin plugin;
	 
	 public JoinListener(JavaPlugin plugin) {
	     JoinListener.plugin = plugin;
	 }
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	 
	 @EventHandler(priority = EventPriority.HIGH)
	 public void Join(PlayerJoinEvent e) {
		 if(plugin.getConfig().getBoolean("JoinEvents.DisableMessage") == true) {
			 e.setJoinMessage(null);
		 } else {
			 e.setJoinMessage(plugin.getConfig().getString("JoinEvents.Message").replace("&", "§").replace("%p", e.getPlayer().getName()));
		 }
		 if(plugin.getConfig().getBoolean("JoinEvents.SilentOpJoin") == true) {
			 if(e.getPlayer().isOp()) {
				 e.setJoinMessage(null);
			 }
		 }
		if(plugin.getConfig().getBoolean("JoinEvents.HubAtLogin") == true) {
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
		if(plugin.getConfig().getBoolean("BookSystem.Enabled") == true) {
			List<String> worlds = plugin.getConfig().getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.getPlayer().getInventory().setItem(plugin.getConfig().getInt("BookSystem.Slot"), Book.build());
			}
		}
		if(plugin.getConfig().getBoolean("BossAnnouncer.Enabled") == true && plugin.getConfig().getBoolean("JoinEvents.BossBarOnJoin") == true) {
			List<String> Announces = new ArrayList<String>();
			Announces = plugin.getConfig().getStringList("BossAnnouncer.Msgs");
			BarAPI.setMessage(e.getPlayer(), Announces.get(BossAnnouncer.Stamp));
		}
		if(plugin.getConfig().getBoolean("MagicClock.Enabled") == true) {
			List<String> worlds = plugin.getConfig().getStringList("Worlds");
			if(worlds.contains(e.getPlayer().getWorld().getName())) {
				e.getPlayer().getInventory().setItem(plugin.getConfig().getInt("MagicClock.Slot"), Items.MagicClock(e.getPlayer().getName()));
			}
		}
		if(plugin.getConfig().getBoolean("TitleSystem.Enabled") == true) {
			String title = " ";
			String subtitle = " ";
			if(plugin.getConfig().getString("TitleSystem.Title") != "") {
				title = plugin.getConfig().getString("TitleSystem.Title").replace("&", "§").replace("%p", e.getPlayer().getName());
			}
			subtitle = plugin.getConfig().getString("TitleSystem.Subtitle").replace("&", "§").replace("%p", e.getPlayer().getName());
			ActionAPI.sendTitle(e.getPlayer(), title);
			ActionAPI.sendSubtitle(e.getPlayer(), subtitle);
		}
	}
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
}