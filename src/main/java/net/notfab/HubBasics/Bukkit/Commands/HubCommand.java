package net.notfab.HubBasics.Bukkit.Commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.json.JSONObject;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.notfab.HubBasics.Bukkit.HubBasics;
import net.notfab.HubBasics.Bukkit.Abstract.HBCommand;

public class HubCommand extends HBCommand {

	public HubCommand() {
		super(new Permission("HubBasics.Hub"), new String[]{"hub", "lobby"});
	}

	@Override
	public void onCommand(Player sender, String[] args) {
		JSONObject _Config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
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
		}
	}
	
	public void sendToServer(Player player, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(HubBasics.getInstance(), "BungeeCord", out.toByteArray());
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
