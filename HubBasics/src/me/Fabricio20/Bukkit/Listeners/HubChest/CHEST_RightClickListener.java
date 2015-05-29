package me.Fabricio20.Bukkit.Listeners.HubChest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Constructors.HubChest;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CHEST_RightClickListener implements Listener {
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			for(String name: getAllChests()) {
				HubChest chest = new HubChest(name);
				if(chest.getRightClick()) {
					Material mat = chest.getItem();
					if(e.getPlayer().getItemInHand().getType().equals(mat)) {
						e.getPlayer().openInventory(chest.getInventory());
					}
				}
			}
		}
	}
	
	private List<String> getAllChests() {
		ArrayList<String> files = new ArrayList<String>();
		File folder = new File(Main.theClass.getDataFolder() + File.separator + "Chests");
		for(File fileEntry : folder.listFiles()) {
		    files.add(fileEntry.getName().toLowerCase().replace(".yml", ""));
		}
		return files;
	}
	
}
