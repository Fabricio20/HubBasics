package me.Fabricio20.runnables;

import me.Fabricio20.Strings;
import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossAnnouncer extends BukkitRunnable {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public BossAnnouncer(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("deprecation")
	public void run() {
		if(plugin.getConfig().getBoolean("UseAnnouncer") == true && plugin.getConfig().getBoolean("1.8Fix") == false) {
			if(Strings.BossAnnIndex == 0) {
				if (!(plugin.getConfig().getString("BossAnnouncerMsg1").equalsIgnoreCase("null"))) {
					for (Player user : Bukkit.getOnlinePlayers()) {
						BarAPI.setMessage(user, plugin.getConfig().getString("BossAnnouncerMsg1").replace("&", "§"), 61);
					}
				Strings.BossAnnIndex = 1;
				}
			} else {
				if(Strings.BossAnnIndex == 1) {
					if(!(plugin.getConfig().getString("BossAnnouncerMsg2").equalsIgnoreCase("null"))) {
						for (Player user : Bukkit.getOnlinePlayers()) {
							BarAPI.setMessage(user, plugin.getConfig().getString("BossAnnouncerMsg2").replace("&", "§"), 61);
						}
					Strings.BossAnnIndex = 2;
					}
				} else {
					if(Strings.BossAnnIndex == 2) {
						if(!(plugin.getConfig().getString("BossAnnouncerMsg3").equalsIgnoreCase("null"))) {
							for (Player user : Bukkit.getOnlinePlayers()) {
								BarAPI.setMessage(user, plugin.getConfig().getString("BossAnnouncerMsg3").replace("&", "§"), 61);
							}
						Strings.BossAnnIndex = 3;
						}
					} else {
						if(Strings.BossAnnIndex == 3) {
							if(!(plugin.getConfig().getString("BossAnnouncerMsg4").equalsIgnoreCase("null"))) {
								for (Player user : Bukkit.getOnlinePlayers()) {
									BarAPI.setMessage(user, plugin.getConfig().getString("BossAnnouncerMsg4").replace("&", "§"), 61);
								}
							Strings.BossAnnIndex = 4;
							}
						} else {
							if(Strings.BossAnnIndex == 4) {
								if(!(plugin.getConfig().getString("BossAnnouncerMsg5").equalsIgnoreCase("null"))) {
									for (Player user : Bukkit.getOnlinePlayers()) {
										BarAPI.setMessage(user, plugin.getConfig().getString("BossAnnouncerMsg5").replace("&", "§"), 61);
									}
								Strings.BossAnnIndex = 0;
								}
							} else {
								if(Strings.BossAnnIndex > 4) {
									Strings.BossAnnIndex = 0;
								}
							}
						}
					}
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
