package me.Fabricio20.listeners.Others;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.GameMode;
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
		if (Main.theClass.config.getBoolean("Others.DoubleJump") == true) {
				Player player = event.getPlayer();
				if (player.getGameMode() == GameMode.CREATIVE)
					return;
				event.setCancelled(true);
				player.setAllowFlight(false);
				player.setFlying(false);
				player.setVelocity(player.getLocation().getDirection()
						.multiply(1.5).setY(1));
				player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1.0F,
						-5.0F);
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if(Main.theClass.config.getBoolean("Others.DoubleJump") == true) {
			List<String> worlds = Main.theClass.config.getStringList("Worlds");
			Player player = event.getPlayer();
			if(worlds.contains(player.getWorld().getName())) {
				if ((player.getGameMode() != GameMode.CREATIVE)
						&& (player.getLocation().subtract(0, 1, 0).getBlock()
								.getType() != Material.AIR) && (!player.isFlying())) {
					player.setAllowFlight(true);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println((float) -5 + (float) (Math.random() * ((5 - -5) + 1)));
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
