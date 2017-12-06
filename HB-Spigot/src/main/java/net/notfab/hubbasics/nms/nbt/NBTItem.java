package net.notfab.hubbasics.nms.nbt;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import org.bukkit.inventory.ItemStack;

public class NBTItem {

    private ItemStack bukkititem;

    public NBTItem(ItemStack Item) {
        bukkititem = Item.clone();
    }

    public ItemStack getItem() {
        return bukkititem;
    }

    public void setString(String Key, String Text) {
        bukkititem = NBTReflectionUtils.setString(bukkititem, Key, Text);
    }

    public String getString(String Key) {
        return NBTReflectionUtils.getString(bukkititem, Key);
    }

    public void setInteger(String key, Integer Int) {
        bukkititem = NBTReflectionUtils.setInt(bukkititem, key, Int);
    }

    public Integer getInteger(String key) {
        return NBTReflectionUtils.getInt(bukkititem, key);
    }

    public void setDouble(String key, Double d) {
        bukkititem = NBTReflectionUtils.setDouble(bukkititem, key, d);
    }

    public Double getDouble(String key) {
        return NBTReflectionUtils.getDouble(bukkititem, key);
    }

    public void setBoolean(String key, Boolean b) {
        bukkititem = NBTReflectionUtils.setBoolean(bukkititem, key, b);
    }

    public Boolean getBoolean(String key) {
        return NBTReflectionUtils.getBoolean(bukkititem, key);
    }

    public Boolean hasKey(String key) {
        return NBTReflectionUtils.hasKey(bukkititem, key);
    }
}
