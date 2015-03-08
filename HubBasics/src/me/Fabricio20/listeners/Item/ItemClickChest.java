package me.Fabricio20.listeners.Item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Set;

import me.Fabricio20.Main;
import me.Fabricio20.API.LanguageAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemClickChest implements Listener {
	
	public void sendToServer(Player player, String targetServer) {
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
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() != null) {
			if(e.getClickedInventory().getName().equals(LanguageAPI.theClass.QWarpChestName(player.getName(), player.getWorld().getName()))) {
				if(e.getCurrentItem().hasItemMeta()) {
					Set<String> s = Main.theClass.QuickWarpChest.getConfigurationSection("Items").getKeys(false);
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if(meta.hasDisplayName()) {
						for(String st : s) {
							if(Main.theClass.QuickWarpChest.getString("Items." + st + ".Name").equals(meta.getDisplayName().replace("§", "&"))) {
								if(Main.theClass.QuickWarpChest.getBoolean("Items." + st + ".UsePerm")) {
									if(player.hasPermission(Main.theClass.QuickWarpChest.getString("Items." + st + ".Permission"))) {
										if(Main.theClass.QuickWarpChest.getBoolean("Items." + st + ".Console")) {
											if(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").startsWith("SERVER:")) {
												String[] serverName = Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").split(":");
												sendToServer(player, serverName[1]);
											} else {
												Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").replace("/", ""));
											}
										} else {
											if(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").startsWith("SERVER:")) {
												String[] serverName = Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").split(":");
												sendToServer(player, serverName[1]);
											} else {
												player.chat(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command"));
											}
										}
									} else {
										player.sendMessage(LanguageAPI.theClass.ServerSelectorNoPerm(player));
									}
								} else {
									if(Main.theClass.QuickWarpChest.getBoolean("Items." + st + ".Console")) {
										if(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").startsWith("SERVER:")) {
											String[] serverName = Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").split(":");
											sendToServer(player, serverName[1]);
										} else {
											Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").replace("/", ""));
										}
									} else {
										if(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").startsWith("SERVER:")) {
											String[] serverName = Main.theClass.QuickWarpChest.getString("Items." + st + ".Command").split(":");
											sendToServer(player, serverName[1]);
										} else {
											player.chat(Main.theClass.QuickWarpChest.getString("Items." + st + ".Command"));
										}
									}
								}
							}
						}
					}
				}
				e.setCancelled(true);
			}
		}
	}
	
}
