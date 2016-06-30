package net.notfab.hubbasics.settings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.notfab.hubbasics.HubBasics;

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
