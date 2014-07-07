package me.Fabricio20.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class KeppFoodAndHealth extends BukkitRunnable {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private final JavaPlugin plugin;

	public KeppFoodAndHealth(JavaPlugin plugin) {
		this.plugin = plugin;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void run() {
		if(plugin.getConfig().getBoolean("KeepFoodHealth") == true) {
			for(Player user : Bukkit.getOnlinePlayers()) {
				user.setHealth(20.0);
				user.setFoodLevel(20);
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
