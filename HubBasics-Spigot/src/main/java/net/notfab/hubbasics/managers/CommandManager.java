
package net.notfab.hubbasics.managers;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.commands.HatCommand;
import net.notfab.hubbasics.commands.HologramsCmd;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class CommandManager {

    @Getter private List<HCommand> commands;

    public CommandManager() {
        this.commands = new ArrayList<>();
        registerCommand(new HatCommand(), HubBasics.getInstance());
        registerCommand(new HologramsCmd(), HubBasics.getInstance());
    }

    /**
     * Register a command that is using the {@link HCommand} wrapper
     *
     * @param cmd    The command
     * @param plugin The plugin registering
     */
    public void registerCommand(HCommand cmd, JavaPlugin plugin) {
        if (cmd.getNames().length > 1) {
            List<String> names = new LinkedList<>(Arrays.asList(cmd.getNames()));
            PluginCommand pcmd = plugin.getCommand(names.get(0));
            names.remove(0);
            pcmd.setAliases(names);
            pcmd.setExecutor(cmd);
            pcmd.setTabCompleter(cmd);
        } else {
            PluginCommand command = plugin.getCommand(cmd.getNames()[0]);
            command.setExecutor(cmd);
            command.setTabCompleter(cmd);
        }
        this.commands.add(cmd);
    }
}
