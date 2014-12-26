package me.Fabricio20.listeners;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.Fabricio20.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidFallListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static JavaPlugin plugin;

	public VoidFallListener(JavaPlugin plugin) {
		VoidFallListener.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler(priority = EventPriority.HIGH)
	public void Void(EntityDamageEvent event) {
		if(plugin.getConfig().getBoolean("VoidFall.Enabled") == true) {
		if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
			Entity ent = event.getEntity();
			if (ent == null) {
				return;
			}
			if (!(ent instanceof Player)) {
				return;
			}
			if ((ent instanceof Player)) {
				Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
				loc.setWorld(Bukkit.getWorld(getCustomConfig().getString("Hub.World")));
				loc.setX(getCustomConfig().getInt("Hub.X"));
				loc.setY(getCustomConfig().getInt("Hub.Y"));
				loc.setZ(getCustomConfig().getInt("Hub.Z"));
				loc.setYaw(getCustomConfig().getInt("Hub.Yaw"));
				loc.setPitch(getCustomConfig().getInt("Hub.Pitch"));
				((Player) ent).teleport(loc);
				((Player) ent).setFallDistance(0.0F);
				((Player) ent).sendMessage(plugin.getConfig().getString("VoidFall.Message").replace("&", "§").replace("%p", ((Player) ent).getName()));
				event.setCancelled(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void reloadCustomConfig() {
	    if (Main.Storage == null) {
	    Main.Storage = new File(plugin.getDataFolder(), "Storage.yml");
	    }
	    Main.StorageConfig = YamlConfiguration.loadConfiguration(Main.Storage);
	}

	public static FileConfiguration getCustomConfig() {
	  if (Main.StorageConfig == null) {
	        reloadCustomConfig();
	  }
	  return Main.StorageConfig;
	}

	public static void saveCustomConfig() {
	  if (Main.StorageConfig == null || Main.Storage == null) {
	      return;
	  }
	  try {
	      getCustomConfig().save(Main.Storage);
	  } catch (IOException ex) {
	   plugin.getLogger().log(Level.SEVERE, "Could not save config to " + Main.Storage, ex);
	  }
	}
	
}
