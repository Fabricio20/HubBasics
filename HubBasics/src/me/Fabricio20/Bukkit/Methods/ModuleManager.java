package me.Fabricio20.Bukkit.Methods;

import java.util.List;

import me.Fabricio20.Bukkit.Main;

import org.bukkit.entity.Player;

public class ModuleManager {
	
	public static ModuleManager theClass = new ModuleManager();
	
	public boolean isInWorld(Player player) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		return worlds.contains(player.getWorld().getName());
	}
	
	public boolean isEnabled(String module) {
		switch(module) {
		case "ChatSystem":
			return Main.theClass.config.getBoolean("ChatSystem.Enabled");
		case "Tags":
			return Main.theClass.Tags.getBoolean("Enabled");
		case "Building":
			return Main.theClass.config.getBoolean("Others.AllowBuilding");
		default:
			return false;
		}
	}
	
}
