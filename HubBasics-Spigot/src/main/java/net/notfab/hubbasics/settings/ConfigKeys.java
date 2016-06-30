package net.notfab.hubbasics.settings;

import lombok.Getter;

/**
 * (C) Eirik Lorgen Tanberg 2016
 * <p>
 * This class by Eirik Lorgen Tanberg is licensed under a Creative
 * Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * <p>
 * http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
public enum ConfigKeys {
	ENABLE_DEBUG("messages.debug", Boolean.TRUE);

	@Getter	private String path;
	@Getter private Object defaultValue;

	ConfigKeys(String path, Object defaultValue) {
		this.path = path;
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return this.getPath();
	}
}
