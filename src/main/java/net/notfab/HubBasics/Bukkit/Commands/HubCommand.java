package net.notfab.HubBasics.Bukkit.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import net.notfab.HubBasics.Bukkit.Abstract.HBCommand;

public class HubCommand extends HBCommand {

	public HubCommand() {
		super(new Permission("HubBasics.Hub"), new String[]{"hub", "lobby"});
	}

	@Override
	public void onCommand(Player sender, String[] args) {
		/*SimpleConfig storage = getConfig(EnumConfigs.Storage);
		World world = Bukkit.getWorld(storage.getString("Hub.World"));
		if(world == null) {
			sender.sendMessage("§cError§7: Unkown World");
			return;
		}
		Double x = storage.getDouble("Hub.X");
		Double y = storage.getDouble("Hub.Y");
		Double z = storage.getDouble("Hub.Z");
		Integer yaw = storage.getInt("Hub.Yaw");
		Integer pitch = storage.getInt("Hub.Pitch");
		Location loc = new Location(world, x, y, z, yaw, pitch);
		sender.teleport(loc);*/
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
