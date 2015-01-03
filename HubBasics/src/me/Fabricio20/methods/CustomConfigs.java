package me.Fabricio20.methods;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.Fabricio20.Main;

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
	    if(Main.theClass.Item == null) {
	    	Main.theClass.Item = new File(Main.theClass.getPlugin().getDataFolder(), "Items.yml");
	    }
	    Main.theClass.ItemConfig = YamlConfiguration.loadConfiguration(Main.theClass.Item);
	}

	public static FileConfiguration getItemConfig() {
		if(Main.theClass.ItemConfig == null) {
			reloadItemConfig();
		}
	  return Main.theClass.ItemConfig;
	}

	public static void saveItemConfig() {
		if(Main.theClass.ItemConfig == null || Main.theClass.Item == null) {
			return;
		}
		try {
			getItemConfig().save(Main.theClass.Item);
		} catch (IOException ex) {
			Main.theClass.getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + Main.theClass.Item, ex);
		}
	}
	
	/* ------------- ACESS TO STORAGE CONFIG ------------- */
	
	public static void reloadStorageConfig() {
		if(Main.theClass.Storage == null) {
			Main.theClass.Storage = new File(Main.theClass.getPlugin().getDataFolder(), "Storage.yml");
		}
		Main.theClass.StorageConfig = YamlConfiguration.loadConfiguration(Main.theClass.Storage);
	}

	public static FileConfiguration getStorageConfig() {
		if(Main.theClass.StorageConfig == null) {
			reloadStorageConfig();
		}
	  return Main.theClass.StorageConfig;
	}

	public static void saveStorageConfig() {
		if(Main.theClass.StorageConfig == null || Main.theClass.Storage == null) {
			return;
		}
		try {
			getStorageConfig().save(Main.theClass.Storage);
		} catch (IOException ex) {
			Main.theClass.getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + Main.theClass.Storage, ex);
		}
	}
	
}
