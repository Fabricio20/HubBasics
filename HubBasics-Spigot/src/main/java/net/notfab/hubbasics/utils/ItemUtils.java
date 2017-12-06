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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.objects.CommandItem;
import net.notfab.hubbasics.objects.CustomItem;

public class ItemUtils {
    //TODO: Support for potions and colored leather armor

    /**
     * Item type ({@link Material} or ID:DAMAGE)
     */
    private final static String PATH_TYPE = "Type";
    /**
     * Amount ({@link Integer})
     */
    private final static String PATH_AMOUNT = "Amount";
    /**
     * Damage value ({@link Byte})
     */
    private final static String PATH_DAMAGE = "Damage";
    /**
     * Custom name ({@link String})
     */
    private final static String PATH_NAME = "Name";
    /**
     * Lore ({@link ArrayList} containing {@link String}s)
     */
    private final static String PATH_LORE = "Lore";
    /**
     * Owner of skull if item is skull
     */
    private final static String PATH_SKULL_OWNER = "SkullOwner";
    /**
     * List of enchantments ({@link Enchantment} or ID) and level
     */
    private final static String PATH_ENCHANTMENTS = "Enchantments";
    /**
     * Item is droppable ({@link Boolean})
     */
    private final static String PATH_DROPPABLE = "Droppable";
    /**
     * Commands subsection
     */
    private final static String PATH_SUBSECTION_COMMANDS = "Commands";

    /**
     * Command to be executed on click of item while in inventory menu
     */
    private final static String INTERNAL_SECTION_PATH_ON_INVENTORY_CLICK = "OnInventoryClick";
    /**
     * Command to be executed on right click while in world (Not in inventory menu)
     */
    private final static String INTERNAL_SECTION_PATH_ON_RIGHT_CLICK = "OnRightClick";
    /**
     * The actual command ({@link String})
     */
    private final static String INTERNAL_SECTION_PATH_COMMAND = "Command";
    /**
     * Whether or not the  console should execute the command ({@link Boolean})
     */
    private final static String INTERNAL_SECTION_PATH_USE_CONSOLE = "UseConsole";

    /* Methods */

    public static Map<String, Object> serialize(CustomItem item) {
        Map<String, Object>  result = new LinkedHashMap<>();
        result.put(PATH_TYPE, item.getMaterial().name());
        result.put(PATH_AMOUNT, item.getAmount());

        if (item.getDamage() != 0) result.put(PATH_DAMAGE, item.getDamage());
        if (item.getName() != null) result.put(PATH_NAME, item.getName().replace('&', '§'));
        if (item.getLore() != null) result.put(PATH_LORE, item.getLore().stream().map(str -> str.replace('&', '§')).collect(Collectors.toList()));
        if (item.getSkullOwner() != null) result.put(PATH_SKULL_OWNER, item.getSkullOwner());

        Map<String, Integer> enchants = new HashMap<>();
        item.getEnchants().entrySet().parallelStream().forEach(entry -> enchants.put(entry.getKey().getName(), entry.getValue()));
        if (enchants.size() > 0) result.put(PATH_ENCHANTMENTS, enchants);

        if (item instanceof CommandItem) {
            CommandItem configItem = (CommandItem) item;
            result.put(PATH_DROPPABLE, configItem.isDroppable());
            Map<String, Object> commands = new LinkedHashMap<>();
            if (configItem.getClickCommand() != null)
                commands.put(INTERNAL_SECTION_PATH_ON_INVENTORY_CLICK, ImmutableMap.<String, Object>builder()
                        .put(INTERNAL_SECTION_PATH_COMMAND, configItem.getClickCommand())
                        .put(INTERNAL_SECTION_PATH_USE_CONSOLE, configItem.isClickConsoleExecutor())
                        .build());
            if (configItem.getInteractCommand() != null)
                commands.put(INTERNAL_SECTION_PATH_ON_RIGHT_CLICK, ImmutableMap.<String, Object>builder()
                        .put(INTERNAL_SECTION_PATH_COMMAND, configItem.getInteractCommand())
                        .put(INTERNAL_SECTION_PATH_USE_CONSOLE, configItem.isInteractConsoleExecutor())
                        .build());
            if (commands.size() > 0) result.put(PATH_SUBSECTION_COMMANDS, commands);
        }

        return result;
    }

