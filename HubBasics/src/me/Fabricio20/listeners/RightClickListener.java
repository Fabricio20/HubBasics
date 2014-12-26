package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Permissions;
import me.Fabricio20.methods.VEK;

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
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item1.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item1.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item1.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item2.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item2.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item2.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item3.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item3.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item3.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item4.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item4.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item4.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item5.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item5.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item5.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item6.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item6.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item6.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item7.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item7.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item7.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item8.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item8.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item8.CMD"));
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.getMaterial(JoinListenerForItems.items.getString("Item9.Material")))) {
			if(JoinListenerForItems.items.getBoolean("Item9.CMDA") == true) {
				e.getPlayer().chat(JoinListenerForItems.items.getString("Item9.CMD"));
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
