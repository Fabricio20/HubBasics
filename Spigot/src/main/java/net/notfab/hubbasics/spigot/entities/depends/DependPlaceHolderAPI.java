package net.notfab.hubbasics.spigot.entities.depends;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class DependPlaceHolderAPI {

    public String setPlaceHolders(Player player, String message) {
        return PlaceholderAPI.setPlaceholders(player, message);
    }

}
