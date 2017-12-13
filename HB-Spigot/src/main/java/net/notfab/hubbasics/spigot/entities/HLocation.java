package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.json.JSONObject;

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

    @Getter private String server;
    private String world;
    @Getter private Double X;
    @Getter private Double Y;
    @Getter private Double Z;
    @Getter private Float Yaw;
    @Getter private Float Pitch;

    public HLocation(Location location) {
        this.server = null;
        this.world = location.getWorld().getName();
        this.X = location.getX();
        this.Y = location.getY();
        this.Z = location.getZ();
        this.Yaw = location.getYaw();
        this.Pitch = location.getPitch();
    }

    public HLocation(World world, Double x, Double y, Double z, Float yaw, Float pitch) {
        this.server = null;
        this.world = world.getName();
        this.X = x;
        this.Y = y;
        this.Z = z;
        this.Yaw = yaw;
        this.Pitch = pitch;
    }

    public HLocation(String server, String world, Double x, Double y, Double z, Float yaw, Float pitch) {
        this.server = server;
        this.world = world;
        this.X = x;
        this.Y = y;
        this.Z = z;
        this.Yaw = yaw;
        this.Pitch = pitch;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public JSONObject toJSON() {
        return new JSONObject().put("server", server).put("world", world).put("x", X).put("y", Y).put("z", Z).put("yaw", Yaw).put("pitch", Pitch);
    }

    public void teleport(Player player) {
        //TODO
    }

    public Location toBukkitLocation() {
        return new Location(Bukkit.getWorld(world), X, Y, Z, Yaw, Pitch);
    }

}
