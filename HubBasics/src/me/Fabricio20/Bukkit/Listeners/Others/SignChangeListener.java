package me.Fabricio20.Bukkit.Listeners.Others;

import me.Fabricio20.Bukkit.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
	
	@EventHandler
	public void SignChange(SignChangeEvent e) {
		String line = e.getLine(0);
		if(line.equalsIgnoreCase("[Site]") || line.equalsIgnoreCase("[Website]")) {
			e.setLine(0, "§1[WebSite]");
			e.setLine(1, Main.theClass.config.getString("WebSiteLink").replace("&", "§"));
			e.setLine(2, Main.theClass.config.getString("ClickHereTo").replace("&", "§"));
			e.setLine(3, Main.theClass.config.getString("SeeOurSiteLink").replace("&", "§"));
		}
		
		if(line.equalsIgnoreCase("[TS3]")) {
			e.setLine(0, "§1[TS3]");
			e.setLine(1, Main.theClass.config.getString("TeamSpeakIP").replace("&", "§"));
			e.setLine(2, Main.theClass.config.getString("ClickHereToTS").replace("&", "§"));
			e.setLine(3, Main.theClass.config.getString("SeeOurTSIP").replace("&", "§"));
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
