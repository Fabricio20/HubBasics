package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.Messages;
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
 * File Created by Fabricio20 on 13/12/2017.
 */
public class HubBasicsCommand extends Command {

    public HubBasicsCommand() {
        super("hb");
        // -- Perms
        this.addRequiredPermission("hubbasics.admin");
        // -- Docs
        this.addUsage("/hb &areload", "Reloads the plugin");
        this.addUsage("/hb &aupdate", "Checks for updates");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length == 0) {
            HubBasics.getMessenger().send(player, getHelp());
        } else {
            if(args[0].equalsIgnoreCase("reload")) {
                // Perform reload
                HubBasics.getMessenger().send(player, Messages.get(player, "PLUGIN_RELOADED"));
            } else if(args[0].equalsIgnoreCase("update")) {
                // Update
            } else {
                // Not found
            }
        }
    }

}
