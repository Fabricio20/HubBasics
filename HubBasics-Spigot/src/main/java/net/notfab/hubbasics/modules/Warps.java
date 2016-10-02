package net.notfab.hubbasics.modules;

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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.objects.SimpleConfig;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

import lombok.Getter;

public class Warps extends Module {
    @Getter private Map<String, Location> warps;

    public Warps() {
        super(ModuleEnum.WARPS);
        this.warps = new HashMap<>();
    }

    @Override
    public void onEnable() {
        if (!getConfig().contains(ConfigurationKey.WARPS_WARPS)) return;
        Set<String> warpNames = getConfig().getConfigurationSection(ConfigurationKey.WARPS_WARPS).getKeys(false);
        warpNames.forEach(this::loadWarp);
    }

    @Override
    public void onDisable() {

    }

    public Location getWarp(String string) {
        string = string.toLowerCase();
        return this.getWarps().get(string);
    }

    public boolean warpExists(String string) {
        string = string.toLowerCase();
        return this.getWarps().containsKey(string);
    }

    private void loadWarp(String string) {
        string = string.toLowerCase();
        ConfigurationSection section = getConfig().getConfigurationSection(ConfigurationKey.WARPS_WARPS);
        double x = section.getDouble(string + ".x");
        double y = section.getDouble(string + ".y");
        double z = section.getDouble(string + ".z");
        float yaw = (float) section.getDouble(string + ".yaw");
        float pitch = (float) section.getDouble(string + ".pitch");
        World world = Bukkit.getWorld(section.getString(string + ".world"));
        Location loc = new Location(world, x, y, z, yaw, pitch);
        this.getWarps().put(string, loc);
    }

    public void setWarp(String string, Location loc) {
        string = string.toLowerCase();
        SimpleConfig raw = getConfig().getRawConfig();
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string + ".x", loc.getX());
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string + ".y", loc.getY());
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string + ".z", loc.getZ());
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string + ".yaw", loc.getYaw());
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string + ".pitch", loc.getPitch());
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string + ".world", loc.getWorld().getName());
        raw.saveConfig();
        this.getWarps().put(string, loc);
    }

    public void deleteWarp(String string) {
        string = string.toLowerCase();
        SimpleConfig raw = getConfig().getRawConfig();
        raw.set(ConfigurationKey.WARPS_WARPS + "." + string, null);
        this.warps.remove(string);
        raw.saveConfig();
    }
}
