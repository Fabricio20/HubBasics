package net.notfab.hubbasics;

import lombok.Getter;
import net.notfab.hubbasics.managers.CommandManager;
import net.notfab.hubbasics.managers.ModuleManager;
import net.notfab.hubbasics.managers.SimpleConfigManager;
import net.notfab.hubbasics.plugin.messages.MessageManager;
import net.notfab.hubbasics.plugin.settings.HConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class HubBasics extends JavaPlugin {

	@Getter	private static HubBasics instance;

	@Getter	private CommandManager commandManager;
	@Getter	private HConfiguration pluginConfiguration;
	@Getter private SimpleConfigManager configManager;
	@Getter private MessageManager messageManager;
	@Getter private ModuleManager moduleManager;

	@Override
	public void onEnable() {
		instance = this;
		this.init();
		//TODO: Load
	}

	@Override
	public void onDisable() {
		this.getCommandManager().unloadMap();
		//TODO: Unload
		instance = null;
	}

	private void init() {
		this.configManager = new SimpleConfigManager(this);
		this.messageManager = new MessageManager();
		this.getMessageManager().loadMessages();

		this.commandManager = new CommandManager();
		this.getCommandManager().loadMap();

		this.pluginConfiguration = new HConfiguration();
		this.getPluginConfiguration().loadDefaults();

		this.moduleManager = new ModuleManager();
	}
}
