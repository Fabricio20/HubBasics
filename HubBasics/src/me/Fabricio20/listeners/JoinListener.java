package me.Fabricio20.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.Fabricio20.Main;
import me.Fabricio20.runnables.BossAnnouncer;
import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
			if(getCustomConfig().contains("Hub.World")) {
				Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
				if(Bukkit.getWorld(getCustomConfig().getString("Hub.World")) != null) {
					loc.setWorld(Bukkit.getWorld(getCustomConfig().getString("Hub.World")));
					loc.setX(getCustomConfig().getInt("Hub.X"));
					loc.setY(getCustomConfig().getInt("Hub.Y"));
					loc.setZ(getCustomConfig().getInt("Hub.Z"));
					loc.setYaw(getCustomConfig().getInt("Hub.Yaw"));
					loc.setPitch(getCustomConfig().getInt("Hub.Pitch"));
					e.getPlayer().teleport(loc);
				}
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
				ItemStack MagicClock = new ItemStack(Material.getMaterial(plugin.getConfig().getString("MagicClock.Material")));
				ItemMeta Meta = MagicClock.getItemMeta();
				Meta.setDisplayName(plugin.getConfig().getString("MagicClock.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
				List<String> Lore = plugin.getConfig().getStringList("MagicClock.Lore");
				ArrayList<String> LoreNew = new ArrayList<String>();
				for(String string : Lore) {
					LoreNew.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
				}
				Meta.setLore(LoreNew);
				MagicClock.setItemMeta(Meta);
				e.getPlayer().getInventory().setItem(plugin.getConfig().getInt("MagicClock.Slot"), MagicClock);
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