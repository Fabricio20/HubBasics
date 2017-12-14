package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.managers.Logger;
import org.bukkit.ChatColor;
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
public abstract class Command {

    @Getter private List<String> names;
    @Getter private List<String> requiredPermissions = new ArrayList<>();
    private List<String> help = new ArrayList<>();

    protected HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();
    protected Logger Logger = HubBasics.getLoggerManager();

    public Command(String... names) {
        this.names = Arrays.asList(names);
        this.help.add("&a=======[ Usage ]=======");
    }

    public abstract void onCommand(Player player, String[] args);

    protected void addRequiredPermission(String name) {
        this.requiredPermissions.add(name);
    }

    protected void addUsage(String usage, String description) {
        this.help.add(ChatColor.translateAlternateColorCodes('&', "&b" + usage));
        this.help.add(ChatColor.translateAlternateColorCodes('&', " &7&o" + description));
    }

    protected String[] getHelp() {
        return this.help.toArray(new String[this.help.size()]);
    }

}
