package me.Fabricio20.API;

import me.Fabricio20.Storage.Permissions;
import me.Fabricio20.methods.ModuleManager;
import me.Fabricio20.methods.Managers.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MagicClockAPI {
	
	public static MagicClockAPI theClass = new MagicClockAPI();
	
	/**
	 * 
	 * @param player
	 * @return true if players are enabled
	 */
	@SuppressWarnings("deprecation")
	public boolean toggleClock(Player player) {
		if(ModuleManager.theClass.isInWorld(player)) {
			if(!SettingsManager.theClass.isPlayersEnabled(player.getName())) {
				for(Player ps: Bukkit.getOnlinePlayers()) {
					if(player.canSee(ps)) {
						if(!ps.hasPermission(new Permissions().IgnoreMagicClock)) {
							player.hidePlayer(ps);
						}
					}
				}
				return false;
			} else {
				for(Player ps: Bukkit.getOnlinePlayers()) {
					if(!player.canSee(ps)) {
						player.showPlayer(ps);
					}
				}
				return true;
			}
		} else {
			for(Player ps: Bukkit.getOnlinePlayers()) {
				if(!player.canSee(ps)) {
					player.showPlayer(ps);
				}
			}
			return true;
		}
	}
	
}
