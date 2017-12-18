package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.managers.Logger;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Command extends BukkitCommand {

    @Getter private List<String> permissions = new ArrayList<>();
    private List<String> help = new ArrayList<>();

    protected HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();
    protected Logger Logger = HubBasics.getLoggerManager();

    public Command(String... names) {
        super(names[0]);
        if(names.length > 1)
            this.setAliases(Arrays.asList(names).subList(1, names.length - 1));
        this.help.add(ChatColor.translateAlternateColorCodes('&', "&9=======[ Usage ]======="));
    }

    protected void execute(CommandSender sender, String[] args) {}
    protected void execute(Player player, String[] args) {}
    protected void execute(ConsoleCommandSender ender, String[] args) {}

    @Override
    public boolean execute(CommandSender sender, String name, String[] args) {
        if(this.permissions.size() > 0) {
            for(String perm: this.permissions) {
                if(!sender.hasPermission(perm)) {
                    HubBasics.getMessenger().send(sender, Messages.get(sender, "NO_PERMISSION"));
                    return true;
                }
            }
        }
        try {
            if(sender instanceof Player) {
                this.execute((Player) sender, args);
            } else if(sender instanceof ConsoleCommandSender) { // Is commandblock a Console?
                this.execute((ConsoleCommandSender) sender, args);
            }
            this.execute(sender, args); // Always execute the "universal" one
        } catch (Exception ex) {
            Logger.error("Error while executing command (" + getName() + ")", ex);
        }
        return true;
    }

    protected void addPermission(String name) {
        this.permissions.add(name);
    }

    protected void addUsage(String usage, String description) {
        this.help.add(ChatColor.translateAlternateColorCodes('&', "&b" + usage));
        this.help.add(ChatColor.translateAlternateColorCodes('&', " &7&o" + description));
    }

    protected String[] getHelp() {
        return this.help.toArray(new String[this.help.size()]);
    }

}
