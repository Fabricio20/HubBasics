package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.nms.nbt.NBTItem;
import net.notfab.hubbasics.spigot.utils.Messages;
import net.notfab.hubbasics.spigot.utils.PlaceHolderUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CustomItem {

    @Getter private final String id;

    public CustomItem(String id) {
        this.id = id;
    }

    @Getter @Setter private String name = null;
    @Getter @Setter private Material material = Material.EGG;
    @Getter @Setter private int amount = 1;
    @Getter @Setter private List<String> description = new ArrayList<>();
    @Getter @Setter private Map<Enchantment, Integer> enchantments = new HashMap<>();
    @Getter @Setter private boolean unbreakable = false;
    @Getter @Setter private List<ItemFlag> itemFlags = new ArrayList<>();
    @Getter @Setter private short durability = 0;
    @Getter @Setter private String permission = null;

    @Getter @Setter private List<String> commands = new ArrayList<>();
    @Getter @Setter private Boolean allowDrop = true;
    @Getter @Setter private Boolean allowMove = true;
    @Getter @Setter private Integer slot = -1; // -1 is add instead of set

    @Getter @Setter private Boolean runOnInventory = false; // Run commands on Inventory click
    @Getter @Setter private Boolean runOnRightClick = false; // Run commands on Right click
    @Getter @Setter private Boolean runOnLeftClick = false; // Run commands on Left click
    @Getter @Setter private Boolean runOnOffhand = false; // Run commands on offhand?

    public void addEnchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
    }

    public ItemStack toItemStack(Player player) {
        ItemStack stack = new ItemStack(this.material);
        stack.setAmount(this.amount);

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(PlaceHolderUtil.replace(player, this.name));

        List<String> lore = new ArrayList<>();
        this.description.forEach(text -> lore.add(PlaceHolderUtil.replace(player, text)));
        meta.setLore(lore);

        meta.setUnbreakable(this.unbreakable);
        this.enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));
        this.itemFlags.forEach(meta::addItemFlags);

        stack.setItemMeta(meta);
        stack.setDurability(this.durability);


        NBTItem item = new NBTItem(stack);
        item.setString("HubBasics", this.getId());

        return item.getItem();
    }

    public void onCommand(Player player) {
        if(this.permission != null && !player.hasPermission(this.permission)) {
            HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "NO_PERMISSION"));
            return;
        }
        this.commands.forEach(rawCommand -> {
            String operator = rawCommand.contains(":") ? rawCommand.split(":")[0] : "nolmao";
            String command = rawCommand.contains(":") ? rawCommand.split(":")[1] : rawCommand;
            command = PlaceHolderUtil.replace(player, command);
            if(operator.equalsIgnoreCase("op")) {
                Boolean op = player.isOp();
                player.setOp(true);
                try {
                    player.chat("/" + command);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    player.setOp(op);
                }
            } else if(operator.equalsIgnoreCase("console")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            } else if(operator.equalsIgnoreCase("msg")) {
                HubBasics.getInstance().getMessenger().send(player, command);
            } else if(operator.equalsIgnoreCase("warp")) {
                //TODO
            } else if(operator.equalsIgnoreCase("server")) {
                //TODO
            } else if(operator.equalsIgnoreCase("open")) {
                Menu menu = HubBasics.getInstance().getMenuManager().get(command);
                if(menu == null)
                    HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "INVALID_MENU"));
                else
                    menu.open(player);
            } else if(operator.equalsIgnoreCase("item")) {
                //TODO
            } else {
                player.chat("/" + command);
            }
        });
    }

}
