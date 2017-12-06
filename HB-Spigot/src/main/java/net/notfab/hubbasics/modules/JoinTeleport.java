package net.notfab.hubbasics.modules;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.HubBasics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

import lombok.Getter;

public class JoinTeleport extends Module {
    @Getter private Location location;

    public JoinTeleport() {
        super(ModuleEnum.JOIN_TELEPORT);
    }

    @Override
    public void onEnable() {
        double x = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_X);
        double y = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_Y);
        double z = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_Z);
        float yaw = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_YAW).floatValue();
        float pitch = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_PITCH).floatValue();
        String world = null;
        for(World w: Bukkit.getWorlds()) {
            if(w.getName().equalsIgnoreCase(getString(ConfigurationKey.JOIN_TELEPORT_LOCATION_WORLD))) {
                world = w.getName();
            }
        }
        if(world == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "YOU HAVE SPECIFIED A WORLD THAT DOES NOT EXIST IN THE CONFIGURATION FILE FOR JOIN_TELEPORT!");
            HubBasics.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(HubBasics.getInstance(), () -> HubBasics.getInstance().getModuleManager().getModule(ModuleEnum.JOIN_TELEPORT).onDisableInternal(), 40);
            return;
        }
        this.location = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        if (isEnabledInWorld(event.getPlayer().getWorld())) event.getPlayer().teleport(this.getLocation());
    }
}
