package net.notfab.hubbasics.spigot;

/*
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

import lombok.Getter;
import net.notfab.hubbasics.spigot.entities.Metrics;
import net.notfab.hubbasics.spigot.listeners.ItemListener;
import net.notfab.hubbasics.spigot.managers.*;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.spigot.simpleconfig.SimpleConfigManager;
import net.notfab.spigot.simpleconfig.spigot.SpigotConfigManager;
import okhttp3.OkHttpClient;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public class HubBasics extends JavaPlugin {

    @Getter private static HubBasics instance;

    @Getter private Logger loggerManager;
    @Getter private TaskManager taskManager;
    @Getter private SimpleConfigManager configManager;
    @Getter private ConfigHandler configHandler;
    @Getter private OkHttpClient httpClient;
    @Getter private Messenger messenger;
    @Getter private UpdateManager updateManager;
    @Getter private Metrics metrics;
    @Getter private CommandFramework commandFramework;
    @Getter private NMSVersion NMS;
    @Getter private ItemManager itemManager;
    @Getter private MenuManager menuManager;
    @Getter private LocationManager locationManager;
    @Getter private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        instance = this;
        this.loggerManager = new Logger(null);
        this.taskManager = new TaskManager();
        this.configManager = new SpigotConfigManager(this);
        this.configHandler = new ConfigHandler(this);
        Bukkit.getPluginManager().registerEvents(this.configHandler, this);
        this.httpClient = new OkHttpClient.Builder()
                .followSslRedirects(true)
                .connectTimeout(3, TimeUnit.SECONDS)
                .build();
        this.messenger = new Messenger();
        this.updateManager = new UpdateManager();
        this.metrics = new Metrics(this);
        this.commandFramework = new CommandFramework();
        this.NMS = new NMSVersion();
        this.itemManager = new ItemManager();
        this.menuManager = new MenuManager();
        this.locationManager = new LocationManager();
        this.moduleManager = new ModuleManager();

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}