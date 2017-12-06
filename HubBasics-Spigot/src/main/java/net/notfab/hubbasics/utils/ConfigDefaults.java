package net.notfab.hubbasics.utils;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.objects.CommandItem;
import net.notfab.hubbasics.objects.Menu;

public class ConfigDefaults {
    public static List<String> getWorlds() {
        return Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList());
    }

    public static List<Map<String, Object>> getJoinItemsDefault() {
        ItemStack itemStack1 = new ItemStack(Material.DIAMOND_SWORD);
        itemStack1.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        ItemMeta meta1 = itemStack1.getItemMeta();
        meta1.setDisplayName(ChatColor.RED + "This is just an example");
        itemStack1.setItemMeta(meta1);
        CommandItem item1 = new CommandItem(itemStack1);

        ItemStack itemStack2 = new ItemStack(Material.GOLD_AXE);
        itemStack2.addEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta2 = itemStack2.getItemMeta();
        meta2.setDisplayName(ChatColor.GREEN + "Please edit the config.yml");
        itemStack2.setItemMeta(meta2);
        CommandItem item2 = new CommandItem(itemStack2);
        item2.setInteractCommand("kill");

        ItemStack itemStack3 = new ItemStack(Material.POTION);
        PotionMeta meta3 = (PotionMeta) itemStack3.getItemMeta();
        meta3.setDisplayName(ChatColor.DARK_AQUA + "Items use our custom item serialization! NB: Still in BETA!");
        meta3.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 1), true);
        meta3.setLore(ImmutableList.<String>builder().add(ChatColor.GRAY + "Yay, you can even add lore!").add(ChatColor.GREEN + "Make it fancy :)").build());
        itemStack3.setItemMeta(meta3);
        CommandItem item3 = new CommandItem(itemStack3);
        item3.setClickCommand("say Hello! I am an example!");
        item3.setClickConsoleExecutor(true);

        return ImmutableList.<Map<String, Object>>builder().add(item1.serialize(), item2.serialize(), item3.serialize()).build();
    }

    public static List<Map<String, Object>> getGraphicalMenusDefault() {
        Menu menu = new Menu("§aThis is another example", 5, new CommandItem(new ItemStack(Material.ANVIL)));

        CommandItem item1 = new CommandItem(new ItemStack(Material.GOLD_BLOCK));
        item1.setName("§cYou can add as many as you want!");
        item1.setClickCommand("me just tested the cool new HubBasics menu API!");
        menu.setItem(14, item1);

        CommandItem item2 = new CommandItem(new ItemStack(Material.DIAMOND_BLOCK));
        item2.setName("§cAll items use the same syntax!");
        item2.setClickCommand("kill");
        menu.setItem(10, item2);

        CommandItem item3 = new CommandItem(new ItemStack(Material.IRON_BLOCK));
        item3.setName("§eWanna create a 100% custom server menu?");
        item3.setClickCommand("server SERVER_NAME_HERE");
        menu.setItem(18, item3);

        CommandItem item4 = new CommandItem(new ItemStack(Material.EMERALD_BLOCK));
        item4.setName("&3This system is still in BETA!");
        item4.setLore(ImmutableList.<String>builder().add("§fPlease report bugs quickly so we can fix them!").build());
        item4.setClickCommand("me needs to edit the config file :)");
        menu.setItem(22, item4);

        return ImmutableList.<Map<String, Object>>builder().add(menu.serialize()).build();
    }
}
