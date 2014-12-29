package me.Fabricio20.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.Fabricio20.Main;
import me.Fabricio20.Permissions;
import me.Fabricio20.Strings;
import me.Fabricio20.methods.VEK;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RightClickListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static JavaPlugin plugin;

	public RightClickListener(JavaPlugin plugin) {
		RightClickListener.plugin = plugin;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static ArrayList<Player> players = new ArrayList();
	 VEK gv = new VEK();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				 Sign sign = (Sign) e.getClickedBlock().getState();
				 if(sign.getLine(0).equalsIgnoreCase("§1[WebSite]")){
					 e.getPlayer().sendMessage(plugin.getConfig().getString("WebSiteMessage").replace("&", "§"));
				 }
				 if(sign.getLine(0).equalsIgnoreCase("§1[TS3]")) {
					 e.getPlayer().sendMessage(plugin.getConfig().getString("TS3Message").replace("&", "§"));
				 }
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if(plugin.getConfig().getBoolean("Others.Stacker") == true) {
			Player player = e.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if ((e.getRightClicked() instanceof Player)) {
					Player target = (Player) e.getRightClicked();
					if(target.isInsideVehicle() == false) {
						players.remove(e.getRightClicked());
					}
					if (!target.hasPermission(new Permissions().NonStackable)) {
						if (!players.contains(target)) {
							player.setPassenger(target);
							players.add(target);
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		List<String> worlds = plugin.getConfig().getStringList("Worlds");
		if(plugin.getConfig().getBoolean("Others.Stacker") == true) {
			Player player = e.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if (e.getAction() == Action.LEFT_CLICK_AIR) {
					if ((player.getPassenger() instanceof Player)) {
						Player passenger = (Player)player.getPassenger();
						if(players.contains(passenger)) {
							passenger.leaveVehicle();
				            Location loc = player.getLocation();
				            passenger.setVelocity(this.gv.VEC(loc).multiply(2));
				            players.remove(passenger);
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
			if(e.getPlayer().getItemInHand().hasItemMeta()) {
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item1.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item1.Enabled") == true) {
						if(getCustomConfig().getString("Item1.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item1.Command"));
						}
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item2.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item2.Enabled") == true) {
						if(getCustomConfig().getString("Item2.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item2.Command"));
						}
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item3.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item3.Enabled") == true) {
						if(getCustomConfig().getString("Item3.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item3.Command"));
						}
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item4.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item4.Enabled") == true) {
						if(getCustomConfig().getString("Item4.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item4.Command"));
						}
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item5.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item5.Enabled") == true) {
						if(getCustomConfig().getString("Item5.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item5.Command"));
						}
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item6.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item6.Enabled") == true) {
						if(getCustomConfig().getString("Item6.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item6.Command"));
						}
					}
				}
				if(getCustomConfig().getBoolean("Item7.Enabled") == true) {
					if(getCustomConfig().getString("Item7.Command") != null) {
						e.getPlayer().chat(getCustomConfig().getString("Item7.Command"));
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item8.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item8.Enabled") == true) {
						if(getCustomConfig().getString("Item8.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item8.Command"));
						}
					}
				}
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getCustomConfig().getString("Item9.Name").replace("&", "§"))) {
					if(getCustomConfig().getBoolean("Item9.Enabled") == true) {
						if(getCustomConfig().getString("Item9.Command") != null) {
							e.getPlayer().chat(getCustomConfig().getString("Item9.Command"));
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
			if(e.getPlayer().getItemInHand() != null  && e.getPlayer().getItemInHand().getType() != Material.AIR) {
				if(e.getPlayer().getItemInHand().hasItemMeta()) {
					if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("MagicClock.Name").replace("&", "§").replace("%p", e.getPlayer().getName()))) {
						if(Strings.MagicClockActive.contains(e.getPlayer())) {
							for(Player user : Bukkit.getOnlinePlayers()) {
								if(e.getPlayer().canSee(user) == false) {
									e.getPlayer().showPlayer(user);
								}
							}
							Strings.MagicClockActive.remove(e.getPlayer());
							e.getPlayer().sendMessage(plugin.getConfig().getString("MagicClock.DisabledMessage").replace("&", "§").replace("%p", e.getPlayer().getName()));
						} else {
							for(Player user : Bukkit.getOnlinePlayers()) {
								if(e.getPlayer().canSee(user) == true) {
									e.getPlayer().hidePlayer(user);
								}
							}
							Strings.MagicClockActive.add(e.getPlayer());
							e.getPlayer().sendMessage(plugin.getConfig().getString("MagicClock.EnabledMessage").replace("&", "§").replace("%p", e.getPlayer().getName()));
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void reloadCustomConfig() {
	    if (Main.Storage2 == null) {
	    Main.Storage2 = new File(plugin.getDataFolder(), "Items.yml");
	    }
	    Main.StorageConfig2 = YamlConfiguration.loadConfiguration(Main.Storage2);
	}

	public static FileConfiguration getCustomConfig() {
	  if (Main.StorageConfig2 == null) {
	        reloadCustomConfig();
	  }
	  return Main.StorageConfig2;
	}

	public static void saveCustomConfig() {
	  if (Main.StorageConfig2 == null || Main.Storage2 == null) {
	      return;
	  }
	  try {
	      getCustomConfig().save(Main.Storage2);
	  } catch (IOException ex) {
	   plugin.getLogger().log(Level.SEVERE, "Could not save config to " + Main.Storage2, ex);
	  }
	}
	
}
