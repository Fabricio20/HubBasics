package net.notfab.hubbasics.settings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.notfab.hubbasics.HubBasics;

/**
 * (C) Eirik Lorgen Tanberg 2016
 * <p>
 * This class by Eirik Lorgen Tanberg is licensed under a Creative
 * Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * <p>
 * http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
public class HConfiguration {

	private HubBasics pl;

	public HConfiguration() {
		this.pl = HubBasics.getInstance();
	}

	public void loadDefaults() {
		pl.getConfig().options().copyDefaults(true);
		Map<String, Object> defaults = new HashMap<>();
		Arrays.stream(ConfigKeys.values()).forEach(key -> defaults.put(key.getPath(), key.getDefaultValue()));
		pl.getConfig().addDefaults(defaults);
	}
}
