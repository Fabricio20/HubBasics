package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.utils.PlaceHolderUtil;
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
        return stack;
    }

}
