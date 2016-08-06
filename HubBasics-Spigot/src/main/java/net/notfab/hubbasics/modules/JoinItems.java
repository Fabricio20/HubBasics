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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.utils.ItemUtils;

public class JoinItems extends Module {

    private List<ItemStack> items;

    public JoinItems() {
        super(ModuleEnum.JOIN_ITEMS);
        this.items = new ArrayList<>();
    }

    @Override
    public void onEnable() {
        List<Map<?, ?>> mapList = getConfig().getMapList(ConfigurationKey.JOIN_ITEMS_ITEMS);
        mapList.parallelStream().forEach(map -> this.items.add(ItemUtils.deserialize((Map<String, Object>) map)));
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

    public static List<Map<String, Object>> getDefaultItemSection() {
        ItemStack item1 = new ItemStack(Material.DIAMOND_SWORD);
        item1.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.RED + "This is just an example");
        item1.setItemMeta(meta1);

        ItemStack item2 = new ItemStack(Material.GOLD_AXE);
        item2.addEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GREEN + "Please edit the config.yml");
        item2.setItemMeta(meta2);

        ItemStack item3 = new ItemStack(Material.POTION);
        PotionMeta meta3 = (PotionMeta) item3.getItemMeta();
        meta3.setDisplayName(ChatColor.DARK_AQUA + "Items use the built in Bukkit ItemStack serialization!");
        meta3.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 1), true);
        meta3.setLore(ImmutableList.<String>builder().add(ChatColor.GRAY + "Yay, you can even add lore!").add(ChatColor.GREEN + "Make it fancy :)").build());
        item3.setItemMeta(meta3);

        return ImmutableList.<Map<String, Object>>builder().add(ItemUtils.serialize(item1), ItemUtils.serialize(item2), ItemUtils.serialize(item3)).build();
    }


}
