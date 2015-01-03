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
	    if(Main.Item == null) {
	    	Main.Item = new File(Main.plugin.getDataFolder(), "Items.yml");
	    }
	    Main.ItemConfig = YamlConfiguration.loadConfiguration(Main.Item);
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
			Main.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + Main.Item, ex);
		}
	}
	
	/* ------------- ACESS TO STORAGE CONFIG ------------- */
	
	public static void reloadStorageConfig() {
		if(Main.Storage == null) {
			Main.Storage = new File(Main.plugin.getDataFolder(), "Storage.yml");
		}
		Main.StorageConfig = YamlConfiguration.loadConfiguration(Main.Storage);
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
			Main.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + Main.Storage, ex);
		}
	}
	
}
