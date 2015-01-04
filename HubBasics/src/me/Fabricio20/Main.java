package me.Fabricio20;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import me.Fabricio20.runnables.ActionAnnouncer;
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
	public static FileConfiguration StorageConfig = null;
    public static File Storage = null;
	public static FileConfiguration ItemConfig = null;
    public static File Item = null;
	
    public static int ChatTime = 0;
    public static int BossTime = 0;
    public static int ActionTime = 0;
    
	// Console Coloring Made Easy
	@SuppressWarnings("unused")
	private final String ANSI_RESET = "\u001B[0m";
	@SuppressWarnings("unused")
	private final String ANSI_BLACK = "\u001B[30m";
	@SuppressWarnings("unused")
	private final String ANSI_RED = "\u001B[31m";
	@SuppressWarnings("unused")
	private final String ANSI_GREEN = "\u001B[32m";
	@SuppressWarnings("unused")
	private final String ANSI_YELLOW = "\u001B[33m";
	@SuppressWarnings("unused")
	private final String ANSI_BLUE = "\u001B[34m";
	@SuppressWarnings("unused")
	private final String ANSI_PURPLE = "\u001B[35m";
	@SuppressWarnings("unused")
	private final String ANSI_CYAN = "\u001B[36m";
	@SuppressWarnings("unused")
	private final String ANSI_WHITE = "\u001B[37m";
	@SuppressWarnings("unused")
	private final String ANSI_BOLD = "\u001B[1m";
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onEnable() {
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
	
	private void setupConfig() {
		if(!getConfig().contains("JoinEvents.DisableMessage")) {
			getConfig().set("JoinEvents.DisableMessage", false);
			saveConfig();
		}
		if(!getConfig().contains("JoinEvents.SilentOpJoin")) {
			getConfig().set("JoinEvents.SilentOpJoin", false);
			saveConfig();
		}
		if(!getConfig().contains("JoinEvents.Message")) {
			getConfig().set("JoinEvents.Message", "&8[&cHubBasics&8] &eWelcome &a%p &eto the server!");
			saveConfig();
		}
		if(!getConfig().contains("JoinEvents.HubAtLogin")) {
			getConfig().set("JoinEvents.HubAtLogin", false);
			saveConfig();
		}
		if(!getConfig().contains("JoinEvents.BossBarOnJoin")) {
			getConfig().set("JoinEvents.BossBarOnJoin", false);
			saveConfig();
		}
		if(!getConfig().contains("LeaveEvents.DisableLeaveMessage")) {
			getConfig().set("LeaveEvents.DisableLeaveMessage", false);
			saveConfig();
		}
		if(!getConfig().contains("LeaveEvents.SilentOpLeave")) {
			getConfig().set("LeaveEvents.SilentOpLeave", false);
			saveConfig();
		}
		if(!getConfig().contains("LeaveEvents.Message")) {
			getConfig().set("LeaveEvents.Message", "&8[&cHubBasics&8] &a%p &eLeft!");
			saveConfig();
		}
		if(!getConfig().contains("VoidFall.Enabled")) {
			getConfig().set("VoidFall.Enabled", true);
			saveConfig();
		}
		if(!getConfig().contains("VoidFall.Message")) {
			getConfig().set("VoidFall.Message", "&8[&cHubBasics&8] &a%p &eYou were teleported back to spawn!");
			saveConfig();
		}
		if(!getConfig().contains("MotdSystem.CustomMotd")) {
			getConfig().set("MotdSystem.CustomMotd", true);
			saveConfig();
		}
		if(!getConfig().contains("MotdSystem.MoreMotds")) {
			getConfig().set("MotdSystem.MoreMotds", false);
			saveConfig();
		}
		if(!getConfig().contains("MotdSystem.Motds")) {
			ArrayList<String> motds = new ArrayList<String>();
			motds.add("&cThis is a default motd! Change it in the config.");
			getConfig().set("MotdSystem.Motds", motds);
			saveConfig();
		}
		if(!getConfig().contains("ChatAnnouncer.Enabled")) {
			getConfig().set("ChatAnnouncer.Enabled", true);
			saveConfig();
		}
		if(!getConfig().contains("ChatAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			getConfig().set("ChatAnnouncer.Msgs", chatann);
			saveConfig();
		}
		if(!getConfig().contains("ChatAnnouncer.Perworld")) {
			getConfig().set("ChatAnnouncer.Perworld", false);
			saveConfig();
		}
		if(!getConfig().contains("ChatAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			getConfig().set("ChatAnnouncer.Worlds", Worlds);
			saveConfig();
		}
		if(!getConfig().contains("ChatAnnouncer.Time")) {
			getConfig().set("ChatAnnouncer.Time", 60);
			saveConfig();
		}
		if(!getConfig().contains("BossAnnouncer.Enabled")) {
			getConfig().set("BossAnnouncer.Enabled", false);
			saveConfig();
		}
		if(!getConfig().contains("BossAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			getConfig().set("BossAnnouncer.Msgs", chatann);
			saveConfig();
		}
		if(!getConfig().contains("BossAnnouncer.Perworld")) {
			getConfig().set("BossAnnouncer.Perworld", false);
			saveConfig();
		}
		if(!getConfig().contains("BossAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			getConfig().set("BossAnnouncer.Worlds", Worlds);
			saveConfig();
		}
		if(!getConfig().contains("BossAnnouncer.Time")) {
			getConfig().set("BossAnnouncer.Time", 60);
			saveConfig();
		}
		if(!getConfig().contains("ActionAnnouncer.Enabled")) {
			getConfig().set("ActionAnnouncer.Enabled", false);
			saveConfig();
		}
		if(!getConfig().contains("ActionAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			getConfig().set("ActionAnnouncer.Msgs", chatann);
			saveConfig();
		}
		if(!getConfig().contains("ActionAnnouncer.Perworld")) {
			getConfig().set("ActionAnnouncer.Perworld", false);
			saveConfig();
		}
		if(!getConfig().contains("ActionAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			getConfig().set("ActionAnnouncer.Worlds", Worlds);
			saveConfig();
		}
		if(!getConfig().contains("ActionAnnouncer.Time")) {
			getConfig().set("ActionAnnouncer.Time", 3);
			saveConfig();
		}
		if(!getConfig().contains("FakePlugins.Enabled")) {
			getConfig().set("FakePlugins.Enabled", true);
			saveConfig();
		}
		if(!getConfig().contains("FakePlugins.Msg")) {
			getConfig().set("FakePlugins.Msg", "&fPlugins (5): &aYou&f, &aCannot&f, &aSee&f, &aThe&f, &aPlugins");
			saveConfig();
		}
		if(!getConfig().contains("BungeeCord.Enabled")) {
			getConfig().set("BungeeCord.Enabled", false);
			saveConfig();
		}
		if(!getConfig().contains("BungeeCord.LobbyServer")) {
			getConfig().set("BungeeCord.LobbyServer", "lobby");
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.Enabled")) {
			getConfig().set("MagicClock.Enabled", false);
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.Name")) {
			getConfig().set("MagicClock.Name", "&cMagic Clock");
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&7- &a&oMagicClock Lore");
			getConfig().set("MagicClock.Lore", Lore);
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.Slot")) {
			getConfig().set("MagicClock.Slot", 5);
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.Material")) {
			getConfig().set("MagicClock.Material", "WATCH");
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.EnabledMessage")) {
			getConfig().set("MagicClock.EnabledMessage", "&cMagic Clock Is Now Enabled!");
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.DisabledMessage")) {
			getConfig().set("MagicClock.DisabledMessage", "&cMagic Clock Is Now Disabled!");
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.AllowMove")) {
			getConfig().set("MagicClock.AllowMove", false);
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.NoDrop")) {
			getConfig().set("MagicClock.NoDrop", false);
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.Cooldown")) {
			getConfig().set("MagicClock.Cooldown", 12);
			saveConfig();
		}
		if(!getConfig().contains("MagicClock.CooldownMSG")) {
			getConfig().set("MagicClock.CooldownMSG", "&cCooldown! You Must Wait 12 Seconds!");
			saveConfig();
		}
		if(!getConfig().contains("TitleSystem.Enabled")) {
			getConfig().set("TitleSystem.Enabled", false);
			saveConfig();
		}
		if(!getConfig().contains("TitleSystem.Title")) {
			getConfig().set("TitleSystem.Title", "&cThis Is A Title!");
			saveConfig();
		}
		if(!getConfig().contains("TitleSystem.Subtitle")) {
			getConfig().set("TitleSystem.Subtitle", "&9This is a Sub-Title!");
			saveConfig();
		}
		if(!getConfig().contains("BookSystem.Enabled")) {
			getConfig().set("BookSystem.Enabled", false);
			saveConfig();
		}
		if(!getConfig().contains("BookSystem.Author")) {
			getConfig().set("BookSystem.Author", "HubBasics");
			saveConfig();
		}
		if(!getConfig().contains("BookSystem.Name")) {
			getConfig().set("BookSystem.Name", "&cTha Book");
			saveConfig();
		}
		if(!getConfig().contains("BookSystem.Slot")) {
			getConfig().set("BookSystem.Slot", 5);
			saveConfig();
		}
		if(!getConfig().contains("BookSystem.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&bTha lore&c.");
			getConfig().set("BookSystem.Lore", Lore);
			saveConfig();
		}
		if(!getConfig().contains("BookSystem.Pages")) {
			ArrayList<String> Pages = new ArrayList<String>();
			Pages.add("&cThis is a page.");
			getConfig().set("BookSystem.Pages", Pages);
			saveConfig();
		}
		if(!getConfig().contains("Others.DoubleJump")) {
			getConfig().set("Others.DoubleJump", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.Prefix")) {
			getConfig().set("Others.Prefix", "&8[&cHubBasics&8] ");
			saveConfig();
		}
		if(!getConfig().contains("Others.HatSet")) {
			getConfig().set("Others.HatSet", "&cEnjoy your new hat!");
			saveConfig();
		}
		if(!getConfig().contains("Others.HatRemoved")) {
			getConfig().set("Others.HatRemoved", "&cYour Hat Was Removed!");
			saveConfig();
		}
		if(!getConfig().contains("Others.HatAllowMove")) {
			getConfig().set("Others.HatAllowMove", false);
			saveConfig();
		}
		if(!getConfig().contains("Others.HatDrop")) {
			getConfig().set("Others.HatAllowMove", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.NoPermissionForHat")) {
			getConfig().set("Others.NoPermissionForHat", "&cYou do not have permission for this hat!");
			saveConfig();
		}
		if(!getConfig().contains("Others.JumpPadBlock")) {
			getConfig().set("Others.JumpPadBlock", "REDSTONE_BLOCK");
			saveConfig();
		}
		if(!getConfig().contains("Others.KeepFood")) {
			getConfig().set("Others.KeepFood", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.KeepHealth")) {
			getConfig().set("Others.KeepHealth", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.Stacker")) {
			getConfig().set("Others.Stacker", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.JoinItems")) {
			getConfig().set("Others.JoinItems", false);
			saveConfig();
		}
		if(!getConfig().contains("Others.ClearInventory")) {
			getConfig().set("Others.ClearInventory", false);
			saveConfig();
		}
		if(!getConfig().contains("Others.NoDrops")) {
			getConfig().set("Others.NoDrops", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.NoDeathDrops")) {
			getConfig().set("Others.NoDeathDrops", true);
			saveConfig();
		}
		if(!getConfig().contains("Others.AllowItemMove")) {
			getConfig().set("Others.AllowItemMove", false);
			saveConfig();
		}
		if(!getConfig().contains("Others.AntiOP")) {
			getConfig().set("Others.AntiOP", false);
			saveConfig();
		}
		if(!getConfig().contains("Others.HubItems")) {
			getConfig().set("Others.HubItems", true);
			saveConfig();
		}
		if(!getConfig().contains("Worlds")) {
			ArrayList<String> worlds = new ArrayList<String>();
			worlds.add("Example");
			getConfig().set("Worlds", worlds);
			saveConfig();
		}
		if(getConfig().options().header() == "" || getConfig().options().header() == null ) {
			List<String> Header = new ArrayList<String>();
			Header.add("##########################################");
			Header.add("# HubBasics Configuration File #");
			Header.add("# Documentation at: http://tinyurl.com/qgnx3ke ");
			Header.add("##########################################");
			getConfig().options().header(Header.toString());
			saveConfig();
		}
	}
	
	@SuppressWarnings("unused")
	public void initPlugin() {
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LeaveListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RainListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new VoidFallListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ServerPingListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JumpListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MoveListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignChangeListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HungerListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HealthListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListenerForItems(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ItemMoveListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChangeWorld(), this);
		BukkitTask AntiOP = new AntiOp(this).runTaskTimer(Main.getPlugin(), 300, 300);
		Main.BossTime = Main.getPlugin().getConfig().getInt("BossAnnouncer.Time");
		BukkitTask BossAnnouncer = new BossAnnouncer(this).runTaskTimer(Main.getPlugin(), 20, Main.BossTime * 20);
		Main.ChatTime = Main.getPlugin().getConfig().getInt("ChatAnnouncer.Time");
		BukkitTask ChatAnnouncer = new ChatAnnouncer(this).runTaskTimer(Main.getPlugin(), 20, Main.ChatTime * 20);
		Main.ActionTime = Main.getPlugin().getConfig().getInt("ActionAnnouncer.Time");
		BukkitTask ActionAnnouncer = new ActionAnnouncer(this).runTaskTimer(Main.getPlugin(), 20, Main.ActionTime * 20);
		getCommand("hub").setExecutor(new Commands());
		getCommand("lobby").setExecutor(new Commands());
		getCommand("sethub").setExecutor(new Commands());
		getCommand("hat").setExecutor(new Commands());
		getCommand("hb").setExecutor(new Commands());
		getCommand("uuid").setExecutor(new Commands());
		getCommand("hubitems").setExecutor(new Commands());
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
		getConfig();
		saveDefaultConfig();
		setupConfig();
		CustomConfigs.reloadStorageConfig();
		CustomConfigs.reloadItemConfig();
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-HubBasics-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("= Config: \u001B[32mReady\u001B[0m");
		System.out.println("= Runnables: \u001B[32m" + "4" + "\u001B[0m");
		System.out.println("= Version : \u001B[32m" + getDescription().getVersion() + "\u001B[0m");
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
