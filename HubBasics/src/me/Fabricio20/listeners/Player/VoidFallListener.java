package me.Fabricio20.listeners.Player;

import me.Fabricio20.Main;
import me.Fabricio20.methods.ModuleManager;

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
		if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
			Entity ent = event.getEntity();
			if (ent == null) {
				return;
			}
			if (!(ent instanceof Player)) {
				return;
			}
			if ((ent instanceof Player)) {
				if(!Main.theClass.config.getBoolean("VoidFall.OnlyHub")) {
					Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
					loc.setWorld(Bukkit.getWorld(Main.theClass.Hub.getString("Hub.World")));
					loc.setX(Main.theClass.Hub.getInt("Hub.X"));
					loc.setY(Main.theClass.Hub.getInt("Hub.Y"));
					loc.setZ(Main.theClass.Hub.getInt("Hub.Z"));
					loc.setYaw(Main.theClass.Hub.getInt("Hub.Yaw"));
					loc.setPitch(Main.theClass.Hub.getInt("Hub.Pitch"));
					((Player) ent).teleport(loc);
					((Player) ent).setFallDistance(0.0F);
					((Player) ent).sendMessage(Main.theClass.config.getString("VoidFall.Message").replace("&", "§").replace("%p", ((Player) ent).getName()));
					event.setCancelled(true);
					} else if(ModuleManager.theClass.isInWorld((Player)ent)) {
						Location loc = Bukkit.getWorlds().get(0).getSpawnLocation();
						loc.setWorld(Bukkit.getWorld(Main.theClass.Hub.getString("Hub.World")));
						loc.setX(Main.theClass.Hub.getInt("Hub.X"));
						loc.setY(Main.theClass.Hub.getInt("Hub.Y"));
						loc.setZ(Main.theClass.Hub.getInt("Hub.Z"));
						loc.setYaw(Main.theClass.Hub.getInt("Hub.Yaw"));
						loc.setPitch(Main.theClass.Hub.getInt("Hub.Pitch"));
						((Player) ent).teleport(loc);
						((Player) ent).setFallDistance(0.0F);
						((Player) ent).sendMessage(Main.theClass.config.getString("VoidFall.Message").replace("&", "§").replace("%p", ((Player) ent).getName()));
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
}
