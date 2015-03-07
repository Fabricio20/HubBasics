package me.Fabricio20.listeners.Player;

import me.Fabricio20.methods.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinSettings implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if(!SettingsManager.theClass.isPlayersEnabled(player.getName())) {
			//Players Disabled!
		}
		// Speed Boost
		if(SettingsManager.theClass.isSpeedEnabled(player.getName())) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 199999, SettingsManager.theClass.getSpeedForce(player.getName())));
		} else if(player.hasPotionEffect(PotionEffectType.SPEED)) {
			player.removePotionEffect(PotionEffectType.SPEED);
		}
		// Jump Boost
		if(SettingsManager.theClass.isJumpBoostEnabled(player.getName())) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 199999, SettingsManager.theClass.getJumpForce(player.getName())));
		} else if(player.hasPotionEffect(PotionEffectType.JUMP)) {
			player.removePotionEffect(PotionEffectType.JUMP);
		}
	}
	
}
