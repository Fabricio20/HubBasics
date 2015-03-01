package me.Fabricio20.API;

import me.Fabricio20.Main;

public class LanguageAPI {
	
	public static LanguageAPI theClass = new LanguageAPI();
	
	public String WarpUsage(String playerName, String worldName) {
		return Main.theClass.Language.getString("WarpUsage").replace("&", "§").replace("%player%", playerName).replace("%world%", worldName);
	}
	
	public String WarpedMessage(String playerName, String worldName) {
		return Main.theClass.Language.getString("WarpMessage").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
	public String WarpNoPermission(String playerName, String worldName) {
		return Main.theClass.Language.getString("WarpNoPermission").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
	public String QWarpChestName(String playerName, String worldName) {
		return Main.theClass.Language.getString("QuickWarpChestName").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
	public String ServerSelectorNoPerm(String playerName, String worldName) {
		return Main.theClass.Language.getString("ServerSelectorNoPerm").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
}
