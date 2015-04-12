package me.Fabricio20.Bukkit.Listeners.Player;

import me.Fabricio20.Bukkit.Storage.Storage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveSettings implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if(Storage.playerSettings.containsKey(player.getName())) {
			Storage.playerSettings.remove(player.getName());
		}
	}
	
}
