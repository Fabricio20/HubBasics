package net.notfab.hubbasics.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.settings.ConfigKeys;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class HMessenger {

	/**
	 * Sends the given message formatted as an error
	 *
	 * @param sender The receiver of the message
	 * @param msg    The message
	 */
	public static void sendErrorMessage(CommandSender sender, String... msg) {
		sender.sendMessage(ChatColor.RED + "An error occurred: " + arrayToString(msg));
	}

	/**
	 * Sends the given message to all players with the given permission
	 *
	 * @param permission The permission
	 * @param msg        The message
	 */
	public static void sendSelectiveBroadcast(Permission permission, String... msg) {
		Bukkit.getOnlinePlayers().parallelStream().filter(target -> target.hasPermission(permission)).forEach(target -> target.sendMessage(msg));
	}

	/**
	 * Sends the given message using the debug format to all players with the permission MESSAGE_DEBUG in @{@link HPermissions}
	 *
	 * @param message The message
	 */
	public static void sendDebugMessage(String message) {
		if (!HubBasics.getInstance().getConfig().getBoolean(ConfigKeys.ENABLE_DEBUG.getPath())) return;
		sendSelectiveBroadcast(HPermissions.MESSAGE_DEBUG, ChatColor.YELLOW + "" + ChatColor.ITALIC + "HubBasics debug >> ", ChatColor.GRAY + message);
	}

	/**
	 * Sends the given message using the admin alert format to  all players with the permission MESSAGE_ADMIN in @{@link HPermissions}
	 *
	 * @param message - The message
	 */
	public static void notifyAdmins(String... message) {
		System.out.println("[HubBasics] " + message);
		sendSelectiveBroadcast(HPermissions.MESSAGE_ADMIN, ChatColor.RED + "" + ChatColor.BOLD + "(HB Admin) " + ChatColor.GOLD + arrayToString(message));
	}


	public static void sendCommandUsageMessage(CommandSender sender, HCommand command, String msg) {
		String format = ChatColor.BLUE + "" + ChatColor.ITALIC;
		String prefix = format + "Usage of command " + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + command.getNames()[0] + format + ": ";
		sender.sendMessage(prefix + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + msg);
	}

	public static void sendCommandUsageMessage(CommandSender sender, String[] msg) {
		sender.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "Usage: ");

		for (String str : msg) {
			sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "  " + str);
		}
	}

	public static void errorNoPerms(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
	}

	public static void errorPlayersOnly(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "This command is limited to players only!");
	}

	public static void errorPlayerNotFound(CommandSender sender, String player) {
		sender.sendMessage(ChatColor.RED + "Player " + ChatColor.DARK_RED + player + ChatColor.RED + " was not found.");
	}

	public static void sendStackTrace(Exception exc) {
		exc.printStackTrace();
		sendDebugMessage("Printing stacktrace elements for caught @!" + exc.getClass().getName() + "@@...");
		sendDebugMessage("Message: @!" + exc.getMessage());
		int skipped = 0;
		int index = 0;
		for (StackTraceElement element : exc.getStackTrace()) {
			index++;
			String clazz = element.getClass().getName();
			if (!clazz.startsWith("net.notfab")) {
				skipped++;
				continue;
			} else {
				int lineNumber = element.getLineNumber();
				String method = element.getMethodName();
				if (skipped > 0) {
					sendDebugMessage("Stacktrace skipped @!" + skipped + "@@ non-HubBasics classes.");
				}

				sendDebugMessage("Stacktrace @!" + index + "@@: @!" + clazz + "@@ (@!" + lineNumber + "@@) -> @!" + method);
				skipped = 0;
			}
		}
	}

	private static String arrayToString(String... strings) {
		StringBuilder builder = new StringBuilder();
		Arrays.stream(strings).forEach(s -> builder.append(s + " "));
		return builder.toString().trim();
	}
}
