package me.Fabricio20.listeners;

import me.Fabricio20.Main;
import me.Fabricio20.methods.CustomConfigs;

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
		if(Main.theClass.getPlugin().getConfig().getBoolean("VoidFall.Enabled") == true) {
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
				loc.setWorld(Bukkit.getWorld(CustomConfigs.getStorageConfig().getString("Hub.World")));
				loc.setX(CustomConfigs.getStorageConfig().getInt("Hub.X"));
				loc.setY(CustomConfigs.getStorageConfig().getInt("Hub.Y"));
				loc.setZ(CustomConfigs.getStorageConfig().getInt("Hub.Z"));
				loc.setYaw(CustomConfigs.getStorageConfig().getInt("Hub.Yaw"));
				loc.setPitch(CustomConfigs.getStorageConfig().getInt("Hub.Pitch"));
				((Player) ent).teleport(loc);
				((Player) ent).setFallDistance(0.0F);
				((Player) ent).sendMessage(Main.theClass.getPlugin().getConfig().getString("VoidFall.Message").replace("&", "§").replace("%p", ((Player) ent).getName()));
				event.setCancelled(true);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
}
