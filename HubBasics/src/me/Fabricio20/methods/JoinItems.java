package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.methods.Configs.SimpleConfig;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class JoinItems {
	
	public static JoinItems theClass = new JoinItems();
	
	public SimpleConfig ItemsConfig = Main.theClass.JoinItems;
	
	public String getPermission(ItemStack stack, Player player) {
		String permission = null;
		for(String s: ItemsConfig.getConfigurationSection("Items").getKeys(false)) {
			if(ItemsConfig.contains("Items." + s + ".Displayname")) {
				if(stack.hasItemMeta()) {
					String dName = stack.getItemMeta().getDisplayName().replace("§", "&").replace(player.getName(), "%p");
					if(dName.equals(ItemsConfig.getString("Items." + s + ".Displayname"))) {
						if(ItemsConfig.contains("Items." + s + ".Permission")) {
							permission = ItemsConfig.getString("Items." + s + ".Permission");
						}
					}
				}
			}
		}
		return permission;
	}
	
	public String getCommand(ItemStack stack, Player player) {
		String command = null;
		for(String s: ItemsConfig.getConfigurationSection("Items").getKeys(false)) {
			if(ItemsConfig.contains("Items." + s + ".Displayname")) {
				if(stack.hasItemMeta()) {
					String dName = stack.getItemMeta().getDisplayName().replace("§", "&").replace(player.getName(), "%p");
					if(dName.equals(ItemsConfig.getString("Items." + s + ".Displayname"))) {
						if(ItemsConfig.contains("Items." + s + ".Command")) {
							command = ItemsConfig.getString("Items." + s + ".Command");
						}
					}
				}
			}
		}
		return command;
	}
	
	public HashMap<ItemStack, Integer> getItems(Player player) {
		HashMap<ItemStack, Integer> items = new HashMap<ItemStack, Integer>();
		for(String s: ItemsConfig.getConfigurationSection("Items").getKeys(false)) {
			boolean Skull = false;
			if(ItemsConfig.contains("Items." + s + ".Skull")) {
				Skull = ItemsConfig.getBoolean("Items." + s + ".Skull");
			}
			boolean Glow = false;
			if(ItemsConfig.contains("Items." + s + ".Glow")) {
				Glow = ItemsConfig.getBoolean("Items." + s + ".Glow");
			}
			String name = null;
			if(ItemsConfig.contains("Items." + s + ".Displayname")) {
				name = ItemsConfig.getString("Items." + s + ".Displayname").replace("&", "§").replace("%p", player.getName());
			}
			List<String> loreO = new ArrayList<String>();
			if(ItemsConfig.contains("Items." + s + ".Lore")) {
				loreO = ItemsConfig.getStringList("Items." + s + ".Lore");
			}
			ArrayList<String> lore = new ArrayList<String>();
			for(String d: loreO) {
				lore.add(d.replace("&", "§").replace("%p", player.getName()));
			}
			int slot = 0;
			if(ItemsConfig.contains("Items." + s + ".Slot")) {
				slot = ItemsConfig.getInt("Items." + s + ".Slot");
			}
			int Data = 0;
			if(ItemsConfig.contains("Items." + s + ".Data")) {
				Data = ItemsConfig.getInt("Items." + s + ".Data");
			}
			int Quantity = 1;
			if(ItemsConfig.contains("Items." + s + ".Quantity")) {
				Quantity = ItemsConfig.getInt("Items." + s + ".Quantity");
			}
			Material mat = Material.BEDROCK;
			if(ItemsConfig.contains("Items." + s + ".Material")) {
				try {
					mat = Material.getMaterial(ItemsConfig.getString("Items." + s +".Material"));
				} catch (Exception e) {
					System.out.println("[HubBasics] Invalid Item Name '" + ItemsConfig.getString("Items." + s + ".Material") + "'");
				}
			}
			String owner = "Fabricio20";
			if(Skull) {
				ItemStack stack = new ItemStack(Material.SKULL_ITEM, Quantity, (short) 3);
				SkullMeta meta = (SkullMeta) stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				if(ItemsConfig.contains("Items." + s + ".Owner")) {
					owner = ItemsConfig.getString("Items." + s + ".Owner");
				}
				meta.setOwner(owner);
				stack.setItemMeta(meta);
				if(Glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				items.put(stack, slot);
			} else {
				ItemStack stack = new ItemStack(mat, Quantity, (short) Data);
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(Glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				items.put(stack, slot);
			}
		}
		return items;
	}
	
	public static ItemStack Book(String player) {
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
		List<String> PagesFromConfig = Main.theClass.config.getStringList("BookSystem.Pages");
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
		ItemStack Hat = new ItemStack(mat, 1, (short)met);
		ItemMeta Meta = Hat.getItemMeta();
		Meta.setDisplayName(Main.theClass.config.getString("HatSystem.Name").replace("&", "§").replace("%p", player));
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
	
}
