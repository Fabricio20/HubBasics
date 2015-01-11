package me.Fabricio20.API;

import me.Fabricio20.Main;

public class BookAPI {
	
	public static boolean shouldGive(String playerName) {
		if(!Main.theClass.Storage.contains("Players." + playerName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void give(String playerName) {
		if(!Main.theClass.Storage.contains("Players." + playerName)) {
			Main.theClass.Storage.set("Players." + playerName, true);
			Main.theClass.Storage.saveConfig();
		} else {
			return;
		}
	}
	
}
