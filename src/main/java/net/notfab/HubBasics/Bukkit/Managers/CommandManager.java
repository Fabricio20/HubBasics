package net.notfab.HubBasics.Bukkit.Managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import net.notfab.HubBasics.Bukkit.HubBasics;
import net.notfab.HubBasics.Bukkit.Abstract.HBCommand;
import net.notfab.HubBasics.Bukkit.Commands.HatCommand;
import net.notfab.HubBasics.Bukkit.Commands.HubCommand;
import net.notfab.HubBasics.Bukkit.Commands.SetHubCommand;

public class CommandManager {
	
	private List<HBCommand> _Commands = new ArrayList<HBCommand>();
	
	public CommandManager(HubBasics plugin) {
		registerCommand(new HatCommand(), plugin);
		registerCommand(new HubCommand(), plugin);
		registerCommand(new SetHubCommand(), plugin);
	}
	
	public void registerCommand(HBCommand cmd, JavaPlugin plugin) {
		if(cmd.getNames().length > 1) {
			List<String> names = new LinkedList<String>(Arrays.asList(cmd.getNames()));
			PluginCommand pcmd = plugin.getCommand(names.get(0));
			names.remove(0);
			pcmd.setAliases(names);
			pcmd.setExecutor(cmd);
		} else {
			plugin.getCommand(cmd.getNames()[0]).setExecutor(cmd);
		}
		_Commands.add(cmd);
	}
	
	public List<HBCommand> getCommands() {
		return this._Commands;
	}
	
}
