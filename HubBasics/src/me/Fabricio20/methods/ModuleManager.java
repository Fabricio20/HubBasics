package me.Fabricio20.methods;

import java.util.List;

import me.Fabricio20.Main;

public class ModuleManager {
	
	public static ModuleManager theClass = new ModuleManager();
	
	public boolean isInWorld(String world) {
		List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
		return worlds.contains(world);
	}
	
	public boolean isEnabled(String module) {
		if(module.equalsIgnoreCase("ChatSystem")) {
			return Main.theClass.plugin.getConfig().getBoolean("ChatSystem.Enabled");
		} else if(module.equalsIgnoreCase("")) {
			return false;
		} else {
			return false;
		}
	}
	
}
