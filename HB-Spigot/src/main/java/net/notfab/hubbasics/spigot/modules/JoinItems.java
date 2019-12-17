package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-14.
 */
@SuppressWarnings("Duplicates")
public class JoinItems extends Module {

    private List<String> worlds = new ArrayList<>();
    private Map<String, List<String>> items = new HashMap<>();
    private Map<String, Boolean> clearInventory = new HashMap<>();
    private Map<String, Boolean> worldChange = new HashMap<>();

    public JoinItems() {
        super(EnumModules.JoinItems, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {
        SimpleConfig config = getConfig();
        Bukkit.getWorlds().forEach(world -> {
            if(!config.contains(world.getName())) return;
            if(!(config.contains(world.getName() + ".Enabled") && config.getBoolean(world.getName() + ".Enabled"))) return;
            this.worlds.add(world.getName());
            this.clearInventory.put(world.getName(), config.getBoolean(world.getName() + ".ClearInventory", false));
            this.worldChange.put(world.getName(), config.getBoolean(world.getName() + ".WorldChange", true));
            if(config.contains(world.getName() + ".Items")) {
                List<String> items = new ArrayList<>();
                config.getStringList(world.getName() + ".Items").forEach(id -> {
                    CustomItem item = HubBasics.getItemManager().get(id);
                    if(item == null) return;
                    items.add(item.getId());
                });
                this.items.put(world.getName(), items);
            }
        });
    }

    @Override
    public void onDisable() {
        this.worlds.clear();
        this.items.clear();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!this.worlds.contains(event.getPlayer().getWorld().getName())) return;
        if(!this.items.containsKey(event.getPlayer().getWorld().getName())) return;
        if(this.clearInventory.get(event.getPlayer().getWorld().getName())) {
            event.getPlayer().getInventory().clear();
        }
        this.items.get(event.getPlayer().getWorld().getName()).forEach(id -> {
            CustomItem item = HubBasics.getItemManager().get(id);
            if(item == null) return;
            if(item.getPermission() != null && !event.getPlayer().hasPermission(item.getPermission())) return;
            if(item.getSlot() == null || item.getSlot() == -1) {
                event.getPlayer().getInventory().addItem(item.toItemStack(event.getPlayer()));
            } else {
                event.getPlayer().getInventory().setItem(item.getSlot(), item.toItemStack(event.getPlayer()));
            }
        });
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        if(!this.worlds.contains(event.getPlayer().getWorld().getName())) return;
        if(!this.items.containsKey(event.getPlayer().getWorld().getName())) return;
        if(!this.worldChange.get(event.getPlayer().getWorld().getName())) return;
        if(this.clearInventory.get(event.getPlayer().getWorld().getName())) {
            event.getPlayer().getInventory().clear();
        }
        this.items.get(event.getPlayer().getWorld().getName()).forEach(id -> {
            CustomItem item = HubBasics.getItemManager().get(id);
            if(item == null) return;
            if(item.getPermission() != null && !event.getPlayer().hasPermission(item.getPermission())) return;
            if(item.getSlot() == null || item.getSlot() == -1) {
                event.getPlayer().getInventory().addItem(item.toItemStack(event.getPlayer()));
            } else {
                event.getPlayer().getInventory().setItem(item.getSlot(), item.toItemStack(event.getPlayer()));
            }
        });
    }

}