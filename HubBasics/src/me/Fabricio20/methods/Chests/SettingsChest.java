package me.Fabricio20.methods.Chests;

import me.Fabricio20.API.ItemsAPI;
import me.Fabricio20.API.LanguageAPI;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsChest {
	
	public static SettingsChest theClass = new SettingsChest();
	
	public void open(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, LanguageAPI.theClass.Chests_SettingsName(player));
		
		inv.setItem(0, ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(player.getName())));
		inv.setItem(1, ItemsAPI.get("SpeedBoost", SettingsManager.theClass.isSpeedEnabled(player.getName())));
		inv.setItem(2, ItemsAPI.get("JumpBoost", SettingsManager.theClass.isJumpBoostEnabled(player.getName())));
		
		player.openInventory(inv);
	}
	
}
