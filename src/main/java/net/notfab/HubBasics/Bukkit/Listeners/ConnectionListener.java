package net.notfab.HubBasics.Bukkit.Listeners;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.json.JSONObject;

import com.google.common.base.Optional;

import net.md_5.bungee.api.ChatColor;
import net.notfab.HubBasics.Bukkit.HubBasics;
import net.notfab.HubBasics.Bukkit.NMS.Title;

public class ConnectionListener implements Listener {
	
	@EventHandler
	public void onPre(AsyncPlayerPreLoginEvent e) {
		JSONObject o = HubBasics.getInstance().getProfileManager().getProfile(e.getUniqueId(), Optional.of(e.getName()));
		o.has(""); //TODO
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		JSONObject config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
		if(config.getJSONObject("JoinMessage").getBoolean("Enabled")) {
			if(config.getJSONObject("JoinMessage").get("Message") == null) {
				e.setJoinMessage(null);
			} else {
				e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', config.getJSONObject("JoinMessage").getString("Message").replace("%player%", e.getPlayer().getName())));
			}
		}
		
		if(config.getJSONObject("JoinTitle").getBoolean("Enabled")) {
			String title = config.getJSONObject("JoinTitle").getString("Title") == null ? "" : config.getJSONObject("JoinTitle").getString("Title");
			String subtitle = config.getJSONObject("JoinTitle").getString("Subtitle") == null ? "" : config.getJSONObject("JoinTitle").getString("Subtitle");
			if(!(title.equals("") && subtitle.equals(""))) {
				title = title.replace("%player%", e.getPlayer().getName());
				subtitle = subtitle.replace("%player%", e.getPlayer().getName());
				Title t = new Title(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle));
				t.send(e.getPlayer());
			}
		}
		
		if(HubBasics.getInstance().getBossAnnouncer().getEnabled()) {
			HubBasics.getInstance().getBossAnnouncer().getBossBar().addPlayer(e.getPlayer());
		}
		
		//
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		JSONObject config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
		if(config.getJSONObject("LeaveMessage").getBoolean("Enabled")) {
			if(config.getJSONObject("LeaveMessage").get("Message") == null) {
				e.setQuitMessage(null);
			} else {
				e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', config.getJSONObject("LeaveMessage").getString("Message").replace("%player%", e.getPlayer().getName())));
			}
		}
		
		if(HubBasics.getInstance().getBossAnnouncer().getEnabled()) {
			HubBasics.getInstance().getBossAnnouncer().getBossBar().removePlayer(e.getPlayer());
		}
		
		//
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		//TODO
	}
	
}
