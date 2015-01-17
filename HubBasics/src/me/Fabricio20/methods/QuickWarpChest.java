package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.Fabricio20.Main;
import me.Fabricio20.API.LanguageAPI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuickWarpChest {
	
	public static void open(Player player) {
		int slots = Main.theClass.QuickWarpChest.getInt("Rows") * 9;
		Inventory inv = Bukkit.createInventory(null, slots, LanguageAPI.theClass.QWarpChestName(player.getName(), player.getWorld().getName()));
		Set<String> s = Main.theClass.QuickWarpChest.getConfigurationSection("Items").getKeys(false);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		for(String st : s) {
			String mat = Main.theClass.QuickWarpChest.getString(st + ".Material");
			ItemStack stack = new ItemStack(Material.getMaterial(mat));
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(Main.theClass.QuickWarpChest.getString(st + ".Name"));
			List<String> loreFromConfig = Main.theClass.QuickWarpChest.getStringList(st + ".Lore");
			List<String> newLore = new ArrayList<String>();
			for(String str : loreFromConfig) {
				newLore.add(str.replace("&", "§"));
			}
			meta.setLore(newLore);
			stack.setItemMeta(meta);
			items.add(stack);
		}
		for(ItemStack stack: items) {
			inv.addItem(stack);
		}
		player.openInventory(inv);
	}
	
}
