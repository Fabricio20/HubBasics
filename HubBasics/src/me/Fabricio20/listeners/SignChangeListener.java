package me.Fabricio20.listeners;

import me.Fabricio20.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
	
	@EventHandler
	public void SignChange(SignChangeEvent e) {
		String line = e.getLine(0);
		if(line.equalsIgnoreCase("[Site]") || line.equalsIgnoreCase("[Website]")) {
			e.setLine(0, "§1[WebSite]");
			e.setLine(1, Main.theClass.getPlugin().getConfig().getString("WebSiteLink").replace("&", "§"));
			e.setLine(2, Main.theClass.getPlugin().getConfig().getString("ClickHereTo").replace("&", "§"));
			e.setLine(3, Main.theClass.getPlugin().getConfig().getString("SeeOurSiteLink").replace("&", "§"));
		}
		
		if(line.equalsIgnoreCase("[TS3]")) {
			e.setLine(0, "§1[TS3]");
			e.setLine(1, Main.theClass.getPlugin().getConfig().getString("TeamSpeakIP").replace("&", "§"));
			e.setLine(2, Main.theClass.getPlugin().getConfig().getString("ClickHereToTS").replace("&", "§"));
			e.setLine(3, Main.theClass.getPlugin().getConfig().getString("SeeOurTSIP").replace("&", "§"));
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
