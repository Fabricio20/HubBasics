package net.notfab.hubbasics.abstracts.module;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.plugin.settings.HConfiguration;

import org.bukkit.World;
import org.bukkit.event.Listener;
import sun.security.ssl.HandshakeMessage;

import java.util.List;

public abstract class Module implements Listener {

    @Getter private final ModuleEnum moduleEnum;

    public Module(ModuleEnum moduleEnum) {
        this.moduleEnum = moduleEnum;
    }

    public void onEnableInternal() {
        HMessenger.sendDebugMessage("[ModuleManager] " + moduleEnum.name() + " Enabled");
        this.onEnable();
    }

    public void onDisableInternal() {
        HMessenger.sendDebugMessage("[ModuleManager] " + moduleEnum.name() + " Disabled");
        this.onDisable();
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public Boolean isInWorld(World world, ConfigurationKey configurationKey) {
        List<String> worlds = HubBasics.getInstance().getPluginConfiguration().getStringList(configurationKey);
        return worlds.contains(world.getName());
    }

    protected String getString(ConfigurationKey configurationKey) {
        return HubBasics.getInstance().getPluginConfiguration().getString(configurationKey);
    }

    protected Double getDouble(ConfigurationKey configurationKey) {
        return HubBasics.getInstance().getPluginConfiguration().getDouble(configurationKey);
    }

    protected Boolean getBoolean(ConfigurationKey configurationKey) {
        return HubBasics.getInstance().getPluginConfiguration().getBoolean(configurationKey);
    }

    protected HConfiguration getConfig() {
        return HubBasics.getInstance().getPluginConfiguration();
    }
}
