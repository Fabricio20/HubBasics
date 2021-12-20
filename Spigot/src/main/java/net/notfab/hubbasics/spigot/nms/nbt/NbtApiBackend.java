package net.notfab.hubbasics.spigot.nms.nbt;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class NbtApiBackend implements NBTBackend {

    @Override
    public ItemStack setString(ItemStack stack, String key, String value) {
        NBTItem item = new NBTItem(stack);
        item.setString(key, value);
        return item.getItem();
    }

    @Override
    public String getString(ItemStack stack, String key) {
        NBTItem item = new NBTItem(stack);
        return item.getString(key);
    }

    @Override
    public ItemStack setInt(ItemStack stack, String key, Integer value) {
        NBTItem item = new NBTItem(stack);
        item.setInteger(key, value);
        return item.getItem();
    }

    @Override
    public Integer getInt(ItemStack stack, String key) {
        NBTItem item = new NBTItem(stack);
        return item.getInteger(key);
    }

    @Override
    public ItemStack setDouble(ItemStack stack, String key, Double value) {
        NBTItem item = new NBTItem(stack);
        item.setDouble(key, value);
        return item.getItem();
    }

    @Override
    public Double getDouble(ItemStack stack, String key) {
        NBTItem item = new NBTItem(stack);
        return item.getDouble(key);
    }

    @Override
    public ItemStack setBoolean(ItemStack stack, String key, Boolean value) {
        NBTItem item = new NBTItem(stack);
        item.setBoolean(key, value);
        return item.getItem();
    }

    @Override
    public Boolean getBoolean(ItemStack stack, String key) {
        NBTItem item = new NBTItem(stack);
        return item.getBoolean(key);
    }

    @Override
    public boolean hasKey(ItemStack stack, String key) {
        NBTItem item = new NBTItem(stack);
        return item.hasKey(key);
    }

}
