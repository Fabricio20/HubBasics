package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.entity.Player;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-14.
 */
public class WarpCommand extends Command {

    public WarpCommand() {
        super("hb-warp", "hbwarp");
        // -- Perms
        this.addPermission("hubbasics.admin.warp");
        // -- Docs
        this.addUsage("/hb-warp &alist", "Lists all existing warps");
        this.addUsage("/hb-warp &acreate &e<name>", "Creates a new warp");
        this.addUsage("/hb-warp &atp &e<name>", "Teleports to an existing warp");
        this.addUsage("/hb-warp &adelete &e<name>", "Deletes a warp");
    }

    @Override
    public void execute(Player player, String[] args) {
        if(args.length == 0) {
            HubBasics.getMessenger().send(player, getHelp());
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("list")) {

            } else {
                HubBasics.getMessenger().send(player, Messages.get(player, "UNKNOWN_SUBCOMMAND"));
            }
        } else {
            if(args[0].equalsIgnoreCase("create")) {
                String name = args[1];
                if(HubBasics.getLocationManager().get(name) != null) {
                    HubBasics.getMessenger().send(player, Messages.get(player, "WARP_EXISTS"));
                    return;
                }
                HubBasics.getLocationManager().create(name, player.getLocation());
                HubBasics.getMessenger().send(player, Messages.get(player, "WARP_CREATED"));
            } else if(args[0].equalsIgnoreCase("tp")) {
                String name = args[1];
                HLocation warp = HubBasics.getLocationManager().get(name);
                if(warp == null) {
                    HubBasics.getMessenger().send(player, Messages.get(player, "INVALID_WARP"));
                    return;
                }
                warp.teleport(player);
            } else if(args[0].equalsIgnoreCase("delete")) {
                String name = args[1];
                HLocation warp = HubBasics.getLocationManager().get(name);
                if(warp == null) {
                    HubBasics.getMessenger().send(player, Messages.get(player, "INVALID_WARP"));
                    return;
                }
                if(HubBasics.getLocationManager().delete(warp)) {
                    HubBasics.getMessenger().send(player, Messages.get(player, "WARP_DELETED"));
                } else {
                    HubBasics.getMessenger().send(player, Messages.get(player, "INTERNAL_ERROR"));
                }
            } else {
                HubBasics.getMessenger().send(player, Messages.get(player, "UNKNOWN_SUBCOMMAND"));
            }
        }
    }

}
