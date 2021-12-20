package net.notfab.hubbasics.spigot.nms.nbt;

import org.bukkit.inventory.ItemStack;

public class NbtDummyBackend implements NBTBackend {

    @Override
    public ItemStack setString(ItemStack stack, String key, String value) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public String getString(ItemStack stack, String key) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public ItemStack setInt(ItemStack stack, String key, Integer value) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public Integer getInt(ItemStack stack, String key) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public ItemStack setDouble(ItemStack stack, String key, Double value) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public Double getDouble(ItemStack stack, String key) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public ItemStack setBoolean(ItemStack stack, String key, Boolean value) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public Boolean getBoolean(ItemStack stack, String key) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

    @Override
    public boolean hasKey(ItemStack stack, String key) {
        throw new UnsupportedOperationException("Minecraft version not supported, please install NBTAPI.");
    }

}
