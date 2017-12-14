package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Messages;
import net.notfab.hubbasics.spigot.entities.Result;
import net.notfab.hubbasics.spigot.objects.SimpleConfig;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.util.*;

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
public class ItemManager extends Manager {

    private Map<String, CustomItem> items = new HashMap<>();

    public ItemManager() {
        this.onEnable();
    }

    @Override
    public void onDisable() {

    }

    public void onEnable() {
        this.items.clear();
        File folder = new File(HubBasics.getDataFolder(), "items/");
        if(!folder.exists()) return;
        File[] listFiles = folder.listFiles();
        if(listFiles == null) return;
        List<File> files = Arrays.asList(listFiles);
        files.forEach(file -> {
            SimpleConfig config = HubBasics.getConfigManager().getNewConfig("items/" + file.getName());
            Result result = this.read(config);
            if(!result.isSuccess()) {
                Logger.warn("Error while loading custom item: " + Messages.get(result.getExtra(0)));
            } else {
                CustomItem item = result.getExtra(0);
                this.items.put(item.getId(), item);
                Logger.debug("Loaded item " + item.getId());
            }
        });
        Logger.info("[ItemManager] Loaded " + this.items.size() + " items.");
    }

    public CustomItem get(String id) {
        return this.items.get(id);
    }

    private Result read(SimpleConfig config) {
        CustomItem item = new CustomItem(config.getName().replace(".yml", ""));
        if(config.contains("Material")) {
            Material material = FinderUtil.findOneMaterial(config.getString("Material"));
            if(material == null)
                return new Result(false, "INVALID_MATERIAL");
            item.setMaterial(material);
        }
        if(config.contains("Amount")) {
            item.setAmount(config.getInt("Amount"));
        }
        if(config.contains("Name")) {
            item.setName(ChatColor.translateAlternateColorCodes('&', config.getString("Name")));
        }
        if(config.contains("Description")) {
            List<String> description = new ArrayList<>();
            config.getStringList("Description").forEach(text -> description.add(ChatColor.translateAlternateColorCodes('&', text)));
            item.setDescription(description);
        }
        if(config.contains("Flags")) {
            List<ItemFlag> flags = new ArrayList<>();
            config.getStringList("Flags").forEach(text -> {
                try {
                    ItemFlag flag = ItemFlag.valueOf(text);
                    flags.add(flag);
                } catch (IllegalArgumentException ignored) {}
            });
            item.setItemFlags(flags);
        }
        if(config.contains("Enchantments")) {
            List<String> enchantments = config.getStringList("Enchantments");
            enchantments.forEach(text -> {
                try {
                    String name = text.split(":")[0];
                    Integer level = Integer.parseInt(text.split(":")[1]);
                    Enchantment enchantment = FinderUtil.findOneEnchantment(name);
                    if(enchantment == null)
                        return;
                    item.addEnchantment(enchantment, level);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ignored) {}
            });
        }
        if(config.contains("Unbreakable")) {
            item.setUnbreakable(config.getBoolean("Unbreakable"));
        }
        if(config.contains("Durability")) {
            item.setDurability(Integer.valueOf(config.getInt("Durability")).shortValue());
        }
        return new Result(item);
    }

}
