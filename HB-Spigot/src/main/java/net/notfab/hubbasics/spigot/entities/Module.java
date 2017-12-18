package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.managers.Logger;
import net.notfab.hubbasics.spigot.objects.SimpleConfig;
import org.bukkit.event.Listener;

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
public abstract class Module implements Listener {

    public HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();
    public Logger Logger = HubBasics.getLoggerManager();
    @Getter private final EnumModules module;
    @Getter private final String minimumVersion;

    public Module(EnumModules module, String version) {
        this.module = module;
        this.minimumVersion = version;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public SimpleConfig getConfig() {
        return HubBasics.getConfigManager().getNewConfig("modules/" + this.module.name() + ".yml");
    }

}
