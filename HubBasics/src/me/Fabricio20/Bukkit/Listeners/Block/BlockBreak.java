package me.Fabricio20.Bukkit.Listeners.Block;

import me.Fabricio20.Bukkit.Storage.Permissions;
import me.Fabricio20.Bukkit.Methods.ModuleManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
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