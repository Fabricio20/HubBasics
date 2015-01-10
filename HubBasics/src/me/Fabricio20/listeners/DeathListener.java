package me.Fabricio20.listeners;

import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
	
	@EventHandler
	public void Die(PlayerDeathEvent e) {
		List<String> worlds = Main.theClass.getPlugin().getConfig().getStringList("Worlds");
		if(Main.theClass.getPlugin().getConfig().getBoolean("Others.NoDeathDrops") == true) {
			if(worlds.contains(e.getEntity().getWorld().getName())) {
				e.getDrops().clear();
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
