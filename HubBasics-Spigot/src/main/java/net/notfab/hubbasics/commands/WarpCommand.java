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

import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HPlayerCommand;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.Warps;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.messages.HubBasicsMessage;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class WarpCommand extends HPlayerCommand {
    private Warps warps;

    public WarpCommand() {
        super(HPermissions.WARP_COMMAND, "warp");
        this.warps = (Warps) HubBasics.getInstance().getModuleManager().getModule(ModuleEnum.WARPS);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (this.warps == null) {
            HMessenger.sendErrorMessage(player, HubBasicsMessage.WARP_DISABLED_GLOBAL.getMessage());
            return;
        } else if (!this.warps.isEnabledInWorld(player.getWorld())) {
            HMessenger.sendErrorMessage(player, HubBasicsMessage.WARP_DISABLED_WORLD.getMessage());
            return;
        }

        if (args.length == 0) {
            HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.WARP_USAGE.getMessage());
        } else if (!this.warps.warpExists(args[0])) {
            HMessenger.sendErrorMessage(player, HubBasicsMessage.WARP_NOT_EXIST.getMessage(WordUtils.capitalizeFully(args[0], '_')));
        } else {
            player.teleport(this.warps.getWarp(args[0]));
            HMessenger.sendMessage(player, ChatColor.GREEN + HubBasicsMessage.WARP_SUCCESS.getMessage(WordUtils.capitalizeFully(args[0], '_')));
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return new ArrayList<>(this.warps.getWarps().keySet());
    }
}
