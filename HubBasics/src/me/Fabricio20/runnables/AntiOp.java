package me.Fabricio20.runnables;

import java.util.logging.Level;

import me.Fabricio20.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AntiOp extends BukkitRunnable {

	@SuppressWarnings("deprecation")
	public void run() {
		if(Main.getPlugin().getConfig().getBoolean("Others.AntiOP") == true) {
		for (Player user : Bukkit.getOnlinePlayers()) {
			if (user.isOp()) {
					user.setOp(false);
					Main.getPlugin().getServer().getLogger().log(Level.WARNING, "The User " + user.getName() + " Has OP!! REMOVING!");
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}