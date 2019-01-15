package net.notfab.hubbasics.spigot.listeners;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.nms.nbt.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
@SuppressWarnings("Duplicates")
public class ItemListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onItemMove(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        if(currentItem == null || currentItem.getType() == Material.AIR) return;
        NBTItem nbtItem = new NBTItem(currentItem);
        if(!nbtItem.hasKey("HubBasics")) return;

        CustomItem item = HubBasics.getInstance().getItemManager().get(nbtItem.getString("HubBasics"));
        if(item == null) {
            currentItem.setType(Material.AIR); // Destroy old item
            return;
        }
        if(item.getRunOnInventory()) {
            item.onCommand((Player) event.getWhoClicked());
        }
        if(!item.getAllowMove())
            event.setCancelled(true); // Call setCancelled only when needed to not conflict with other plugins
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemDrop(PlayerDropItemEvent event) {
        ItemStack currentItem = event.getItemDrop().getItemStack();
        if(currentItem == null || currentItem.getType() == Material.AIR) return;
        NBTItem nbtItem = new NBTItem(currentItem);
        if(!nbtItem.hasKey("HubBasics")) return;

        CustomItem item = HubBasics.getInstance().getItemManager().get(nbtItem.getString("HubBasics"));
        if(item == null) {
            currentItem.setType(Material.AIR); // Destroy old item
            return;
        }
        if(!item.getAllowDrop())
            event.setCancelled(true); // Call setCancelled only when needed to not conflict with other plugins
    }

    @EventHandler(ignoreCancelled = true)
    public void onLeftClick(PlayerInteractEvent event) {
        if(!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        List<ItemStack> items = new ArrayList<>();
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        if(mainHand != null) items.add(mainHand);
        ItemStack offHand = event.getPlayer().getInventory().getItemInOffHand();
        if(offHand != null) items.add(offHand);

        items.stream().filter(item -> item.getType() != Material.AIR).forEach(itemStack -> {
            NBTItem nbtItem = new NBTItem(itemStack);
            if(!nbtItem.hasKey("HubBasics")) return;

            event.setCancelled(true);
            CustomItem item = HubBasics.getInstance().getItemManager().get(nbtItem.getString("HubBasics"));
            if(item == null) {
                itemStack.setType(Material.AIR); // Destroy old item
                return;
            }
            if(!item.getRunOnLeftClick()) return;

            if(mainHand == itemStack) {
                item.onCommand(event.getPlayer());
            } else if(offHand == itemStack && item.getRunOnOffhand()) {
                item.onCommand(event.getPlayer());
            }
        });
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        List<ItemStack> items = new ArrayList<>();
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        if(mainHand != null) items.add(mainHand);
        ItemStack offHand = event.getPlayer().getInventory().getItemInOffHand();
        if(offHand != null) items.add(offHand);

        items.stream().filter(item -> item.getType() != Material.AIR).forEach(itemStack -> {
            NBTItem nbtItem = new NBTItem(itemStack);
            if(!nbtItem.hasKey("HubBasics")) return;

            event.setCancelled(true);
            CustomItem item = HubBasics.getInstance().getItemManager().get(nbtItem.getString("HubBasics"));
            if(item == null) {
                itemStack.setType(Material.AIR); // Destroy old item
                return;
            }
            if(!item.getRunOnRightClick()) return;

            if(mainHand == itemStack) {
                item.onCommand(event.getPlayer());
            } else if(offHand == itemStack && item.getRunOnOffhand()) {
                item.onCommand(event.getPlayer());
            }
        });
    }

}
