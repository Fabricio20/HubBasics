package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.commands.HologramsCommand;
import net.notfab.hubbasics.spigot.commands.HubBasicsCommand;
import net.notfab.hubbasics.spigot.commands.WarpCommand;
import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.Manager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommandFramework extends Manager {

    private List<Command> commandList;

    public CommandFramework() {
        this.commandList = new CopyOnWriteArrayList<>();
        this.register(new HubBasicsCommand());
        this.register(new WarpCommand());
        this.register(new HologramsCommand());
        Logger.info("[CommandFramework] Loaded " + commandList.size() + " command(s).");
    }

    @Override
    public void onDisable() {
        this.commandList.forEach(this::unregister);
    }

    public void register(Command command) {
        if (command.getDescription().equals("")) {
            command.setDescription("A HubBasics command.");
        }
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            if (!field.isAccessible())
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
            if (!field.isAccessible())
                field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            command.unregister(commandMap);
            this.commandList.remove(command);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Logger.error("Error while unregistering command", ex);
        }
    }

}