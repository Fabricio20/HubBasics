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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class HubCommand extends HCommand {
	
	public HubCommand() {
		super(HPermissions.HUB_COMMAND, "hub", "lobby");
	}

	@Override
	public void onCommand(Player sender, String[] args) {
		/*JSONObject _Config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
		JSONObject _Hub = _Config.getJSONObject("Hub");
		if(_Hub.getBoolean("Enabled")) {
			if(_Hub.getBoolean("BungeeCord")) {
				String server = _Hub.getString("Server");
				sendToServer(sender, server);
			} else {
				World world = Bukkit.getWorld(_Hub.getJSONObject("Location").getString("World"));
				Double x = _Hub.getJSONObject("Location").getDouble("X");
				Double y = _Hub.getJSONObject("Location").getDouble("Y");
				Double z = _Hub.getJSONObject("Location").getDouble("Z");
				Double yaw = _Hub.getJSONObject("Location").getDouble("Yaw");
				Double pitch = _Hub.getJSONObject("Location").getDouble("Pitch");
				if(world == null) {
					sender.sendMessage("§cError§7: Invalid Lobby World.");
					return;
				}
				Location loc = new Location(world, x, y, z, Float.valueOf(yaw.toString()), Float.valueOf(pitch.toString()));
				sender.teleport(loc);
			}
		} else {
			// Unkown Command
		}*/
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
