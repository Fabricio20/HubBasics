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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class HubCommand extends HCommand {

	private HubBasics pl;

	public HubCommand() {
		super(HPermissions.HUB_COMMAND, "hub", "lobby");
		this.pl = HubBasics.getInstance();
	}

	@Override
	public void onCommand(Player sender, String[] args) {
		if(pl.getPluginConfiguration().getBoolean(ConfigurationKey.HUB_COMMANDS_ENABLED)) {
			if(pl.getPluginConfiguration().getBoolean(ConfigurationKey.USE_BUNGEECORD)) {
				String server = pl.getPluginConfiguration().getString(ConfigurationKey.HUB_LOCATION_SERVER);
				sendToServer(sender, server);
			} else {
				World world = Bukkit.getWorld(pl.getPluginConfiguration().getString(ConfigurationKey.HUB_LOCATION_WORLD));
				double x = pl.getPluginConfiguration().getDouble(ConfigurationKey.HUB_LOCATION_X);
				double y = pl.getPluginConfiguration().getDouble(ConfigurationKey.HUB_LOCATION_Y);
				double z = pl.getPluginConfiguration().getDouble(ConfigurationKey.HUB_LOCATION_Z);
				double yaw = pl.getPluginConfiguration().getDouble(ConfigurationKey.HUB_LOCATION_YAW);
				double pitch = pl.getPluginConfiguration().getDouble(ConfigurationKey.HUB_LOCATION_PITCH);

				if(world == null) {
					sender.sendMessage("§cError§7: Invalid lobby world.");
					return;
				}

				Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
				sender.teleport(loc);
			}
		} else {
			HMessenger.unknownCommand(sender);
		}
	}
	
	public void sendToServer(Player player, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(HubBasics.getInstance(), "BungeeCord", out.toByteArray());
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
