package me.Fabricio20;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.Fabricio20.listeners.CommandListener;
import me.Fabricio20.listeners.DeathListener;
import me.Fabricio20.listeners.DropItemListener;
import me.Fabricio20.listeners.HealthListener;
import me.Fabricio20.listeners.HungerListener;
import me.Fabricio20.listeners.ItemMoveListener;
import me.Fabricio20.listeners.JoinListener;
import me.Fabricio20.listeners.JoinListenerForItems;
import me.Fabricio20.listeners.JumpListener;
import me.Fabricio20.listeners.LeaveListener;
import me.Fabricio20.listeners.MoveListener;
import me.Fabricio20.listeners.PlayerChangeWorld;
import me.Fabricio20.listeners.RainListener;
import me.Fabricio20.listeners.RightClickListener;
import me.Fabricio20.listeners.ServerPingListener;
import me.Fabricio20.listeners.SignChangeListener;
import me.Fabricio20.listeners.VoidFallListener;
import me.Fabricio20.methods.CustomConfigs;
import me.Fabricio20.methods.FixConfig;
import me.Fabricio20.runnables.AntiOp;
import me.Fabricio20.runnables.BossAnnouncer;
import me.Fabricio20.runnables.ChatAnnouncer;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Plugin getPlugin() {
		return plugin;
	}
	
	public static Main theClass = null;
	public String version;
	
	public Main plugin;
	public static FileConfiguration StorageConfig = null;
    public static File Storage = null;
	public static FileConfiguration ItemConfig = null;
    public static File Item = null;
	
    private int ChatTime = 0;
    private int BossTime = 0;
    private int ActionTime = 0;
    
	// Console Coloring Made Easy
	public final String ANSI_RESET = "\u001B[0m";
	public final String ANSI_BLACK = "\u001B[30m";
	public final String ANSI_RED = "\u001B[31m";
	public final String ANSI_GREEN = "\u001B[32m";
	public final String ANSI_YELLOW = "\u001B[33m";
	public final String ANSI_BLUE = "\u001B[34m";
	public final String ANSI_PURPLE = "\u001B[35m";
	public final String ANSI_CYAN = "\u001B[36m";
	public final String ANSI_WHITE = "\u001B[37m";
	public final String ANSI_BOLD = "\u001B[1m";
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onEnable() {
		theClass = this;
		plugin = this;
		initPlugin();
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onDisable() {
		Bukkit.getScheduler().cancelAllTasks();
		plugin = null;
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-HubBasics-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("= Version : \u001B[32m" + getDescription().getVersion() + "\u001B[0m");
		System.out.println("= Author  : \u001B[33mFabricio20\u001B[0m");
		System.out.println("= Status  : \u001B[31mDeactivated\u001B[0m");
	    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	public void initPlugin() {
		version = Bukkit.getVersion();
		getConfig();
		saveDefaultConfig();
		FixConfig.fix();
		CustomConfigs.reloadStorageConfig();
		CustomConfigs.reloadItemConfig();
		if(version.contains("1.7")) {
			getServer().getPluginManager().registerEvents(new me.Fabricio20.listeners.V1_7.TabListJoin(), this);
			getServer().getPluginManager().registerEvents(new me.Fabricio20.listeners.V1_7.TitleJoin(), this);
			ActionTime = getPlugin().getConfig().getInt("ActionAnnouncer.Time");
			BukkitTask ActionAnnouncer = new me.Fabricio20.runnables.V1_7.ActionAnnouncer().runTaskTimer(getPlugin(), 20, ActionTime * 20);
		} else if(version.contains("1.8")) {
			getServer().getPluginManager().registerEvents(new me.Fabricio20.listeners.V1_8.TabListJoin(), this);
			getServer().getPluginManager().registerEvents(new me.Fabricio20.listeners.V1_8.TitleJoin(), this);
			ActionTime = getPlugin().getConfig().getInt("ActionAnnouncer.Time");
			BukkitTask ActionAnnouncer = new me.Fabricio20.runnables.V1_8.ActionAnnouncer().runTaskTimer(getPlugin(), 20, ActionTime * 20);
		} else {
			Bukkit.getLogger().log(Level.WARNING, "[HubBasics] Unsuported Server Version Detected!");
			Bukkit.getLogger().log(Level.WARNING, "[HubBasics] Some Options Where Disabled!");
		}
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LeaveListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RainListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new VoidFallListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ServerPingListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JumpListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MoveListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HungerListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HealthListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListenerForItems(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DropItemListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ItemMoveListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChangeWorld(), this);
		BukkitTask AntiOP = new AntiOp().runTaskTimer(getPlugin(), 300, 300);
		BossTime = getPlugin().getConfig().getInt("BossAnnouncer.Time");
		BukkitTask BossAnnouncer = new BossAnnouncer().runTaskTimer(getPlugin(), 20, BossTime * 20);
		ChatTime = getPlugin().getConfig().getInt("ChatAnnouncer.Time");
		BukkitTask ChatAnnouncer = new ChatAnnouncer().runTaskTimer(getPlugin(), 20, ChatTime * 20);
		getCommand("hub").setExecutor(new Commands());
		getCommand("lobby").setExecutor(new Commands());
		getCommand("sethub").setExecutor(new Commands());
		getCommand("hat").setExecutor(new Commands());
		getCommand("hb").setExecutor(new Commands());
		getCommand("uuid").setExecutor(new Commands());
		getCommand("hubitems").setExecutor(new Commands());
		getCommand("stacker").setExecutor(new Commands());
		try {
			MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		    System.out.println("[HubBasics] Metrics Started!");
		} catch (IOException e) {
		   System.out.println("[HubBasics] Error while trying to submit metrics!");
		}
		Strings.Prefix = getConfig().getString("Others.Prefix").replace("&", "§");
		Strings.LaunchPadBlock = getConfig().getString("Others.JumpPadBlock");
		getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-HubBasics-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("= Config: \u001B[32mReady\u001B[0m");
		System.out.println("= Runnables: \u001B[32m" + "4" + "\u001B[0m");
		System.out.println("= Version : \u001B[32m" + Strings.Version + "\u001B[0m");
		if(Bukkit.getPluginManager().getPlugin("BarAPI") != null) {
			System.out.println("= BarAPI: \u001B[32mFound\u001B[0m");
		} else {
			System.out.println("= BarAPI: \u001B[34mNot Found\u001B[0m");
		}
		System.out.println("= Author  : \u001B[33mFabricio20\u001B[0m");
		System.out.println("= Status  : \u001B[32mActive\u001B[0m");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
}
