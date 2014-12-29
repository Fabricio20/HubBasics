package me.Fabricio20.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.Fabricio20.Main;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Items {
	
	private static JavaPlugin plugin;

	public Items(JavaPlugin plugin) {
		Items.plugin = plugin;
	}
	
	private static String Item1Name = "";
	private static String Item2Name = "";
	private static String Item3Name = "";
	private static String Item4Name = "";
	private static String Item5Name = "";
	private static String Item6Name = "";
	private static String Item7Name = "";
	private static String Item8Name = "";
	private static String Item9Name = "";
	
	public static ItemStack Item1(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item1.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item1Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item1.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item1.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item1Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item1.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item2(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item2.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item2Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item2.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item2.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item2Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item2.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item3(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item3.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item3Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item3.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item3.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item3Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item3.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item4(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item4.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item4Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item4.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item4.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item4Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item4.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item5(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item5.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item5Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item5.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item5.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item5Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item5.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item6(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item6.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item6Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item6.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item6.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item6Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item6.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item7(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item7.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item7Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item7.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item7.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item7Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item7.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item8(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item8.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item8Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item8.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item8.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item8Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item8.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack Item9(String player) {
		fixItemName(player);
		if(getCustomConfig().getBoolean("Item9.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item9Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item9.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item9.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item9Name);
			List<String> LoreFromConfig = getCustomConfig().getStringList("Item9.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void fixItemName(String playername) {
		if(getCustomConfig().contains("Item1.Name") && getCustomConfig().getString("Item1.Name") != null && getCustomConfig().getString("Item1.Name") != "") {
			Item1Name = getCustomConfig().getString("Item1.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item2.Name") && getCustomConfig().getString("Item2.Name") != null && getCustomConfig().getString("Item2.Name") != "") {
			Item2Name = getCustomConfig().getString("Item2.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item3.Name") && getCustomConfig().getString("Item3.Name") != null && getCustomConfig().getString("Item3.Name") != "") {
			Item3Name = getCustomConfig().getString("Item3.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item4.Name") && getCustomConfig().getString("Item4.Name") != null && getCustomConfig().getString("Item4.Name") != "") {
			Item4Name = getCustomConfig().getString("Item4.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item5.Name") && getCustomConfig().getString("Item5.Name") != null && getCustomConfig().getString("Item5.Name") != "") {
			Item5Name = getCustomConfig().getString("Item5.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item6.Name") && getCustomConfig().getString("Item6.Name") != null && getCustomConfig().getString("Item6.Name") != "") {
			Item6Name = getCustomConfig().getString("Item6.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item7.Name") && getCustomConfig().getString("Item7.Name") != null && getCustomConfig().getString("Item7.Name") != "") {
			Item7Name = getCustomConfig().getString("Item7.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item8.Name") && getCustomConfig().getString("Item8.Name") != null && getCustomConfig().getString("Item8.Name") != "") {
			Item8Name = getCustomConfig().getString("Item8.Name").replace("&", "§").replace("%p", playername);
		}
		if(getCustomConfig().contains("Item9.Name") && getCustomConfig().getString("Item9.Name") != null && getCustomConfig().getString("Item9.Name") != "") {
			Item9Name = getCustomConfig().getString("Item9.Name").replace("&", "§").replace("%p", playername);
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
