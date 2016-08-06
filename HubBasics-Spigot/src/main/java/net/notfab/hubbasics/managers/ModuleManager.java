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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.AdvancedMOTD;
import net.notfab.hubbasics.modules.AntiVoid;
import net.notfab.hubbasics.modules.AutomatedBroadcast;
import net.notfab.hubbasics.modules.BossBarMessages;
import net.notfab.hubbasics.modules.CommandOverride;
import net.notfab.hubbasics.modules.ConnectionMessages;
import net.notfab.hubbasics.modules.CustomHolograms;
import net.notfab.hubbasics.modules.DoubleJump;
import net.notfab.hubbasics.modules.FixedWeather;
import net.notfab.hubbasics.modules.JoinItems;
import net.notfab.hubbasics.modules.JoinTeleport;
import net.notfab.hubbasics.modules.JumpPads;
import net.notfab.hubbasics.modules.KeepFood;
import net.notfab.hubbasics.modules.KeepHealth;
import net.notfab.hubbasics.nms.NMSVersion;

public class ModuleManager {

    @Getter private HashMap<ModuleEnum, Module> moduleMap;
    @Getter private List<ModuleEnum> disabled;

    public ModuleManager() {
        this.moduleMap = new HashMap<>();
        this.disabled = new ArrayList<>();
    }

    public void onEnable() {
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
        registerModule(new JoinItems());
        registerModule(new BossBarMessages());

        registerListeners();

        if (this.disabled.size() > 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "HubBasics > Detected NMS version doesn't support the following module(s):");
            this.disabled.stream().forEach(module -> Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "  - " + WordUtils.capitalizeFully(module.name(), new char[] {'_'})));
        }

        this.moduleMap.values().parallelStream().filter(Module::performLoading).forEach(Module::onEnableInternal);
    }

    public void onDisable() {
        this.moduleMap.values().parallelStream().filter(Module::performLoading).forEach(Module::onDisableInternal);
    }

    private void registerListeners() {
        this.moduleMap.values().parallelStream().filter(Module::performLoading).forEach(module -> Bukkit.getPluginManager().registerEvents(module, HubBasics.getInstance()));
    }

    public Module getModule(ModuleEnum moduleEnum) {
        return this.moduleMap.get(moduleEnum);
    }

    public void registerModule(Module module) {
        if (module.getModuleEnum().getVersions() == null || module.getModuleEnum().getVersions().length == 0) {
            this.moduleMap.put(module.getModuleEnum(), module);
        } else {
            if (!Arrays.asList(module.getModuleEnum().getVersions()).contains(HubBasics.getInstance().getNmsVersion().getVersionString())) {
               this.disabled.add(module.getModuleEnum());
            } else {
                this.moduleMap.put(module.getModuleEnum(), module);
            }
        }
    }
}
