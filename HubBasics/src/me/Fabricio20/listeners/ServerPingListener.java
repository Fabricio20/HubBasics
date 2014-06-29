package me.Fabricio20.listeners;

import me.Fabricio20.Strings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerPingListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public ServerPingListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler(priority = EventPriority.HIGH)
	public void List(ServerListPingEvent e) {
		if(plugin.getConfig().getBoolean("CustomMotd") == true) {
			if(plugin.getConfig().getBoolean("2Motds") == true) {
				if(Strings.MOTD == 0) {
					e.setMotd(plugin.getConfig().getString("MOTD1").replace("&", "§"));
					Strings.MOTD = 1;
				} else {
					if(Strings.MOTD == 1) {
						e.setMotd(plugin.getConfig().getString("MOTD2").replace("&", "§"));
					} else {
						if(Strings.MOTD > 1) {
							Strings.MOTD = 0;
						}
					}
				}
			} else {
				e.setMotd(plugin.getConfig().getString("MOTD1").replace("&", "§"));
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
