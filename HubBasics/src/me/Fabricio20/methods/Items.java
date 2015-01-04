package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Items {
	
	private static String Item1Name = "";
	private static String Item2Name = "";
	private static String Item3Name = "";
	private static String Item4Name = "";
	private static String Item5Name = "";
	private static String Item6Name = "";
	private static String Item7Name = "";
	private static String Item8Name = "";
	private static String Item9Name = "";
	private static String MagicClockName = "";
	
	public static ItemStack Item1(String player) {
		fixItemName(player);
		if(CustomConfigs.getItemConfig().getBoolean("Item1.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item1Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item1.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item1.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item1Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item1.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item2.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item2Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item2.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item2.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item2Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item2.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item3.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item3Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item3.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item3.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item3Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item3.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item4.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item4Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item4.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item4.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item4Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item4.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item5.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item5Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item5.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item5.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item5Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item5.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item6.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item6Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item6.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item6.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item6Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item6.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item7.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item7Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item7.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item7.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item7Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item7.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item8.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item8Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item8.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item8.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item8Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item8.Lore");
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
		if(CustomConfigs.getItemConfig().getBoolean("Item9.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item9Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item9.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(CustomConfigs.getItemConfig().getString("Item9.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item9Name);
			List<String> LoreFromConfig = CustomConfigs.getItemConfig().getStringList("Item9.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		}
	}
	
	public static ItemStack MagicClock(String player) {
		fixItemName(player);
		ItemStack Clock = new ItemStack(Material.getMaterial(Main.getPlugin().getConfig().getString("MagicClock.Material")));
		ItemMeta Meta = Clock.getItemMeta();
		Meta.setDisplayName(MagicClockName);
		List<String> LoreFromConfig = Main.getPlugin().getConfig().getStringList("MagicClock.Lore");
		List<String> NewLore = new ArrayList<String>();
		for(String string : LoreFromConfig) {
			NewLore.add(string.replace("&", "§").replace("%p", player));
		}
		Meta.setLore(NewLore);
		Clock.setItemMeta(Meta);
		return Clock;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void fixItemName(String playername) {
		if(CustomConfigs.getItemConfig().contains("Item1.Name") && CustomConfigs.getItemConfig().getString("Item1.Name") != null && CustomConfigs.getItemConfig().getString("Item1.Name") != "") {
			Item1Name = CustomConfigs.getItemConfig().getString("Item1.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item2.Name") && CustomConfigs.getItemConfig().getString("Item2.Name") != null && CustomConfigs.getItemConfig().getString("Item2.Name") != "") {
			Item2Name = CustomConfigs.getItemConfig().getString("Item2.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item3.Name") && CustomConfigs.getItemConfig().getString("Item3.Name") != null && CustomConfigs.getItemConfig().getString("Item3.Name") != "") {
			Item3Name = CustomConfigs.getItemConfig().getString("Item3.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item4.Name") && CustomConfigs.getItemConfig().getString("Item4.Name") != null && CustomConfigs.getItemConfig().getString("Item4.Name") != "") {
			Item4Name = CustomConfigs.getItemConfig().getString("Item4.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item5.Name") && CustomConfigs.getItemConfig().getString("Item5.Name") != null && CustomConfigs.getItemConfig().getString("Item5.Name") != "") {
			Item5Name = CustomConfigs.getItemConfig().getString("Item5.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item6.Name") && CustomConfigs.getItemConfig().getString("Item6.Name") != null && CustomConfigs.getItemConfig().getString("Item6.Name") != "") {
			Item6Name = CustomConfigs.getItemConfig().getString("Item6.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item7.Name") && CustomConfigs.getItemConfig().getString("Item7.Name") != null && CustomConfigs.getItemConfig().getString("Item7.Name") != "") {
			Item7Name = CustomConfigs.getItemConfig().getString("Item7.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item8.Name") && CustomConfigs.getItemConfig().getString("Item8.Name") != null && CustomConfigs.getItemConfig().getString("Item8.Name") != "") {
			Item8Name = CustomConfigs.getItemConfig().getString("Item8.Name").replace("&", "§").replace("%p", playername);
		}
		if(CustomConfigs.getItemConfig().contains("Item9.Name") && CustomConfigs.getItemConfig().getString("Item9.Name") != null && CustomConfigs.getItemConfig().getString("Item9.Name") != "") {
			Item9Name = CustomConfigs.getItemConfig().getString("Item9.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.getPlugin().getConfig().contains("MagicClock.Name")) {
			MagicClockName = Main.getPlugin().getConfig().getString("MagicClock.Name").replace("&", "§").replace("%p", playername);
		}
	}
	
}
