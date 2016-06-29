package net.notfab.hubbasics.utils;

import org.bukkit.command.CommandSender;

public class MessageUtils {

    public static void sendMessage(CommandSender sender, String... msg) {
        sender.sendMessage(msg);
    }

    public static void sendError(CommandSender sender, String... msg) {
        sender.sendMessage(msg); //TODO Colors + "error"
    }

}
