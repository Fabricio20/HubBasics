package me.Fabricio20.BungeeCord;

import me.Fabricio20.BungeeCord.Commands.ListCommand;
import me.Fabricio20.BungeeCord.Configs.DatabaseConfig;
import me.Fabricio20.BungeeCord.Configs.MainConfig;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	
	public static Main theClass;
	
	public MainConfig config = null;
	public DatabaseConfig dbSettings = null;
	
	/** ----------------------------------------------- **/
	
	@Override
	public void onEnable() {
		initConfigs();
		theClass = this;
		callListeners();
		getLogger().info("[HubBasics] Started!");
	}
	
	/** ----------------------------------------------- **/
	
	@Override
	public void onDisable() {
		getLogger().info("[HubBasics] Stopped!");
		theClass = null;
	}
	
	/** ----------------------------------------------- **/
	
	void callListeners() {
		if(config.List_Enabled) {
			getProxy().getPluginManager().registerCommand(this, new ListCommand());
		}
		//
		if(config.Friends_Enabled) {
			//Register Command
			MySQLAPI.openConnection();
			// START KEEP ALIVE
		}
		//
	}
	
	/** ----------------------------------------------- **/
	
	void initConfigs() {
		try {
		    config = new MainConfig(this);
		    config.init();
		} catch(InvalidConfigurationException ex) {
		    System.out.println("[HubBasics] Error While Loading Your Config!");
		    ex.printStackTrace();
		}
		try {
		    dbSettings = new DatabaseConfig(this);
		    dbSettings.init();
		} catch(InvalidConfigurationException ex) {
		    System.out.println("[HubBasics] Error While Loading Your Database Config!");
		    ex.printStackTrace();
		}
	}
	
	/** ----------------------------------------------- **/
	
}
