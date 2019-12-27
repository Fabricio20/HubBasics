package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Result;
import net.notfab.hubbasics.spigot.utils.Messages;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.util.*;

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
        if (!folder.exists()) return;
        File[] listFiles = folder.listFiles();
        if (listFiles == null) return;
        List<File> files = Arrays.asList(listFiles);
        files.forEach(file -> {
            SimpleConfig config = HubBasics.getConfigManager().getNewConfig("warps/" + file.getName());
            Result result = this.read(config);
            if (!result.isSuccess()) {
                Logger.warn("Error while loading warp: " + Messages.get(result.getExtra(0)));
            } else {
                HLocation location = result.getExtra(0);
                this.locationMap.put(location.getId().toLowerCase(), location);
                Logger.debug("Loaded warp " + location.getId());
            }
        });
        this.locationMap.put("default", new HLocation("default", Bukkit.getWorlds().get(0).getSpawnLocation()));
        Logger.info("[LocationManager] Loaded " + this.locationMap.size() + " warp(s).");
    }

    public HLocation get(String id) {
        if (id == null) return null;
        return this.locationMap.get(id.toLowerCase());
    }

    private Result read(SimpleConfig config) {
        HLocation location = new HLocation(config.getName().replace(".yml", ""));
        if (config.contains("World")) {
            World world = Bukkit.getWorld(config.getString("World"));
            if (world == null) return new Result(false, "INVALID_WORLD");
            location.setWorld(world);
        }
        if (config.contains("X")) {
            location.setX(config.getDouble("X"));
        }
        if (config.contains("Y")) {
            location.setY(config.getDouble("Y"));
        }
        if (config.contains("Z")) {
            location.setZ(config.getDouble("Z"));
        }
        if (config.contains("Yaw")) {
            double d = config.getDouble("Yaw");
            location.setYaw((float) d);
        }
        if (config.contains("Pitch")) {
            double d = config.getDouble("Pitch");
            location.setPitch((float) d);
        }
        if (config.contains("Server")) {
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
        config.save();
        this.locationMap.put(name.toLowerCase(), new HLocation(name, location));
        return new Result(new HLocation(name, location));
    }

    public boolean delete(HLocation warp) {
        this.locationMap.remove(warp.getId().toLowerCase());
        File file = new File(HubBasics.getDataFolder(), "warps/" + warp.getId() + ".yml");
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public Set<String> list() {
        return this.locationMap.keySet();
    }

}