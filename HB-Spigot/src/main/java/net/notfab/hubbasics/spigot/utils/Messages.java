package net.notfab.hubbasics.spigot.utils;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.objects.SimpleConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright (c) HubBasics 2018.
 * <p>
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 * <p>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 * <p>
 * File Created by Fabricio20 on 06/12/2017.
 */
public class Messages {

    public static String get(String name) {
        SimpleConfig config = HubBasics.getInstance().getConfigManager().getNewConfig("messages.yml");
        return config.getString(name, "<Internal Error - Unknown Message>");
    }

    public static String get(Player player, String name) {
        String message = get(name);
        message = message.replace("${World}", player.getWorld().getName());
        message = message.replace("${UUID}", player.getUniqueId().toString());
        return message;
    }

    public static String get(CommandSender sender, String name) {
        String message = get(name);
        message = message.replace("${Name}", sender.getName());
        return message;
    }

}