    public static CommandItem deserialize(Map<String, Object> data) {
        CommandItem item = new CommandItem();

        String type = String.valueOf(data.get(PATH_TYPE));
        if (type.matches("\\d+:\\d+") || type.matches("\\d+")) {
            if (!type.contains(":")) type += ":0";
            String[] itemInfo = type.split(":");

            try {
                item.setMaterial(Material.getMaterial(Integer.parseInt(itemInfo[0])));
                item.setDamage(Byte.parseByte(itemInfo[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Given item type isn't viable!");
            }
        } else {
            Material material = Material.getMaterial(type.toUpperCase());
            if (material != null) {
                item.setMaterial(material);
            } else {
                throw new IllegalArgumentException("Given item type isn't viable!");
            }
        }

        item.setAmount((int) data.get(PATH_AMOUNT));

        if (data.containsKey(PATH_DAMAGE)) item.setDamage((byte) data.get(PATH_DAMAGE));
        if (data.containsKey(PATH_NAME)) item.setName(ChatColor.translateAlternateColorCodes('&', (String) data.get(PATH_NAME)));
        if (data.containsKey(PATH_LORE)) item.setLore(((List<String>) data.get(PATH_LORE)).stream().map(str ->
                ChatColor.translateAlternateColorCodes('&', str)).collect(Collectors.toList()));
        if (data.containsKey(PATH_SKULL_OWNER)) item.setSkullOwner((String) data.get(PATH_SKULL_OWNER));
        if (data.containsKey(PATH_DROPPABLE)) item.setDroppable((Boolean) data.get(PATH_DROPPABLE));
        if (data.containsKey(PATH_SUBSECTION_COMMANDS)) {
            Map<String, Object> commandData = (Map<String, Object>) data.get(PATH_SUBSECTION_COMMANDS);
            if (commandData.containsKey(INTERNAL_SECTION_PATH_ON_INVENTORY_CLICK)) {
                Map<String, Object> inventoryCommandData = (Map<String, Object>) commandData.get(INTERNAL_SECTION_PATH_ON_INVENTORY_CLICK);
                String inventoryCommand = (String) inventoryCommandData.get(INTERNAL_SECTION_PATH_COMMAND);
                item.setClickCommand(inventoryCommand);
                if (inventoryCommandData.containsKey(INTERNAL_SECTION_PATH_USE_CONSOLE))
                    item.setClickConsoleExecutor((Boolean) inventoryCommandData.get(INTERNAL_SECTION_PATH_USE_CONSOLE));
            }

            if (commandData.containsKey(INTERNAL_SECTION_PATH_ON_RIGHT_CLICK)) {
                Map<String, Object> interactCommandData = (Map<String, Object>) commandData.get(INTERNAL_SECTION_PATH_ON_RIGHT_CLICK);
                String interactCommand = (String) interactCommandData.get(INTERNAL_SECTION_PATH_COMMAND);
                item.setInteractCommand(interactCommand);
                if (interactCommandData.containsKey(INTERNAL_SECTION_PATH_USE_CONSOLE))
                    item.setInteractConsoleExecutor((Boolean) interactCommandData.get(INTERNAL_SECTION_PATH_USE_CONSOLE));
            }
        }

        if (data.containsKey(PATH_ENCHANTMENTS)) {
            Map<String, String> map = (Map<String, String>) data.get(PATH_ENCHANTMENTS);
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();
                Enchantment enchantment = Enchantment.getByName(entry.getKey().toString());
                if(enchantment != null && entry.getValue() instanceof Integer) item.addEnchantment(enchantment, ((Integer)entry.getValue()).intValue());
            }
        }

        return item;
    }
}
