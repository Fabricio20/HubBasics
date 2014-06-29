package me.Fabricio20.runnables;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AntiOp extends BukkitRunnable {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public AntiOp(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void run() {
		if(plugin.getConfig().getBoolean("AntiOP") == true) {
		for (Player user : Bukkit.getOnlinePlayers()) {
			if (user.isOp()) {
					user.setOp(false);
					plugin.getServer().getLogger().log(Level.WARNING, "The User " + user.getName() + " Has OP!! REMOVING!");
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}