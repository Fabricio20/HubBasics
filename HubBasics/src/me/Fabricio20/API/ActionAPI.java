package me.Fabricio20.API;

import org.bukkit.entity.Player;

import me.Fabricio20.methods.TitleManager;

public class ActionAPI {
	
	public static void sendTitle(Player player, String message) {
		TitleManager.sendTitle(player, "'" + message + "'");
	}
	
	public static void sendSubtitle(Player player, String message) {
		TitleManager.sendSubTitle(player, "'" + message + "'");
	}
	
	public static void sendAction(Player player, String message) {
		TitleManager.sendAction(player, message);
	}
	
}
