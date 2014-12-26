package me.Fabricio20.listeners;

import me.Fabricio20.Permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandListener implements Listener {	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public CommandListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
		if(event.getMessage().toLowerCase().startsWith("/plugins")) {
			if(plugin.getConfig().getBoolean("FakePlugins.Enabled") == true) {
				if(!event.getPlayer().hasPermission(new Permissions().Plugins)) {
					event.getPlayer().sendMessage(plugin.getConfig().getString("FakePlugins.Msg").replace("&", "§").replace("%p", event.getPlayer().getName()));
					event.setCancelled(true);
				}
			}
		} else {
			if(event.getMessage().toLowerCase().startsWith("/pl")) {
				if(plugin.getConfig().getBoolean("FakePlugins.Enabled") == true) {
					if(!event.getPlayer().hasPermission(new Permissions().Plugins)) {
						event.getPlayer().sendMessage(plugin.getConfig().getString("FakePlugins.Msg").replace("&", "§").replace("%p", event.getPlayer().getName()));
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
