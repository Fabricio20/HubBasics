package net.notfab.hubbasics.settings;

import lombok.Getter;

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
