package me.Fabricio20.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LeaveListener implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public LeaveListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@EventHandler(priority = EventPriority.HIGH)
	public void Leave(PlayerQuitEvent e) {
		if(plugin.getConfig().getBoolean("LeaveEvents.DisableLeaveMessage") == true) {
			e.setQuitMessage(null);
		} else {
			e.setQuitMessage(plugin.getConfig().getString("LeaveEvents.Message").replace("&", "§").replace("%p", e.getPlayer().getName()));
		}
		if(plugin.getConfig().getBoolean("LeaveEvents.SilentOpLeave") == true) {
			if(e.getPlayer().isOp()) {
				e.setQuitMessage(null);
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
