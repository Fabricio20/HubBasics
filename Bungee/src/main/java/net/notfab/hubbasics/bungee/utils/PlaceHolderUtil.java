package net.notfab.hubbasics.bungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlaceHolderUtil {

    public static String replace(ProxiedPlayer player, String text) {
        text = text.replace("${Player.Name}", player.getName());
        text = text.replace("${Player.DisplayName}", player.getDisplayName());
        text = text.replace("${Player.UUID}", player.getUniqueId().toString());
        text = text.replace("${Player.Server}", player.getServer().getInfo().getName());
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}