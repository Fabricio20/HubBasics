package net.notfab.hubbasics.spigot.objects;

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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.notfab.hubbasics.spigot.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.nms.nbt.NBTItem;

import lombok.Getter;
import lombok.Setter;

public class CustomItem implements ConfigurationSerializable {

    @Getter @Setter private String name;
    @Getter @Setter private Material material;
    @Getter @Setter private List<String> lore;
    @Getter @Setter private ItemInteractionHandler itemInteractionHandler;
    @Getter @Setter private Integer amount;
    @Getter @Setter private byte damage;
    @Getter @Setter private Boolean unbreakable;
    @Getter @Setter private String skullOwner;

    @Getter private Map<Enchantment, Integer> enchants = new HashMap<>();
    @Getter private Set<ItemFlag> itemFlags = new HashSet<>();
    @Getter private UUID uniqueId;

    public static abstract class ItemInteractionHandler {
        public abstract Boolean onInventoryClick(Player player, ItemStack stack, InventoryAction action, Inventory inv);

        public abstract Boolean onInteract(Player player, ItemStack stack, Action action, Block block);

        public abstract Boolean onDrop(Player player, ItemStack stack);
    }

    public CustomItem(Material mat, Integer amount, byte damage) {
        this.material = mat;
        this.amount = amount;
        this.damage = damage;
        this.uniqueId = UUID.randomUUID();
    }

    @SuppressWarnings("deprecation")
    public CustomItem(ItemStack stack) {
        this.material = stack.getType();
        this.amount = stack.getAmount();
        this.damage = stack.getData().getData();
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
        ItemStack stack = new ItemStack(material, amount, damage);
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

    @Override
    public Map<String, Object> serialize() {
        return ItemUtils.serialize(this);
    }
}
