package me.Fabricio20.listeners;

import me.Fabricio20.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidFallListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void Void(EntityDamageEvent event) {
		if(Main.theClass.config.getBoolean("VoidFall.Enabled") == true) {
		if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
			Entity ent = event.getEntity();
			if (ent == null) {
				return;
			}
			if (!(ent instanceof Player)) {
				return;
			}
			if ((ent instanceof Player)) {
				Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
				loc.setWorld(Bukkit.getWorld(Main.theClass.Storage.getString("Hub.World")));
				loc.setX(Main.theClass.Storage.getInt("Hub.X"));
				loc.setY(Main.theClass.Storage.getInt("Hub.Y"));
				loc.setZ(Main.theClass.Storage.getInt("Hub.Z"));
				loc.setYaw(Main.theClass.Storage.getInt("Hub.Yaw"));
				loc.setPitch(Main.theClass.Storage.getInt("Hub.Pitch"));
				((Player) ent).teleport(loc);
				((Player) ent).setFallDistance(0.0F);
				((Player) ent).sendMessage(Main.theClass.config.getString("VoidFall.Message").replace("&", "§").replace("%p", ((Player) ent).getName()));
				event.setCancelled(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
}
