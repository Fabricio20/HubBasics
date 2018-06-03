package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.Bukkit;
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
        this.addPermission("hubbasics.admin");
        // -- Docs
        this.setDescription("HubBasics' main command");
        this.addUsage("/hb &areload", "Reloads the plugin");
        this.addUsage("/hb &aupdate", "Checks for updates");
        this.addUsage("/hb &aitem &e<name>", "Spawns the item with the given name");
    }

    @Override
    protected void execute(Player player, String[] args) {
        if(args.length == 0) {
            HubBasics.getMessenger().send(player, getHelp());
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                // Perform reload
                HubBasics.getModuleManager().onDisable();
                HubBasics.getConfigManager().reload();
                HubBasics.getItemManager().onDisable();
                HubBasics.getItemManager().onEnable();
                HubBasics.getModuleManager().onEnable();
                HubBasics.getMessenger().send(player, Messages.get(player, "PLUGIN_RELOADED"));
            } else if(args[0].equalsIgnoreCase("update")) {
                HubBasics.getUpdateManager().run();
                if(HubBasics.getUpdateManager().isUpdateAvailable()) {
                    Bukkit.getOnlinePlayers().stream()
                            .filter(p -> p.hasPermission("hubbasics.update") || p.hasPermission("hubbasics.admin"))
                            .forEach(p -> {
                                HubBasics.getMessenger().send(p, "&9[&aHubBasics&9] &eThere is an update available.");
                                HubBasics.getMessenger()
                                        .send(p, "&9[&aHubBasics&9] &eCurrent: &a" +
                                                HubBasics.getUpdateManager().getBuild() +
                                                " &9/ &eLatest: &a" + HubBasics.getUpdateManager().getLatest());
                            });
                } else {
                    Bukkit.getOnlinePlayers().stream()
                            .filter(p -> p.hasPermission("hubbasics.update") || p.hasPermission("hubbasics.admin"))
                            .forEach(p -> {
                                HubBasics.getMessenger().send(p, "&9[&aHubBasics&9] &eNo updates found.");
                            });
                }
            } else {
                HubBasics.getMessenger().send(player, Messages.get(player, "UNKNOWN_SUBCOMMAND"));
            }
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("item")) {
                String name = args[1];
                CustomItem item = HubBasics.getItemManager().get(name);
                if(item == null) {
                    HubBasics.getMessenger().send(player, Messages.get(player, "ITEM_NOT_FOUND"));
                    return;
                }
                player.getInventory().addItem(item.toItemStack(player));
            } else {
                HubBasics.getMessenger().send(player, Messages.get(player, "UNKNOWN_SUBCOMMAND"));
            }
        }
    }

}
