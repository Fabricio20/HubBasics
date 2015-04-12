package me.Fabricio20.Bukkit.Listeners.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.API.ItemsAPI;
import me.Fabricio20.Bukkit.API.LanguageAPI;
import me.Fabricio20.Bukkit.API.MagicClockAPI;
import me.Fabricio20.Bukkit.Storage.Permissions;
import me.Fabricio20.Bukkit.Methods.JoinItems;
import me.Fabricio20.Bukkit.Methods.ModuleManager;
import me.Fabricio20.Bukkit.Methods.Managers.SettingsManager;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class RightClickListener implements Listener {
	
	ArrayList<Player> cooldown = new ArrayList<Player>();
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Player> stackeron = new ArrayList<Player>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				 Sign sign = (Sign) e.getClickedBlock().getState();
				 if(sign.getLine(0).equalsIgnoreCase("§1[WebSite]")){
					 e.getPlayer().sendMessage(Main.theClass.config.getString("WebSiteMessage").replace("&", "§"));
				 }
				 if(sign.getLine(0).equalsIgnoreCase("§1[TS3]")) {
					 e.getPlayer().sendMessage(Main.theClass.config.getString("TS3Message").replace("&", "§"));
				 }
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if(Main.theClass.config.getBoolean("Others.Stacker") == true) {
			Player player = e.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if(e.getRightClicked() instanceof Player) {
					Player target = (Player) e.getRightClicked();
					if(!target.hasPermission(new Permissions().NonStackable) && target.hasPermission(new Permissions().Stacker) && stackeron.contains(target) && stackeron.contains(player) && player.hasPermission(new Permissions().Stacker)) {
						if(!target.isInsideVehicle()) {
							if(!player.isInsideVehicle()) {
								if(!players.contains(target)) {
									player.setPassenger(target);
									players.add(target);
								}
							}
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if(Main.theClass.config.getBoolean("Others.Stacker") == true) {
			Player player = e.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if(e.getAction() == Action.LEFT_CLICK_AIR) {
					if((player.getPassenger() instanceof Player)) {
						Player passenger = (Player)player.getPassenger();
						if(players.contains(passenger)) {
							passenger.leaveVehicle();
				            Location loc = player.getLocation();
				            passenger.setVelocity(VEC(loc).multiply(2));
				            players.remove(passenger);
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	@EventHandler
	public void Interact(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getPlayer().getItemInHand() != null  || e.getPlayer().getItemInHand().getType() != Material.AIR) {
			if(Main.theClass.config.getBoolean("Others.JoinItems") == true) {
				if(ModuleManager.theClass.isInWorld(player)) {
					if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						ItemStack stk = e.getPlayer().getItemInHand();
						for(ItemStack stack: JoinItems.theClass.getItems(player).keySet()) {
							if(stk.equals(stack)) {
								if(JoinItems.theClass.getCommand(stack, player) != null) {
									String[] arg = JoinItems.theClass.getCommand(stack, player).split(":");
									String command = JoinItems.theClass.getCommand(stack, player);
									if(command.startsWith("SERVER:")) {
										sendToServer(player, arg[1]);
									} else if(command.startsWith("CONSOLE:")) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), arg[1].replace("&", "§").replace("%p", player.getName()));
									} else if(command.startsWith("TELL:")){
										player.sendMessage(arg[1].replace("&", "§"));
									} else {
										player.chat(JoinItems.theClass.getCommand(stack, player).replace("&", "§").replace("%p", player.getName()));
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onRightClickAgaing(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getType() != Material.AIR) {
				final Player player = e.getPlayer();
				int time = Main.theClass.config.getInt("MagicClock.Cooldown");
				if(ModuleManager.theClass.isInWorld(player)) {
					if(e.getPlayer().getItemInHand().equals(ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(e.getPlayer().getName())))) {
						if(cooldown.contains(e.getPlayer())) {
							player.sendMessage(LanguageAPI.theClass.MagicClock_Cooldown(player));
						} else {
							if(SettingsManager.theClass.toggleClock(player.getName())) {
								player.sendMessage(LanguageAPI.theClass.MagicClock_Enabled(player));
							} else {
								player.sendMessage(LanguageAPI.theClass.MagicClock_Disabled(player));
							}
							e.getPlayer().setItemInHand(ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(e.getPlayer().getName())));
							e.getPlayer().updateInventory();
							MagicClockAPI.theClass.toggleClock(player);
							cooldown.add(player);
							Bukkit.getScheduler().runTaskLater(Main.theClass.getPlugin(), new Runnable() {
								public void run() {
									if(cooldown.contains(player)) {
										cooldown.remove(player);
									}
								}
							}, time * 20);
						}
					}
				}
			}
		}
	}	
	
	public Vector VEC(Location loc) {
	    double pitch = (loc.getPitch() + 90.0F) * 3.141592653589793D / 180.0D;
	    double yaw = (loc.getYaw() + 90.0F) * 3.141592653589793D / 180.0D;
	    
	    double x = Math.sin(pitch) * Math.cos(yaw);
	    double y = Math.sin(pitch) * Math.sin(yaw);
	    double z = Math.cos(pitch);
	    
	    Vector vec = new Vector(x, z, y);
	    
	    return vec;
	  }
	
}
