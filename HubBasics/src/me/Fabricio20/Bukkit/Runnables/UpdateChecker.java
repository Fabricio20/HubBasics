package me.Fabricio20.Bukkit.Runnables;

import me.Fabricio20.Bukkit.API.UpdateAPI;

import org.bukkit.scheduler.BukkitRunnable;

public class UpdateChecker extends BukkitRunnable {

	@Override
	public void run() {
		UpdateAPI.silentCheck();
	}
	
}
