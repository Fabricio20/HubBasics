package me.Fabricio20.Bukkit.Listeners.Veichle;

import me.Fabricio20.Bukkit.Listeners.Player.RightClickListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class VeichleLeaveListener implements Listener {
	
	@EventHandler
	public void onVLeave(VehicleExitEvent e) {
		if(RightClickListener.players.contains(e.getExited())) {
			RightClickListener.players.remove(e.getExited());
		}
	}
	
}
