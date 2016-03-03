package net.notfab.HubBasics.Bukkit.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import net.notfab.HubBasics.Bukkit.Abstract.HBCommand;

public class SetHubCommand extends HBCommand {

	public SetHubCommand() {
		super(new Permission("HubBasics.SetHub"), new String[]{"sethub", "setlobby"});
	}

	@Override
	public void onCommand(Player sender, String[] args) {
		/*SimpleConfig storage = getConfig(EnumConfigs.Storage);
		storage.set("Hub.World", sender.getLocation().getWorld().getName());
		storage.set("Hub.X", sender.getLocation().getX());
		storage.set("Hub.Y", sender.getLocation().getY());
		storage.set("Hub.Z", sender.getLocation().getZ());
		storage.set("Hub.Yaw", sender.getLocation().getYaw());
		storage.set("Hub.Pitch", sender.getLocation().getPitch());
		storage.saveConfig();
		sender.sendMessage("§f[§6HubBasics§f]: Hub Set!");*/
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
