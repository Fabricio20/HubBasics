package net.notfab.hubbasics.modules;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.jsoup.helper.Validate;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.utils.ItemUtils;

public class JoinItems extends Module {

    private List<ItemStack> items;

    public JoinItems() {
        super(ModuleEnum.JOIN_ITEMS);
        this.items = new LinkedList<>();
    }

    @Override
    public void onEnable() {
        List<Map<?, ?>> mapList = getConfig().getMapList(ConfigurationKey.JOIN_ITEMS_ITEMS);
        mapList.parallelStream().forEach(map -> this.addItem(ItemUtils.deserialize((Map<String, Object>) map).assembleItem()));
        HMessenger.sendDebugMessage("JoinItems Module > Deserialized " + this.items.size() + " items successfully.");
    }

    @Override
    public void onDisable() { }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!isEnabledInWorld(event.getPlayer().getWorld())) return;
        if (getBoolean(ConfigurationKey.JOIN_ITEMS_CLEAR_ON_CONNECT)) {
            event.getPlayer().getInventory().clear();
            event.getPlayer().updateInventory();
            this.items.stream().filter(item -> item != null).forEach(item -> event.getPlayer().getInventory().addItem(item));
            event.getPlayer().updateInventory();
        } else {
            required:
            for (ItemStack item : this.items) {
                inventory:
                for (ItemStack invItem : event.getPlayer().getInventory().getContents()) {
                    if (invItem == null) continue inventory;
                    ItemStack clone = new ItemStack(invItem);
                    clone.setAmount(item.getAmount());
                    if (item.equals(clone)) continue required;
                }

                event.getPlayer().getInventory().addItem(item);
            }
            event.getPlayer().updateInventory();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (!getBoolean(ConfigurationKey.JOIN_ITEMS_ADD_ON_DEATH)) return;
        if (!isEnabledInWorld(event.getPlayer().getWorld())) return;
        if (getBoolean(ConfigurationKey.JOIN_ITEMS_CLEAR_ON_CONNECT)) {
            event.getPlayer().getInventory().clear();
            event.getPlayer().updateInventory();
            event.getPlayer().getInventory().addItem(this.items.toArray(new ItemStack[this.items.size()]));
            event.getPlayer().updateInventory();
        } else {
            required:
            for (ItemStack item : this.items) {
                inventory:
                for (ItemStack invItem : event.getPlayer().getInventory().getContents()) {
                    if (invItem == null) continue inventory;
                    ItemStack clone = new ItemStack(invItem);
                    clone.setAmount(item.getAmount());
                    if (item.equals(clone)) continue required;
                }

                event.getPlayer().getInventory().addItem(item);
            }
            event.getPlayer().updateInventory();
        }
    }

    public void addItem(ItemStack item) {
        Validate.notNull(item, "Item can't be null");
        this.items.add(item);
    }
}
