package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
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
	private static String HatName = "";
	
	public static ItemStack Item1(String player) {
		fixItemName(player);
		if(Main.theClass.ItemConfig.getBoolean("Item1.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item1Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item1.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item1.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item1Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item1.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item2.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item2Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item2.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item2.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item2Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item2.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item3.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item3Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item3.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item3.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item3Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item3.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item4.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item4Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item4.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item4.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item4Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item4.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item5.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item5Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item5.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item5.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item5Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item5.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item6.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item6Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item6.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item6.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item6Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item6.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item7.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item7Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item7.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item7.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item7Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item7.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item8.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item8Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item8.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item8.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item8Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item8.Lore");
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
		if(Main.theClass.ItemConfig.getBoolean("Item9.Skull") == true) {
			ItemStack Item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta Meta = (SkullMeta) Item1.getItemMeta();
			Meta.setOwner(player);
			Meta.setDisplayName(Item9Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item9.Lore");
			List<String> NewLore = new ArrayList<String>();
			for(String string : LoreFromConfig) {
				NewLore.add(string.replace("&", "§").replace("%p", player));
			}
			Meta.setLore(NewLore);
			Item1.setItemMeta(Meta);
			return Item1;
		} else {
			ItemStack Item1 = new ItemStack(Material.getMaterial(Main.theClass.ItemConfig.getString("Item9.Material")));
			ItemMeta Meta = Item1.getItemMeta();
			Meta.setDisplayName(Item9Name);
			List<String> LoreFromConfig = Main.theClass.ItemConfig.getStringList("Item9.Lore");
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
		ItemStack Clock = new ItemStack(Material.getMaterial(Main.theClass.config.getString("MagicClock.Material")));
		ItemMeta Meta = Clock.getItemMeta();
		Meta.setDisplayName(MagicClockName);
		List<String> LoreFromConfig = Main.theClass.config.getStringList("MagicClock.Lore");
		List<String> NewLore = new ArrayList<String>();
		for(String string : LoreFromConfig) {
			NewLore.add(string.replace("&", "§").replace("%p", player));
		}
		Meta.setLore(NewLore);
		Clock.setItemMeta(Meta);
		return Clock;
	}
	
	public static ItemStack Book17(String player) {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta Meta = (BookMeta) book.getItemMeta();
		Meta.setAuthor(Main.theClass.config.getString("BookSystem.Author"));
		Meta.setDisplayName(Main.theClass.config.getString("BookSystem.Name").replace("&", "§"));
		List<String> LoreFromConfig = Main.theClass.config.getStringList("BookSystem.Lore");
		List<String> NewLore = new ArrayList<String>();
		for(String string : LoreFromConfig) {
			NewLore.add(string.replace("&", "§").replace("%p", player));
		}
		Meta.setLore(NewLore);
		List<String> PagesFromConfig = Main.theClass.config.getStringList("BookSystem.Lore");
		List<String> newPages = new ArrayList<String>();
		for(String string : PagesFromConfig) {
			newPages.add(string.replace("&", "§"));
		}
		for(String line : newPages) {
			Meta.addPage(line);
		}
		book.setItemMeta(Meta);
		return book;
	}
	
	public static ItemStack Hat(String player, Material mat, int met) {
		fixItemName(player);
		ItemStack Hat = new ItemStack(mat, 1, (short)met);
		ItemMeta Meta = Hat.getItemMeta();
		Meta.setDisplayName(HatName);
		List<String> LoreFromConfig = Main.theClass.config.getStringList("HatSystem.Lore");
		List<String> NewLore = new ArrayList<String>();
		for(String string : LoreFromConfig) {
			NewLore.add(string.replace("&", "§").replace("%p", player));
		}
		Meta.setLore(NewLore);
		Hat.setItemMeta(Meta);
		return Hat;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void fixItemName(String playername) {
		if(Main.theClass.ItemConfig.contains("Item1.Name") && Main.theClass.ItemConfig.getString("Item1.Name") != null && Main.theClass.ItemConfig.getString("Item1.Name") != "") {
			Item1Name = Main.theClass.ItemConfig.getString("Item1.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item2.Name") && Main.theClass.ItemConfig.getString("Item2.Name") != null && Main.theClass.ItemConfig.getString("Item2.Name") != "") {
			Item2Name = Main.theClass.ItemConfig.getString("Item2.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item3.Name") && Main.theClass.ItemConfig.getString("Item3.Name") != null && Main.theClass.ItemConfig.getString("Item3.Name") != "") {
			Item3Name = Main.theClass.ItemConfig.getString("Item3.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item4.Name") && Main.theClass.ItemConfig.getString("Item4.Name") != null && Main.theClass.ItemConfig.getString("Item4.Name") != "") {
			Item4Name = Main.theClass.ItemConfig.getString("Item4.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item5.Name") && Main.theClass.ItemConfig.getString("Item5.Name") != null && Main.theClass.ItemConfig.getString("Item5.Name") != "") {
			Item5Name = Main.theClass.ItemConfig.getString("Item5.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item6.Name") && Main.theClass.ItemConfig.getString("Item6.Name") != null && Main.theClass.ItemConfig.getString("Item6.Name") != "") {
			Item6Name = Main.theClass.ItemConfig.getString("Item6.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item7.Name") && Main.theClass.ItemConfig.getString("Item7.Name") != null && Main.theClass.ItemConfig.getString("Item7.Name") != "") {
			Item7Name = Main.theClass.ItemConfig.getString("Item7.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item8.Name") && Main.theClass.ItemConfig.getString("Item8.Name") != null && Main.theClass.ItemConfig.getString("Item8.Name") != "") {
			Item8Name = Main.theClass.ItemConfig.getString("Item8.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.ItemConfig.contains("Item9.Name") && Main.theClass.ItemConfig.getString("Item9.Name") != null && Main.theClass.ItemConfig.getString("Item9.Name") != "") {
			Item9Name = Main.theClass.ItemConfig.getString("Item9.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.config.contains("MagicClock.Name")) {
			MagicClockName = Main.theClass.config.getString("MagicClock.Name").replace("&", "§").replace("%p", playername);
		}
		if(Main.theClass.config.contains("HatSystem.Name")) {
			HatName = Main.theClass.config.getString("HatSystem.Name").replace("&", "§").replace("%p", playername);
		}
	}
	
}
