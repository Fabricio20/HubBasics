package me.Fabricio20.Bukkit;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import me.Fabricio20.Bukkit.Storage.Permissions;
import me.Fabricio20.Bukkit.Storage.Storage;
import me.Fabricio20.Bukkit.Storage.Strings;
import me.Fabricio20.Bukkit.Listeners.Player.RightClickListener;
import me.Fabricio20.Bukkit.Methods.JoinItems;
import me.Fabricio20.Global.GlobalStrings;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

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
		player.sendPluginMessage(Main.theClass.getPlugin(), "BungeeCord", b.toByteArray());
	}
	
/* --------------------------------------------------------------------------------------------------------------------------- */
	
	/** 
	 * 
	 * @author Fabricio20
	 * @since 2014
	 * 
	 * HubBasics Class That Handles All Commands
	 * 
	 */
	
/* --------------------------------------------------------------------------------------------------------------------------- */
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("hub") || commandLabel.equalsIgnoreCase("lobby")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be ran by a player.");
			} else {
				if(sender.hasPermission(new Permissions().Hub)) {
					if(Main.theClass.config.getBoolean("BungeeCord.Enabled") == false) {
						if(Main.theClass.Hub.contains("Hub.World")) {
							Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
							if(Bukkit.getWorld(Main.theClass.Hub.getString("Hub.World")) != null) {
								loc.setWorld(Bukkit.getWorld(Main.theClass.Hub.getString("Hub.World")));
								loc.setX(Main.theClass.Hub.getDouble("Hub.X"));
								loc.setY(Main.theClass.Hub.getDouble("Hub.Y"));
								loc.setZ(Main.theClass.Hub.getDouble("Hub.Z"));
								loc.setYaw(Main.theClass.Hub.getInt("Hub.Yaw"));
								loc.setPitch(Main.theClass.Hub.getInt("Hub.Pitch"));
								Player player = (Player) sender;
								player.teleport(loc);
							} else {
								sender.sendMessage("§cHub world does not exist!");
							}
						} else {
							sender.sendMessage("§cHub was not found!");
						}
					} else {
						sendToServer(((Player) sender), Main.theClass.config.getString("BungeeCord.LobbyServer"));
					}
				} else {
					sender.sendMessage(Strings.PermissionError);
				}
			}
		} else if(commandLabel.equalsIgnoreCase("sethub")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be ran by a player.");
			} else {
				Player player = (Player) sender;
				if(player.hasPermission(new Permissions().SetHub)) {
					if(Main.theClass.config.getBoolean("BungeeCord.Enabled") == false) {
						Main.theClass.Hub.set("Hub.World", player.getWorld().getName());
						Main.theClass.Hub.set("Hub.X", player.getLocation().getX());
						Main.theClass.Hub.set("Hub.Y", player.getLocation().getY());
						Main.theClass.Hub.set("Hub.Z", player.getLocation().getZ());
						Main.theClass.Hub.set("Hub.Yaw", player.getLocation().getYaw());
						Main.theClass.Hub.set("Hub.Pitch", player.getLocation().getPitch());
						Main.theClass.Hub.saveConfig();
						player.sendMessage("§8[§cHubBasics§8] §eHub set!");
					} else {
						sender.sendMessage("§8[§cHubBasics§8] §cBungeeCord Support Is Enabled!");
					}
				} else {
					sender.sendMessage(Strings.PermissionError);
				}
			}
		} else if(commandLabel.equalsIgnoreCase("hat")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("This command can only be ran by a player.");
				} else {
					if(Main.theClass.config.getBoolean("HatSystem.Enabled")) {
						if(args.length > 2) {
							sender.sendMessage("§c/hat <id> or /hat <id> <meta>");
						}
						if(args.length == 1) {
							if(args[0].matches("[0-999]+")) {
								Player player = (Player) sender;
								id = Integer.parseInt(args[0]);
								if(player.hasPermission("HubBasics.Hat." + id)) {
									player.getInventory().setHelmet(JoinItems.Hat(player.getName(), Material.getMaterial(id), 0));
									player.sendMessage(Main.theClass.config.getString("HatSystem.Set").replace("&", "§")
													.replace("%p", player.getName()));
								} else {
									player.sendMessage(Main.theClass.config.getString("HatSystem.NoPerm")
											.replace("&", "§").replace("%p", player.getName()));
								}
							} else if(args[0].equalsIgnoreCase("remove")) {
								Player player = (Player) sender;
								player.getInventory().setHelmet(new ItemStack(Material.AIR));
								player.sendMessage(Main.theClass.config.getString("HatSystem.Removed").replace("&", "§")
												.replace("%p", player.getName()));
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
										player.getInventory().setHelmet(JoinItems.Hat(player.getName(), Material.getMaterial(id), meta));
										player.sendMessage(Main.theClass.config.getString("HatSystem.Set").replace("&", "§")
														.replace("%p", player.getName()));
									} else {
										player.sendMessage(Main.theClass.config.getString("HatSystem.NoPerm")
														.replace("&", "§").replace("%p", player.getName()));
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
					} else {
						sender.sendMessage("§cThis command is disabled!");
					}
				}
		} else if(commandLabel.equalsIgnoreCase("hb")) {
			if(sender.hasPermission(new Permissions().Hub)) {
				if(args.length == 0) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("[HubBasics] Avaliable Commands:");
						sender.sendMessage("[HubBasics] /hb reload");
					} else {
						Player player = (Player) sender;
						player.sendMessage("§f-§a-§f-§a-§f-§a-  §9HubBasics  §a-§f-§a-§f-§a-§f-");
						player.sendMessage("§c/hb reload | Reloads the plugin");
						player.sendMessage("§c/hb version | Gets plugin version");
					}
				} else if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						Main.theClass.config.reloadConfig();
						Main.theClass.Hub.reloadConfig();
						Main.theClass.JoinItems.reloadConfig();
						Main.theClass.Warps.reloadConfig();
						Main.theClass.Language.reloadConfig();
						Main.theClass.Tags.reloadConfig();
						Main.theClass.QuickWarpChest.reloadConfig();
						Main.theClass.ChestItems.reloadConfig();
						Storage.playerSettings.clear();
						sender.sendMessage("§8[§cHubBasics§8] §eConfig Reloaded!");
					} else if(args[0].equalsIgnoreCase("version")) {
						sender.sendMessage("§8[§cHubBasics§8] §eRunning version: " + GlobalStrings.Version);
						sender.sendMessage("§8[§cHubBasics§8] §eOn Server Version: " + Bukkit.getVersion());
						sender.sendMessage("§8[§cHubBasics§8] §eOn Bukkit Version: " + Bukkit.getBukkitVersion());
					} else {
						if(!(sender instanceof Player)) {
							sender.sendMessage("[HubBasics] Avaliable Commands:");
							sender.sendMessage("[HubBasics] /hb reload");
							sender.sendMessage("[HubBasics] /hb version");
						} else {
							Player player = (Player) sender;
							player.sendMessage("§f-§a-§f-§a-§f-§a-  §9HubBasics  §a-§f-§a-§f-§a-§f-");
							player.sendMessage("§c/hb reload | Reloads the plugin");
							player.sendMessage("§c/hb version | Gets plugin version");
						}
					}
				}
			} else {
				sender.sendMessage(Strings.PermissionError);
			}
		} else if(commandLabel.equalsIgnoreCase("uuid")) {
			if(sender.hasPermission(new Permissions().UUID)) {
				if(args.length == 0) {
					sender.sendMessage("§c/uuid <nick>");
				} else if(args.length >= 1) {
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
		} else if(commandLabel.equalsIgnoreCase("stacker")) {
			if(sender.hasPermission(new Permissions().Stacker)) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("Only Players Can Use This Command!");
				} else {
					if(RightClickListener.stackeron.contains((Player)sender)) {
						RightClickListener.stackeron.remove((Player)sender);
						sender.sendMessage(Strings.StackerDisabled);
					} else {
						RightClickListener.stackeron.add((Player)sender);
						sender.sendMessage(Strings.StackerEnabled);
					}
				}
			} else {
				sender.sendMessage(Strings.PermissionError);
			}
		}
		return false;
	}
}
