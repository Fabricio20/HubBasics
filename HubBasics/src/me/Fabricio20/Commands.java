package me.Fabricio20;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static JavaPlugin plugin;

	public Commands(JavaPlugin plugin) {
		Commands.plugin = plugin;
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
	
	if(commandLabel.equalsIgnoreCase("hub") || commandLabel.equalsIgnoreCase("lobby")) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("This command can only be ran by a player.");
		} else {
			if(sender.hasPermission(new Permissions().Hub)) {
				if(plugin.getConfig().getBoolean("BungeeCord.Enabled") == false) {
					if(getCustomConfig().contains("Hub.World")) {
						Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
						if(Bukkit.getWorld(getCustomConfig().getString("Hub.World")) != null) {
							loc.setWorld(Bukkit.getWorld(getCustomConfig().getString("Hub.World")));
							loc.setX(getCustomConfig().getInt("Hub.X"));
							loc.setY(getCustomConfig().getInt("Hub.Y"));
							loc.setZ(getCustomConfig().getInt("Hub.Z"));
							loc.setYaw(getCustomConfig().getInt("Hub.Yaw"));
							loc.setPitch(getCustomConfig().getInt("Hub.Pitch"));
							Player player = (Player) sender;
							player.teleport(loc);
						} else {
							sender.sendMessage("§cHub world does not exist!");
						}
					} else {
						sender.sendMessage("§cHub was not found!");
					}
				} else {
					sendToServer(((Player)sender), plugin.getConfig().getString("BungeeCord.LobbyServer"));
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
					if(plugin.getConfig().getBoolean("BungeeCord.Enabled") == false) {
						getCustomConfig().set("Hub.World", player.getWorld().getName());
						getCustomConfig().set("Hub.X", player.getLocation().getX());
						getCustomConfig().set("Hub.Y", player.getLocation().getY());
						getCustomConfig().set("Hub.Z", player.getLocation().getZ());
						getCustomConfig().set("Hub.Yaw", player.getLocation().getYaw());
						getCustomConfig().set("Hub.Pitch", player.getLocation().getPitch());
						saveCustomConfig();
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
								player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.HatSet").replace("&", "§"));
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
									player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.HatSet").replace("&", "§"));
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
					if(commandLabel.equalsIgnoreCase("uuid")) {
						if(sender.hasPermission(new Permissions().UUID)) {
							if(args.length == 0) {
								sender.sendMessage("§c/uuid <nick>");
							} else if(args.length >= 1){
								Player target = Bukkit.getPlayer(args[0]);
								if(target == null) {
									sender.sendMessage("§cPlayer not found!");
								} else {
									sender.sendMessage("§9" + target.getName() + "'s UUID: " + target.getUniqueId());
								}
							}
						} else {
							sender.sendMessage("§cNo Permission!");
						}
					} else {
						//TODO More Commands;
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	return false;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static void reloadCustomConfig() {
    if (Main.Storage == null) {
    Main.Storage = new File(plugin.getDataFolder(), "Storage.yml");
    }
    Main.StorageConfig = YamlConfiguration.loadConfiguration(Main.Storage);
}

public static FileConfiguration getCustomConfig() {
  if (Main.StorageConfig == null) {
        reloadCustomConfig();
  }
  return Main.StorageConfig;
}

public static void saveCustomConfig() {
  if (Main.StorageConfig == null || Main.Storage == null) {
      return;
  }
  try {
      getCustomConfig().save(Main.Storage);
  } catch (IOException ex) {
   plugin.getLogger().log(Level.SEVERE, "Could not save config to " + Main.Storage, ex);
  }
}

}
