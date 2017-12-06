package net.notfab.hubbasics.plugin.settings;

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
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

public class ConfigurationSectionHWrapper {

    private ConfigurationSection config;

    /**
     * This class functions as a wrapper for ConfigurationSection, but it accepts {@link ConfigurationKey} as path
     *
     * @param config The ConfigurationSection
     */
    public ConfigurationSectionHWrapper(ConfigurationSection config) {
        this.config = config;
    }

    public Object get(String path) {
        return this.config.get(path);
    }

    public Object get(ConfigurationKey key) {
        return this.config.get(key.getPath());
    }

    public Object get(ConfigurationKey key, Object def) {
        return this.config.get(key.getPath(), def);
    }

    public String getString(ConfigurationKey key) {
        return this.config.getString(key.getPath());
    }

    public String getString(ConfigurationKey key, String def) {
        return this.config.getString(key.getPath(), def);
    }

    public int getInt(ConfigurationKey key) {
        return this.config.getInt(key.getPath());
    }

    public int getInt(ConfigurationKey key, int def) {
        return this.config.getInt(key.getPath(), def);
    }

    public boolean getBoolean(ConfigurationKey key) {
        return this.config.getBoolean(key.getPath());
    }

    public boolean getBoolean(ConfigurationKey key, boolean def) {
        return this.config.getBoolean(key.getPath(), def);
    }

    public void createSection(ConfigurationKey key) {
        this.config.createSection(key.getPath());
    }

    public ConfigurationSection getConfigurationSection(ConfigurationKey key) {
        return this.config.getConfigurationSection(key.getPath());
    }

    public double getDouble(ConfigurationKey key) {
        return this.config.getDouble(key.getPath());
    }

    public double getDouble(ConfigurationKey key, double def) {
        return this.config.getDouble(key.getPath(), def);
    }

    public List<?> getList(ConfigurationKey key) {
        return this.config.getList(key.getPath());
    }

    public List<?> getList(ConfigurationKey key, List<?> def) {
        return this.config.getList(key.getPath(), def);
    }

    public List<String> getStringList(ConfigurationKey key) {
        return this.config.getStringList(key.getPath());
    }

    public List<Integer> getIntegerList(ConfigurationKey key) {
        return this.config.getIntegerList(key.getPath());
    }

    public List<Boolean> getBooleanList(ConfigurationKey key) {
        return this.config.getBooleanList(key.getPath());
    }

    public List<Double> getDoubleList(ConfigurationKey key) {
        return this.config.getDoubleList(key.getPath());
    }

    public List<Float> getFloatList(ConfigurationKey key) {
        return this.config.getFloatList(key.getPath());
    }

    public List<Long> getLongList(ConfigurationKey key) {
        return this.config.getLongList(key.getPath());
    }

    public List<Byte> getByteList(ConfigurationKey key) {
        return this.config.getByteList(key.getPath());
    }

    public List<Character> getCharacterList(ConfigurationKey key) {
        return this.config.getCharacterList(key.getPath());
    }

    public List<Short> getShortList(ConfigurationKey key) {
        return this.config.getShortList(key.getPath());
    }

    public List<Map<?, ?>> getMapList(ConfigurationKey key) {
        return this.config.getMapList(key.getPath());
    }

    public boolean contains(ConfigurationKey key) {
        return this.config.contains(key.getPath());
    }

    public void removeKey(ConfigurationKey key) {
        this.config.set(key.getPath(), null);
    }

    public void set(ConfigurationKey key, Object value) {
        this.config.set(key.getPath(), value);
    }

    public void set(ConfigurationKey key, Object value, String comment) {
        this.config.set(key.getPath(), value);
    }

    public void set(ConfigurationKey key, Object value, String[] comment) {
        this.config.set(key.getPath(), value);
    }

    public Set<String> getKeys() {
        return this.config.getKeys(false);
    }
}
