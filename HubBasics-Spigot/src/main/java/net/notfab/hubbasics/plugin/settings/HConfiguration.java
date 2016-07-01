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
	private Map<String, ConfigurationSectionHWrapper> worldConfigs;

	public HConfiguration() {
		this.pl = HubBasics.getInstance();
		this.config = pl.getConfigManager().getNewConfig("config.yml");
		this.loadWorldConfigs();
	}

	private void loadWorldConfigs() {
		this.worldConfigs = new HashMap<>();
		for (String world : config.getConfigurationSection(ConfigurationKey.WORLD_PARENT_SECTION.getPath()).getKeys(false)) {
			if (Bukkit.getWorld(world) == null) {
				throw new IllegalArgumentException("World " + world + " does not exist!");
			} else {
				this.worldConfigs.put(world, new ConfigurationSectionHWrapper(config.getConfigurationSection(ConfigurationKey.WORLD_PARENT_SECTION.getPath() + "." + world)));
				System.out.println("Registered separate configuration file for world \"" + world + "\".");
			}
		}
	}

	public void loadDefaults() {
		pl.getConfig().options().copyDefaults(true);
		Map<String, Object> defaults = new HashMap<>();
		Arrays.stream(ConfigurationKey.values()).forEach(key -> defaults.put(key.getPath(), key.getDefaultValue()));
		pl.getConfig().addDefaults(defaults);
	}

	public boolean hasSeparateConfig(World world) {
		return this.worldConfigs.containsKey(world.getName());
	}

	public void createWorldConfig(World world) {
		Arrays.stream(ConfigurationKey.values()).forEach(key -> this.set(key, key.getDefaultValue(), world));
	}

	public ConfigurationSectionHWrapper getSubConfig(World world) {
		return this.worldConfigs.get(world.getName());
	}

	public Object get(String path) {
		return this.config.get(path);
	}

	public Object get(ConfigurationKey key) {
		return this.config.get(key.getPath());
	}

	public Object get(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.get(key.getPath());
		} else {
			return this.getSubConfig(world).get(key);
		}
	}

	public Object get(ConfigurationKey key, Object def) {
		return this.config.get(key.getPath(), def);
	}

	public String getString(ConfigurationKey key) {
		return this.config.getString(key.getPath());
	}

	public String getString(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getString(key.getPath());
		} else {
			return this.getSubConfig(world).getString(key);
		}
	}

	public String getString(ConfigurationKey key, String def) {
		return this.config.getString(key.getPath(), def);
	}

	public int getInt(ConfigurationKey key) {
		return this.config.getInt(key.getPath());
	}

	public int getInt(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getInt(key.getPath());
		} else {
			return this.getSubConfig(world).getInt(key);
		}
	}

	public int getInt(ConfigurationKey key, int def) {
		return this.config.getInt(key.getPath(), def);
	}

	public boolean getBoolean(ConfigurationKey key) {
		return this.config.getBoolean(key.getPath());
	}

	public boolean getBoolean(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getBoolean(key.getPath());
		} else {
			return this.getSubConfig(world).getBoolean(key);
		}
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

	public ConfigurationSection getConfigurationSection(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getConfigurationSection(key.getPath());
		} else {
			return this.getSubConfig(world).getConfigurationSection(key);
		}
	}

	public double getDouble(ConfigurationKey key) {
		return this.config.getDouble(key.getPath());
	}

	public double getDouble(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getDouble(key.getPath());
		} else {
			return this.getSubConfig(world).getDouble(key);
		}
	}

	public double getDouble(ConfigurationKey key, double def) {
		return this.config.getDouble(key.getPath(), def);
	}

	public List<?> getList(ConfigurationKey key) {
		return this.config.getList(key.getPath());
	}

	public List<?> getList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getList(key.getPath());
		} else {
			return this.getSubConfig(world).getList(key);
		}
	}

	public List<?> getList(ConfigurationKey key, List<?> def) {
		return this.config.getList(key.getPath(), def);
	}

	public List<String> getStringList(ConfigurationKey key) {
		return this.config.getStringList(key.getPath());
	}

	public List<String> getStringList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getStringList(key.getPath());
		} else {
			return this.getSubConfig(world).getStringList(key);
		}
	}

	public List<Integer> getIntegerList(ConfigurationKey key) {
		return this.config.getIntegerList(key.getPath());
	}

	public List<Integer> getIntegerList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getIntegerList(key.getPath());
		} else {
			return this.getSubConfig(world).getIntegerList(key);
		}
	}

	public List<Boolean> getBooleanList(ConfigurationKey key) {
		return this.config.getBooleanList(key.getPath());
	}

	public List<Boolean> getBooleanList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getBooleanList(key.getPath());
		} else {
			return this.getSubConfig(world).getBooleanList(key);
		}
	}

	public List<Double> getDoubleList(ConfigurationKey key) {
		return this.config.getDoubleList(key.getPath());
	}

	public List<Double> getDoubleList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getDoubleList(key.getPath());
		} else {
			return this.getSubConfig(world).getDoubleList(key);
		}
	}

	public List<Float> getFloatList(ConfigurationKey key) {
		return this.config.getFloatList(key.getPath());
	}

	public List<Float> getFloatList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getFloatList(key.getPath());
		} else {
			return this.getSubConfig(world).getFloatList(key);
		}
	}

	public List<Long> getLongList(ConfigurationKey key) {
		return this.config.getLongList(key.getPath());
	}

	public List<Long> getLongList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getLongList(key.getPath());
		} else {
			return this.getSubConfig(world).getLongList(key);
		}
	}

	public List<Byte> getByteList(ConfigurationKey key) {
		return this.config.getByteList(key.getPath());
	}

	public List<Byte> getByteList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getByteList(key.getPath());
		} else {
			return this.getSubConfig(world).getByteList(key);
		}
	}

	public List<Character> getCharacterList(ConfigurationKey key) {
		return this.config.getCharacterList(key.getPath());
	}

	public List<Character> getCharacterList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getCharacterList(key.getPath());
		} else {
			return this.getSubConfig(world).getCharacterList(key);
		}
	}

	public List<Short> getShortList(ConfigurationKey key) {
		return this.config.getShortList(key.getPath());
	}

	public List<Short> getShortList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getShortList(key.getPath());
		} else {
			return this.getSubConfig(world).getShortList(key);
		}
	}

	public List<Map<?, ?>> getMapList(ConfigurationKey key) {
		return this.config.getMapList(key.getPath());
	}

	public List<Map<?, ?>> getMapList(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.getMapList(key.getPath());
		} else {
			return this.getSubConfig(world).getMapList(key);
		}
	}

	public boolean contains(ConfigurationKey key) {
		return this.config.contains(key.getPath());
	}

	public boolean contains(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			return this.config.contains(key.getPath());
		} else {
			return this.getSubConfig(world).contains(key);
		}
	}

	public void removeKey(ConfigurationKey key) {
		this.config.set(key.getPath(), null);
	}

	public void removeKey(ConfigurationKey key, World world) {
		if (!this.hasSeparateConfig(world)) {
			this.config.removeKey(key.getPath());
		} else {
			this.getSubConfig(world).removeKey(key);
		}
	}

	public void set(ConfigurationKey key, Object value) {
		this.config.set(key.getPath(), value);
	}

	public void set(ConfigurationKey key, Object value, World world) {
		if (!this.hasSeparateConfig(world)) {
			this.config.set(key.getPath(), value);
		} else {
			this.getSubConfig(world).set(key, value);
		}
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
