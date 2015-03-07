package me.Fabricio20.methods.Managers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectsManager {
	
	public static EffectsManager theClass = new EffectsManager();
	
	public void fixEffects(Player player) {
		if(SettingsManager.theClass.isJumpBoostEnabled(player.getName())) {
			if(player.hasPotionEffect(PotionEffectType.JUMP)) {
				player.removePotionEffect(PotionEffectType.JUMP);
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 199999, (SettingsManager.theClass.getJumpForce(player.getName())-1)));
		} else if(player.hasPotionEffect(PotionEffectType.JUMP)) {
			player.removePotionEffect(PotionEffectType.JUMP);
		}
		if(SettingsManager.theClass.isSpeedEnabled(player.getName())) {
			if(player.hasPotionEffect(PotionEffectType.SPEED)) {
				player.removePotionEffect(PotionEffectType.SPEED);
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 199999, (SettingsManager.theClass.getSpeedForce(player.getName())-1)));
		} else if(player.hasPotionEffect(PotionEffectType.SPEED)) {
			player.removePotionEffect(PotionEffectType.SPEED);
		}
	}
	
	public void removeEffects(Player player) {
		if(player.hasPotionEffect(PotionEffectType.JUMP)) {
			player.removePotionEffect(PotionEffectType.JUMP);
		}
		if(player.hasPotionEffect(PotionEffectType.SPEED)) {
			player.removePotionEffect(PotionEffectType.SPEED);
		}
	}
	
}
