package me.Fabricio20.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.Fabricio20.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MakeItemsConfig {
	
	private static JavaPlugin plugin;

	public MakeItemsConfig(JavaPlugin plugin) {
		MakeItemsConfig.plugin = plugin;
	}
	
	/* ----------------------------------------------------------------------------------------- */
	
	public static void make1() {
		getCustomConfig().set("Item1.Enabled", true);
		getCustomConfig().set("Item1.Skull", false);
		getCustomConfig().set("Item1.Material", "PAPER");
		getCustomConfig().set("Item1.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item1.Lore", NewLore);
		getCustomConfig().set("Item1.Slot", 0);
		getCustomConfig().set("Item1.Command", "/me I just installed HubBasics!");
	}
	
	public static void make2() {
		getCustomConfig().set("Item2.Enabled", false);
		getCustomConfig().set("Item2.Skull", false);
		getCustomConfig().set("Item2.Material", "PAPER");
		getCustomConfig().set("Item2.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item2.Lore", NewLore);
		getCustomConfig().set("Item2.Slot", 1);
		getCustomConfig().set("Item2.Command", "/me I just installed HubBasics!");
	}
	
	public static void make3() {
		getCustomConfig().set("Item3.Enabled", false);
		getCustomConfig().set("Item3.Skull", false);
		getCustomConfig().set("Item3.Material", "PAPER");
		getCustomConfig().set("Item3.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item3.Lore", NewLore);
		getCustomConfig().set("Item3.Slot", 1);
		getCustomConfig().set("Item3.Command", "/me I just installed HubBasics!");
	}
	
	public static void make4() {
		getCustomConfig().set("Item4.Enabled", false);
		getCustomConfig().set("Item4.Skull", false);
		getCustomConfig().set("Item4.Material", "PAPER");
		getCustomConfig().set("Item4.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item4.Lore", NewLore);
		getCustomConfig().set("Item4.Slot", 1);
		getCustomConfig().set("Item4.Command", "/me I just installed HubBasics!");
	}
	
	public static void make5() {
		getCustomConfig().set("Item5.Enabled", false);
		getCustomConfig().set("Item5.Skull", false);
		getCustomConfig().set("Item5.Material", "PAPER");
		getCustomConfig().set("Item5.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item5.Lore", NewLore);
		getCustomConfig().set("Item5.Slot", 1);
		getCustomConfig().set("Item5.Command", "/me I just installed HubBasics!");
	}
	
	public static void make6() {
		getCustomConfig().set("Item6.Enabled", false);
		getCustomConfig().set("Item6.Skull", false);
		getCustomConfig().set("Item6.Material", "PAPER");
		getCustomConfig().set("Item6.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item6.Lore", NewLore);
		getCustomConfig().set("Item6.Slot", 1);
		getCustomConfig().set("Item6.Command", "/me I just installed HubBasics!");
	}
	
	public static void make7() {
		getCustomConfig().set("Item7.Enabled", false);
		getCustomConfig().set("Item7.Skull", false);
		getCustomConfig().set("Item7.Material", "PAPER");
		getCustomConfig().set("Item7.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item7.Lore", NewLore);
		getCustomConfig().set("Item7.Slot", 1);
		getCustomConfig().set("Item7.Command", "/me I just installed HubBasics!");
	}
	
	public static void make8() {
		getCustomConfig().set("Item8.Enabled", false);
		getCustomConfig().set("Item8.Skull", false);
		getCustomConfig().set("Item8.Material", "PAPER");
		getCustomConfig().set("Item8.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item8.Lore", NewLore);
		getCustomConfig().set("Item8.Slot", 1);
		getCustomConfig().set("Item8.Command", "/me I just installed HubBasics!");
	}
	
	public static void make9() {
		getCustomConfig().set("Item9.Enabled", false);
		getCustomConfig().set("Item9.Skull", false);
		getCustomConfig().set("Item9.Material", "PAPER");
		getCustomConfig().set("Item9.Name", "&bThis is a paper!");
		List<String> NewLore = new ArrayList<String>();
		NewLore.add("&cThis is the &bPAPER's &cLore!");
		getCustomConfig().set("Item9.Lore", NewLore);
		getCustomConfig().set("Item9.Slot", 1);
		getCustomConfig().set("Item9.Command", "/me I just installed HubBasics!");
	}
	
	/* ----------------------------------------------------------------------------------------- */
	
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
