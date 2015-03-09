package me.Fabricio20.listeners.Player;

import me.Fabricio20.Main;
import me.Fabricio20.methods.ModuleManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
	
	@EventHandler
	public void Die(PlayerDeathEvent e) {
		if(Main.theClass.config.getBoolean("Others.NoDeathDrops") == true) {
			if(ModuleManager.theClass.isInWorld(e.getEntity())) {
				e.getDrops().clear();
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
