package me.Fabricio20;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import me.Fabricio20.methods.CustomConfigs;
import me.Fabricio20.methods.Items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
					if(CustomConfigs.getStorageConfig().contains("Hub.World")) {
						Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
						if(Bukkit.getWorld(CustomConfigs.getStorageConfig().getString("Hub.World")) != null) {
							loc.setWorld(Bukkit.getWorld(CustomConfigs.getStorageConfig().getString("Hub.World")));
							loc.setX(CustomConfigs.getStorageConfig().getInt("Hub.X"));
							loc.setY(CustomConfigs.getStorageConfig().getInt("Hub.Y"));
							loc.setZ(CustomConfigs.getStorageConfig().getInt("Hub.Z"));
							loc.setYaw(CustomConfigs.getStorageConfig().getInt("Hub.Yaw"));
							loc.setPitch(CustomConfigs.getStorageConfig().getInt("Hub.Pitch"));
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
						CustomConfigs.getStorageConfig().set("Hub.World", player.getWorld().getName());
						CustomConfigs.getStorageConfig().set("Hub.X", player.getLocation().getX());
						CustomConfigs.getStorageConfig().set("Hub.Y", player.getLocation().getY());
						CustomConfigs.getStorageConfig().set("Hub.Z", player.getLocation().getZ());
						CustomConfigs.getStorageConfig().set("Hub.Yaw", player.getLocation().getYaw());
						CustomConfigs.getStorageConfig().set("Hub.Pitch", player.getLocation().getPitch());
						CustomConfigs.saveStorageConfig();
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
								if(player.hasPermission("HubBasics.Hat." + id)) {
									player.getInventory().setHelmet(new ItemStack(id));
									player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.HatSet").replace("&", "§").replace("%p", player.getName()));
								} else {
									player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.NoPermissionForHat").replace("&", "§").replace("%p", player.getName()));
								}
						} else if(args[0].equalsIgnoreCase("remove")) {
							Player player = (Player) sender;
							player.getInventory().setHelmet(new ItemStack(Material.AIR));
							player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.HatRemoved").replace("&", "§").replace("%p", player.getName()));
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
									if(player.hasPermission("HubBasics.Hat." + id)) {
										player.getInventory().setHelmet(new ItemStack(id, 1, (short) meta));
										player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.HatSet").replace("&", "§").replace("%p", player.getName()));
									} else {
										player.sendMessage(Strings.Prefix + plugin.getConfig().getString("Others.NoPermissionForHat").replace("&", "§").replace("%p", player.getName()));
									}
							} else {
								sender.sendMessage("§cMETA must be a number!");
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
								Strings.Prefix = plugin.getConfig().getString("Others.Prefix").replace("&", "§");
								Strings.LaunchPadBlock = plugin.getConfig().getString("Others.JumpPadBlock");
								sender.sendMessage("§8[§cHubBasics§8] §eConfig Reloaded!");
							} else {
								sender.sendMessage("§c<!--HubBasics Commands --!>");
								sender.sendMessage("§c/hb reload");
							}
						}
					} else {
						sender.sendMessage(Strings.PermissionError);
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
							sender.sendMessage(Strings.PermissionError);
						}
					} else {
						if(commandLabel.equalsIgnoreCase("hubitems")) {
							if(!(sender instanceof Player)) {
								sender.sendMessage("Only Players Can Use This Command Sir!");
							} else {
								if(plugin.getConfig().getBoolean("Others.HubItems") == true) {
									Player player = (Player) sender;
									// ITEM 1
									if(CustomConfigs.getItemConfig().getBoolean("Item1.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item1(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item1.Slot"), Items.Item1(player.getName()));
										}
									}
									// Item 2
									if(CustomConfigs.getItemConfig().getBoolean("Item2.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item2(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item2.Slot"), Items.Item2(player.getName()));
										}
									}
									// Item 3
									if(CustomConfigs.getItemConfig().getBoolean("Item3.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item3(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item3.Slot"), Items.Item3(player.getName()));
										}
									}
									// Item 4
									if(CustomConfigs.getItemConfig().getBoolean("Item4.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item4(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item4.Slot"), Items.Item4(player.getName()));
										}
									}
									// Item 5
									if(CustomConfigs.getItemConfig().getBoolean("Item5.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item5(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item5.Slot"), Items.Item5(player.getName()));
										}
									}
									// Item 6
									if(CustomConfigs.getItemConfig().getBoolean("Item6.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item6(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item6.Slot"), Items.Item6(player.getName()));
										}
									}
									//Item 7
									if(CustomConfigs.getItemConfig().getBoolean("Item7.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item7(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item7.Slot"), Items.Item7(player.getName()));
										}
									}
									// Item 8
									if(CustomConfigs.getItemConfig().getBoolean("Item8.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item8(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item8.Slot"), Items.Item8(player.getName()));
										}
									}
									// Item 9
									if(CustomConfigs.getItemConfig().getBoolean("Item9.Enabled") == true) {
										if(!player.getInventory().contains(Items.Item9(player.getName()))) {
											player.getInventory().setItem(CustomConfigs.getItemConfig().getInt("Item9.Slot"), Items.Item9(player.getName()));
										}
									}
								} else {
									sender.sendMessage("§cThis command is disabled!");
								}
							}
						}
					}
				}
			}
		}
	}
	return false;
  }
}
