package me.Fabricio20.methods;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.Fabricio20.Main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfigs {
	
	/**
	 * 
	 * @author Fabricio20
	 * @since 2014
	 * 
	 * Config Class Wich Gives Acess To All Custom Configs
	 * 
	 */
	
	/* ------------- ACESS TO ITEM CONFIG ------------- */
	
	public static void reloadItemConfig() {
	    if(Main.Item == null) {
	    	Main.Item = new File(Main.theClass.getPlugin().getDataFolder(), "Items.yml");
	    }
	    Main.ItemConfig = YamlConfiguration.loadConfiguration(Main.Item);
	    if(!Main.ItemConfig.contains("Item1.Skull")) {
        	MakeItemsConfig.make1();
        }
        if(!Main.ItemConfig.contains("Item2.Skull")) {
        	MakeItemsConfig.make2();
        }
        if(!Main.ItemConfig.contains("Item3.Skull")) {
        	MakeItemsConfig.make3();
        }
        if(!Main.ItemConfig.contains("Item4.Skull")) {
        	MakeItemsConfig.make4();
        }
        if(!Main.ItemConfig.contains("Item5.Skull")) {
        	MakeItemsConfig.make5();
        }
        if(!Main.ItemConfig.contains("Item6.Skull")) {
        	MakeItemsConfig.make6();
        }
        if(!Main.ItemConfig.contains("Item7.Skull")) {
        	MakeItemsConfig.make7();
        }
        if(!Main.ItemConfig.contains("Item8.Skull")) {
        	MakeItemsConfig.make8();
        }
        if(!Main.ItemConfig.contains("Item9.Skull")) {
        	MakeItemsConfig.make9();
        }
	}

	public static FileConfiguration getItemConfig() {
		if(Main.ItemConfig == null) {
			reloadItemConfig();
		}
	  return Main.ItemConfig;
	}

	public static void saveItemConfig() {
		if(Main.ItemConfig == null || Main.Item == null) {
			return;
		}
		try {
			getItemConfig().save(Main.Item);
		} catch (IOException ex) {
			Main.theClass.getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + Main.Item, ex);
		}
	}
	
	/* ------------- ACESS TO STORAGE CONFIG ------------- */
	
	public static void reloadStorageConfig() {
		if(Main.Storage == null) {
			Main.Storage = new File(Main.theClass.getPlugin().getDataFolder(), "Storage.yml");
		}
		Main.StorageConfig = YamlConfiguration.loadConfiguration(Main.Storage);
		if(!Main.StorageConfig.contains("Hub.World")) {
			Main.StorageConfig.set("Hub.World", Bukkit.getWorlds().get(0).getSpawnLocation().getWorld().getName());
			Main.StorageConfig.set("Hub.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
			Main.StorageConfig.set("Hub.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
			Main.StorageConfig.set("Hub.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
			Main.StorageConfig.set("Hub.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
			Main.StorageConfig.set("Hub.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
			saveStorageConfig();
		}
	}

	public static FileConfiguration getStorageConfig() {
		if(Main.StorageConfig == null) {
			reloadStorageConfig();
		}
	  return Main.StorageConfig;
	}

	public static void saveStorageConfig() {
		if(Main.StorageConfig == null || Main.Storage == null) {
			return;
		}
		try {
			getStorageConfig().save(Main.Storage);
		} catch (IOException ex) {
			Main.theClass.getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + Main.Storage, ex);
		}
	}
	
}
