package net.notfab.hubbasics.plugin.settings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.SimpleConfig;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class HConfiguration {

    private HubBasics pl;
    private SimpleConfig config;

    private final String globalSetting = ".global";

    /**
     * YAML configuration wrapper with world configuration support.
     */
    public HConfiguration() {
        this.pl = HubBasics.getInstance();
        this.config = pl.getConfigManager().getNewConfig("config.yml");
    }

    /**
     * Iterates through all the {@link ConfigurationKey}s and checks if they are in the file. If not, it will set the value to the keys default value.
     */
    public void loadDefaults() {
        Arrays.stream(ConfigurationKey.values()).filter(configurationKey ->
                !this.config.contains(configurationKey.getPath())).forEach(key ->
                this.config.set(key.getPath(), key.getDefaultValue()));
    }

    private boolean hasWorldSpecificSetting(ConfigurationKey key, World world) {
        return key.isPerWorldAllowed() && this.contains(key.getPath() + "." + world.getName());
    }

    private String getGlobalPath(ConfigurationKey key) {
        return key.getPath() + (key.isPerWorldAllowed() ? this.globalSetting : "");
    }

    public Object get(String path) {
        return this.config.get(path);
    }

    public Object get(ConfigurationKey key) {
        return this.config.get(key.getPath());
    }

    public Object get(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.get(key.getPath() + "." + world.getName());
        } else {
            return this.config.get(this.getGlobalPath(key));
        }
    }

    public Object get(ConfigurationKey key, Object def) {
        return this.config.get(this.getGlobalPath(key), def);
    }

    public String getString(ConfigurationKey key) {
        return this.config.getString(this.getGlobalPath(key));
    }

    public String getString(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getString(key.getPath() + "." + world.getName());
        } else {
            return this.config.getString(this.getGlobalPath(key));
        }
    }

    public String getString(ConfigurationKey key, String def) {
        return this.config.getString(this.getGlobalPath(key), def);
    }

    public int getInt(ConfigurationKey key) {
        return this.config.getInt(this.getGlobalPath(key));
    }

    public int getInt(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getInt(key.getPath() + "." + world.getName());
        } else {
            return this.config.getInt(this.getGlobalPath(key));
        }
    }

    public int getInt(ConfigurationKey key, int def) {
        return this.config.getInt(this.getGlobalPath(key), def);
    }

    public boolean getBoolean(ConfigurationKey key) {
        return this.config.getBoolean(this.getGlobalPath(key));
    }

    public boolean getBoolean(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getBoolean(key.getPath() + "." + world.getName());
        } else {
            return this.config.getBoolean(this.getGlobalPath(key));
        }
    }

    public boolean getBoolean(ConfigurationKey key, boolean def) {
        return this.config.getBoolean(this.getGlobalPath(key), def);
    }

    public void createSection(ConfigurationKey key) {
        this.config.createSection(this.getGlobalPath(key));
    }

    public ConfigurationSection getConfigurationSection(ConfigurationKey key) {
        return this.config.getConfigurationSection(this.getGlobalPath(key));
    }

    public ConfigurationSectionHWrapper getWrappedConfigSection(ConfigurationKey key) {
        return new ConfigurationSectionHWrapper(this.getConfigurationSection(key));
    }

    public double getDouble(ConfigurationKey key) {
        return this.config.getDouble(key.getPath());
    }

    public double getDouble(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getDouble(key.getPath() + "." + world.getName());
        } else {
            return this.config.getDouble(this.getGlobalPath(key));
        }
    }

    public double getDouble(ConfigurationKey key, double def) {
        return this.config.getDouble(this.getGlobalPath(key), def);
    }

    public List<?> getList(ConfigurationKey key) {
        return this.config.getList(key.getPath());
    }

    public List<?> getList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getList(this.getGlobalPath(key));
        }
    }

    public List<?> getList(ConfigurationKey key, List<?> def) {
        return this.config.getList(this.getGlobalPath(key), def);
    }

    public List<String> getStringList(ConfigurationKey key) {
        return this.config.getStringList(key.getPath());
    }

    public List<String> getStringList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getStringList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getStringList(this.getGlobalPath(key));
        }
    }

    public List<Integer> getIntegerList(ConfigurationKey key) {
        return this.config.getIntegerList(key.getPath());
    }

    public List<Integer> getIntegerList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getIntegerList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getIntegerList(this.getGlobalPath(key));
        }
    }

    public List<Boolean> getBooleanList(ConfigurationKey key) {
        return this.config.getBooleanList(key.getPath());
    }

    public List<Boolean> getBooleanList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getBooleanList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getBooleanList(this.getGlobalPath(key));
        }
    }

    public List<Double> getDoubleList(ConfigurationKey key) {
        return this.config.getDoubleList(key.getPath());
    }

    public List<Double> getDoubleList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getDoubleList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getDoubleList(this.getGlobalPath(key));
        }
    }

    public List<Float> getFloatList(ConfigurationKey key) {
        return this.config.getFloatList(key.getPath());
    }

    public List<Float> getFloatList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getFloatList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getFloatList(this.getGlobalPath(key));
        }
    }

    public List<Long> getLongList(ConfigurationKey key) {
        return this.config.getLongList(key.getPath());
    }

    public List<Long> getLongList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getLongList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getLongList(this.getGlobalPath(key));
        }
    }

    public List<Byte> getByteList(ConfigurationKey key) {
        return this.config.getByteList(key.getPath());
    }

    public List<Byte> getByteList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getByteList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getByteList(this.getGlobalPath(key));
        }
    }

    public List<Character> getCharacterList(ConfigurationKey key) {
        return this.config.getCharacterList(key.getPath());
    }

    public List<Character> getCharacterList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getCharacterList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getCharacterList(this.getGlobalPath(key));
        }
    }

    public List<Short> getShortList(ConfigurationKey key) {
        return this.config.getShortList(key.getPath());
    }

    public List<Short> getShortList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getShortList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getShortList(this.getGlobalPath(key));
        }
    }

    public List<Map<?, ?>> getMapList(ConfigurationKey key) {
        return this.config.getMapList(key.getPath());
    }

    public List<Map<?, ?>> getMapList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.config.getMapList(key.getPath() + "." + world.getName());
        } else {
            return this.config.getMapList(this.getGlobalPath(key));
        }
    }

    public boolean contains(ConfigurationKey key) {
        return this.config.contains(key.getPath());
    }

    public boolean contains(String string) {
        return this.config.contains(string);
    }

    public boolean contains(ConfigurationKey key, World world) {
        return this.contains(key.getPath() + "." + world.getName());
    }

    public void removeKey(ConfigurationKey key) {
        this.config.removeKey(key.getPath());
    }

    public void removeKey(ConfigurationKey key, World world) {
        this.config.removeKey(key.getPath() + "." + world.getName());
    }

    public void set(ConfigurationKey key, Object value) {
        this.config.set(key.getPath(), value);
    }

    public void set(ConfigurationKey key, Object value, World world) {
        this.config.set(key.getPath() + "." + world.getName(), value);
    }

    public void set(ConfigurationKey key, Object value, String comment) {
        this.config.set(key.getPath(), value, comment);
    }

    public void set(ConfigurationKey key, Object value, String[] comment) {
        this.config.set(key.getPath(), value, comment);
    }

    public Set<String> getKeys() {
        return this.config.getKeys();
    }
}
