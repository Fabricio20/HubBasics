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

import java.util.List;

import org.bukkit.World;
import org.bukkit.event.Listener;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey.ModuleSetting;
import net.notfab.hubbasics.plugin.settings.HConfiguration;

public abstract class Module implements Listener {

    @Getter private final ModuleEnum moduleEnum;
    @Getter private final ConfigurationKey loadKey;
    @Getter private final ConfigurationKey enabledKey;

    public Module(ModuleEnum moduleEnum) {
        this.moduleEnum = moduleEnum;
        this.loadKey = ConfigurationKey.getKey(this.getModuleEnum(), ModuleSetting.LOAD);

        ConfigurationKey enabledList = ConfigurationKey.getKey(this.getModuleEnum(), ModuleSetting.ENABLED_WORLD_LIST);
        if (enabledList != null) this.enabledKey = enabledList;
        else this.enabledKey = ConfigurationKey.getKey(this.getModuleEnum(), ModuleSetting.ENABLED_GLOBAL);

        if (this.getEnabledKey() != null) {
            if (this.getEnabledKey().getModuleSetting() == ModuleSetting.ENABLED_WORLD_LIST && this.getLoadKey() == null) {
                HMessenger.sendDebugMessage("Detected module with world-list, but a load key wasn't found.");
            }
        }
    }

    public void onEnableInternal() {
        HMessenger.sendDebugMessage("[ModuleManager] " + moduleEnum.name() + " enabled");
        this.onEnable();
    }

    public void onDisableInternal() {
        HMessenger.sendDebugMessage("[ModuleManager] " + moduleEnum.name() + " disabled");
        this.onDisable();
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public Boolean isEnabledInWorld(World world) {
        if (this.getEnabledKey().getModuleSetting() != ModuleSetting.ENABLED_WORLD_LIST)
            throw new UnsupportedOperationException("Can't check if module is enabled in world without world-list");
        List<String> worlds = HubBasics.getInstance().getPluginConfiguration().getStringList(this.getEnabledKey());
        return worlds.contains(world.getName());
    }

    public Boolean isEnabled() {
        if (this.getEnabledKey().getModuleSetting() != ModuleSetting.ENABLED_GLOBAL)
            throw new UnsupportedOperationException("Can't check if module is enabled in world without global enabled-key");
        else return getBoolean(this.getLoadKey());
    }

    public Boolean performLoading() {
        if (this.getLoadKey() == null) return true;
        else return getBoolean(this.getLoadKey());
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
