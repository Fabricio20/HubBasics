package net.notfab.hubbasics.spigot.utils;

import org.bukkit.ChatColor;
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
 * File Created by Fabricio20 on 13/12/2017.
 */
public class PlaceHolderUtil {

    public static String replace(Player player, String text) {
        text = text.replace("${Player.Name}", player.getName());
        text = text.replace("${Player.DisplayName}", player.getDisplayName());
        text = text.replace("${Player.UUID}", player.getUniqueId().toString());
        text = text.replace("${Player.World}", player.getWorld().getName());
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
