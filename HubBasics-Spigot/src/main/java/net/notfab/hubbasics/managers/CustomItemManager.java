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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    private Map<UUID, CustomItem> customItems = new HashMap<>();

    public void addItem(UUID uuid, CustomItem itemFactory) {
        this.customItems.put(uuid, itemFactory);
    }

    public void removeItem(UUID uid) {
        this.customItems.remove(uid);
    }
    

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getPlayer().getInventory().getItemInHand() != null) {
            if(e.getPlayer().getInventory().getItemInHand().getType() != Material.AIR) {
                ItemStack stack = e.getPlayer().getInventory().getItemInHand();
                NBTItem item = new NBTItem(stack);
                if(item.hasKey("UUID")) {
                    UUID uid = UUID.fromString(item.getString("UUID"));
                    CustomItem factory = this.customItems.get(uid);
                    e.setCancelled(factory.getItemActionHandler().onInteract(e.getPlayer(), e.getPlayer().getInventory().getItemInHand(), e.getAction(), e.getClickedBlock()));
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
                if(item.hasKey("UUID")) {
                    UUID uid = UUID.fromString(item.getString("UUID"));
                    CustomItem factory = this.customItems.get(uid);
                    event.setCancelled(factory.getItemActionHandler().onClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getAction(), event.getClickedInventory()));
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack() != null) {
            ItemStack stack = event.getItemDrop().getItemStack();
            NBTItem item = new NBTItem(stack);
            if(item.hasKey("UUID")) {
                UUID uid = UUID.fromString(item.getString("UUID"));
                CustomItem factory = this.customItems.get(uid);
                event.setCancelled(factory.getItemActionHandler().onDrop(event.getPlayer(), event.getItemDrop().getItemStack()));
            }
        }
    }

    @EventHandler
    public void onDespawn(ItemDespawnEvent e) {
        if(e.getEntity() != null) {
            ItemStack stack = e.getEntity().getItemStack();
            NBTItem item = new NBTItem(stack);
            if(item.hasKey("UUID")) {
                UUID uid = UUID.fromString(item.getString("UUID"));
                this.removeItem(uid);
            }
        }
    }
}
