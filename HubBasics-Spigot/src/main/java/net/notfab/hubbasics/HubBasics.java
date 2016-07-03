package net.notfab.hubbasics;

import lombok.Getter;
import net.notfab.hubbasics.managers.CommandManager;
import net.notfab.hubbasics.managers.ModuleManager;
import net.notfab.hubbasics.managers.SimpleConfigManager;
import net.notfab.hubbasics.managers.UpdateManager;
import net.notfab.hubbasics.objects.MetricsLite;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.messages.MessageManager;
import net.notfab.hubbasics.plugin.settings.HConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class HubBasics extends JavaPlugin {

    @Getter private static HubBasics instance;

    @Getter private CommandManager commandManager;
    @Getter private HConfiguration pluginConfiguration;
    @Getter private SimpleConfigManager configManager;
    @Getter private MessageManager messageManager;
    @Getter private ModuleManager moduleManager;
    @Getter private String serverVersion;
    @Getter private MetricsLite metrics;
    @Getter private UpdateManager updateManager;

    @Override
    public void onEnable() {
        instance = this;
        String packageName = getServer().getClass().getPackage().getName();
        this.serverVersion = packageName.substring(packageName.lastIndexOf('.') + 1);
        this.configManager = new SimpleConfigManager(this);
        this.pluginConfiguration = new HConfiguration();
        this.messageManager = new MessageManager();

        this.getMessageManager().loadMessages();
        this.getPluginConfiguration().loadDefaults();

        this.moduleManager = new ModuleManager();

        try {
            metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException ex) {
            HMessenger.printStackTrace(ex);
        }

        this.updateManager = new UpdateManager();
        this.commandManager = new CommandManager();
    }

    @Override
    public void onDisable() {
        this.moduleManager.onDisable();
        instance = null;
    }

}
