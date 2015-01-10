package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.Permissions;
import me.Fabricio20.Strings;
import me.Fabricio20.methods.CustomConfigs;
import me.Fabricio20.methods.Items;
import me.Fabricio20.methods.VEK;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickListener implements Listener {
	
	ArrayList<Player> cooldown = new ArrayList<Player>();
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	 VEK gv = new VEK();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				 Sign sign = (Sign) e.getClickedBlock().getState();
				 if(sign.getLine(0).equalsIgnoreCase("§1[WebSite]")){
					 e.getPlayer().sendMessage(Main.theClass.getPlugin().getConfig().getString("WebSiteMessage").replace("&", "§"));
				 }
				 if(sign.getLine(0).equalsIgnoreCase("§1[TS3]")) {
					 e.getPlayer().sendMessage(Main.theClass.getPlugin().getConfig().getString("TS3Message").replace("&", "§"));
				 }
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
		if(Main.theClass.getPlugin().getConfig().getBoolean("Others.Stacker") == true) {
			Player player = e.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if(e.getRightClicked() instanceof Player) {
					Player target = (Player) e.getRightClicked();
					if(target.isInsideVehicle() == false) {
						players.add(target);
					}
					if(!target.hasPermission(new Permissions().NonStackable)) {
						if(players.contains(target)) {
							player.setPassenger(target);
							players.remove(target);
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
		if(Main.theClass.getPlugin().getConfig().getBoolean("Others.Stacker") == true) {
			Player player = e.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if(e.getAction() == Action.LEFT_CLICK_AIR) {
					if((player.getPassenger() instanceof Player)) {
						Player passenger = (Player)player.getPassenger();
						if(!players.contains(passenger)) {
							passenger.leaveVehicle();
				            Location loc = player.getLocation();
				            passenger.setVelocity(this.gv.VEC(loc).multiply(2));
				            players.add(passenger);
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void Interact(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand() != null  || e.getPlayer().getItemInHand().getType() != Material.AIR) {
			if(Main.theClass.getPlugin().getConfig().getBoolean("Others.JoinItems") == true) {
				List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
				if(worlds.contains(e.getPlayer().getWorld().getName())) {
					if(CustomConfigs.getItemConfig().getBoolean("Item1.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item1(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item1.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item1.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item2.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item2(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item2.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item2.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item3.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item3(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item3.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item3.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item4.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item4(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item4.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item4.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item5.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item5(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item5.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item5.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item6.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item6(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item6.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item6.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item7.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item7(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item7.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item7.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item8.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item8(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item8.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item8.Command"));
							}
						}
					}
					if(CustomConfigs.getItemConfig().getBoolean("Item9.Enabled") == true) {
						if(e.getPlayer().getItemInHand().equals(Items.Item9(e.getPlayer().getName()))) {
							if(CustomConfigs.getItemConfig().getString("Item9.Command") != null) {
								e.getPlayer().chat(CustomConfigs.getItemConfig().getString("Item9.Command"));
							}
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClickAgaing(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getType() != Material.AIR) {
				final Player player = e.getPlayer();
				List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
				int time = Main.theClass.getPlugin().getConfig().getInt("MagicClock.Cooldown");
				if(worlds.contains(e.getPlayer().getWorld().getName())) {
					if(e.getPlayer().getItemInHand().equals(Items.MagicClock(e.getPlayer().getName()))) {
						if(Strings.MagicClockActive.contains(e.getPlayer())) {
							if(cooldown.contains(e.getPlayer())) {
								e.getPlayer().sendMessage(Main.theClass.getPlugin().getConfig().getString("MagicClock.CooldownMSG").replace("&", "§").replace("%p", e.getPlayer().getName()));
							} else {
								for (Player user : Bukkit.getOnlinePlayers()) {
									if(e.getPlayer().canSee(user) == false) {
										e.getPlayer().showPlayer(user);
									}
								}
								Strings.MagicClockActive.remove(e.getPlayer());
								cooldown.add(e.getPlayer());
								Bukkit.getScheduler().runTaskLater(Main.theClass.getPlugin(), new Runnable() {
									public void run() {
										if(cooldown.contains(player)) {
											cooldown.remove(player);
										}
									}
								}, time * 20);
								e.getPlayer().sendMessage(Main.theClass.getPlugin().getConfig().getString("MagicClock.DisabledMessage").replace("&", "§").replace("%p", e.getPlayer().getName()));
							}
						} else {
							if(cooldown.contains(e.getPlayer())) {
								e.getPlayer().sendMessage(Main.theClass.getPlugin().getConfig().getString("MagicClock.CooldownMSG").replace("&", "§").replace("%p", e.getPlayer().getName()));
							} else {
								for (Player user : Bukkit.getOnlinePlayers()) {
									if(e.getPlayer().canSee(user) == true) {
										e.getPlayer().hidePlayer(user);
									}
								}
								Strings.MagicClockActive.add(e.getPlayer());
								cooldown.add(e.getPlayer());
								Bukkit.getScheduler().runTaskLater(Main.theClass.getPlugin(), new Runnable() {
									public void run() {
										if(cooldown.contains(player)) {
											cooldown.remove(player);
										}
									}
								}, time * 20);
								e.getPlayer().sendMessage(Main.theClass.getPlugin().getConfig().getString("MagicClock.EnabledMessage").replace("&", "§").replace("%p", e.getPlayer().getName()));
							}
						}
					}
				}
			}
		}
	}	
}
