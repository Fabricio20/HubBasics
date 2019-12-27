package net.notfab.hubbasics.spigot.utils;

import net.notfab.hubbasics.spigot.entities.depends.DependPlaceHolderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlaceHolderUtil {

    private static DependPlaceHolderAPI dependPlaceHolderAPI;

    static {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            dependPlaceHolderAPI = new DependPlaceHolderAPI();
        }
    }

    public static String replace(CommandSender sender, String text) {
        if (text == null) return null;

        text = text.replace("${Player.Name}", sender.getName());
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (dependPlaceHolderAPI != null) {
                text = dependPlaceHolderAPI.setPlaceHolders(player, text);
            }
            text = text.replace("${Player.DisplayName}", player.getDisplayName());
            text = text.replace("${Player.UUID}", player.getUniqueId().toString());
            text = text.replace("${Player.World}", player.getWorld().getName());
        } else {
            text = text.replace("${Player.DisplayName}", sender.getName());
            text = text.replace("${Player.UUID}", "CONSOLE");
            text = text.replace("${Player.World}", sender.getServer().getName());
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
