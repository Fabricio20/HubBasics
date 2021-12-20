package net.notfab.hubbasics.spigot.nms.nbt;

import org.bukkit.inventory.ItemStack;

public interface NBTBackend {

    ItemStack setString(ItemStack stack, String key, String value);

    String getString(ItemStack stack, String key);

    ItemStack setInt(ItemStack stack, String key, Integer value);

    Integer getInt(ItemStack stack, String key);

    ItemStack setDouble(ItemStack stack, String key, Double value);

    Double getDouble(ItemStack stack, String key);

    ItemStack setBoolean(ItemStack stack, String key, Boolean value);

    Boolean getBoolean(ItemStack stack, String key);

    boolean hasKey(ItemStack stack, String key);

}
