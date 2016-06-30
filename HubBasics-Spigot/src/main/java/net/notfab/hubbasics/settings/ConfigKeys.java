package net.notfab.hubbasics.settings;

import lombok.Getter;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
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
