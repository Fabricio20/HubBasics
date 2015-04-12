package me.Fabricio20.Bukkit.API;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfig;

import org.bukkit.entity.Player;

public class LanguageAPI {
	
	public static LanguageAPI theClass = new LanguageAPI();
	
	public SimpleConfig Language = Main.theClass.Language;
	
	public String WarpUsage(Player player) {
		return Language.getString("WarpUsage").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String WarpedMessage(String playerName, String worldName) {
		return Language.getString("WarpMessage").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
	public String WarpNoPermission(String playerName, String worldName) {
		return Language.getString("WarpNoPermission").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
	public String QWarpChestName(String playerName, String worldName) {
		return Language.getString("QuickWarpChestName").replace("&", "§").replace("%p", playerName).replace("%world%", worldName);
	}
	
	// Chest Names
	public String Chests_SettingsName(Player player) {
		return Language.getString("Chests.SettingsName").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	// MagicClock Messages
	public String MagicClock_Enabled(Player player) {
		return Language.getString("MagicClock.Enabled").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String MagicClock_Disabled(Player player) {
		return Language.getString("MagicClock.Disabled").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String MagicClock_Cooldown(Player player) {
		return Language.getString("MagicClock.Cooldown").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	// General Messages
	public String General_NoArgs(Player player) {
		return Language.getString("General.NoArgs").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String General_NotNumber(Player player) {
		return Language.getString("General.NotNumber").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	// Messages
	
	public String ServerSelectorNoPerm(Player player) {
		return Language.getString("ServerSelectorNoPerm").replace("&", "§").replace("%p", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String Effects_SpeedEnabled(Player player) {
		return Language.getString("Effects.SpeedEnabled").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String Effects_SpeedDisabled(Player player) {
		return Language.getString("Effects.SpeedDisabled").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	public String Effects_JumpEnabled(Player player) {
		return Language.getString("Effects.JumpEnabled").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String Effects_JumpDisabled(Player player) {
		return Language.getString("Effects.JumpDisabled").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String Effects_SpeedSet(Player player) {
		return Language.getString("Effects.SpeedSet").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
	public String Effects_JumpSet(Player player) {
		return Language.getString("Effects.JumpSet").replace("&", "§").replace("%player%", player.getName()).replace("%world%", player.getWorld().getName());
	}
	
}
