package me.Fabricio20.methods;

import me.Fabricio20.Main;
import me.Fabricio20.methods.Configs.SimpleConfig;

public class SettingsManager {
	
	public static SettingsManager theClass = new SettingsManager();
	
	/**
	 * 
	 * @param playerName
	 * @return Config Instance For PlayerName with all default values, no null pointer here.
	 * 
	 */
	public SimpleConfig getSettings(String playerName) {
		SimpleConfig pName;
		String[] header = {"HubBasics Player Settings File", "Documentation Can Be Found On GitHub Page"};
		pName = Main.theClass.manager.getNewConfig("Storage/Players/" + playerName.toLowerCase() + ".yml", header);
		pName.saveConfig();
		return FixConfig.fixPlayer(pName);
	}
	
	/**
	 * 
	 * @param playerName
	 * @return true if player has MagicClock disabled
	 */
	public boolean isPlayersEnabled(String playerName) {
		return getSettings(playerName).getBoolean("IsPlayersEnabled");
	}
	
	/**
	 * 
	 * @param playerName
	 * @return true if SpeedBoost is enabled
	 */
	public boolean isSpeedEnabled(String playerName) {
		return getSettings(playerName).getBoolean("SpeedBoost.Enabled");
	}
	
	/**
	 * 
	 * @param playerName
	 * @return Speed Effect Force
	 */
	public Integer getSpeedForce(String playerName) {
		return getSettings(playerName).getInt("SpeedBoost.Force");
	}
	
	/**
	 * 
	 * @param playerName
	 * @return true if JumpBoost is enabled
	 */
	public boolean isJumpBoostEnabled(String playerName) {
		return getSettings(playerName).getBoolean("JumpBoost.Enabled");
	}
	
	/**
	 * 
	 * @param playerName
	 * @return Jump Effect Force
	 */
	public Integer getJumpForce(String playerName) {
		return getSettings(playerName).getInt("JumpBoost.Force");
	}
	
	
}
