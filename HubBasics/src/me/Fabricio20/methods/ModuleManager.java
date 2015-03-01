package me.Fabricio20.methods;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.entity.Player;

public class ModuleManager {
	
	public static ModuleManager theClass = new ModuleManager();
	
	public boolean isInWorld(Player player) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		return worlds.contains(player.getWorld().getName());
	}
	
	public boolean isEnabled(String module) {
		if(module.equalsIgnoreCase("ChatSystem")) {
			return Main.theClass.config.getBoolean("ChatSystem.Enabled");
		} else if(module.equalsIgnoreCase("Tags")) {
			return Main.theClass.Tags.getBoolean("Enabled");
		} else {
			return false;
		}
	}
	
}
