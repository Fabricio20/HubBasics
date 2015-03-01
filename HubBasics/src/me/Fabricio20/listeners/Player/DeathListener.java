package me.Fabricio20.listeners.Player;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
	
	@EventHandler
	public void Die(PlayerDeathEvent e) {
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if(Main.theClass.config.getBoolean("Others.NoDeathDrops") == true) {
			if(worlds.contains(e.getEntity().getWorld().getName())) {
				e.getDrops().clear();
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
