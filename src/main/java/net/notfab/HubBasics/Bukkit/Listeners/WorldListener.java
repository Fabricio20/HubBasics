package net.notfab.HubBasics.Bukkit.Listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.json.JSONObject;

import net.notfab.HubBasics.Bukkit.HubBasics;

public class WorldListener implements Listener {
	
	public WorldListener() {
		File folder = new File("plugins/HubBasics/Worlds");
		if(!folder.exists()) {
			folder.mkdirs();
		}
		for(World w: Bukkit.getWorlds()) {
			File f = new File("plugins/HubBasics/Worlds/" + w.getName() + ".json");
			if(!f.exists()) {
				JSONObject o = new JSONObject();
				o.put("Anti-Void", true);
				o.put("Anti-Rain", true);
				o.put("Keep-Food", true);
				o.put("Keep-Health", true);
				o.put("Double-Jump", true);
				o.put("Stacker", true);
				HubBasics.getInstance().getConfigManager().writeFile(f, o);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntityType().equals(EntityType.PLAYER) && !e.getCause().equals(DamageCause.VOID)) {
			File f = new File("plugins/HubBasics/Worlds/" + e.getEntity().getWorld().getName() + ".json");
			JSONObject _World = HubBasics.getInstance().getConfigManager().readFile(f);
			if(_World.getBoolean("Keep-Health")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		if(e.getFoodLevel() != 20) {
			File f = new File("plugins/HubBasics/Worlds/" + e.getEntity().getWorld().getName() + ".json");
			JSONObject _World = HubBasics.getInstance().getConfigManager().readFile(f);
			if(_World.getBoolean("Keep-Food")) {
				e.setFoodLevel(20);
			}
		}
	}
	
	@EventHandler
	public void onRain(WeatherChangeEvent e) {
		if(e.toWeatherState()) {
			File f = new File("plugins/HubBasics/Worlds/" + e.getWorld().getName() + ".json");
			JSONObject _World = HubBasics.getInstance().getConfigManager().readFile(f);
			if(_World.getBoolean("Anti-Rain")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onVoid(EntityDamageEvent e) {
		if(e.getEntityType().equals(EntityType.PLAYER) && e.getCause().equals(DamageCause.VOID)) {
			File f = new File("plugins/HubBasics/Worlds/" + e.getEntity().getWorld().getName() + ".json");
			JSONObject _World = HubBasics.getInstance().getConfigManager().readFile(f);
			if(_World.getBoolean("Anti-Void")) {
				Player player = (Player) e.getEntity();
				e.setDamage(0.0);
				player.setFallDistance(0);
				//
				JSONObject _Config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
				JSONObject _Hub = _Config.getJSONObject("Hub");
				//
				if(_Hub.getBoolean("Enabled")) {
					if(_Hub.getBoolean("BungeeCord")) {
						player.teleport(player.getWorld().getSpawnLocation());
					} else {
						World world = Bukkit.getWorld(_Hub.getJSONObject("Location").getString("World"));
						Double x = _Hub.getJSONObject("Location").getDouble("X");
						Double y = _Hub.getJSONObject("Location").getDouble("Y");
						Double z = _Hub.getJSONObject("Location").getDouble("Z");
						Double yaw = _Hub.getJSONObject("Location").getDouble("Yaw");
						Double pitch = _Hub.getJSONObject("Location").getDouble("Pitch");
						if(world == null) {
							player.sendMessage("§cError§7: Invalid Lobby World.");
							player.teleport(player.getWorld().getSpawnLocation());
							return;
						}
						Location loc = new Location(world, x, y, z, Float.valueOf(yaw.toString()), Float.valueOf(pitch.toString()));
						player.teleport(loc);
					}
				} else {
					player.teleport(player.getWorld().getSpawnLocation());
				}
			}
		}
	}
	
}
