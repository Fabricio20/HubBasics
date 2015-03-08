package me.Fabricio20.API;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.methods.Configs.SimpleConfig;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsAPI {
	
	public static SimpleConfig Items = Main.theClass.Items;
	
	public static boolean shouldGive(String playerName) {
		SimpleConfig pConfig = SettingsManager.theClass.getSettings(playerName);
		if(!pConfig.contains("JoinItems")) {
			return true;
		} else {
			if(pConfig.getBoolean("JoinItems") == true) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static void give(String playerName) {
		SimpleConfig pConfig = SettingsManager.theClass.getSettings(playerName);
		if(!pConfig.contains("JoinItems")) {
			pConfig.set("JoinItems", true);
			pConfig.saveConfig();
		} else {
			return;
		}
	}

	public static ItemStack get(String string, boolean Enabled) {
		switch(string) {
		case "MagicClock":
			if(Enabled) {
				String name = Items.getString("MagicClockEnabled.Name").replace("&", "§");
				List<String> loreO = Items.getStringList("MagicClockEnabled.Lore");
				String material = Items.getString("MagicClockEnabled.Material");
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = Items.getBoolean("MagicClockEnabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material));
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			} else {
				String name = Items.getString("MagicClockDisabled.Name").replace("&", "§");
				List<String> loreO = Items.getStringList("MagicClockDisabled.Lore");
				String material = Items.getString("MagicClockDisabled.Material");
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = Items.getBoolean("MagicClockDisabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material));
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			}
		case "SpeedBoost":
			if(Enabled) {
				String name = Items.getString("SpeedBoostEnabled.Name").replace("&", "§");
				List<String> loreO = Items.getStringList("SpeedBoostEnabled.Lore");
				String material = Items.getString("SpeedBoostEnabled.Material");
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = Items.getBoolean("SpeedBoostEnabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material));
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			} else {
				String name = Items.getString("SpeedBoostDisabled.Name").replace("&", "§");
				List<String> loreO = Items.getStringList("SpeedBoostDisabled.Lore");
				String material = Items.getString("SpeedBoostDisabled.Material");
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = Items.getBoolean("SpeedBoostDisabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material));
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			}
		case "JumpBoost":
			if(Enabled) {
				String name = Items.getString("JumpBoostEnabled.Name").replace("&", "§");
				List<String> loreO = Items.getStringList("JumpBoostEnabled.Lore");
				String material = Items.getString("JumpBoostEnabled.Material");
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = Items.getBoolean("JumpBoostEnabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material));
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			} else {
				String name = Items.getString("JumpBoostDisabled.Name").replace("&", "§");
				List<String> loreO = Items.getStringList("JumpBoostDisabled.Lore");
				String material = Items.getString("JumpBoostDisabled.Material");
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = Items.getBoolean("JumpBoostDisabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material));
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			}
		default:
			return new ItemStack(Material.AIR);
		}
	}
	
}
