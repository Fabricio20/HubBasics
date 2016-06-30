package net.notfab.hubbasics.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.HCommand;
import net.notfab.hubbasics.settings.ConfigKeys;

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
	public void notifyAdmins(String... message) {
		System.out.println("[HubBasics] " + message);
		sendSelectiveBroadcast(HPermissions.MESSAGE_ADMIN, ChatColor.RED + "" + ChatColor.BOLD + "(HB Admin) " + ChatColor.GOLD + arrayToString(message));
	}


	public void sendCommandUsageMessage(CommandSender sender, HCommand command, String msg) {
		String format = ChatColor.BLUE + "" + ChatColor.ITALIC;
		String prefix = format + "Usage of command " + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + command.getNames()[0] + format + ": ";
		sender.sendMessage(prefix + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + msg);
	}

	public void sendCommandUsageMessage(CommandSender sender, String[] msg) {
		sender.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "Usage: ");

		for (String str : msg) {
			sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "  " + str);
		}
	}

	public void errorNoPerms(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
	}

	public void errorPlayersOnly(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "This command is limited to players only!");
	}

	public void errorPlayerNotFound(CommandSender sender, String player) {
		sender.sendMessage(ChatColor.RED + "Player " + ChatColor.DARK_RED + player + ChatColor.RED + " was not found.");
	}

	public void sendStackTrace(Exception exc) {
		exc.printStackTrace();
		this.sendDebugMessage("Printing stacktrace elements for caught @!" + exc.getClass().getName() + "@@...");
		this.sendDebugMessage("Message: @!" + exc.getMessage());
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
					this.sendDebugMessage("Stacktrace skipped @!" + skipped + "@@ non-HubBasics classes.");
				}

				this.sendDebugMessage("Stacktrace @!" + index + "@@: @!" + clazz + "@@ (@!" + lineNumber + "@@) -> @!" + method);
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
