package me.Fabricio20.listeners;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
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
	 
	File f = new File("/plugins/HubBasics/Location.yml");
	YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
	
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
			 if(plugin.getConfig().getBoolean("HubAtLogin") == true) {
				 int x = yamlFile.getInt("Hub.x"), y = yamlFile.getInt("Hub.y"), z = yamlFile.getInt("Hub.z");
				 e.getPlayer().teleport(new Location(plugin.getServer().getWorlds().get(0),x,y,z));
			 }
			 e.setJoinMessage(plugin.getConfig().getString("JoinMessage").replace("&", "§").replace("%p", e.getPlayer().getName()));
		 }
	 }
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
