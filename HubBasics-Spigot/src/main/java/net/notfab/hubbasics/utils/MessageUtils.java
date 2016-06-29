package net.notfab.hubbasics.utils;

import org.bukkit.command.CommandSender;

public class MessageUtils {

    //TODO: static final string variables for common stuff like "You do not have permission" or "Command not available at the console"

    public static void sendMessage(CommandSender sender, String... msg) {
        sender.sendMessage(msg);
    }

    public static void sendError(CommandSender sender, String... msg) {
        sender.sendMessage(msg); //TODO Colors + "error"
    }

}
