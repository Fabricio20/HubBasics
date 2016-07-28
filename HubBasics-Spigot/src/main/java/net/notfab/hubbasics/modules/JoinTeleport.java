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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

public class JoinTeleport extends Module {

    private Location location;

    public JoinTeleport() {
        super(ModuleEnum.JOIN_TELEPORT);
    }

    @Override
    public void onEnable() {
        double x = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_X);
        double y = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_Y);
        double z = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_Z);
        double yaw = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_YAW);
        double pitch = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_PITCH);
        World world = Bukkit.getWorld(getString(ConfigurationKey.JOIN_TELEPORT_LOCATION_WORLD));
        this.location = new Location(world, x, y, z, (float) yaw, (float) pitch);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        if (isEnabledInWorld(event.getPlayer().getWorld())) event.getPlayer().teleport(this.location);
    }
}
