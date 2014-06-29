package me.Fabricio20;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public Commands(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	File f = new File("/plugins/HubBasics/Location.yml");
	YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
	
	if(commandLabel.equalsIgnoreCase("hub") || commandLabel.equalsIgnoreCase("lobby")) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("This command can only be ran by a player.");
		} else {
			Player player = (Player) sender;
			if(player.hasPermission(new Permissions().Hub)) {
					int x = yamlFile.getInt("Hub.x"), y = yamlFile.getInt("Hub.y"), z = yamlFile.getInt("Hub.z");
				player.teleport(new Location(plugin.getServer().getWorlds().get(0),x,y,z));
			}
		}
	} else {
		if(commandLabel.equalsIgnoreCase("sethub")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be ran by a player.");
			} else {
				Player player = (Player) sender;
				if(player.hasPermission(new Permissions().SetHub)) {
						yamlFile.set("Hub.x", player.getLocation().getBlockX());
						yamlFile.set("Hub.y", player.getLocation().getBlockY());
						yamlFile.set("Hub.z", player.getLocation().getBlockZ());
						try {
							  yamlFile.save(f);
							} catch(IOException e) {
							  e.printStackTrace();
							}
						player.sendMessage("§8[§cHubBasics§8] §eHub set!");
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	return false;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
