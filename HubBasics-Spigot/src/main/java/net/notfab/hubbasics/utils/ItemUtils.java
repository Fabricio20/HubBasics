package net.notfab.hubbasics.utils;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static Map<String, Object> serialize(ItemStack stack) {
        LinkedHashMap result = new LinkedHashMap();
        result.put("type", stack.getType().name());
        if(stack.getDurability() != 0) result.put("damage", Short.valueOf(stack.getDurability()));
        if(stack.getAmount() != 1) result.put("amount", Integer.valueOf(stack.getAmount()));

        ItemMeta meta = stack.getItemMeta();
        if(!Bukkit.getItemFactory().equals(meta, null)) result.put("meta", meta.serialize());

        return result;
    }

    public static ItemStack deserialize(Map<String, Object> data) {
        Material type = Material.getMaterial((String) data.get("type"));
        short damage = 0;
        int amount = 1;
        if(data.containsKey("damage")) damage = ((Number)data.get("damage")).shortValue();
        if(data.containsKey("amount")) amount = ((Number)data.get("amount")).intValue();
        ItemStack result = new ItemStack(type, amount, damage);
        if (data.containsKey("meta")) {
            Map<String, Object> map = new HashMap<>((Map<String, Object>) data.get("meta"));
            map.put("==", "ItemMeta");
            result.setItemMeta((ItemMeta) ConfigurationSerialization.deserializeObject(map));
        }

        return result;
    }
}
