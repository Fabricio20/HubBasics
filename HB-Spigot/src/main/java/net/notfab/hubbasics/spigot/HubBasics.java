package net.notfab.hubbasics.spigot;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import lombok.Getter;
import net.notfab.hubbasics.spigot.listeners.ItemListener;
import net.notfab.hubbasics.spigot.managers.*;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HubBasics extends JavaPlugin {

    @Getter private static HubBasics instance;

    @Getter private Logger loggerManager;
    @Getter private SimpleConfigManager configManager;
    @Getter private Messenger messenger;
    @Getter private CommandFramework commandFramework;
    @Getter private NMSVersion NMS;
    @Getter private ItemManager itemManager;
    @Getter private MenuManager menuManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loggerManager = new Logger(this);
        this.configManager = new SimpleConfigManager(this);
        this.messenger = new Messenger();
        this.commandFramework = new CommandFramework();
        this.NMS = new NMSVersion();
        this.itemManager = new ItemManager();
        this.menuManager = new MenuManager();

        Bukkit.getPluginManager().registerEvents(this.commandFramework, this);
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
