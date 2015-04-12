package me.Fabricio20.BungeeCord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import me.Fabricio20.BungeeCord.API.MySQLAPI;
import me.Fabricio20.BungeeCord.Commands.AlertCommand;
import me.Fabricio20.BungeeCord.Commands.AlertCommandWithTitle;
import me.Fabricio20.BungeeCord.Commands.FriendsCommand;
import me.Fabricio20.BungeeCord.Commands.ListCommand;
import me.Fabricio20.BungeeCord.Configs.DatabaseConfig;
import me.Fabricio20.BungeeCord.Configs.LanguageFile;
import me.Fabricio20.BungeeCord.Configs.MainConfig;
import me.Fabricio20.BungeeCord.Listeners.FriendsPostLoginListener;
import me.Fabricio20.BungeeCord.Runnables.KeepAlive;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public class Main extends Plugin {
	
	public static Main theClass;
	
	public MainConfig config = null;
	public DatabaseConfig dbSettings = null;
	public LanguageFile language = null;
	
	private ScheduledTask KeepAlive = null;
	
	public ArrayList<String> WantsToBe = new ArrayList<String>();
	
	/** ----------------------------------------------- **/
	
	@Override
	public void onEnable() {
		theClass = this;
		FixConfig.Fix();
		initConfigs();
		callListeners();
		initMetrics();
		getLogger().info("Started!");
	}

	/** ----------------------------------------------- **/
	
	private void initMetrics() {
		try {
			MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		    System.out.println("[HubBasics] Metrics Started!");
		} catch (IOException e) {
		   System.out.println("[HubBasics] Error while trying to submit metrics!");
		}
	}
	
	/** ----------------------------------------------- **/
	
	@Override
	public void onDisable() {
		getLogger().info("Stopped!");
		theClass = null;
		if(MySQLAPI.connection != null) {
			MySQLAPI.shutdown();
		}
		if(KeepAlive != null) {
			KeepAlive.cancel();
		}
	}
	
	/** ----------------------------------------------- **/
	
	void callListeners() {
		if(config.List_Enabled) {
			getProxy().getPluginManager().registerCommand(this, new ListCommand());
		}
		//
		if(config.Friends_Enabled) {
			getProxy().getPluginManager().registerCommand(this, new FriendsCommand());
			MySQLAPI.openConnection();
			KeepAlive = BungeeCord.getInstance().getScheduler().schedule(this, new KeepAlive(), 2, 60, TimeUnit.SECONDS);
			getProxy().getPluginManager().registerListener(this, new FriendsPostLoginListener());
		}
		if(config.Alert_Enabled) {
			if(BungeeCord.getInstance().getVersion().contains("1.8")) {
				getProxy().getPluginManager().registerCommand(this, new AlertCommandWithTitle());
				System.out.println("[HubBasics] Alert Command Enabled With Title!");
			} else {
				getProxy().getPluginManager().registerCommand(this, new AlertCommand());
				System.out.println("[HubBasics] Alert Command Enabled Without Title!");
			}
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
		try {
			language = new LanguageFile(this);
			language.init();
		} catch(InvalidConfigurationException ex) {
			System.out.println("[HubBasics] Error While Loading Your Language File!");
			ex.printStackTrace();
		}
	}
	
	/** ----------------------------------------------- **/
	
}
