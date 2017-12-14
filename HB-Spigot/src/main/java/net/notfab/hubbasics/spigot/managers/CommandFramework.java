package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.commands.HubBasicsCommand;
import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class CommandFramework extends Manager implements Listener {

    private List<Command> commandList;

    public CommandFramework() {
        this.commandList = new ArrayList<>();
        this.addCommand(new HubBasicsCommand());
        Logger.info("[CommandFramework] Loaded " + commandList.size() + " commands.");
    }

    @Override
    public void onDisable() {
        this.commandList.clear();
    }

    public void addCommand(Command command) {
        this.commandList.add(command);
    }

    @EventHandler
    public void handleCommand(PlayerCommandPreprocessEvent event) {
        if(!event.getMessage().startsWith("/")) return;
        String[] split = event.getMessage().split("\\s+");
        String name = split[0].replaceFirst("/", "");
        String[] args = split.length == 1 ? new String[0] : Arrays.copyOfRange(split, 1, split.length);
        Player player = event.getPlayer();
        this.commandList.forEach(command -> {
            if(!command.getNames().contains(name.toLowerCase())) return;
            event.setCancelled(true);
            if(command.getRequiredPermissions().stream().filter(perm -> !player.hasPermission(perm)).collect(Collectors.toList()).size() > 0) {
                HubBasics.getMessenger().send(player, Messages.get(player, "NO_PERMISSION"));
                return;
            }
            command.onCommand(player, args);
        });
    }

}
