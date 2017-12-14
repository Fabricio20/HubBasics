package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

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
public class Menu {

    @Getter private final String id;

    @Getter @Setter private String name;
    @Getter @Setter private int size = 27;
    @Getter @Setter private String command;
    @Getter @Setter private String permission;
    @Getter @Setter private Sound sound;

    private List<String> items = new ArrayList<>();

    public Menu(String id) {
        this.id = id;
    }

    public void addItem(String id) {
        this.items.add(id);
    }

    public void open(Player player) {
        if(this.permission != null && !player.hasPermission(this.permission)) {
            HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "NO_PERMISSION"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, size, this.name);

        this.items.forEach(id -> {
            CustomItem item = HubBasics.getInstance().getItemManager().get(id);
            if(item == null) return;
            if(item.getPermission() != null && !player.hasPermission(item.getPermission())) return;
            if(item.getSlot() == -1 || item.getSlot() >= this.size) {
                inventory.addItem(item.toItemStack(player));
            } else {
                inventory.setItem(item.getSlot(), item.toItemStack(player));
            }
        });

        player.openInventory(inventory);
        if(this.sound != null)
            player.playSound(player.getLocation(), sound, 1, 1);
    }

}
