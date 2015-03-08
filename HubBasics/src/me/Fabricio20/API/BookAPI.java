package me.Fabricio20.API;

import me.Fabricio20.methods.Configs.SimpleConfig;
import me.Fabricio20.methods.Managers.SettingsManager;

public class BookAPI {
	
	public static boolean shouldGive(String playerName) {
		SimpleConfig pConfig = SettingsManager.theClass.getSettings(playerName);
		if(!pConfig.contains("JoinBook")) {
			return true;
		} else {
			if(pConfig.getBoolean("JoinBook") == true) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static void give(String playerName) {
		SimpleConfig pConfig = SettingsManager.theClass.getSettings(playerName);
		if(!pConfig.contains("JoinBook")) {
			pConfig.set("JoinBook", true);
			pConfig.saveConfig();
		} else {
			return;
		}
	}
	
}
