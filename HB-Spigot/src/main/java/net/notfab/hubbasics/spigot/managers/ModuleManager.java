package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.modules.AntiVoid;
import net.notfab.hubbasics.spigot.modules.JoinItems;
import net.notfab.hubbasics.spigot.modules.KeepFood;
import net.notfab.hubbasics.spigot.modules.KeepHealth;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-14.
 */
public class ModuleManager extends Manager {

    private Map<EnumModules, Module> modules = new HashMap<>();

    public ModuleManager() {
        this.modules.put(EnumModules.JoinItems, new JoinItems());
        this.modules.put(EnumModules.KeepHealth, new KeepHealth());
        this.modules.put(EnumModules.KeepFood, new KeepFood());
        this.modules.put(EnumModules.AntiVoid, new AntiVoid());
    }

    public void onEnable() {
        this.modules.forEach((enumModule, module) -> {
            if(!module.getConfig().getBoolean("Enabled", true)) return;
            if(!HubBasics.getNMS().runningNewerThan(module.getMinimumVersion())) {
                Logger.warn("[ModuleManager] The " + enumModule.name() + " module requires at least version " +
                        module.getMinimumVersion() + ", which means it will not be enabled.");
            } else {
                module.onEnable();
                Bukkit.getPluginManager().registerEvents(module, HubBasics);
                Logger.debug("[ModuleManager] " + enumModule.name() + " is now enabled.");
            }
        });
    }

    @Override
    public void onDisable() {
        this.modules.forEach(((enumModules, module) ->  module.onDisable()));
        // Cannot unregister listeners, thanks Bukkit.
    }

}
