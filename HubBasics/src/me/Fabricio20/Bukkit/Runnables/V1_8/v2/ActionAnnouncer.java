package me.Fabricio20.Bukkit.Runnables.V1_8.v2;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.API.V1_8.v2.ActionAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionAnnouncer extends BukkitRunnable {

	List<String> Announces = new ArrayList<String>();
	List<String> Worlds = new ArrayList<String>();
	public static int Stamp = 0;
	int Max = 0;

	@SuppressWarnings("deprecation")
	public void run() {
		if (Main.theClass.config.getBoolean("ActionAnnouncer.Enabled") == true) {
			if (Main.theClass.config.getBoolean("ActionAnnouncer.Perworld") == false) {
				Announces = Main.theClass.config.getStringList("ActionAnnouncer.Msgs");
				int Max = Announces.size() - 1;
				if (Stamp == 0) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						ActionAPI.sendAction(player, Announces.get(0).replace("&", "§"));
					}
					Stamp++;
				} else if (Stamp != 0 && Stamp != Max) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						ActionAPI.sendAction(player, Announces.get(Stamp).replace("&", "§"));
					}
					Stamp++;
				} else if (Stamp == Max) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						ActionAPI.sendAction(player, Announces.get(Max).replace("&", "§"));
					}
					Stamp = 0;
				}
			} else {
				Announces = Main.theClass.config.getStringList("ActionAnnouncer.Msgs");
				Worlds = Main.theClass.config.getStringList("ActionAnnouncer.Worlds");
				int Max = Announces.size() - 1;
				if (Stamp == 0) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (Worlds.contains(player.getWorld().getName())) {
							ActionAPI.sendAction(player, Announces.get(0).replace("&", "§"));
						}
					}
					Stamp++;
				} else if (Stamp != 0 && Stamp != Max) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (Worlds.contains(player.getWorld().getName())) {
							ActionAPI.sendAction(player, Announces.get(Stamp).replace("&", "§"));
						}
					}
					Stamp++;
				} else if (Stamp == Max) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (Worlds.contains(player.getWorld().getName())) {
							ActionAPI.sendAction(player, Announces.get(Max).replace("&", "§"));
						}
					}
					Stamp = 0;
				}
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
