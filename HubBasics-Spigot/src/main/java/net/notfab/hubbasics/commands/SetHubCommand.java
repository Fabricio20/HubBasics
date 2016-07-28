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

import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class SetHubCommand extends HCommand {
	
	public SetHubCommand() {
		super(HPermissions.SETHUB_COMMAND, "sethub", "setlobby");
	}

	@Override
	public void onCommand(Player player, String[] args) {
		/*JSONObject _Config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
		JSONObject _Hub = _Config.getJSONObject("Hub");
		if(_Hub.getBoolean("Enabled")) {
			if(_Hub.getBoolean("BungeeCord")) {
				sender.sendMessage("§cHubBasics§7: You Cannot Modify Lobby Location With BungeeCord Enabled.");
			} else {
				Location loc = sender.getLocation();
				JSONObject _Location = new JSONObject();
				_Location.put("World", loc.getWorld().getName());
				_Location.put("X", loc.getX());
				_Location.put("Y", loc.getY());
				_Location.put("Z", loc.getZ());
				_Location.put("Yaw", loc.getYaw());
				_Location.put("Pitch", loc.getPitch());
				_Hub.put("Location", _Location);
				_Config.put("Hub", _Hub);
				HubBasics.getInstance().getConfigManager().writeFile(new File("plugins/HubBasics/config.json"), _Config);
				sender.sendMessage("§aHubBasics§7: Lobby Location Updated.");
			}
		} else {
			// Unkown Command since this command is not enabled
			 HMessenger.unknownCommand(player); // Config?
		}*/
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