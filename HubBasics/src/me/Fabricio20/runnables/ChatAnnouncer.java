package me.Fabricio20.runnables;

import me.Fabricio20.Strings;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatAnnouncer extends BukkitRunnable {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public ChatAnnouncer(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void run() {
		if(plugin.getConfig().getBoolean("UseAnnouncer") == true) {
			if(Strings.ChatAnnIndex == 0) {
				if(!(plugin.getConfig().getString("ChatAnnouncerMsg1").equalsIgnoreCase("null"))) {
				Bukkit.broadcastMessage(plugin.getConfig().getString("ChatAnnouncerMsg1").replace("&", "§"));
				}
				Strings.ChatAnnIndex = 1;
			} else {
				if(Strings.ChatAnnIndex == 1) {
					if(!(plugin.getConfig().getString("ChatAnnouncerMsg2").equalsIgnoreCase("null"))) {
					Bukkit.broadcastMessage(plugin.getConfig().getString("ChatAnnouncerMsg2").replace("&", "§"));
					}
					Strings.ChatAnnIndex = 2;
				} else {
					if(Strings.ChatAnnIndex == 2) {
						if(!(plugin.getConfig().getString("ChatAnnouncerMsg3").equalsIgnoreCase("null"))) {
						Bukkit.broadcastMessage(plugin.getConfig().getString("ChatAnnouncerMsg3").replace("&", "§"));
						}
						Strings.ChatAnnIndex = 3;
					} else {
						if(Strings.ChatAnnIndex == 3) {
							if(!(plugin.getConfig().getString("ChatAnnouncerMsg4").equalsIgnoreCase("null"))) {
							Bukkit.broadcastMessage(plugin.getConfig().getString("ChatAnnouncerMsg4").replace("&", "§"));
							}
							Strings.ChatAnnIndex = 4;
						} else {
							if(Strings.ChatAnnIndex == 4) {
								if(!(plugin.getConfig().getString("ChatAnnouncerMsg5").equalsIgnoreCase("null"))) {
								Bukkit.broadcastMessage(plugin.getConfig().getString("ChatAnnouncerMsg5").replace("&", "§"));
								}
								Strings.ChatAnnIndex = 0;
							} else {
								if(Strings.ChatAnnIndex > 4) {
									Strings.ChatAnnIndex = 0;
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
