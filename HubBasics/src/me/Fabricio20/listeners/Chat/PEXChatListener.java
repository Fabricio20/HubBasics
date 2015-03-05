package me.Fabricio20.listeners.Chat;

import me.Fabricio20.Main;
import me.Fabricio20.Storage.Permissions;
import me.Fabricio20.methods.ModuleManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PEXChatListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		PermissionUser user = PermissionsEx.getUser(player);
		String prefix = user.getPrefix().replace("&", "§");
		String msg = e.getMessage();
		String world = player.getWorld().getName();
		if(!e.isCancelled()) {
			if(ModuleManager.theClass.isEnabled("ChatSystem")) {
				if(Main.theClass.config.getBoolean("ChatSystem.PerWorld")) {
					e.setCancelled(true);
					if(Main.theClass.config.getBoolean("ChatSystem.AllowColors") && player.hasPermission(new Permissions().ChatColor)) {
						msg = msg.replace("&", "§");
					}
					String cf = Main.theClass.config.getString("ChatSystem.Format").replace("&", "§")
							.replace("%p", player.getName()).replace("%m", msg).replace("$group", prefix).replace("%w", world);
					for(Player ps : Bukkit.getOnlinePlayers()) {
						if(ps.getWorld().getName().equals(player.getWorld().getName())) {
							ps.sendMessage(cf);
						}
					}
				} else {
					e.setCancelled(true);
					if(Main.theClass.config.getBoolean("ChatSystem.AllowColors") && player.hasPermission(new Permissions().ChatColor)) {
						msg = msg.replace("&", "§");
					}
					String cf = Main.theClass.config.getString("ChatSystem.Format").replace("&", "§")
							.replace("%p", player.getName()).replace("%m", msg).replace("$group", prefix).replace("%w", world);
					Bukkit.broadcastMessage(cf);
				}
			}
		}
	}
	
}
