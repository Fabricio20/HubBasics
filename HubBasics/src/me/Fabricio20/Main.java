package me.Fabricio20;

import java.io.File;
import java.io.IOException;

import me.Fabricio20.listeners.CommandListener;
import me.Fabricio20.listeners.DeathListener;
import me.Fabricio20.listeners.DropItemListener;
import me.Fabricio20.listeners.HealthListener;
import me.Fabricio20.listeners.HungerListener;
import me.Fabricio20.listeners.JoinListener;
import me.Fabricio20.listeners.JoinListenerForItems;
import me.Fabricio20.listeners.JumpListener;
import me.Fabricio20.listeners.LeaveListener;
import me.Fabricio20.listeners.MoveListener;
import me.Fabricio20.listeners.RainListener;
import me.Fabricio20.listeners.RightClickListener;
import me.Fabricio20.listeners.ServerPingListener;
import me.Fabricio20.listeners.SignChangeListener;
import me.Fabricio20.listeners.VoidFallListener;
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
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static Main plugin;
	public FileConfiguration LocationConfig = null;
    public File Location;
	
	// Console Coloring Made Easy
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BOLD = "\u001B[1m";
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		getConfig();
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(new LeaveListener(this), this);
		getServer().getPluginManager().registerEvents(new RainListener(this), this);
		getServer().getPluginManager().registerEvents(new VoidFallListener(this), this);
		getServer().getPluginManager().registerEvents(new ServerPingListener(this), this);
		getServer().getPluginManager().registerEvents(new CommandListener(this), this);
		getServer().getPluginManager().registerEvents(new JumpListener(this), this);
		getServer().getPluginManager().registerEvents(new MoveListener(this), this);
		getServer().getPluginManager().registerEvents(new RightClickListener(this), this);
		getServer().getPluginManager().registerEvents(new SignChangeListener(this), this);
		getServer().getPluginManager().registerEvents(new HungerListener(this), this);
		getServer().getPluginManager().registerEvents(new HealthListener(this), this);
		getServer().getPluginManager().registerEvents(new JoinListenerForItems(this), this);
		getServer().getPluginManager().registerEvents(new DeathListener(this), this);
		getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
		BukkitTask AntiOP = new AntiOp(this).runTaskTimer(this, 300, 300);
		Strings.RunnablesEnabled = 1;
		BukkitTask BossAnnouncer = new BossAnnouncer(this).runTaskTimer(this, 1200, 1200);
		Strings.RunnablesEnabled = Strings.RunnablesEnabled + 1;
		BukkitTask ChatAnnouncer = new ChatAnnouncer(this).runTaskTimer(this, 1200, 1200);
		Strings.RunnablesEnabled = Strings.RunnablesEnabled + 1;
		getCommand("hub").setExecutor(new Commands(this));
		getCommand("lobby").setExecutor(new Commands(this));
		getCommand("sethub").setExecutor(new Commands(this));
		getCommand("hat").setExecutor(new Commands(this));
		plugin = this;
		getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
		Strings.Prefix = plugin.getConfig().getString("Prefix").replace("&", "§");
		Strings.LaunchPadBlock = getConfig().getString("JumpPadBlock");
		try {
			MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		    System.out.println("[HubBasics] Metrics Started!");
		} catch (IOException e) {
		   System.out.println("[HubBasics] Error when trying to submit metrics!");
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-HubBasics-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("= Config: \u001B[32mReady\u001B[0m");
		System.out.println("= Runnables: \u001B[32m" + Strings.RunnablesEnabled + "\u001B[0m");
		System.out.println("= Version : \u001B[32m" + getDescription().getVersion() + "\u001B[0m");
		System.out.println("= BarAPI: \u001B[32mFound\u001B[0m");
		System.out.println("= Author  : \u001B[33mFabricio20\u001B[0m");
		System.out.println("= Status  : \u001B[32mActive\u001B[0m");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onDisable() {
		Bukkit.getScheduler().cancelAllTasks();
		plugin = null;
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-FallCraftBOT2-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("= Version : \u001B[32m" + getDescription().getVersion() + "\u001B[0m");
		System.out.println("= Author  : \u001B[33mFabricio20\u001B[0m");
		System.out.println("= Status  : \u001B[31mDeactivated\u001B[0m");
	    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
