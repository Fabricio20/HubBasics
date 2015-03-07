package me.Fabricio20.listeners.Player;

import me.Fabricio20.methods.ModuleManager;
import me.Fabricio20.methods.Managers.EffectsManager;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeWorldSettings implements Listener {
	
	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
		if(ModuleManager.theClass.isInWorld(player)) {
			if(!SettingsManager.theClass.isPlayersEnabled(player.getName())) {
				//Players Disabled!
			}
			// Effects
			EffectsManager.theClass.fixEffects(player);
		} else {
			EffectsManager.theClass.removeEffects(player);
		}
	}
	
}
