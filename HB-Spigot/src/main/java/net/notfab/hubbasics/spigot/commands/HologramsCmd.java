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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.notfab.hubbasics.spigot.abstracts.command.HPlayerCommand;
import net.notfab.hubbasics.spigot.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.spigot.plugin.messages.HMessenger;
import net.notfab.hubbasics.spigot.plugin.messages.HubBasicsMessage;
import net.notfab.hubbasics.spigot.plugin.utils.HPermissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.modules.CustomHolograms;

public class HologramsCmd extends HPlayerCommand {

    private HubBasics pl;
    private CustomHolograms holograms;

    public HologramsCmd() {
        super(HPermissions.HOLOGRAMS_COMMAND, "holograms");
        this.pl = HubBasics.getInstance();
        this.holograms = (CustomHolograms) pl.getModuleManager().getModule(ModuleEnum.HOLOGRAMS);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            HMessenger.sendCommandUsageMessage(player,
                    HubBasicsMessage.HOLOGRAMS_USAGE_CREATE.getMessage(),
                    HubBasicsMessage.HOLOGRAMS_USAGE_RESET.getMessage(),
                    HubBasicsMessage.HOLOGRAMS_USAGE_ADDLINE.getMessage(),
                    HubBasicsMessage.HOLOGRAMS_USAGE_DELETE.getMessage(),
                    HubBasicsMessage.HOLOGRAMS_USAGE_LIST.getMessage());
        } else {
            switch (args[0].toUpperCase()) {
                case "CREATE":
                    if (args.length == 1) {
                        HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.HOLOGRAMS_USAGE_CREATE.getMessage());
                    } else {
                        String text = ChatColor.translateAlternateColorCodes('&', argsToString(args, 1));
                        String id = this.holograms.createHologram(player.getLocation(), text) + "";
                        String holoID = ChatColor.DARK_GREEN + id + ChatColor.GREEN;
                        player.sendMessage(ChatColor.GREEN + HubBasicsMessage.HOLOGRAMS_SUCCESS_CREATED.getMessage(holoID));
                    }
                    return;
                case "RESET":
                    if (args.length == 1) {
                        HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.HOLOGRAMS_USAGE_RESET.getMessage());
                    } else if (!args[1].matches("\\d{1,5}")) {
                        HMessenger.sendErrorMessage(player, ChatColor.RED + HubBasicsMessage.COMMAND_ERROR_NOTNUMBER.getMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED));
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            HMessenger.sendErrorMessage(player, ChatColor.RED + HubBasicsMessage.HOLOGRAMS_ERROR_NOTEXIST.getMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED));
                        } else {
                            this.holograms.resetLines(index);
                            String holoID = ChatColor.DARK_GREEN + "" + index + ChatColor.GREEN;
                            player.sendMessage(ChatColor.GREEN + HubBasicsMessage.HOLOGRAMS_SUCCESS_RESET.getMessage(holoID));
                        }
                    }
                    return;
                case "ADDLINE":
                    if (args.length < 3) {
                        HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.HOLOGRAMS_USAGE_ADDLINE.getMessage());
                    } else if (!args[1].matches("\\d{1,5}")) {
                        HMessenger.sendErrorMessage(player, ChatColor.RED + HubBasicsMessage.COMMAND_ERROR_NOTNUMBER.getMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED));
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            HMessenger.sendErrorMessage(player, ChatColor.RED + HubBasicsMessage.HOLOGRAMS_ERROR_NOTEXIST.getMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED));
                        } else {
                            this.holograms.addLines(index, ChatColor.translateAlternateColorCodes('&', argsToString(args, 2)));
                            String holoID = ChatColor.DARK_GREEN + "" + index + ChatColor.GREEN;
                            player.sendMessage(ChatColor.GREEN + HubBasicsMessage.HOLOGRAMS_SUCCESS_ADDLINE.getMessage(holoID));
                        }
                    }
                    return;
                case "DELETE":
                    if (args.length == 1) {
                        HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.HOLOGRAMS_USAGE_DELETE.getMessage());
                    } else if (!args[1].matches("\\d{1,5}")) {
                        HMessenger.sendErrorMessage(player, ChatColor.RED + HubBasicsMessage.COMMAND_ERROR_NOTNUMBER.getMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED));
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            HMessenger.sendErrorMessage(player, ChatColor.RED + HubBasicsMessage.HOLOGRAMS_ERROR_NOTEXIST.getMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED));
                        } else {
                            this.holograms.deleteHologram(index);
                            String holoID = ChatColor.DARK_GREEN + "" + index + ChatColor.GREEN;
                            player.sendMessage(ChatColor.GREEN + HubBasicsMessage.HOLOGRAMS_SUCCESS_DELETE.getMessage(holoID));
                        }
                    }
                    return;
                case "LIST":
                    Set<Integer> ids = this.holograms.getHolograms();
                    StringBuilder builder = new StringBuilder();
                    for (int id : ids) builder.append(ChatColor.GREEN + ", " + ChatColor.DARK_GREEN + id);
                    String list = builder.toString().length() == 0 ? ChatColor.GREEN + HubBasicsMessage.HOLOGRAMS_SUCCESS_LIST_EMPTY.getMessage() : builder.toString().substring(4) + ".";
                    player.sendMessage(ChatColor.GREEN + HubBasicsMessage.HOLOGRAMS_SUCCESS_LIST_PREFIX.getMessage() + ": " + list);
                    return;
                default:
                    HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.HOLOGRAMS_USAGE_CREATE.getMessage(),
                            HubBasicsMessage.HOLOGRAMS_USAGE_RESET.getMessage(),
                            HubBasicsMessage.HOLOGRAMS_USAGE_ADDLINE.getMessage(),
                            HubBasicsMessage.HOLOGRAMS_USAGE_DELETE.getMessage(),
                            HubBasicsMessage.HOLOGRAMS_USAGE_LIST.getMessage());
                    return;
            }
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("create", "reset", "addline", "delete", " list");
        } else {
            return null;
        }
    }
}
