package me.Fabricio20;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Set;

import me.Fabricio20.API.HoverAPI;
import me.Fabricio20.API.LanguageAPI;
import me.Fabricio20.API.WarpAPI;
import me.Fabricio20.listeners.RightClickListener;
import me.Fabricio20.methods.Items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
						if(Main.theClass.Storage.contains("Hub.World")) {
							Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
							if(Bukkit.getWorld(Main.theClass.Storage.getString("Hub.World")) != null) {
								loc.setWorld(Bukkit.getWorld(Main.theClass.Storage.getString("Hub.World")));
								loc.setX(Main.theClass.Storage.getDouble("Hub.X"));
								loc.setY(Main.theClass.Storage.getDouble("Hub.Y"));
								loc.setZ(Main.theClass.Storage.getDouble("Hub.Z"));
								loc.setYaw(Main.theClass.Storage.getInt("Hub.Yaw"));
								loc.setPitch(Main.theClass.Storage.getInt("Hub.Pitch"));
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
						Main.theClass.Storage.set("Hub.World", player.getWorld().getName());
						Main.theClass.Storage.set("Hub.X", player.getLocation().getX());
						Main.theClass.Storage.set("Hub.Y", player.getLocation().getY());
						Main.theClass.Storage.set("Hub.Z", player.getLocation().getZ());
						Main.theClass.Storage.set("Hub.Yaw", player.getLocation().getYaw());
						Main.theClass.Storage.set("Hub.Pitch", player.getLocation().getPitch());
						Main.theClass.Storage.saveConfig();
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
									player.getInventory().setHelmet(Items.Hat(player.getName(), Material.getMaterial(id), 0));
									player.sendMessage(Strings.Prefix + Main.theClass.config.getString("HatSystem.Set").replace("&", "§")
													.replace("%p", player.getName()));
								} else {
									player.sendMessage(Strings.Prefix + Main.theClass.config.getString("HatSystem.NoPerm")
											.replace("&", "§").replace("%p", player.getName()));
								}
							} else if(args[0].equalsIgnoreCase("remove")) {
								Player player = (Player) sender;
								player.getInventory().setHelmet(new ItemStack(Material.AIR));
								player.sendMessage(Strings.Prefix + Main.theClass.config.getString("HatSystem.Removed").replace("&", "§")
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
										player.getInventory().setHelmet(Items.Hat(player.getName(), Material.getMaterial(id), meta));
										player.sendMessage(Strings.Prefix + Main.theClass.config.getString("HatSystem.Set").replace("&", "§")
														.replace("%p", player.getName()));
									} else {
										player.sendMessage(Strings.Prefix + Main.theClass.config.getString("HatSystem.NoPerm")
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
						player.spigot().sendMessage(HoverAPI.buildTextLinkText(player, "§f-§a-§f-§a-§f-§a-  ", "§9HubBasics", "http://www.spigotmc.org/resources/hubbasics.2654/", "§eClick to open HubBasics website", "  §a-§f-§a-§f-§a-§f-"));
						player.spigot().sendMessage(HoverAPI.buildTextCommandText(player, "", "§c/hb reload", "/hb reload", "§9Reloads The Plugin\n§e- Click to reload", ""));
					}
				} else if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						Main.theClass.config.reloadConfig();
						Main.theClass.ItemConfig.reloadConfig();
						Main.theClass.Storage.reloadConfig();
						Main.theClass.Warps.reloadConfig();
						Main.theClass.Language.reloadConfig();
						sender.sendMessage("§8[§cHubBasics§8] §eConfig Reloaded!");
					} else {
						if(!(sender instanceof Player)) {
							sender.sendMessage("[HubBasics] Avaliable Commands:");
							sender.sendMessage("[HubBasics] /hb reload");
						} else {
							Player player = (Player) sender;
							player.spigot().sendMessage(HoverAPI.buildTextLinkText(player, "§f-§a-§f-§a-§f-§a-  ", "§9HubBasics", "http://www.spigotmc.org/resources/hubbasics.2654/", "§eClick to open HubBasics website", "  §a-§f-§a-§f-§a-§f-"));
							player.spigot().sendMessage(HoverAPI.buildTextCommandText(player, "", "§c/hb reload", "/hb reload", "§9Reloads The Plugin\n§e- Click to reload", ""));
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
		} else if(commandLabel.equalsIgnoreCase("hubitems")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only Players Can Use This Command Sir!");
			} else {
				if(Main.theClass.config.getBoolean("Others.HubItems") == true) {
					Player player = (Player) sender;
					// ITEM 1
					if(Main.theClass.ItemConfig.getBoolean("Item1.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item1(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item1.Slot"),
									Items.Item1(player.getName()));
						}
					}
					// Item 2
					if(Main.theClass.ItemConfig.getBoolean("Item2.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item2(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item2.Slot"),
									Items.Item2(player.getName()));
						}
					}
					// Item 3
					if(Main.theClass.ItemConfig.getBoolean("Item3.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item3(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item3.Slot"),
									Items.Item3(player.getName()));
						}
					}
					// Item 4
					if(Main.theClass.ItemConfig.getBoolean("Item4.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item4(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item4.Slot"),
									Items.Item4(player.getName()));
						}
					}
					// Item 5
					if(Main.theClass.ItemConfig.getBoolean("Item5.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item5(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item5.Slot"),
									Items.Item5(player.getName()));
						}
					}
					// Item 6
					if(Main.theClass.ItemConfig.getBoolean("Item6.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item6(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item6.Slot"),
									Items.Item6(player.getName()));
						}
					}
					// Item 7
					if(Main.theClass.ItemConfig.getBoolean("Item7.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item7(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item7.Slot"),
									Items.Item7(player.getName()));
						}
					}
					// Item 8
					if(Main.theClass.ItemConfig.getBoolean("Item8.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item8(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item8.Slot"),
									Items.Item8(player.getName()));
						}
					}
					// Item 9
					if(Main.theClass.ItemConfig.getBoolean("Item9.Enabled") == true) {
						if(!player.getInventory().contains(Items.Item9(player.getName()))) {
							player.getInventory().setItem(Main.theClass.ItemConfig.getInt("Item9.Slot"),
									Items.Item9(player.getName()));
						}
					}
				} else {
					sender.sendMessage("§cThis command is disabled!");
				}
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
		} else if(commandLabel.equalsIgnoreCase("setwarp")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only Players Sir!");
			} else {
				Player player = (Player) sender;
				World world = player.getWorld();
				Location loc = player.getLocation();
				if(player.hasPermission(new Permissions().SetWarp)) {
					if(args.length >= 1) {
						String name = args[0];
						if(!WarpAPI.warpExist(name)) {
							if(WarpAPI.createWarp(name, world.getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch())) {
								player.sendMessage("§aWarp §b" + name + "§a Created!");
							} else {
								player.sendMessage("§cThere was an error creating this warp.");
							}
						} else {
							player.sendMessage("§cThis Warp Already Exist!");
						}
					} else {
						player.sendMessage("§cUsage: /setwarp <name>");
					}
				} else {
					player.sendMessage(Strings.PermissionError);
				}
			}
		} else if(commandLabel.equalsIgnoreCase("warp")) {
			if(!(sender instanceof Player)) {
				if(args.length >= 2) {
					String name = args[1];
					Player target = Bukkit.getPlayer(args[0]);
					if(sender.hasPermission(new Permissions().WarpAdmin)) {
						if(target != null) {
							if(WarpAPI.warpExist(name)) {
								if(target.hasPermission("HubBasics.Warps." + name.toLowerCase())) {
									WarpAPI.sendToWarp(name, target);
									sender.sendMessage("Teleported " + target.getName() + " to " + name);
								} else {
									sender.sendMessage(target.getName() + " §cDoesn't have Permission for this warp!");
								}
							} else {
								sender.sendMessage("§cWarp Not Found!");
							}
						} else {
							sender.sendMessage("§cPlayer Not Found!");
						}
					}
				} else {
					sender.sendMessage("Usage: /warp <Player> <name>");
				}
			} else {
				Player player = (Player) sender;
				if(args.length < 1) {
					player.sendMessage(LanguageAPI.theClass.WarpUsage(player.getName(), player.getWorld().getName()));
				}
				if(args.length == 1) {
					String name = args[0];
					if(WarpAPI.warpExist(name)) {
						if(player.hasPermission("HubBasics.Warps." + name.toLowerCase())) {
							WarpAPI.sendToWarp(name, player);
						} else {
							player.sendMessage(LanguageAPI.theClass.WarpNoPermission(player.getName(), player.getWorld().getName()));
						}
					} else {
						player.sendMessage("§cWarp Not Found!");
					}
				}
				if(args.length >= 2) {
					String name = args[1];
					Player target = Bukkit.getPlayer(args[0]);
					if(player.hasPermission(new Permissions().WarpAdmin)) {
						if(target != null) {
							if(WarpAPI.warpExist(name)) {
								if(target.hasPermission("HubBasics.Warps." + name.toLowerCase())) {
									WarpAPI.sendToWarp(name, player);
									player.sendMessage("§eTeleported §9" + target.getName() + " §eto §9" + name);
								} else {
									player.sendMessage(target.getName() + " §cDoesn't have Permission for this warp!");
								}
							} else {
								player.sendMessage("§cWarp Not Found!");
							}
						} else {
							player.sendMessage("§cPlayer Not Found!");
						}
					} else {
						player.sendMessage(Strings.PermissionError);
					}
				}
			}
		} else if(commandLabel.equalsIgnoreCase("delwarp")) {
			if(!(sender instanceof Player)) {
				if(args.length < 1) {
					sender.sendMessage("Usage: /delwarp <name>");
				}
				if(args.length >= 1) {
					if(WarpAPI.warpExist(args[0])) {
						if(WarpAPI.removeWarp(args[0])) {
							sender.sendMessage("Warp '" + args[0] + "' Removed!");
						} else {
							sender.sendMessage("Error While Removing Warp!");
						}
					} else {
						sender.sendMessage("This warp does not exist!");
					}
				}
			} else {
				Player player = (Player) sender;
				if(player.hasPermission(new Permissions().DelWarp)) {
					if(args.length < 1) {
						player.sendMessage("§cUsage: /delwarp <name>");
					}
					if(args.length >= 1) {
						if(WarpAPI.warpExist(args[0])) {
							if(WarpAPI.removeWarp(args[0])) {
								player.sendMessage("§aWarp '§b" + args[0] + "§a' Removed!");
							} else {
								player.sendMessage("§cError While Removing Warp!");
							}
						} else {
							player.sendMessage("§cThis warp does not exist!");
						}
					}
				} else {
					player.sendMessage(Strings.PermissionError);
				}
			}
		} else if(commandLabel.equalsIgnoreCase("warps")) {
			String word = "§6Warps: §a";
			Set<String> s = Main.theClass.Warps.getConfigurationSection("Warps").getKeys(false);
			for(String st : s) {
				if(sender.hasPermission("HubBasics.Warps." + st.toLowerCase())) {
					word = word + "§a" + st + "§f, ";
				} else {
					word =  word + "§c" + st + "§f, ";
				}
			}
			sender.sendMessage(word);
		}
		return false;
	}
}
