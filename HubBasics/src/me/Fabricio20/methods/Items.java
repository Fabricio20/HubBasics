package me.Fabricio20.methods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Items {
	
	public static ItemStack Skull(String player) {
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta sm = (SkullMeta) is.getItemMeta();
		sm.setOwner(player);
		is.setItemMeta(sm);
		return is;
	}
	
}
