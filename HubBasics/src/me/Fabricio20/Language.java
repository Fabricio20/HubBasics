package me.Fabricio20;

public class Language {
	
	public static Language theClass = new Language();
	

	
	public String WarpUsage(String playerName) {
		return Main.theClass.Language.getString("WarpUsage").replace("&", "§").replace("%p", playerName);
	}
	
	public String WarpedMessage(String playerName) {
		return Main.theClass.Language.getString("WarpMessage").replace("&", "§").replace("%p", playerName);
	}
	
	public String WarpNoPermission(String playerName) {
		return Main.theClass.Language.getString("WarpNoPermission").replace("&", "§").replace("%p", playerName);
	}
	
	public String QWarpChestName(String playerName) {
		return Main.theClass.Language.getString("QuickWarpChestName").replace("&", "§").replace("%p", playerName);
	}
	
}
