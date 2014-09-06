package me.Fabricio20;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public Commands(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public int id;
	public int meta;
	
	public void sendToServer(Player player, String targetServer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(targetServer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	File f = new File("/plugins/HubBasics/Location.yml");
	YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
	
	if(commandLabel.equalsIgnoreCase("hub") || commandLabel.equalsIgnoreCase("lobby")) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("This command can only be ran by a player.");
		} else {
			if(sender.hasPermission(new Permissions().Hub)) {
				if(plugin.getConfig().getBoolean("BungeeCord") == false) {
					Player player = (Player) sender;
					int x = yamlFile.getInt("Hub.x"), y = yamlFile.getInt("Hub.y"), z = yamlFile.getInt("Hub.z");
					player.teleport(new Location(plugin.getServer().getWorlds().get(0),x,y,z));
				} else {
					sendToServer(((Player)sender), plugin.getConfig().getString("BungeeCordConfig.LobbyServer"));
				}
			} else {
				sender.sendMessage(Strings.PermissionError);
			}
		}
	} else {
		if(commandLabel.equalsIgnoreCase("sethub")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be ran by a player.");
			} else {
				Player player = (Player) sender;
				if(player.hasPermission(new Permissions().SetHub)) {
					if(plugin.getConfig().getBoolean("BungeeCord") == false) {
						yamlFile.set("Hub.x", player.getLocation().getBlockX());
						yamlFile.set("Hub.y", player.getLocation().getBlockY());
						yamlFile.set("Hub.z", player.getLocation().getBlockZ());
						try {
							  yamlFile.save(f);
							} catch(IOException e) {
							  e.printStackTrace();
							}
						player.sendMessage("§8[§cHubBasics§8] §eHub set!");
					} else {
						sender.sendMessage("§8[§cHubBasics§8] §cBungeeCord Support Is Enabled!");
					}
				} else {
					sender.sendMessage(Strings.PermissionError);
				}
			}
		} else {
			if(commandLabel.equalsIgnoreCase("hat")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("This command can only be ran by a player.");
				} else {
					if(args.length > 2) {
						sender.sendMessage("§c/hat <id> or /hat <id> <meta>");
					}
					if(args.length == 1) {
						if(args[0].matches("[0-999]+")) {
								Player player = (Player) sender;
								id = Integer.parseInt(args[0]);
								player.getInventory().setHelmet(new ItemStack(id));
								player.sendMessage(Strings.Prefix + plugin.getConfig().getString("HatSet").replace("&", "§"));
						} else {
							sender.sendMessage("§cID must be a number!");
						}
					}
					if(args.length == 2) {
						if(args[0].matches("[0-999]+")) {
							if(args[1].matches("[0-999]+")) {
									Player player = (Player) sender;
									id = Integer.parseInt(args[0]);
									meta = Integer.parseInt(args[1]);
									player.getInventory().setHelmet(new ItemStack(id, 1, (short) meta));
									player.sendMessage(Strings.Prefix + plugin.getConfig().getString("HatSet").replace("&", "§"));
							} else {
								sender.sendMessage("§cID must be a number!");
							}
						} else {
							sender.sendMessage("§cID must be a number!");
						}
					}
					if(args.length < 1) {
						sender.sendMessage("§c/hat <id> or /hat <id> <meta>");
					}
				}
			} else {
				if(commandLabel.equalsIgnoreCase("hb")) {
					if(sender.hasPermission(new Permissions().Hub)) {
						if(args.length == 0) {
							sender.sendMessage("§c<!--HubBasics Commands --!>");
							sender.sendMessage("§c/hb reload");
						} else if(args.length >= 1) {
							if(args[0].equalsIgnoreCase("reload")) {
								plugin.reloadConfig();
								sender.sendMessage("§8[§cHubBasics§8] §eConfig Reloaded!");
							} else {
								sender.sendMessage("§c<!--HubBasics Commands --!>");
								sender.sendMessage("§c/hb reload");
							}
						}
					}
				} else {
					//TODO More Commands
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	return false;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
