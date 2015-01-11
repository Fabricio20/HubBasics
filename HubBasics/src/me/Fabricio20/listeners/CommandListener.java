package me.Fabricio20.listeners;

import me.Fabricio20.Main;
import me.Fabricio20.Permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
		if(event.getMessage().toLowerCase().startsWith("/plugins")) {
			if(Main.theClass.config.getBoolean("FakePlugins.Enabled") == true) {
				if(!event.getPlayer().hasPermission(new Permissions().Plugins)) {
					event.getPlayer().sendMessage(Main.theClass.config.getString("FakePlugins.Msg").replace("&", "§").replace("%p", event.getPlayer().getName()));
					event.setCancelled(true);
				}
			}
		} else {
			if(event.getMessage().toLowerCase().startsWith("/pl")) {
				if(Main.theClass.config.getBoolean("FakePlugins.Enabled") == true) {
					if(!event.getPlayer().hasPermission(new Permissions().Plugins)) {
						event.getPlayer().sendMessage(Main.theClass.config.getString("FakePlugins.Msg").replace("&", "§").replace("%p", event.getPlayer().getName()));
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
