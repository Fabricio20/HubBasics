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
	
	public static SimpleConfig ChestItems = Main.theClass.ChestItems;
	
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
				String name = ChestItems.getString("MagicClockEnabled.Name").replace("&", "§");
				List<String> loreO = ChestItems.getStringList("MagicClockEnabled.Lore");
				String material = ChestItems.getString("MagicClockEnabled.Material");
				int Data = 0;
				if(ChestItems.contains("MagicClockEnabled.Data")) {
					Data = ChestItems.getInt("MagicClockEnabled.Data");
				}
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = ChestItems.getBoolean("MagicClockEnabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material), 1, (short) Data);
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			} else {
				String name = ChestItems.getString("MagicClockDisabled.Name").replace("&", "§");
				List<String> loreO = ChestItems.getStringList("MagicClockDisabled.Lore");
				String material = ChestItems.getString("MagicClockDisabled.Material");
				int Data = 0;
				if(ChestItems.contains("MagicClockDisabled.Data")) {
					Data = ChestItems.getInt("MagicClockDisabled.Data");
				}
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = ChestItems.getBoolean("MagicClockDisabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material), 1, (short) Data);
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
				String name = ChestItems.getString("SpeedBoostEnabled.Name").replace("&", "§");
				List<String> loreO = ChestItems.getStringList("SpeedBoostEnabled.Lore");
				String material = ChestItems.getString("SpeedBoostEnabled.Material");
				int Data = 0;
				if(ChestItems.contains("SpeedBoostEnabled.Data")) {
					Data = ChestItems.getInt("SpeedBoostEnabled.Data");
				}
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = ChestItems.getBoolean("SpeedBoostEnabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material), 1, (short) Data);
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			} else {
				String name = ChestItems.getString("SpeedBoostDisabled.Name").replace("&", "§");
				List<String> loreO = ChestItems.getStringList("SpeedBoostDisabled.Lore");
				String material = ChestItems.getString("SpeedBoostDisabled.Material");
				int Data = 0;
				if(ChestItems.contains("SpeedBoostDisabled.Data")) {
					Data = ChestItems.getInt("SpeedBoostDisabled.Data");
				}
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = ChestItems.getBoolean("SpeedBoostDisabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material), 1, (short) Data);
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
				String name = ChestItems.getString("JumpBoostEnabled.Name").replace("&", "§");
				List<String> loreO = ChestItems.getStringList("JumpBoostEnabled.Lore");
				String material = ChestItems.getString("JumpBoostEnabled.Material");
				int Data = 0;
				if(ChestItems.contains("JumpBoostEnabled.Data")) {
					Data = ChestItems.getInt("JumpBoostEnabled.Data");
				}
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = ChestItems.getBoolean("JumpBoostEnabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material), 1, (short) Data);
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(name);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				if(glow) {
					stack.addUnsafeEnchantment(Main.theClass.ench, 1);
				}
				return stack;
			} else {
				String name = ChestItems.getString("JumpBoostDisabled.Name").replace("&", "§");
				List<String> loreO = ChestItems.getStringList("JumpBoostDisabled.Lore");
				String material = ChestItems.getString("JumpBoostDisabled.Material");
				int Data = 0;
				if(ChestItems.contains("JumpBoostDisabled.Data")) {
					Data = ChestItems.getInt("JumpBoostDisabled.Data");
				}
				List<String> lore = new ArrayList<String>();
				for(String s: loreO) {
					lore.add(s.replace("&", "§"));
				}
				boolean glow = ChestItems.getBoolean("JumpBoostDisabled.Glow");
				ItemStack stack = new ItemStack(Material.getMaterial(material), 1, (short) Data);
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
