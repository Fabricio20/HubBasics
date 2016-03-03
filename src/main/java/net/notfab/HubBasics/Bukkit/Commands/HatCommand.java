package net.notfab.HubBasics.Bukkit.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import net.notfab.HubBasics.Bukkit.Abstract.HBCommand;

public class HatCommand extends HBCommand {

	public HatCommand() {
		super(new Permission("HubBasics.Hat"), "hat");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player sender, String[] args) {
		if(args.length == 0) {
			// USAGE
		} else if(args.length == 1) { // Really Shit Way Here <3
			Material mat;
			try {
				mat = Material.getMaterial(Integer.valueOf(args[0]));
			} catch (IllegalArgumentException ex) {
				try {
					mat = Material.getMaterial(args[0]);
				} catch (IllegalArgumentException exx) {
					// Not Material
					// Send Message
					return;
				}
			}
			sender.getInventory().setHelmet(new ItemStack(mat));
		} else {
			Material mat;
			try {
				mat = Material.getMaterial(Integer.valueOf(args[0]));
			} catch (IllegalArgumentException ex) {
				try {
					mat = Material.getMaterial(args[0]);
				} catch (IllegalArgumentException exx) {
					// Not Material
					// Send Message
					return;
				}
			}
			short data;
			try {
				data = Short.valueOf(args[1]);
			} catch (IllegalArgumentException ex) {
				// Not A Valid Metadada
				// Send Message
				return;
			}
			sender.getInventory().setHelmet(new ItemStack(mat, 1, (short) data));
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
