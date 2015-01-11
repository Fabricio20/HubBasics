package me.Fabricio20.API;

import me.Fabricio20.Main;

public class BookAPI {
	
	public static boolean shouldGive(String playerName) {
		if(!Main.theClass.Storage.contains("Players." + playerName + ".Book")) {
			return true;
		} else {
			if(Main.theClass.Storage.getBoolean("Players." + playerName + ".Book") == true) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static void give(String playerName) {
		if(!Main.theClass.Storage.contains("Players." + playerName + ".Book")) {
			Main.theClass.Storage.set("Players." + playerName + ".Book", true);
			Main.theClass.Storage.saveConfig();
		} else {
			return;
		}
	}
	
}
