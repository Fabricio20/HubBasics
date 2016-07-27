package net.notfab.hubbasics.objects;

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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.nms.nbt.NBTItem;

import lombok.Getter;
import lombok.Setter;

public class CustomItem {

    @Getter @Setter private String name;
    @Getter @Setter private Material material;
    @Getter @Setter private List<String> lore;
    @Getter @Setter private ItemAction itemActionHandler;
    @Getter @Setter private Integer amount;
    @Getter @Setter private int metaData;
    @Getter @Setter private Boolean unbreakable;
    @Getter @Setter private String skullOwner;

    @Getter private Map<Enchantment, Integer> enchants = new HashMap<>();
    @Getter private Set<ItemFlag> itemFlags = new HashSet<>();
    @Getter private UUID uniqueId;

    public static abstract class ItemAction {
        public abstract Boolean onClick(Player player, ItemStack stack, InventoryAction action, Inventory inv);

        public abstract Boolean onInteract(Player player, ItemStack stack, Action action, Block block);

        public abstract Boolean onDrop(Player player, ItemStack stack);
    }

    public CustomItem(Material mat, Integer amount, int metaData) {
        this.material = mat;
        this.amount = amount;
        this.metaData = metaData;
        this.uniqueId = UUID.randomUUID();
    }

    @SuppressWarnings("deprecation")
    public CustomItem(ItemStack stack) {
        this.material = stack.getType();
        this.amount = stack.getAmount();
        this.metaData = stack.getData().getData();
        this.uniqueId = UUID.randomUUID();

        if(stack.hasItemMeta()) {
            ItemMeta meta = stack.getItemMeta();
            if(meta.hasDisplayName()) this.name = meta.getDisplayName();
            if(meta.hasLore()) this.lore = meta.getLore();
            if(meta.hasEnchants()) this.enchants = meta.getEnchants();
            if(meta.getItemFlags() != null) this.itemFlags = meta.getItemFlags();
            this.unbreakable = meta.spigot().isUnbreakable();
        }
    }

    public void addEnchantment(Enchantment enchantment, Integer level) {
        this.enchants.put(enchantment, level);
    }

    public void removeEnchantment(Enchantment enchantment) {
        this.enchants.remove(enchantment);
    }

    public void addItemFlag(ItemFlag flag) {
        this.itemFlags.add(flag);
    }

    public void removeItemFlag(ItemFlag flag) {
        this.itemFlags.remove(flag);
    }

    public ItemStack assembleItem() {
        ItemStack stack = new ItemStack(material, amount, (byte) metaData);
        ItemMeta meta = stack.getItemMeta();

        if(this.name != null) meta.setDisplayName(this.name);
        if(this.lore != null) meta.setLore(this.lore);
        if(this.itemFlags != null) this.itemFlags.parallelStream().forEach(meta::addItemFlags);
        if(this.unbreakable != null) meta.spigot().setUnbreakable(this.unbreakable);
        if(this.enchants != null) stack.addEnchantments(this.enchants);
        if(this.skullOwner != null) {
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwner(this.skullOwner);
            meta = skullMeta;
        }

        stack.setItemMeta(meta);

        NBTItem item = new NBTItem(stack);
        item.setString("UUID", this.uniqueId.toString());
        HubBasics.getInstance().getCustomItemManager().addItem(this.getUniqueId(), this);
        return item.getItem();
    }
}
