package me.Fabricio20.listeners.Block;

import me.Fabricio20.Storage.Permissions;
import me.Fabricio20.methods.ModuleManager;

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
