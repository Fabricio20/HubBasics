package me.Fabricio20.BungeeCord.API;

import java.util.List;

import me.Fabricio20.BungeeCord.Main;

public class CommandAPI {
	
	public enum Commands {
		Alert, Friends, List, Lobby
	}
	
	public static Boolean isEnabled(Commands command) {
		if(command.equals(Commands.List)) {
			return Main.theClass.config.List_Enabled;
		} else if(command.equals(Commands.Alert)) {
			return Main.theClass.config.Alert_Enabled;
		} else if(command.equals(Commands.Friends)) {
			return Main.theClass.config.Friends_Enabled;
		} else if(command.equals(Commands.Lobby)) {
			return Main.theClass.config.Lobby_Enabled;
		} else {
			return false;
		}
	}

	public static String getPermission(Commands command) {
		if(command.equals(Commands.Alert)) {
			return Main.theClass.config.Alert_Permission;
		} else if(command.equals(Commands.Friends)) {
			return Main.theClass.config.Friends_Permission;
		} else if(command.equals(Commands.Lobby)) {
			return Main.theClass.config.Lobby_Permission;
		} else {
			return "";
		}
	}
	
	public static String getCommand(Commands command) {
		if(command.equals(Commands.Alert)) {
			return Main.theClass.config.Alert_Command;
		} else if(command.equals(Commands.Friends)) {
			return Main.theClass.config.Friends_Command;
		} else if(command.equals(Commands.Lobby)) {
			return Main.theClass.config.Lobby_Command;
		} else {
			return command.name();
		}
	}

	public static String[] getAliases(Commands command) {
		if(command.equals(Commands.Alert)) {
			List<String> list = Main.theClass.config.Alert_Aliases;
			String[] array = list.toArray(new String[list.size()]);
			return array;
		} else if(command.equals(Commands.Friends)) {
			List<String> list = Main.theClass.config.Friends_Aliases;
			String[] array = list.toArray(new String[list.size()]);
			return array;
		} else if(command.equals(Commands.Lobby)) {
			List<String> list = Main.theClass.config.Lobby_Aliases;
			String[] array = list.toArray(new String[list.size()]);
			return array;
		} else {
			return new String[]{""};
		}
	}
	
}
