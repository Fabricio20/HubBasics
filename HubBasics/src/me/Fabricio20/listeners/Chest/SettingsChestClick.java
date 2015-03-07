package me.Fabricio20.listeners.Chest;

import me.Fabricio20.API.ItemsAPI;
import me.Fabricio20.API.LanguageAPI;
import me.Fabricio20.methods.Chests.SettingsChest;
import me.Fabricio20.methods.Managers.EffectsManager;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsChestClick implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() != null) {
			if(e.getClickedInventory().getName().equals(LanguageAPI.theClass.Chests_SettingsName(player))) {
				e.setCancelled(true);
				if(e.getCurrentItem().equals(ItemsAPI.get("MagicClock", SettingsManager.theClass.isPlayersEnabled(player.getName())))) {
					//TODO: Toggle MagicClock
					SettingsChest.theClass.open(player);
				}
				if(e.getCurrentItem().equals(ItemsAPI.get("SpeedBoost", SettingsManager.theClass.isSpeedEnabled(player.getName())))) {
					if(SettingsManager.theClass.toggleSpeed(player.getName())) {
						player.sendMessage(LanguageAPI.theClass.Effects_SpeedEnabled(player));
					} else {
						player.sendMessage(LanguageAPI.theClass.Effects_SpeedDisabled(player));
					}
					EffectsManager.theClass.fixEffects(player);
					SettingsChest.theClass.open(player);
				}
				if(e.getCurrentItem().equals(ItemsAPI.get("JumpBoost", SettingsManager.theClass.isJumpBoostEnabled(player.getName())))) {
					if(SettingsManager.theClass.toggleJump(player.getName())) {
						player.sendMessage(LanguageAPI.theClass.Effects_JumpEnabled(player));
					} else {
						player.sendMessage(LanguageAPI.theClass.Effects_JumpDisabled(player));
					}
					EffectsManager.theClass.fixEffects(player);
					SettingsChest.theClass.open(player);
				}
			}
		}
	}
	
}
