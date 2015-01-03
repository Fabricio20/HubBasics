package me.Fabricio20.listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import me.Fabricio20.Main;
import me.Fabricio20.methods.Items;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinListenerForItems implements Listener {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static JavaPlugin plugin;

	public JoinListenerForItems(JavaPlugin plugin) {
		JoinListenerForItems.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void PlayerJoinGetItem(PlayerJoinEvent e) {
		 List<String> worlds = plugin.getConfig().getStringList("Worlds");
		 Player player = e.getPlayer();
		 if(worlds.contains(e.getPlayer().getWorld().getName())) {
			 if(plugin.getConfig().getBoolean("Others.ClearInventory") == true) {
				 e.getPlayer().getInventory().clear();
			 }
			 if(plugin.getConfig().getBoolean("Others.JoinItems") == true) {
    ////////////////////////////////////////////////////////////////////////////////////////- Start Of 1
				if(getCustomConfig().getBoolean("Item1.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item1(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item1.Slot"), Items.Item1(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 1 & Start Of 2
				if(getCustomConfig().getBoolean("Item2.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item2(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item2.Slot"), Items.Item2(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 2 & Start Of 3
				if(getCustomConfig().getBoolean("Item3.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item3(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item3.Slot"), Items.Item3(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 3 & Start Of 4
				if(getCustomConfig().getBoolean("Item4.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item4(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item4.Slot"), Items.Item4(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 4 & Start Of 5
				if(getCustomConfig().getBoolean("Item5.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item5(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item5.Slot"), Items.Item5(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 5 & Start Of 6
				if(getCustomConfig().getBoolean("Item6.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item6(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item6.Slot"), Items.Item6(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 6 & Start Of 7
				if(getCustomConfig().getBoolean("Item7.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item7(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item7.Slot"), Items.Item7(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 7 & Start Of 8
				if(getCustomConfig().getBoolean("Item8.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item8(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item8.Slot"), Items.Item8(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 8 & Start Of 9
				if(getCustomConfig().getBoolean("Item9.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item9(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item9.Slot"), Items.Item9(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 9
			}
		 }
	}
	
	public static void reloadCustomConfig() {
	    if (Main.Storage2 == null) {
	    Main.Storage2 = new File(plugin.getDataFolder(), "Items.yml");
	    }
	    Main.StorageConfig2 = YamlConfiguration.loadConfiguration(Main.Storage2);
	}

	public static FileConfiguration getCustomConfig() {
	  if (Main.StorageConfig2 == null) {
	        reloadCustomConfig();
	  }
	  return Main.StorageConfig2;
	}

	public static void saveCustomConfig() {
	  if (Main.StorageConfig2 == null || Main.Storage2 == null) {
	      return;
	  }
	  try {
	      getCustomConfig().save(Main.Storage2);
	  } catch (IOException ex) {
	   plugin.getLogger().log(Level.SEVERE, "Could not save config to " + Main.Storage2, ex);
	  }
	}
	
}
