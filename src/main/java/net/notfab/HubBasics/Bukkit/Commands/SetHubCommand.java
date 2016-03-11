package net.notfab.HubBasics.Bukkit.Commands;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.json.JSONObject;

import net.notfab.HubBasics.Bukkit.HubBasics;
import net.notfab.HubBasics.Bukkit.Abstract.HBCommand;

public class SetHubCommand extends HBCommand {

	public SetHubCommand() {
		super(new Permission("HubBasics.SetHub"), new String[]{"sethub", "setlobby"});
	}

	@Override
	public void onCommand(Player sender, String[] args) {
		JSONObject _Config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
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
			// Unkown Command
		}
	}

	@Override
	public void onCommand(ConsoleCommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		
	}
	
}
