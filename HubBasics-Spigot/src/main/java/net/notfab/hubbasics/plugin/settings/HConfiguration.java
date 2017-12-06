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

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.CommandItem;
import net.notfab.hubbasics.objects.SimpleConfig;
import net.notfab.hubbasics.plugin.utils.HubBasicsFile;
import net.notfab.hubbasics.utils.ItemUtils;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

public class HConfiguration {

    private HubBasics pl;
    @Getter private SimpleConfig rawConfig;

    private final String globalSetting = ".default";

    /*
     * TODO: Improve usage of built-in Bukkit serialization API
     */

    /**
     * YAML configuration wrapper with world configuration support.
     */
    public HConfiguration() {
        this.pl = HubBasics.getInstance();
        this.rawConfig = HubBasicsFile.CONFIGURATION;
    }

    /**
     * Iterates through all the {@link ConfigurationKey}s and checks if they are in the file. If not, it will set the value to the keys default value.
     */
    public void loadConfig() {
        Arrays.stream(ConfigurationKey.values()).filter(configurationKey ->
                !this.rawConfig.contains(configurationKey.getPath())).forEach(key ->
                this.rawConfig.set(key.getPath(), key.getDefaultValue()));
        this.rawConfig.saveConfig();
    }

    /* Internal */

    private boolean hasWorldSpecificSetting(ConfigurationKey key, World world) {
        return key.isPerWorldAllowed() && this.contains(key.getPath() + "." + world.getName());
    }

    private String getGlobalPath(ConfigurationKey key) {
        return key.getPath() + (key.isPerWorldAllowed() ? this.globalSetting : "");
    }

    /* Public */

    public void saveConfig() {
        this.rawConfig.saveConfig();
    }

    public Object get(String path) {
        return this.rawConfig.get(path);
    }

    public Object get(ConfigurationKey key) {
        return this.rawConfig.get(key.getPath());
    }

    public Object get(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.get(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.get(this.getGlobalPath(key));
        }
    }

    public Object get(ConfigurationKey key, Object def) {
        return this.rawConfig.get(this.getGlobalPath(key), def);
    }

    /* HubBasics objects */

    public CommandItem getConfigurationItem(ConfigurationKey key) {
        return ItemUtils.deserialize(this.getConfigurationSection(key).getValues(true));
    }

    public List<CommandItem> getConfigurationItemList(ConfigurationKey key) {
        return this.getMapList(key).parallelStream().map(map -> ItemUtils.deserialize((Map<String, Object>) map)).collect(Collectors.toList());
    }

    /* Java/Bukkit objects */

    public String getString(ConfigurationKey key) {
        return this.rawConfig.getString(this.getGlobalPath(key));
    }

    public String getColoredString(ConfigurationKey key) {
        return ChatColor.translateAlternateColorCodes('&', this.rawConfig.getString(this.getGlobalPath(key)));
    }

    public String getString(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getString(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getString(this.getGlobalPath(key));
        }
    }

    public String getString(ConfigurationKey key, String def) {
        return this.rawConfig.getString(this.getGlobalPath(key), def);
    }

    public int getInt(ConfigurationKey key) {
        return this.rawConfig.getInt(this.getGlobalPath(key));
    }

    public int getInt(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getInt(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getInt(this.getGlobalPath(key));
        }
    }

    public int getInt(ConfigurationKey key, int def) {
        return this.rawConfig.getInt(this.getGlobalPath(key), def);
    }

    public boolean getBoolean(ConfigurationKey key) {
        return this.rawConfig.getBoolean(this.getGlobalPath(key));
    }

    public boolean getBoolean(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getBoolean(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getBoolean(this.getGlobalPath(key));
        }
    }

    public boolean getBoolean(ConfigurationKey key, boolean def) {
        return this.rawConfig.getBoolean(this.getGlobalPath(key), def);
    }

    public void createSection(ConfigurationKey key) {
        this.rawConfig.createSection(this.getGlobalPath(key));
    }

    public ConfigurationSection getConfigurationSection(ConfigurationKey key) {
        return this.rawConfig.getConfigurationSection(this.getGlobalPath(key));
    }

    public ConfigurationSectionHWrapper getWrappedConfigSection(ConfigurationKey key) {
        return new ConfigurationSectionHWrapper(this.getConfigurationSection(key));
    }

    public double getDouble(ConfigurationKey key) {
        return this.rawConfig.getDouble(key.getPath());
    }

