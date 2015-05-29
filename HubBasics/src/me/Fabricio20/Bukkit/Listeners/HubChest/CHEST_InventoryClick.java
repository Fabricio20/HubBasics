package me.Fabricio20.Bukkit.Listeners.HubChest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Constructors.HubChest;
import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfig;
import me.Fabricio20.Bukkit.Utils.InventoryUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CHEST_InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getClickedInventory() != null) {
			for(String s: getAllChests()) {
				HubChest chest = new HubChest(s);
				if(chest.getContents() != null) {
					SimpleConfig config = chest.getConfig();
					for(String d: chest.getContents().keySet()) {
						ItemStack stack = chest.getContents().get(d);
						if(e.getCurrentItem().equals(stack)) {
							e.setCancelled(true);
							Player player = (Player) e.getWhoClicked();
							if(config.contains("Items." + d + ".Command")) {
								player.chat(InventoryUtils.parseString("Items." + d + ".Command", config));
							}
							if(config.contains("Items." + d + ".Server")) {
								sendToServer(player, config.getString("Items." + d + ".Server"));
							}
						}
					}
				}
			}
		}
	}
	
	private void sendToServer(Player player, String targetServer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(targetServer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(Main.theClass.getPlugin(), "BungeeCord", b.toByteArray());
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
