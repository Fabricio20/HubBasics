package net.notfab.hubbasics.spigot.managers;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Arrays;

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
public class Messenger {

    public void send(Player player, String... messages) {
        Arrays.asList(messages).forEach(message -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void send(World world, String... messages) {
        Arrays.asList(messages).forEach(message -> world.getPlayers().forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', message))));
    }

}
