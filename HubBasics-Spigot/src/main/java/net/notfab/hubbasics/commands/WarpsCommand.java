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

import java.util.List;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HPlayerCommand;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.Warps;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.messages.HubBasicsMessage;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class WarpsCommand extends HPlayerCommand {
    private Warps warps;

    public WarpsCommand() {
        super(HPermissions.WARPS_COMMAND, "warps");
        this.warps = (Warps) HubBasics.getInstance().getModuleManager().getModule(ModuleEnum.WARPS);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (this.warps == null) {
            HMessenger.sendErrorMessage(player, HubBasicsMessage.WARP_DISABLED_GLOBAL.getMessage());
            return;
        }

        StringBuilder builder = new StringBuilder();
        this.warps.getWarps().keySet().forEach(str -> builder.append(ChatColor.DARK_GREEN + ", " + ChatColor.GREEN + str));
        String str = this.warps.getWarps().size() == 0 ? ChatColor.GREEN + "None" : builder.toString().substring(4);
        player.sendMessage(ChatColor.DARK_AQUA + HubBasicsMessage.WARPS_PREFIX.getMessage() + ": " + str);
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return null;
    }
}
