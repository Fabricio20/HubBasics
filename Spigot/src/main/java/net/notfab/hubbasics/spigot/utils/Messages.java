package net.notfab.hubbasics.spigot.utils;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.command.CommandSender;

public class Messages {

    public static String get(String name) {
        SimpleConfig config = HubBasics.getInstance().getConfigManager().getNewConfig("messages.yml");
        return config.getString(name, "<Internal Error - Unknown Message>");
    }

    public static String get(CommandSender sender, String name) {
        return PlaceHolderUtil.replace(sender, get(name));
    }

    public static String get(CommandSender sender, String name, String... args) {
        return PlaceHolderUtil.replace(sender, replaceArgs(get(name), args));
    }

    private static String replaceArgs(String message, String... args) {
        for (int i = 0; i < args.length; i++) {
            message = message.replace("{" + i + "}", args[i]);
        }
        return message;
    }

}
