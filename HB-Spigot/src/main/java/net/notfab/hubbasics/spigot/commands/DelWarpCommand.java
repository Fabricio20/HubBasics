package net.notfab.hubbasics.spigot.commands;

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

import net.notfab.hubbasics.spigot.plugin.utils.HPermissions;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.abstracts.command.HPlayerCommand;
import net.notfab.hubbasics.spigot.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.spigot.modules.Warps;
import net.notfab.hubbasics.spigot.plugin.messages.HMessenger;
import net.notfab.hubbasics.spigot.plugin.messages.HubBasicsMessage;

public class DelWarpCommand extends HPlayerCommand {
    private Warps warps;

    public DelWarpCommand() {
        super(HPermissions.DELWARP_COMMAND, "delwarp");
        this.warps = (Warps) HubBasics.getInstance().getModuleManager().getModule(ModuleEnum.WARPS);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (this.warps == null) {
            HMessenger.sendErrorMessage(player, HubBasicsMessage.WARP_DISABLED_GLOBAL.getMessage());
            return;
        }

        if (args.length == 0) {
            HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.DELWARP_USAGE.getMessage());
        } else if (!this.warps.warpExists(args[0])) {
            HMessenger.sendErrorMessage(player, HubBasicsMessage.DELWARP_NOT_EXIST.getMessage(WordUtils.capitalizeFully(args[0], '_')));
        } else {
            this.warps.deleteWarp(args[0]);
            player.sendMessage(ChatColor.GREEN + HubBasicsMessage.DELWARP_SUCCESS.getMessage(WordUtils.capitalizeFully(args[0], '_')));
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return null;
    }
}
