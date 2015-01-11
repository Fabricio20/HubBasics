package me.Fabricio20.listeners;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {
	
	List<String> Motds = new ArrayList<String>();

	int Stamp = 0;
	
	@EventHandler(priority = EventPriority.HIGH)
	public void List(ServerListPingEvent e) {
		Motds = Main.theClass.config.getStringList("MotdSystem.Motds");
		if(Main.theClass.config.getBoolean("MotdSystem.CustomMotd") == true) {
			if(Main.theClass.config.getBoolean("MotdSystem.MoreMotds") == true) {
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
