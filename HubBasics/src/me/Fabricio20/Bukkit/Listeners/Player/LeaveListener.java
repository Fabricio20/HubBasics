package me.Fabricio20.Bukkit.Listeners.Player;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Storage.Strings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void Leave(PlayerQuitEvent e) {
		if(Main.theClass.config.getBoolean("LeaveEvents.DisableLeaveMessage") == true) {
			e.setQuitMessage(null);
		} else {
			e.setQuitMessage(Main.theClass.config.getString("LeaveEvents.Message").replace("&", "§").replace("%p", e.getPlayer().getName()));
		}
		if(Main.theClass.config.getBoolean("LeaveEvents.SilentOpLeave") == true) {
			if(e.getPlayer().isOp()) {
				e.setQuitMessage(null);
			}
		}
		for(Player user : Bukkit.getOnlinePlayers()) {
			if(Strings.MagicClockActive.contains(user)) {
				if(user.canSee(e.getPlayer()) == false) {
					user.showPlayer(e.getPlayer());
				}
			}
		}
		if(Strings.MagicClockActive.contains(e.getPlayer())) {
			for(Player users : Bukkit.getOnlinePlayers()) {
				if(e.getPlayer().canSee(users) == false) {
					e.getPlayer().showPlayer(users);
				}
			}
			Strings.MagicClockActive.remove(e.getPlayer());
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
