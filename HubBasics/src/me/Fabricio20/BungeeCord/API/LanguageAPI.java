package me.Fabricio20.BungeeCord.API;

import me.Fabricio20.BungeeCord.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class LanguageAPI {
	
	public static void sendGeneral_UnkownServer(CommandSender player) {
		for(String s: Main.theClass.language.General_UnkownServer) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendGeneral_OnlyPlayers(CommandSender player) {
		for(String s: Main.theClass.language.General_OnlyPlayers) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendLobby_Teleporting(CommandSender player) {
		for(String s: Main.theClass.language.Lobby_Teleporting) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendAlert_Usage(CommandSender player) {
		for(String s: Main.theClass.language.Alert_Usage) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendFriends_NotFound(CommandSender player) {
		for(String s: Main.theClass.language.Friends_NotFound) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendFriends_RequestRemoved(CommandSender player) {
		for(String s: Main.theClass.language.Friends_RequestRemoved) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendFriends_RequestNotThere(CommandSender player) {
		for(String s: Main.theClass.language.Friends_RequestNotThere) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}
	
	public static void sendFriends_AlreadyAsked(CommandSender player) {
		for(String s: Main.theClass.language.Friends_AlreadyAsked) {
			TextComponent base = new TextComponent(s.replace("{who}", player.getName()).replace("&", "§"));
			player.sendMessage(base);
		}
	}

	public static BaseComponent[] HoverOnline(String x) {
		String server = BungeeCord.getInstance().getPlayer(x).getServer().getInfo().getName();
		for(String s: Main.theClass.language.Friends_HoverOnline) {
			BaseComponent[] base = new ComponentBuilder(s.replace("{who}", x).replace("&", "§").replace("{server}", server)).create();
			return base;
		}
		return new ComponentBuilder("§cError While Building HoverOnline Message").create();
	}

	public static BaseComponent[] HoverOffline() {
		for(String s: Main.theClass.language.Friends_HoverOffline) {
			BaseComponent[] base = new ComponentBuilder(s.replace("&", "§")).create();
			return base;
		}
		return new ComponentBuilder("§cError While Building HoverOnline Message").create();
	}
	
}
