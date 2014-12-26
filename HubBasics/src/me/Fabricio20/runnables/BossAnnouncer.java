package me.Fabricio20.runnables;

import java.util.ArrayList;
import java.util.List;

import me.confuser.barapi.BarAPI;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossAnnouncer extends BukkitRunnable {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public BossAnnouncer(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	List<String> Announces = new ArrayList<String>();
	public static int Stamp = 0;
	int Max = 0;
	
	public void run() {
		if(plugin.getConfig().getBoolean("BossAnnouncer.Enabled") == true) {
			Announces = plugin.getConfig().getStringList("BossAnnouncer.Msgs");
			int Max = Announces.size() - 1;
			if(Stamp == 0) {
				BarAPI.setMessage(Announces.get(0).replace("&", "§"));
				Stamp = Stamp + 1;
			} else if(Stamp != 0 && Stamp != Max) {
				BarAPI.setMessage(Announces.get(Stamp).replace("&", "§"));
				Stamp = Stamp + 1;
			} else if(Stamp == Max) {
				BarAPI.setMessage(Announces.get(Max).replace("&", "§"));
				Stamp = 0;
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
