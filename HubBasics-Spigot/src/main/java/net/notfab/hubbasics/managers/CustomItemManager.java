package net.notfab.hubbasics.managers;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sun.org.apache.xpath.internal.operations.Bool;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.notfab.hubbasics.nms.nbt.NBTItem;
import net.notfab.hubbasics.objects.CustomItem;


@SuppressWarnings("deprecation")
public class CustomItemManager implements Listener {

    private final String UUID_TAG = "UUID";
    private Map<UUID, CustomItem> customItems = new HashMap<>();

    public void addItem(UUID uuid, CustomItem itemFactory) {
        this.customItems.put(uuid, itemFactory);
    }

    public void removeItem(UUID uid) {
        this.customItems.remove(uid);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getPlayer().getInventory().getItemInHand() != null) {
            if(event.getPlayer().getInventory().getItemInHand().getType() != Material.AIR) {
                ItemStack stack = event.getPlayer().getInventory().getItemInHand();
                NBTItem item = new NBTItem(stack);
                if(item.hasKey(UUID_TAG)) {
                    UUID uid = UUID.fromString(item.getString(UUID_TAG));
                    CustomItem customItem = this.customItems.get(uid);
                    if (customItem == null) return;
                    Boolean bool = customItem.getItemInteractionHandler().onInteract(event.getPlayer(), event.getPlayer().getInventory().getItemInHand(), event.getAction(), event.getClickedBlock());
                    if (bool != null) event.setCancelled(bool);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() != null && event.getCurrentItem() != null) {
            if(event.getCurrentItem().getType() != Material.AIR) {
                ItemStack stack = event.getCurrentItem();
                NBTItem item = new NBTItem(stack);
                if(item.hasKey(UUID_TAG)) {
                    UUID uid = UUID.fromString(item.getString(UUID_TAG));
                    CustomItem customItem = this.customItems.get(uid);
                    if (customItem == null) return;
                    Boolean bool = customItem.getItemInteractionHandler().onInventoryClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getAction(), event.getClickedInventory());
                    if (bool != null) event.setCancelled(bool);
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack() != null) {
            ItemStack stack = event.getItemDrop().getItemStack();
            NBTItem item = new NBTItem(stack);
            if(item.hasKey(UUID_TAG)) {
                UUID uid = UUID.fromString(item.getString(UUID_TAG));
                CustomItem customItem = this.customItems.get(uid);
                if (customItem == null) return;
                Boolean bool = customItem.getItemInteractionHandler().onDrop(event.getPlayer(), event.getItemDrop().getItemStack());
                if (bool != null) event.setCancelled(bool);
            }
        }
    }

    @EventHandler
    public void onDespawn(ItemDespawnEvent e) {
        if(e.getEntity() != null) {
            ItemStack stack = e.getEntity().getItemStack();
            NBTItem item = new NBTItem(stack);
            if(item.hasKey(UUID_TAG)) {
                UUID uid = UUID.fromString(item.getString(UUID_TAG));
                this.removeItem(uid);
            }
        }
    }
}
