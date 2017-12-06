package net.notfab.hubbasics.objects;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.GraphicalMenus;
import net.notfab.hubbasics.modules.JoinItems;
import net.notfab.hubbasics.objects.CustomItem.ItemInteractionHandler;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.utils.HubBasicsFile;
import net.notfab.hubbasics.utils.ItemUtils;

import lombok.Getter;
import lombok.Setter;

public class Menu implements ConfigurationSerializable {
    @Getter private Inventory menuInventory;
    @Getter private String title;
    @Getter private int height;
    @Getter private CommandItem item;
    @Getter private Map<Integer, CustomItem> contents;
    @Getter @Setter private boolean automaticallyAdded;

    private final String PATH_TITLE = "Title";
    private final String PATH_HEIGHT = "Height";
    private final String PATH_ITEM = "Item";
    private final String PATH_CONTENTS = "Contents";
    private final String PATH_INVENTORY_ON_CONNECT = "AddToInventory";

    public Menu(Map<String, Object> data) {
        String invTitle = ChatColor.translateAlternateColorCodes('&', String.valueOf(data.get(PATH_TITLE)));
        this.title = invTitle;
        this.height = (int) data.get(PATH_HEIGHT);
        this.contents = new LinkedHashMap<>();
        int invSize = ((Integer) data.get(PATH_HEIGHT))*9;
        this.menuInventory = Bukkit.createInventory(null, invSize, invTitle);

        CommandItem item = ItemUtils.deserialize((Map<String, Object>) data.get(PATH_ITEM));
        item.setItemInteractionHandler(new ItemInteractionHandler() {
            @Override
            public Boolean onInventoryClick(Player player, ItemStack stack, InventoryAction action, Inventory inv) {
                return null;
            }

            @Override
            public Boolean onInteract(Player player, ItemStack stack, Action action, Block block) {
                player.openInventory(menuInventory);
                return true;
            }

            @Override
            public Boolean onDrop(Player player, ItemStack stack) {
                return true;
            }
        });
        this.item = item;

        Map<String, Object> contentData = (Map<String, Object>) data.get(PATH_CONTENTS);
        Iterator iterator = contentData.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
            Integer slotID = Integer.valueOf(entry.getKey());
            CustomItem slotItem = ItemUtils.deserialize((Map<String, Object>) entry.getValue());
            this.setItem(slotID, slotItem);
        }

        if (data.containsKey(PATH_INVENTORY_ON_CONNECT)) {
            this.automaticallyAdded = (boolean) data.get(PATH_INVENTORY_ON_CONNECT);
            if (this.isAutomaticallyAdded()) {
                ((JoinItems) HubBasics.getInstance().getModuleManager().getModule(ModuleEnum.JOIN_ITEMS)).addItem(this.getItem().assembleItem());
                HMessenger.sendDebugMessage("Menu is automatically added!");
            }
        } else {
            this.automaticallyAdded = false;
        }
    }

    public Menu(String title, int height, CommandItem item) {
        this.menuInventory = Bukkit.createInventory(null, height*9, title);
        this.contents = new LinkedHashMap<>();
        this.title = title;
        this.height = height;
        this.item = item;

        item.setItemInteractionHandler(new ItemInteractionHandler() {
            @Override
            public Boolean onInventoryClick(Player player, ItemStack stack, InventoryAction action, Inventory inv) {
                return null;
            }

            @Override
            public Boolean onInteract(Player player, ItemStack stack, Action action, Block block) {
                player.openInventory(menuInventory);
                return null;
            }

            @Override
            public Boolean onDrop(Player player, ItemStack stack) {
                return true;
            }
        });
    }

    public void setItem(int slot, CustomItem item) {
        Validate.notNull(item, "Item cannot be null!");
        this.menuInventory.setItem(slot, item.assembleItem());
        this.contents.put(slot, item);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(PATH_TITLE, this.getTitle());
        map.put(PATH_HEIGHT, this.getHeight());
        map.put(PATH_INVENTORY_ON_CONNECT, this.isAutomaticallyAdded());
        map.put(PATH_ITEM, this.getItem().serialize());

        Map<String, Object> contents = new LinkedHashMap<>();
        for (Entry<Integer, CustomItem> entry : this.getContents().entrySet()) contents.put(entry.getKey() + "", entry.getValue().serialize());
        map.put(PATH_CONTENTS, contents);

        return map;
    }
}