    public double getDouble(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getDouble(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getDouble(this.getGlobalPath(key));
        }
    }

    public double getDouble(ConfigurationKey key, double def) {
        return this.rawConfig.getDouble(this.getGlobalPath(key), def);
    }

    public List<?> getList(ConfigurationKey key) {
        return this.rawConfig.getList(key.getPath());
    }

    public List<?> getList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getList(this.getGlobalPath(key));
        }
    }

    public List<?> getList(ConfigurationKey key, List<?> def) {
        return this.rawConfig.getList(this.getGlobalPath(key), def);
    }

    public List<String> getStringList(ConfigurationKey key) {
        return this.rawConfig.getStringList(key.getPath());
    }

    public List<String> getStringList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getStringList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getStringList(this.getGlobalPath(key));
        }
    }

    public List<Integer> getIntegerList(ConfigurationKey key) {
        return this.rawConfig.getIntegerList(key.getPath());
    }

    public List<Integer> getIntegerList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getIntegerList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getIntegerList(this.getGlobalPath(key));
        }
    }

    public List<Boolean> getBooleanList(ConfigurationKey key) {
        return this.rawConfig.getBooleanList(key.getPath());
    }

    public List<Boolean> getBooleanList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getBooleanList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getBooleanList(this.getGlobalPath(key));
        }
    }

    public List<Double> getDoubleList(ConfigurationKey key) {
        return this.rawConfig.getDoubleList(key.getPath());
    }

    public List<Double> getDoubleList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getDoubleList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getDoubleList(this.getGlobalPath(key));
        }
    }

    public List<Float> getFloatList(ConfigurationKey key) {
        return this.rawConfig.getFloatList(key.getPath());
    }

    public List<Float> getFloatList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getFloatList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getFloatList(this.getGlobalPath(key));
        }
    }

    public List<Long> getLongList(ConfigurationKey key) {
        return this.rawConfig.getLongList(key.getPath());
    }

    public List<Long> getLongList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getLongList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getLongList(this.getGlobalPath(key));
        }
    }

    public List<Byte> getByteList(ConfigurationKey key) {
        return this.rawConfig.getByteList(key.getPath());
    }

    public List<Byte> getByteList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getByteList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getByteList(this.getGlobalPath(key));
        }
    }

    public List<Character> getCharacterList(ConfigurationKey key) {
        return this.rawConfig.getCharacterList(key.getPath());
    }

    public List<Character> getCharacterList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getCharacterList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getCharacterList(this.getGlobalPath(key));
        }
    }

    public List<Short> getShortList(ConfigurationKey key) {
        return this.rawConfig.getShortList(key.getPath());
    }

    public List<Short> getShortList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getShortList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getShortList(this.getGlobalPath(key));
        }
    }

    public List<Map<?, ?>> getMapList(ConfigurationKey key) {
        return this.rawConfig.getMapList(key.getPath());
    }

    public List<Map<?, ?>> getMapList(ConfigurationKey key, World world) {
        if (this.hasWorldSpecificSetting(key, world)) {
            return this.rawConfig.getMapList(key.getPath() + "." + world.getName());
        } else {
            return this.rawConfig.getMapList(this.getGlobalPath(key));
        }
    }

    public boolean contains(ConfigurationKey key) {
        return this.rawConfig.contains(key.getPath());
    }

    public boolean contains(String string) {
        return this.rawConfig.contains(string);
    }

    public boolean contains(ConfigurationKey key, World world) {
        return this.contains(key.getPath() + "." + world.getName());
    }

    public void removeKey(ConfigurationKey key) {
        this.rawConfig.removeKey(key.getPath());
    }

    public void removeKey(ConfigurationKey key, World world) {
        this.rawConfig.removeKey(key.getPath() + "." + world.getName());
    }

    public void set(ConfigurationKey key, Object value) {
        if (key.isPerWorldAllowed()) this.rawConfig.set(getGlobalPath(key), value);
        else this.rawConfig.set(key.getPath(), value);
    }

    public void set(ConfigurationKey key, Object value, World world) {
        if (key.isPerWorldAllowed()) this.rawConfig.set(key.getPath() + "." + world.getName(), value);
        else this.rawConfig.set(key.getPath(), value);
    }

    @Deprecated
    public void set(String path, Object value) {
        this.rawConfig.set(path, value);
    }

    public Set<String> getKeys() {
        return this.rawConfig.getKeys();
    }
}
