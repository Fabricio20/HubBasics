package me.Fabricio20.listeners.Player;

import me.Fabricio20.Storage.Storage;
import me.Fabricio20.methods.ModuleManager;
import me.Fabricio20.methods.Managers.EffectsManager;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinSettings implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Storage.playerSettings.put(player.getName(), SettingsManager.theClass.getSettings(player.getName()));
		if(ModuleManager.theClass.isInWorld(player)) {
			if(!SettingsManager.theClass.isPlayersEnabled(player.getName())) {
				//TODO: MagicClock Also Players Disabled!
			}
			// Effects
			EffectsManager.theClass.fixEffects(player);
		}
	}
	
}
