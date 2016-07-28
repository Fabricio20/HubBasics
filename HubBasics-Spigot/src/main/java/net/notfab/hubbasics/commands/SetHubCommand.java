package net.notfab.hubbasics.commands;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.messages.HubBasicsMessage;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class SetHubCommand extends HCommand {

	private HubBasics pl;

	public SetHubCommand() {
		super(HPermissions.SETHUB_COMMAND, "sethub", "setlobby");
		this.pl = HubBasics.getInstance();
	}

	@Override
	public void onCommand(Player player, String[] args) {
		if(pl.getPluginConfiguration().getBoolean(ConfigurationKey.HUB_COMMANDS_ENABLED)) {
			if(pl.getPluginConfiguration().getBoolean(ConfigurationKey.USE_BUNGEECORD)) {
				HMessenger.sendMessage(player, "Modifying the hub location will NOT have any effect while BungeeCord is running. " +
						"Please manually edit the variables found in the configuration file for HubBasics.");
			} else {
				Location location = player.getLocation();
				pl.getPluginConfiguration().set(ConfigurationKey.HUB_LOCATION_X, location.getX());
				pl.getPluginConfiguration().set(ConfigurationKey.HUB_LOCATION_Y, location.getY());
				pl.getPluginConfiguration().set(ConfigurationKey.HUB_LOCATION_Z, location.getZ());
				pl.getPluginConfiguration().set(ConfigurationKey.HUB_LOCATION_YAW, location.getYaw());
				pl.getPluginConfiguration().set(ConfigurationKey.HUB_LOCATION_PITCH, location.getPitch());
				pl.getPluginConfiguration().set(ConfigurationKey.HUB_LOCATION_WORLD, location.getWorld().getName());

				HMessenger.sendMessage(player, HubBasicsMessage.HUB_LOCATION_UPDATED.getMessage());
			}
		} else {
			 HMessenger.unknownCommand(player);
		}
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		HMessenger.errorPlayersOnly(sender);
	}

	@Override
	public List<String> onTabComplete(Player player, String[] args) {
		return null;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String[] args) {
		return null;
	}

}