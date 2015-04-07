package me.Fabricio20.listeners.Others;

import me.Fabricio20.Main;
import me.Fabricio20.methods.ModuleManager;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class JumpListener implements Listener {
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		if(Main.theClass.config.getBoolean("Others.DoubleJump") == true) {
			Player player = event.getPlayer();
			if (player.getGameMode().equals(GameMode.CREATIVE)) {
				return;
			}
			if(ModuleManager.theClass.isInWorld(player)) {
				event.setCancelled(true);
				player.setAllowFlight(false);
				player.setFlying(false);
				player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
				player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1.0F, -5.0F);
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if(Main.theClass.config.getBoolean("Others.DoubleJump") == true) {
			Player player = event.getPlayer();
			Location loc = player.getLocation();
			loc.setY(player.getLocation().getY() -1);
			if(ModuleManager.theClass.isInWorld(player)) {
				if((player.getGameMode() != GameMode.CREATIVE) && (!loc.getBlock().getType().equals(Material.AIR)) && (!player.isFlying()) && (player.isOnGround() || !player.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.AIR))) {
					player.setAllowFlight(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
