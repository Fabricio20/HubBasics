package me.Fabricio20.BungeeCord;

import java.util.ArrayList;

public class FixConfig {
	
	public static void Fix() {
		Main.theClass.WantsToBe.add("&aFriends: &9{who} &aWants To Be Your Friend!");
		Main.theClass.WantsToBe.add("&aUse: /friends accept {who} to accept");
		Main.theClass.WantsToBe.add("&aOr /friends deny {who} to deny.");
	}
	
	public static ArrayList<String> General_UnkownServer() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&cError: &7Server Not Found!");
		return array;
	}

	public static ArrayList<String> General_OnlyPlayers() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&cError: &7Only Players Can Use This Command!");
		return array;
	}

	public static ArrayList<String> Lobby_Teleporting() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("§9Proxy > §eJoining Lobby..");
		return array;
	}
	
	public static ArrayList<String> Alert_Usage() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("§7Usage: §e/alert <message>");
		return array;
	}
	
	public static ArrayList<String> Friends_NotFound() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&aFriends: &cThat player has never joined the server.");
		return array;
	}
	
	public static ArrayList<String> Friends_RequestRemoved() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&aFriends: &9Friend request denied!");
		return array;
	}
	
	public static ArrayList<String> Friends_RequestNotThere() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&aFriends: &9That player didn't ask to be your friend!");
		return array;
	}
	
	public static ArrayList<String> Friends_AlreadyAsked() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&aFriends: &9You already asked that player to be your friend!");
		return array;
	}
	
	public static ArrayList<String> Friends_HoverOnline() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&e{server} &9| &eClick to follow!");
		return array;
	}
	
	public static ArrayList<String> Friends_HoverOffline() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("&cOffline");
		return array;
	}
	
}
