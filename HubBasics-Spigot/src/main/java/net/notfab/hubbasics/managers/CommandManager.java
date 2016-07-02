
package net.notfab.hubbasics.managers;

import lombok.Getter;
import lombok.Setter;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.plugin.messages.HMessenger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
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
    @Getter private CommandMap commandMap;

    public CommandManager() {
        this.commands = new ArrayList<>();
    }

    /**
     * Uses reflection and loads the current CommandMap from CraftServer
     * Method to be called in onEnable ONLY!
     */
    public void loadMap() {
        try {
            Field mapField = Class.forName("org.bukkit.craftbukkit." + HubBasics.getInstance().getServerVersion() + ".CraftServer").getDeclaredField("commandMap");
            mapField.setAccessible(true);
            this.commandMap = (CommandMap) mapField.get(Bukkit.getServer());
        } catch (NoSuchFieldException e) {
            HMessenger.printStackTrace(e);
        } catch (IllegalAccessException e) {
            HMessenger.printStackTrace(e);
        } catch (ClassNotFoundException e) {
            HMessenger.printStackTrace(e);
        }
    }

    /**
     * Make sure there is no reference left to previous CommandMap
     * Method to be called in onDisable ONLY!
     */
    public void unloadMap() {
        this.commandMap = null;
    }

    /**
     * Register a command that is using the {@link HCommand} wrapper
     *
     * @param cmd    The command
     * @param plugin The plugin registering
     */
    public void registerCommand(HCommand cmd, JavaPlugin plugin) {
        if (plugin.getCommand(cmd.getNames()[0]) != null) {
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
        } else if (cmd.getNames().length > 1) {
            List<String> names = new LinkedList<>(Arrays.asList(cmd.getNames()));
            UnregisteredCommand command = new UnregisteredCommand(names.get(0));
            this.commandMap.register(plugin.getName(), command);
            names.remove(0);
            command.setAliases(names);
            command.setExecutor(cmd);
            command.setTabCompleter(cmd);
        } else {
            UnregisteredCommand command = new UnregisteredCommand(cmd.getNames()[0]);
            command.setExecutor(cmd);
            command.setTabCompleter(cmd);
        }

        this.commands.add(cmd);
    }

    private class UnregisteredCommand extends Command {
        @Setter
        private CommandExecutor executor;
        @Setter
        private TabCompleter tabCompleter;

        protected UnregisteredCommand(String name) {
            super(name);
        }

        @Override
        public boolean execute(CommandSender commandSender, String s, String[] strings) {
            return executor == null ? false : executor.onCommand(commandSender, this, s, strings);
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
            return tabCompleter == null ? null : tabCompleter.onTabComplete(sender, this, alias, args);
        }
    }
}
