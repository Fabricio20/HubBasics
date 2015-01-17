package me.Fabricio20.API;

import me.Fabricio20.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WarpAPI {
	
	public static boolean createWarp(String name, String world, double x, double y, double z, float Yaw, float Pitch) {
		name = name.toLowerCase();
		if(!warpExist(name)) {
			Main.theClass.Warps.set("Warps." + name + ".World", world);
			Main.theClass.Warps.set("Warps." + name + ".X", x);
			Main.theClass.Warps.set("Warps." + name + ".Y", y);
			Main.theClass.Warps.set("Warps." + name + ".Z", z);
			Main.theClass.Warps.set("Warps." + name + ".Yaw", Yaw);
			Main.theClass.Warps.set("Warps." + name + ".Pitch", Pitch);
			Main.theClass.Warps.saveConfig();
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean removeWarp(String name) {
		name = name.toLowerCase();
		if(warpExist(name)) {
			Main.theClass.Warps.set("Warps." + name, null);
			Main.theClass.Warps.saveConfig();
			return true;
		} else {
			return false;
		}
	}
	
	public static Location getLocation(String name) {
		name = name.toLowerCase();
		if(Bukkit.getWorld(Main.theClass.Warps.getString("Warps." + name + ".World")) != null) {
			Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
			loc.setWorld(Bukkit.getWorld(Main.theClass.Warps.getString("Warps." + name + ".World")));
			loc.setX(Main.theClass.Warps.getDouble("Warps." + name + ".X"));
			loc.setY(Main.theClass.Warps.getDouble("Warps." + name + ".Y"));
			loc.setZ(Main.theClass.Warps.getDouble("Warps." + name + ".Z"));
			loc.setYaw(Main.theClass.Warps.getInt("Hub." + name + ".Yaw"));
			loc.setPitch(Main.theClass.Warps.getInt("Warps." + name + ".Pitch"));
			return loc;
		} else {
			return Bukkit.getWorlds().get(0).getSpawnLocation();
		}
	}
	
	public static void sendToWarp(String warp, Player player) {
		Location loc = getLocation(warp);
		player.teleport(loc);
		if(Main.theClass.config.getBoolean("WarpSystem.Message") == true) {
			player.sendMessage(LanguageAPI.theClass.WarpedMessage(player.getName(), player.getWorld().getName()));
		}
		if(Main.theClass.config.getBoolean("WarpSystem.Effect") == true) {
			if(Bukkit.getVersion().contains("1.7")) {
				me.Fabricio20.methods.V1_7.RotatoryParticles.send("crit", player);
			}
			//TODO: 1.8 Particles
		}
	}
	
	public static boolean warpExist(String name) {
		name = name.toLowerCase();
		return Main.theClass.Warps.contains("Warps." + name);
	}
	
}
