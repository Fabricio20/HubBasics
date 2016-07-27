package net.notfab.hubbasics.managers;

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

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.*;
import net.notfab.hubbasics.nms.NMSVersion;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Iterator;

public class ModuleManager {

    @Getter private HashMap<ModuleEnum, Module> moduleMap = new HashMap<>();

    public ModuleManager() {
        registerModule(new DoubleJump());
        registerModule(new JumpPads());
        registerModule(new AntiVoid());
        registerModule(new FixedWeather());
        registerModule(new KeepFood());
        registerModule(new KeepHealth());
        registerModule(new ConnectionMessages());
        registerModule(new AdvancedMOTD());
        registerModule(new CommandOverride());
        registerModule(new CustomHolograms());
        registerModule(new AutomatedBroadcast());
        registerModule(new JoinTeleport());

        if (HubBasics.getInstance().getNmsVersion().runningNewerThan(NMSVersion.V1_9_R1)) {
            registerModule(new BossBarMessages());
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "NMS version earlier than 1.9 detected, this means the following modules WILL NOT WORK;");
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "  - Boss bar messages");
        }

        registerListeners();
        onEnable();
    }

    public void onEnable() {
        this.moduleMap.values().forEach(Module::onEnableInternal);
    }

    public void onDisable() {
        this.moduleMap.values().forEach(Module::onDisableInternal);
    }

    private void registerListeners() {
        this.moduleMap.values().forEach(module -> Bukkit.getPluginManager().registerEvents(module, HubBasics.getInstance()));
    }

    public Module getModule(ModuleEnum moduleEnum) {
        return this.moduleMap.get(moduleEnum);
    }

    public void registerModule(Module module) {
        this.moduleMap.put(module.getModuleEnum(), module);
    }
}
