package net.notfab.hubbasics.spigot.abstracts.command;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public abstract class HUniversalCommand extends HCommand {
    public HUniversalCommand(String... names) {
        super(names);
    }

    public HUniversalCommand(Permission perm, String... names) {
        super(perm, names);
    }

    public void onCommand(Player player, String[] args) {
        onCommand((CommandSender) player, args);
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return onTabComplete((CommandSender) player, args);
    }
}
