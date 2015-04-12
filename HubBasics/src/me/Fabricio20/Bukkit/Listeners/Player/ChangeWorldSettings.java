package me.Fabricio20.Bukkit.Listeners.Player;

import me.Fabricio20.Bukkit.API.MagicClockAPI;
import me.Fabricio20.Bukkit.Methods.ModuleManager;
import me.Fabricio20.Bukkit.Methods.Managers.EffectsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeWorldSettings implements Listener {
	
	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
		MagicClockAPI.theClass.toggleClock(player);
		if(ModuleManager.theClass.isInWorld(player)) {
			EffectsManager.theClass.fixEffects(player);
		} else {
			EffectsManager.theClass.removeEffects(player);
		}
	}
	
}
