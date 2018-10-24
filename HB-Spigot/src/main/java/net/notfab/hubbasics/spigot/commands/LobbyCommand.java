package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.modules.Lobby;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
 * File Created by Fabricio20 on 24/10/2018.
 */
public class LobbyCommand extends Command {

    public LobbyCommand() {
        super("hub", "lobby");
        // -- Perms
        this.addPermission("hubbasics.command.lobby");
        // -- Docs
        this.setDescription("Teleports tou to the lobby.");
        this.addUsage("/lobby", "Teleports you to the lobby");
    }

    @Override
    protected void execute(Player player, String[] args) {
        Location location = ((Lobby) HubBasics.getModuleManager()
                .getModule(EnumModules.Lobby)).getLocation();
        player.teleport(location);
    }

}
