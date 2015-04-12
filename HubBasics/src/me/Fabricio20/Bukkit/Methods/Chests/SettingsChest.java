package me.Fabricio20.Bukkit.Methods.Chests;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.API.ItemsAPI;
import me.Fabricio20.Bukkit.API.LanguageAPI;
import me.Fabricio20.Bukkit.Methods.Managers.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsChest {
	
	public static SettingsChest theClass = new SettingsChest();
	
	public void open(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, LanguageAPI.theClass.Chests_SettingsName(player));
		
		if(Main.theClass.config.getBoolean("MagicClock.EnabledInChest")) {
			inv.addItem(ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(player.getName())));
		}
		inv.addItem(ItemsAPI.get("SpeedBoost", SettingsManager.theClass.isSpeedEnabled(player.getName())));
		inv.addItem(ItemsAPI.get("JumpBoost", SettingsManager.theClass.isJumpBoostEnabled(player.getName())));
		
		player.openInventory(inv);
	}
	
}
