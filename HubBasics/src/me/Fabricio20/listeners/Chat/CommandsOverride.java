package me.Fabricio20.listeners.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.Fabricio20.Main;
import me.Fabricio20.Permissions;
import me.Fabricio20.API.LanguageAPI;
import me.Fabricio20.API.WarpAPI;
import me.Fabricio20.Storage.Strings;
import me.Fabricio20.methods.ModuleManager;
import me.Fabricio20.methods.QuickWarpChest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandsOverride implements Listener {

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String[] args = e.getMessage().split(" ");
		String[] cmd = e.getMessage().split(" ");
		Player sender = e.getPlayer();
		if(!e.isCancelled()) {
			if(cmd[0].equalsIgnoreCase("/warps")) {
				if(!isDisabled("warps")) {
					e.setCancelled(true);
					if(Main.theClass.Warps.contains("Warps")) {
						String word = "§6Warps: §a";
						Set<String> s = Main.theClass.Warps.getConfigurationSection("Warps").getKeys(false);
						for (String st : s) {
							if (sender.hasPermission("HubBasics.Warps." + st.toLowerCase())) {
								word = word + "§a" + st + "§f, ";
							} else {
								word = word + "§c" + st + "§f, ";
							}
						}
						sender.sendMessage(word);
					} else {
						sender.sendMessage("§6Warps: §cNo warps found.");
					}
				}
			} else if(cmd[0].equalsIgnoreCase("/warp")) {
				if(!isDisabled("warp")) {
					e.setCancelled(true);
					Player player = (Player) sender;
					if(args.length < 2) {
						player.sendMessage(LanguageAPI.theClass.WarpUsage(player.getName(), player.getWorld().getName()));
					}
					if(args.length == 2) {
						String name = args[1];
						if(WarpAPI.warpExist(name)) {
							if(player.hasPermission("HubBasics.Warps."
									+ name.toLowerCase())) {
								WarpAPI.sendToWarp(name, player);
							} else {
								player.sendMessage(LanguageAPI.theClass
										.WarpNoPermission(player.getName(), player
												.getWorld().getName()));
							}
						} else {
							player.sendMessage("§cWarp Not Found!");
						}
					}
					if(args.length >= 3) {
						String name = args[2];
						Player target = Bukkit.getPlayer(args[1]);
						if(player.hasPermission(new Permissions().WarpAdmin)) {
							if(target != null) {
								if(WarpAPI.warpExist(name)) {
									if(target.hasPermission("HubBasics.Warps."
											+ name.toLowerCase())) {
										WarpAPI.sendToWarp(name, player);
										player.sendMessage("§eTeleported §9"
												+ target.getName() + " §eto §9"
												+ name);
									} else {
										player.sendMessage(target.getName()
												+ " §cDoesn't have Permission for this warp!");
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
			} else if(cmd[0].equalsIgnoreCase("/setwarp")) {
				if(!isDisabled("setwarp")) {
					e.setCancelled(true);
					Player player = (Player) sender;
					World world = player.getWorld();
					Location loc = player.getLocation();
					if(player.hasPermission(new Permissions().SetWarp)) {
						if(args.length >= 2) {
							String name = args[1];
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
			} else if(cmd[0].equalsIgnoreCase("/delwarp")) {
				if(!isDisabled("delwarp")) {
					e.setCancelled(true);
					Player player = (Player) sender;
					if(player.hasPermission(new Permissions().DelWarp)) {
						if(args.length < 2) {
							player.sendMessage("§cUsage: /delwarp <name>");
						}
						if(args.length >= 2) {
							if(WarpAPI.warpExist(args[1])) {
								if(WarpAPI.removeWarp(args[1])) {
									player.sendMessage("§aWarp '§b" + args[1]
											+ "§a' Removed!");
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
			} else if(cmd[0].equalsIgnoreCase("/quickwarp")) {
				if(!isDisabled("quickwarp")) {
					e.setCancelled(true);
					Player player = (Player) sender;
					if(Main.theClass.QuickWarpChest.getBoolean("PerWorld")) {
						if(ModuleManager.theClass.isInWorld(player)) {
							QuickWarpChest.open(player);
						}
					} else {
						QuickWarpChest.open(player);
					}
					
				}
			}
		}
	}
	
	boolean isDisabled(String command) {
		ArrayList<String> disabledCommands = new ArrayList<String>();
		List<String> commands = Main.theClass.config.getStringList("DisabledCommands");
		for(String s: commands) {
			disabledCommands.add(s.replace("/", ""));
		}
		return disabledCommands.contains(command);
	}

}
