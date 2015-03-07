package me.Fabricio20.methods.Managers;

import me.Fabricio20.Main;
import me.Fabricio20.Storage.Storage;
import me.Fabricio20.methods.FixConfig;
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
		if(Storage.playerSettings.containsKey(playerName)) {
			return Storage.playerSettings.get(playerName);
		} else {
			SimpleConfig pName;
			String[] header = {"HubBasics Player Settings File", "Documentation Can Be Found On GitHub Page"};
			pName = Main.theClass.manager.getNewConfig("Storage/Players/" + playerName.toLowerCase() + ".yml", header);
			pName.saveConfig();
			FixConfig.fixPlayer(pName);
			Storage.playerSettings.put(playerName, pName);
			return pName;
		}
	}
	
	//--------------------------------------------------------------------------------------//
	
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
	 * @return true if magic clock is enabled after toggle
	 */
	public boolean toggleClock(String playerName) {
		boolean value = isPlayersEnabled(playerName);
		value = !value;
		getSettings(playerName).set("IsPlayersEnabled", value);
		getSettings(playerName).saveConfig();
		return isPlayersEnabled(playerName);
	}
	
	//--------------------------------------------------------------------------------------//
	
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
	 * @return true if SpeedBoost is enabled after toggle
	 */
	public boolean toggleSpeed(String playerName) {
		boolean value = isSpeedEnabled(playerName);
		value = !value;
		getSettings(playerName).set("SpeedBoost.Enabled", value);
		getSettings(playerName).saveConfig();
		return isSpeedEnabled(playerName);
	}
	
	/**
	 * 
	 * @param playerName
	 * @param value
	 * @return Changes Speed Boost Speed
	 */
	public Integer setSpeed(String playerName, Integer value) {
		getSettings(playerName).set("SpeedBoost.Force", value);
		getSettings(playerName).saveConfig();
		return getSpeedForce(playerName);
	}
	
	//--------------------------------------------------------------------------------------//
	
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
	
	/**
	 * 
	 * @param playerName
	 * @return true if JumpBoost is enabled after toggle
	 */
	public boolean toggleJump(String playerName) {
		boolean value = isJumpBoostEnabled(playerName);
		value = !value;
		getSettings(playerName).set("JumpBoost.Enabled", value);
		getSettings(playerName).saveConfig();
		return isJumpBoostEnabled(playerName);
	}
	
	/**
	 * 
	 * @param playerName
	 * @param value
	 * @return Changes Jump Boost Speed
	 */
	public Integer setJump(String playerName, Integer value) {
		getSettings(playerName).set("JumpBoost.Force", value);
		getSettings(playerName).saveConfig();
		return getJumpForce(playerName);
	}
	
	//--------------------------------------------------------------------------------------//
	
	
}
