package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.commands.HubBasicsCommand;
import net.notfab.hubbasics.spigot.commands.WarpCommand;
import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.Manager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
@SuppressWarnings("JavaReflectionMemberAccess")
public class CommandFramework extends Manager {

    private List<Command> commandList;

    public CommandFramework() {
        this.commandList = new CopyOnWriteArrayList<>();
        this.register(new HubBasicsCommand());
        this.register(new WarpCommand());
        Logger.info("[CommandFramework] Loaded " + commandList.size() + " commands.");
    }

    @Override
    public void onDisable() {
        this.commandList.forEach(this::unregister);
    }

    public void register(Command command) {
        if(command.getDescription() == null || command.getDescription().equals("")) {
            command.setDescription("A HubBasics command.");
        }
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            if(!field.isAccessible())
                field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            commandMap.register(command.getName(), command);
            this.commandList.add(command);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Logger.error("Error while registering command", ex);
        }
    }

    public void unregister(Command command) {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            if(!field.isAccessible())
                field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            command.unregister(commandMap);
            this.commandList.remove(command);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Logger.error("Error while unregistering command", ex);
        }
    }
}
