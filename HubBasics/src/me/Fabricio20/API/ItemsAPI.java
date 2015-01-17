package me.Fabricio20.API;

import me.Fabricio20.Main;

public class ItemsAPI {
	
	public static boolean shouldGive(String playerName) {
		if(!Main.theClass.Storage.contains("Players." + playerName + ".Items")) {
			return true;
		} else {
			if(Main.theClass.Storage.getBoolean("Players." + playerName + ".Items") == true) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static void give(String playerName) {
		if(!Main.theClass.Storage.contains("Players." + playerName + ".Items")) {
			Main.theClass.Storage.set("Players." + playerName + ".Items", true);
			Main.theClass.Storage.saveConfig();
		} else {
			return;
		}
	}
	
}
