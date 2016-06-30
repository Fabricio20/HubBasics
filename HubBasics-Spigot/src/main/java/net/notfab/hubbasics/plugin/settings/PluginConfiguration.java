package net.notfab.hubbasics.plugin.settings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.notfab.hubbasics.HubBasics;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class PluginConfiguration {

	private HubBasics pl;

	public PluginConfiguration() {
		this.pl = HubBasics.getInstance();
	}

	public void loadDefaults() {
		pl.getConfig().options().copyDefaults(true);
		Map<String, Object> defaults = new HashMap<>();
		Arrays.stream(ConfigKeys.values()).forEach(key -> defaults.put(key.getPath(), key.getDefaultValue()));
		pl.getConfig().addDefaults(defaults);
	}
}
