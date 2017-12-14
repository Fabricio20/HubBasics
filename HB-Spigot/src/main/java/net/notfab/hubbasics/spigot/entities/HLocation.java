package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Copyright (c) HubBasics 2018.
 * <p>
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 * <p>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 * <p>
 * File Created by Fabricio20 on 06/12/2017.
 */
public class HLocation {

    @Getter private final String id;
    @Getter @Setter private String server;
    private String world;
    @Getter @Setter private Double X;
    @Getter @Setter private Double Y;
    @Getter @Setter private Double Z;
    @Getter @Setter private Float Yaw;
    @Getter @Setter private Float Pitch;

    public HLocation(String id) {
        this.id = id;
    }

    public HLocation(String id, Location location) {
        this.id = id;
        this.server = null;
        this.world = location.getWorld().getName();
        this.X = location.getX();
        this.Y = location.getY();
        this.Z = location.getZ();
        this.Yaw = location.getYaw();
        this.Pitch = location.getPitch();
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public void setWorld(World world) {
        this.world = world.getName();
    }

    public void teleport(Player player) {
        if(this.server != null) {
            //TODO
        } else {
            player.teleport(this.toBukkitLocation());
        }
    }

    public Location toBukkitLocation() {
        if(getWorld() == null) return Bukkit.getWorlds().get(0).getSpawnLocation();
        Double X = (this.X == null) ? getWorld().getSpawnLocation().getX() : this.X;
        Double Y = (this.Y == null) ? getWorld().getSpawnLocation().getY() : this.Y;
        Double Z = (this.Z == null) ? getWorld().getSpawnLocation().getZ() : this.Z;
        Float Yaw = (this.Yaw == null) ? getWorld().getSpawnLocation().getYaw() : this.Yaw;
        Float Pitch = (this.Pitch == null) ? getWorld().getSpawnLocation().getPitch() : this.Pitch;
        return new Location(getWorld(), X, Y, Z, Yaw, Pitch);
    }

}
