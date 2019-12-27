package net.notfab.hubbasics.spigot.managers;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Messenger {

    public void send(CommandSender sender, String... messages) {
        Arrays.asList(messages).forEach(message ->
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void send(World world, String... messages) {
        Arrays.asList(messages).forEach(message -> world.getPlayers().forEach(player ->
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message))));
    }

}