package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Result;
import net.notfab.hubbasics.spigot.objects.SimpleConfig;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-14.
 */
public class LocationManager extends Manager {

    private Map<String, HLocation> locationMap = new HashMap<>();

    public LocationManager() {
        this.onEnable();
    }

    @Override
    public void onDisable() {
        this.locationMap.clear();
    }

    public void onEnable() {
        this.locationMap.clear();
        File folder = new File(HubBasics.getDataFolder(), "warps/");
        if(!folder.exists()) return;
        File[] listFiles = folder.listFiles();
        if(listFiles == null) return;
        List<File> files = Arrays.asList(listFiles);
        files.forEach(file -> {
            SimpleConfig config = HubBasics.getConfigManager().getNewConfig("warps/" + file.getName());
            Result result = this.read(config);
            if(!result.isSuccess()) {
                Logger.warn("Error while loading warp: " + Messages.get(result.getExtra(0)));
            } else {
                HLocation location = result.getExtra(0);
                this.locationMap.put(location.getId(), location);
                Logger.debug("Loaded warp " + location.getId());
            }
        });
        Logger.info("[LocationManager] Loaded " + this.locationMap.size() + " warps.");
    }

    public HLocation get(String id) {
        return this.locationMap.get(id);
    }

    private Result read(SimpleConfig config) {
        HLocation location = new HLocation(config.getName().replace(".yml", ""));
        if(config.contains("World")) {
            World world = Bukkit.getWorld(config.getString("World"));
            if(world == null) return new Result(false, "INVALID_WORLD");
            location.setWorld(world);
        }
        if(config.contains("X")) {
            location.setX(config.getDouble("X"));
        }
        if(config.contains("Y")) {
            location.setY(config.getDouble("Y"));
        }
        if(config.contains("Z")) {
            location.setZ(config.getDouble("Z"));
        }
        if(config.contains("Yaw")) {
            Double d = config.getDouble("Yaw");
            location.setYaw(d.floatValue());
        }
        if(config.contains("Pitch")) {
            Double d = config.getDouble("Pitch");
            location.setYaw(d.floatValue());
        }
        if(config.contains("Server")) {
            location.setServer(config.getString("Server"));
        }
        return new Result(true, location);
    }

    public Result create(String name, Location location) {
        SimpleConfig config = HubBasics.getConfigManager().getNewConfig("warps/" + name + ".yml");
        config.set("World", location.getWorld().getName());
        config.set("X", location.getX());
        config.set("Y", location.getY());
        config.set("Z", location.getZ());
        config.set("Yaw", location.getYaw());
        config.set("Pitch", location.getPitch());
        config.saveConfig();
        this.locationMap.put(name, new HLocation(name, location));
        return new Result(new HLocation(name, location));
    }

    public boolean delete(HLocation warp) {
        this.locationMap.remove(warp.getId());
        File file = new File(HubBasics.getDataFolder(), "warps/" + warp.getId() + ".yml");
        if(file.exists()) {
            return file.delete();
        }
        return false;
    }

}
