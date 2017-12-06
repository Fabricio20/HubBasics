package net.notfab.hubbasics.abstracts.command;

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

import com.google.common.collect.Lists;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import net.notfab.hubbasics.plugin.messages.HMessenger;

public abstract class HPlayerCommand extends HCommand {
    public HPlayerCommand(String... names) {
        super(names);
    }

    public HPlayerCommand(Permission perm, String... names) {
        super(perm, names);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        HMessenger.errorPlayersOnly(sender);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        HMessenger.errorPlayersOnly(sender);
        return Lists.newArrayList();
    }
}
