package me.Fabricio20.listeners;

import me.Fabricio20.Strings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		if (Strings.MagicClockActive.contains(e.getPlayer())) {
			for (Player user : Bukkit.getOnlinePlayers()) {
				if (e.getPlayer().canSee(user) == false) {
					e.getPlayer().showPlayer(user);
				}
			}
		}
	}

}
