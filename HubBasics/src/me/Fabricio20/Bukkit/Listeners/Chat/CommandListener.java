package me.Fabricio20.Bukkit.Listeners.Chat;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Storage.Permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent e){
		String[] args = e.getMessage().split(" ");
		if(args[0].equalsIgnoreCase("/plugins")) {
			if(Main.theClass.config.getBoolean("FakePlugins.Enabled") == true) {
				if(!e.getPlayer().hasPermission(new Permissions().Plugins)) {
					e.getPlayer().sendMessage(Main.theClass.config.getString("FakePlugins.Msg").replace("&", "§").replace("%p", e.getPlayer().getName()));
					e.setCancelled(true);
				}
			}
		} else if(args[0].equalsIgnoreCase("/pl")) {
				if(Main.theClass.config.getBoolean("FakePlugins.Enabled") == true) {
					if(!e.getPlayer().hasPermission(new Permissions().Plugins)) {
						e.getPlayer().sendMessage(Main.theClass.config.getString("FakePlugins.Msg").replace("&", "§").replace("%p", e.getPlayer().getName()));
						e.setCancelled(true);
					}
				}
			}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
