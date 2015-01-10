package me.Fabricio20.runnables;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;
import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BossAnnouncer extends BukkitRunnable {

	List<String> Announces = new ArrayList<String>();
	List<String> Worlds = new ArrayList<String>();
	public static int Stamp = 0;
	int Max = 0;
	
	@SuppressWarnings("deprecation")
	public void run() {
		if(Main.theClass.getPlugin().getConfig().getBoolean("BossAnnouncer.Enabled") == true) {
			if(Main.theClass.getPlugin().getConfig().getBoolean("BossAnnouncer.Perworld") == false) {
				Announces = Main.theClass.getPlugin().getConfig().getStringList("BossAnnouncer.Msgs");
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
			} else {
				Announces = Main.theClass.getPlugin().getConfig().getStringList("BossAnnouncer.Msgs");
				Worlds = Main.theClass.getPlugin().getConfig().getStringList("BossAnnouncer.Worlds");
				int Max = Announces.size() - 1;
				if(Stamp == 0) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(Worlds.contains(player.getWorld().getName())) {
							BarAPI.setMessage(player, Announces.get(0).replace("&", "§"));
						}
					}
					Stamp = Stamp + 1;
				} else if(Stamp != 0 && Stamp != Max) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(Worlds.contains(player.getWorld().getName())) {
							BarAPI.setMessage(player, Announces.get(Stamp).replace("&", "§"));
						}
					}
					Stamp = Stamp + 1;
				} else if(Stamp == Max) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(Worlds.contains(player.getWorld().getName()))  {
							BarAPI.setMessage(player, Announces.get(Max).replace("&", "§"));
						}
					}
					Stamp = 0;
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
