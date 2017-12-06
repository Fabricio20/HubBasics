package net.notfab.hubbasics.commands;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HUniversalCommand;
import net.notfab.hubbasics.plugin.utils.HPermissions;
import net.notfab.hubbasics.plugin.utils.HubBasicsFile;

public class HBReloadCommand extends HUniversalCommand {

    public HBReloadCommand() {
        super(HPermissions.RELOAD_COMMAND, "hbreload");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        HubBasicsFile.reloadConfigs();
        HubBasics.getInstance().onEnable();
        sender.sendMessage(ChatColor.GREEN + "Reloaded HubBasics configurations.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
