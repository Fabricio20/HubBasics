package me.Fabricio20.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;
	 
	 public JoinListener(JavaPlugin plugin) {
	     this.plugin = plugin;
	 }
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 @EventHandler(priority = EventPriority.HIGH)
	 public void Join(PlayerJoinEvent e) {
		 if(plugin.getConfig().getBoolean("DisableJoinMessage") == true) {
			 e.setJoinMessage(null);
		 } else {
			 if(plugin.getConfig().getBoolean("SilentOpJoin") == true) {
				 if(e.getPlayer().isOp()) {
					 e.setJoinMessage(null);
				 }
			 }
			 e.setJoinMessage(plugin.getConfig().getString("JoinMessage").replace("&", "§").replace("%p", e.getPlayer().getName()));
		 }
	 }
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
