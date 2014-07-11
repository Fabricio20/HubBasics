package me.Fabricio20.listeners;

import java.util.ArrayList;

import me.Fabricio20.Permissions;
import me.Fabricio20.methods.VecTor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RightClickListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public RightClickListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static ArrayList<Player> players = new ArrayList();
	 VecTor gv = new VecTor();

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
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		if(plugin.getConfig().getBoolean("Stacker") == true) {
			Player player = e.getPlayer();
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
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(plugin.getConfig().getBoolean("Stacker") == true) {
			Player player = e.getPlayer();
			if (e.getAction() == Action.LEFT_CLICK_AIR) {
				if ((player.getPassenger() instanceof Player)) {
					Player passenger = (Player)player.getPassenger();
					if(players.contains(passenger)) {
						passenger.leaveVehicle();
			            Location loc = player.getLocation();
			            passenger.setVelocity(this.gv.VecTor(loc).multiply(2));
			            players.remove(passenger);
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
