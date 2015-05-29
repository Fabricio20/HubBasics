package me.Fabricio20.Bukkit.Listeners.HubChest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Constructors.HubChest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CHEST_Command implements Listener {
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage();
		for(String s: getAllChests()) {
			HubChest chest = new HubChest(s);
			if(cmd.startsWith("/" + chest.getCommand())) {
				e.setCancelled(true);
				e.getPlayer().chat("/" + chest.getCommand());
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
