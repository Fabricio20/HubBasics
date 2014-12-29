package me.Fabricio20.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.Fabricio20.Main;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
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
		 if(worlds.contains(e.getPlayer().getWorld().getName())) {
			 if(plugin.getConfig().getBoolean("Others.JoinItems") == true) {
				 if(plugin.getConfig().getBoolean("Others.ClearInventory") == true) {
					 e.getPlayer().getInventory().clear();
				 }
				if(getCustomConfig().getBoolean("Item1.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item1.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item1.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item1.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item1.Slot"), Item1);
					} else {
						ItemStack Item1 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item1.Material")));
						ItemMeta Meta = Item1.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item1.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item1.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item1.Slot"), Item1);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 1 & Start Of 2
				if(getCustomConfig().getBoolean("Item2.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item2.Skull") == true) { 
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item2.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item2.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item2.Slot"), Item1);
					} else {
						ItemStack Item2 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item2.Material")));
						ItemMeta Meta = Item2.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item2.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item2.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item2.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item2.Slot"), Item2);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 2 & Start Of 3
				if(getCustomConfig().getBoolean("Item3.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item3.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item3.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item3.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item3.Slot"), Item1);
					} else {
						ItemStack Item3 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item3.Material")));
						ItemMeta Meta = Item3.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item3.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item3.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item3.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item3.Slot"), Item3);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 3 & Start Of 4
				if(getCustomConfig().getBoolean("Item4.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item4.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item4.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item4.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item4.Slot"), Item1);
					} else {
						ItemStack Item4 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item4.Material")));
						ItemMeta Meta = Item4.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item4.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item4.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item4.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item4.Slot"), Item4);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 4 & Start Of 5
				if(getCustomConfig().getBoolean("Item5.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item5.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item5.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item5.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item5.Slot"), Item1);
					} else {
						ItemStack Item5 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item5.Material")));
						ItemMeta Meta = Item5.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item5.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item5.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item5.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item5.Slot"), Item5);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 5 & Start Of 6
				if(getCustomConfig().getBoolean("Item6.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item6.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item6.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item6.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item6.Slot"), Item1);
					} else {
						ItemStack Item6 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item6.Material")));
						ItemMeta Meta = Item6.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item6.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item6.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item6.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item6.Slot"), Item6);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 6 & Start Of 7
				if(getCustomConfig().getBoolean("Item7.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item7.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item7.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item7.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item7.Slot"), Item1);
					} else {
						ItemStack Item7 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item7.Material")));
						ItemMeta Meta = Item7.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item7.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item7.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item7.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item7.Slot"), Item7);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 7 & Start Of 8
				if(getCustomConfig().getBoolean("Item8.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item8.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item8.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item8.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item8.Slot"), Item1);
					} else {
						ItemStack Item8 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item8.Material")));
						ItemMeta Meta = Item8.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item8.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item8.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item8.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item8.Slot"), Item8);
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 8 & Start Of 9
				if(getCustomConfig().getBoolean("Item9.Enabled") == true) {
					if(getCustomConfig().getBoolean("Item9.Skull") == true) {
						ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
						Meta.setOwner(e.getPlayer().getName());
						Meta.setDisplayName(getCustomConfig().getString("Item9.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item9.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item1.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item9.Slot"), Item1);
					} else {
						ItemStack Item9 = new ItemStack(Material.getMaterial(getCustomConfig().getString("Item9.Material")));
						ItemMeta Meta = Item9.getItemMeta();
						Meta.setDisplayName(getCustomConfig().getString("Item9.Name").replace("&", "§").replace("%p", e.getPlayer().getName()));
						List<String> LoreFromConfig = getCustomConfig().getStringList("Item9.Lore");
						List<String> NewLore = new ArrayList<String>();
						for(String string : LoreFromConfig) {
							NewLore.add(string.replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
						Meta.setLore(NewLore);
						Item9.setItemMeta(Meta);
						e.getPlayer().getInventory().setItem(getCustomConfig().getInt("Item9.Slot"), Item9);
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
