package net.notfab.hubbasics.plugin.settings;

import lombok.Getter;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public enum ConfigurationKey {

	WORLD_PARENT_SECTION("world.config", false, null),
	ENABLE_DEBUG("messages.debug", false, false),
	PLAYER_CONNECT("messages.player.connect", true, "&8[&a+&8] &f<displayName> &7joined the game"),
	PLAYER_DISCONNECT("messages.player.disconnect", true, "&8[&c-&8] &f<displayName> &7quit the game"),
	PLAYER_FIRST_CONNECT("messages.player.firstConnect", true, "&9Welcome to the server, &f<displayName>&9!"),
	DOUBLE_JUMP_ENABLED("Enabled", true, true, ModuleEnum.DOUBLE_JUMP),
	DOUBLE_JUMP_FORCE("Force", false, 0.3, ModuleEnum.DOUBLE_JUMP),
	DOUBLE_JUMP_SOUND("Sound", false, "ENTITY_BAT_TAKEOFF", ModuleEnum.DOUBLE_JUMP);

	@Getter	private String path;
	@Getter	private boolean perWorldAllowed;
	@Getter	private Object defaultValue;
	@Getter private ModuleEnum moduleEnum;

	ConfigurationKey(String path, Boolean worldDependant, Object defaultValue) {
		this.path = path;
		this.perWorldAllowed = worldDependant;
		this.defaultValue = defaultValue;
	}

	ConfigurationKey(String path, Boolean worldDependant, Object defaultValue, ModuleEnum moduleEnum) {
		this.path = path;
		this.perWorldAllowed = worldDependant;
		this.defaultValue = defaultValue;
		this.moduleEnum = moduleEnum;
	}

	@Override
	public String toString() {
		return this.getPath();
	}
}
