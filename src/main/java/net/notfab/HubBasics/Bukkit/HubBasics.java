package net.notfab.HubBasics.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import net.notfab.HubBasics.Bukkit.API.Updater;
import net.notfab.HubBasics.Bukkit.Listeners.ConnectionListener;
import net.notfab.HubBasics.Bukkit.Listeners.MovementListener;
import net.notfab.HubBasics.Bukkit.Listeners.WorldListener;
import net.notfab.HubBasics.Bukkit.Managers.CommandManager;
import net.notfab.HubBasics.Bukkit.Managers.JSONConfigManager;
import net.notfab.HubBasics.Bukkit.Managers.ProfileManager;
import net.notfab.HubBasics.Bukkit.Managers.SQLManager;
import net.notfab.HubBasics.Bukkit.Runnables.BossAnnouncer;

public class HubBasics extends JavaPlugin {
	
	private static HubBasics _Instance;
	
	public static HubBasics getInstance() {
		return _Instance;
	}
	
	@Getter private JSONConfigManager ConfigManager;
	
	@Getter private BossAnnouncer BossAnnouncer;
	
	/** ------------------------------------------------ **/
	
	@Override
	public void onLoad() {
		/*_Updater = new Updater();
		_Updater.CheckUpdate();
		if((_Updater.getUpdate() != null) && (_Updater.getUpdate().getBoolean("Updated") == false)) {
			getLogger().log(Level.INFO, "[Updater] Starting HubBasics Update, Please Wait...");
			getLogger().log(Level.INFO, "[Updater] Next Version: " + _Updater.getUpdate().getString("Version"));
			getLogger().log(Level.INFO, "[Updater] Change Log: " + _Updater.getUpdate().getString("ChangeLog"));
			getLogger().log(Level.INFO, "[Updater] Additional Info: " + _Updater.getUpdate().getString("Message"));
			if(_Updater.Update()) {
				getLogger().log(Level.INFO, "[Updater] HubBasics Successfully Updated, Restarting...");
				getServer().shutdown();
			} else {
				getLogger().log(Level.SEVERE, "[Updater] Failed To Update HubBasics, Please Do It Manually Or Restart To Try Again.");
			}
		}*/
	}
	
	@Override
	public void onEnable() {
		_Instance = this;
		this._SQLManager = new SQLManager(this);
		this.ConfigManager = new JSONConfigManager();
		this._ProfileManager = new ProfileManager();
		this._CommandManager = new CommandManager(this);
		getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
		getServer().getPluginManager().registerEvents(new MovementListener(), this);
		getServer().getPluginManager().registerEvents(new WorldListener(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.BossAnnouncer = new BossAnnouncer();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/** ------------------------------------------------ **/
	
	private CommandManager _CommandManager;
	
	private Updater _Updater;
	
	private SQLManager _SQLManager;
	
	private ProfileManager _ProfileManager;
	
	/** ------------------------------------------------ **/
	
	public CommandManager getCommandManager() {
		return this._CommandManager;
	}
	
	public Updater getUpdater() {
		return this._Updater;
	}
	
	public SQLManager getMySQL() {
		return this._SQLManager;
	}
	
	public ProfileManager getProfileManager() {
		return this._ProfileManager;
	}
	
}
