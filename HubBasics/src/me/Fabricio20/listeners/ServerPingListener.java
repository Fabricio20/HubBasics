package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

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
	
	List<String> Motds = new ArrayList<String>();

	int Stamp = 0;
	
	@EventHandler(priority = EventPriority.HIGH)
	public void List(ServerListPingEvent e) {
		Motds = plugin.getConfig().getStringList("MotdSystem.Motds");
		if(plugin.getConfig().getBoolean("MotdSystem.CustomMotd") == true) {
			if(plugin.getConfig().getBoolean("MotdSystem.MoreMotds") == true) {
				if(Stamp == 0) {
					e.setMotd(Motds.get(0).replace("&", "§"));
					Stamp = Stamp + 1;
				} else if (Stamp != 0 && Stamp != Motds.size() - 1) {
					e.setMotd(Motds.get(Stamp).replace("&", "§"));
					Stamp = Stamp + 1;
				} else if (Stamp == Motds.size() - 1) {
					int max = Motds.size() - 1;
					e.setMotd(Motds.get(max).replace("&", "§"));
					Stamp = 0;
				}
			} else {
				e.setMotd(Motds.get(0).replace("&", "§"));
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
