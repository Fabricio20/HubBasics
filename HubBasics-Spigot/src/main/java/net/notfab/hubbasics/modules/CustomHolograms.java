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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.json.JSONArray;
import org.json.JSONObject;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.objects.SimpleConfig;
import net.notfab.hubbasics.plugin.utils.HubBasicsFile;

public class CustomHolograms extends Module {
    private final String ARMORSTAND_TEXT = "text";
    private final String ARMORSTAND_UUID = "uuid";
    private final String ARMORSTAND_LINE = "line";

    private SimpleConfig file;
    private HubBasics pl;

    public CustomHolograms() {
        super(ModuleEnum.HOLOGRAMS);
        this.pl = HubBasics.getInstance();
        this.file = HubBasicsFile.HOLOGRAMS;
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    /**
     * @return All holograms stored in file
     */
    public Set<Integer> getHolograms() {
        if (file.getConfigurationSection("hologram") != null) {
            return file.getConfigurationSection("hologram").getKeys(false).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }

    /**
     * Check if a hologram with given ID exists
     *
     * @param hologram The ID
     * @return Exists
     */
    public boolean hologramExists(int hologram) {
        return this.file.getConfigurationSection("hologram." + hologram) != null;
    }

    /**
     * Create a new hologram at given location with given lines
     *
     * @param loc   The location
     * @param lines The lines
     * @return The ID of the created hologram
     */
    public int createHologram(Location loc, String... lines) {
        int index = 0;
        while (file.getConfigurationSection("hologram." + (index = index + 1)) != null) ;

        JSONObject object = new JSONObject();
        object.put("x", loc.getX());
        object.put("y", loc.getY());
        object.put("z", loc.getZ());
        object.put("w", loc.getWorld().getName());
        file.set("hologram." + index + ".location", object.toString());

        JSONArray array = spawnLines(loc, lines);
        file.set("hologram." + index + ".lines", array.toString());
        file.saveConfig();
        return index;
    }

    /**
     * Delete a hologram
     *
     * @param hologram The ID of the hologram
     */
    public void deleteHologram(int hologram) {
        deleteSpawnedLines(hologram);
        file.set("hologram." + hologram, null);
        file.saveConfig();
    }

    /**
     * Get the lines of given hologram
     *
     * @param hologram The ID of the hologram
     * @return JSONArray of lines
     */
    public JSONArray getLines(int hologram) {
        return new JSONArray(file.getString("hologram." + hologram + ".lines"));
    }

    /**
     * Get the lines of given hologram
     *
     * @param hologram The ID of the hologram
     * @return String array of lines
     */
    public String[] getLineStrings(int hologram) {
        JSONArray array = getLines(hologram);
        String[] strings = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            strings[i] = array.getJSONObject(i).getString(ARMORSTAND_TEXT);
        }

        return strings;
    }

    /**
     * Get the location of given hologram
     *
     * @param hologram The ID of the hologram
     * @return Location of hologram
     */
    public Location getLocation(int hologram) {
        JSONObject object = new JSONObject(file.getString("hologram." + hologram + ".location"));
        double x = object.getDouble("x");
        double y = object.getDouble("y");
        double z = object.getDouble("z");
        World world = Bukkit.getWorld(object.getString("w"));
        return new Location(world, x, y, z);
    }

    /**
     * Add lines to already existing hologram
     *
     * @param hologram The ID of the hologram
     * @param lines    The lines to add
     */
    public void addLines(int hologram, String... lines) {
        JSONArray array = getLines(hologram);
        String[] strings = new String[array.length() + lines.length];
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) stringList.add(array.getJSONObject(i).getString(ARMORSTAND_TEXT));
        Collections.addAll(stringList, lines);
        stringList.toArray(strings);

        deleteSpawnedLines(hologram);
        file.set("hologram." + hologram + ".lines", spawnLines(getLocation(hologram), strings).toString());
        file.saveConfig();
    }

    private JSONArray spawnLines(Location loc, String[] lines) {
        int lineIndex = 0;
        JSONArray array = new JSONArray();

        for (String str : lines) {
            ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', str));
            armorStand.setCustomNameVisible(true);
            armorStand.setRemoveWhenFarAway(false);
            loc.setY(loc.getY() - 0.25);

            JSONObject object = new JSONObject();
            object.put(ARMORSTAND_LINE, lineIndex);
            object.put(ARMORSTAND_TEXT, str);
            object.put(ARMORSTAND_UUID, armorStand.getUniqueId().toString());
            array.put(lineIndex, object);
            lineIndex++;
        }

        return array;
    }

    private void deleteSpawnedLines(int hologram) {
        JSONArray array = getLines(hologram);
        List<UUID> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            UUID uuid = UUID.fromString(object.getString(ARMORSTAND_UUID));
            list.add(uuid);
        }

        getLocation(hologram).getWorld().getEntities().stream().filter(entity -> list.contains(entity.getUniqueId())).forEach(Entity::remove);
    }

    /**
     * Reset an existing hologram
     *
     * @param hologram
     */
    public void resetLines(int hologram) {
        deleteSpawnedLines(hologram);
        JSONArray array = new JSONArray();
        file.set("hologram." + hologram + ".lines", array.toString());
        file.saveConfig();
    }
}
