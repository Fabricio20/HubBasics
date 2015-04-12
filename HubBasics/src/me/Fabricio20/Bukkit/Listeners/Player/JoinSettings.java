package me.Fabricio20.Bukkit.Listeners.Player;

import me.Fabricio20.Bukkit.API.MagicClockAPI;
import me.Fabricio20.Bukkit.Storage.Storage;
import me.Fabricio20.Bukkit.Methods.ModuleManager;
import me.Fabricio20.Bukkit.Methods.Managers.EffectsManager;
import me.Fabricio20.Bukkit.Methods.Managers.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinSettings implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Storage.playerSettings.put(player.getName(), SettingsManager.theClass.getSettings(player.getName()));
		MagicClockAPI.theClass.toggleClock(player);
		if(ModuleManager.theClass.isInWorld(player)) {
			EffectsManager.theClass.fixEffects(player);
		}
	}
	
}
