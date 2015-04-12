package me.Fabricio20.Bukkit.Listeners.Block;

import me.Fabricio20.Bukkit.Storage.Permissions;
import me.Fabricio20.Bukkit.Methods.ModuleManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if(!ModuleManager.theClass.isEnabled("Building")) {
			if(ModuleManager.theClass.isInWorld(player)) {
				if(!player.hasPermission(new Permissions().EditWorld)) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
