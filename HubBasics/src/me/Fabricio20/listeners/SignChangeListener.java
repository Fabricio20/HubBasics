package me.Fabricio20.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SignChangeListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public SignChangeListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void SignChange(SignChangeEvent e) {
		String line = e.getLine(0);
		if(line.equalsIgnoreCase("[Site]")) {
			e.setLine(0, "§1[WebSite]");
			e.setLine(1, plugin.getConfig().getString("WebSiteLink").replace("&", "§"));
			e.setLine(2, plugin.getConfig().getString("ClickHereTo").replace("&", "§"));
			e.setLine(3, plugin.getConfig().getString("SeeOurSiteLink").replace("&", "§"));
		}
		
		if(line.equalsIgnoreCase("[TS3]")) {
			e.setLine(0, "§1[TS3]");
			e.setLine(1, plugin.getConfig().getString("TeamSpeakIP").replace("&", "§"));
			e.setLine(2, plugin.getConfig().getString("ClickHereToTS").replace("&", "§"));
			e.setLine(3, plugin.getConfig().getString("SeeOurTSIP").replace("&", "§"));
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
