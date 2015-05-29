package me.Fabricio20.Bukkit;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;

import me.Fabricio20.Bukkit.API.UpdateAPI;
import me.Fabricio20.Bukkit.Listeners.Block.BlockBreak;
import me.Fabricio20.Bukkit.Listeners.Block.BlockPlace;
import me.Fabricio20.Bukkit.Listeners.Chat.CommandListener;
import me.Fabricio20.Bukkit.Listeners.Chat.CommandsOverride;
import me.Fabricio20.Bukkit.Listeners.Chat.PEXChatListener;
import me.Fabricio20.Bukkit.Listeners.Chest.SettingsChestClick;
import me.Fabricio20.Bukkit.Listeners.HubChest.CHEST_Command;
import me.Fabricio20.Bukkit.Listeners.HubChest.CHEST_InventoryClick;
import me.Fabricio20.Bukkit.Listeners.HubChest.CHEST_LeftClickListener;
import me.Fabricio20.Bukkit.Listeners.HubChest.CHEST_RightClickListener;
import me.Fabricio20.Bukkit.Listeners.Item.DropItemListener;
import me.Fabricio20.Bukkit.Listeners.Item.ItemClickChest;
import me.Fabricio20.Bukkit.Listeners.Item.ItemMoveListener;
import me.Fabricio20.Bukkit.Listeners.Others.JumpListener;
import me.Fabricio20.Bukkit.Listeners.Others.RainListener;
import me.Fabricio20.Bukkit.Listeners.Others.ServerPingListener;
import me.Fabricio20.Bukkit.Listeners.Others.SignChangeListener;
import me.Fabricio20.Bukkit.Listeners.Player.ChangeWorldSettings;
import me.Fabricio20.Bukkit.Listeners.Player.DeathListener;
import me.Fabricio20.Bukkit.Listeners.Player.HealthListener;
import me.Fabricio20.Bukkit.Listeners.Player.HungerListener;
import me.Fabricio20.Bukkit.Listeners.Player.JoinListener;
import me.Fabricio20.Bukkit.Listeners.Player.JoinListenerForItems;
import me.Fabricio20.Bukkit.Listeners.Player.JoinListenerForTags;
import me.Fabricio20.Bukkit.Listeners.Player.JoinSettings;
import me.Fabricio20.Bukkit.Listeners.Player.LeaveListener;
import me.Fabricio20.Bukkit.Listeners.Player.LeaveSettings;
import me.Fabricio20.Bukkit.Listeners.Player.MoveListener;
import me.Fabricio20.Bukkit.Listeners.Player.PlayerChangeWorld;
import me.Fabricio20.Bukkit.Listeners.Player.PlayerRespawn;
import me.Fabricio20.Bukkit.Listeners.Player.RightClickListener;
import me.Fabricio20.Bukkit.Listeners.Player.VoidFallListener;
import me.Fabricio20.Bukkit.Listeners.Veichle.VeichleLeaveListener;
import me.Fabricio20.Bukkit.Methods.FixConfig;
import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfig;
import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfigManager;
import me.Fabricio20.Bukkit.Runnables.AntiOp;
import me.Fabricio20.Bukkit.Runnables.BossAnnouncer;
import me.Fabricio20.Bukkit.Runnables.ChatAnnouncer;
import me.Fabricio20.Bukkit.Runnables.UpdateChecker;
import me.Fabricio20.Bukkit.Storage.CustomEnchantment;
import me.Fabricio20.Global.GlobalStrings;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
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
    public SimpleConfigManager manager;
    
    public SimpleConfig config;
    public SimpleConfig Hub;
    public SimpleConfig JoinItems;
    public SimpleConfig Warps;
    public SimpleConfig Language;
    public SimpleConfig Tags;
    public SimpleConfig QuickWarpChest;
    public SimpleConfig ChestItems;
	
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
		UpdateAPI.checkUpdate();
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
	
	public void initPlugin() {
		version = Bukkit.getVersion();
		initConfigs();
		FixConfig.fix();
		registerlisteners();
		registerTasks();
		registerCommands();
		initMetrics();
		fixEnchant();
		getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-HubBasics-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("= Config: \u001B[32mReady\u001B[0m");
		System.out.println("= Runnables: \u001B[32m" + "5" + "\u001B[0m");
		System.out.println("= Version : \u001B[32m" + GlobalStrings.Version + "\u001B[0m");
		if(Bukkit.getPluginManager().getPlugin("BarAPI") != null) {
			System.out.println("= BarAPI: \u001B[32mFound\u001B[0m");
		} else {
			System.out.println("= BarAPI: \u001B[34mNot Found\u001B[0m");
		}
		if(Bukkit.getPluginManager().getPlugin("PermissionsEx") != null) {
			System.out.println("= PEX: \u001B[32mFound\u001B[0m");
		} else {
			System.out.println("= PEX: \u001B[34mNot Found\u001B[0m");
		}
		System.out.println("= Author  : \u001B[33mFabricio20\u001B[0m");
		System.out.println("= Status  : \u001B[32mActive\u001B[0m");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
	private void registerCommands() {
		getCommand("hub").setExecutor(new Commands());
		getCommand("lobby").setExecutor(new Commands());
		getCommand("sethub").setExecutor(new Commands());
		getCommand("hat").setExecutor(new Commands());
		getCommand("hb").setExecutor(new Commands());
		getCommand("uuid").setExecutor(new Commands());
		getCommand("stacker").setExecutor(new Commands());
	}
	
	private void registerlisteners() {
		checkVersion();
		if(getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
			getServer().getPluginManager().registerEvents(new PEXChatListener(), this);
		}
		getServer().getPluginManager().registerEvents(new CommandsOverride(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new LeaveListener(), this);
		getServer().getPluginManager().registerEvents(new RainListener(), this);
		getServer().getPluginManager().registerEvents(new VoidFallListener(), this);
		getServer().getPluginManager().registerEvents(new ServerPingListener(), this);
		getServer().getPluginManager().registerEvents(new CommandListener(), this);
		getServer().getPluginManager().registerEvents(new JumpListener(), this);
		getServer().getPluginManager().registerEvents(new MoveListener(), this);
		getServer().getPluginManager().registerEvents(new RightClickListener(), this);
		getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
		getServer().getPluginManager().registerEvents(new HungerListener(), this);
		getServer().getPluginManager().registerEvents(new HealthListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListenerForItems(), this);
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
		getServer().getPluginManager().registerEvents(new DropItemListener(), this);
		getServer().getPluginManager().registerEvents(new ItemMoveListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerChangeWorld(), this);
		getServer().getPluginManager().registerEvents(new VeichleLeaveListener(), this);
		getServer().getPluginManager().registerEvents(new ItemClickChest(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new JoinSettings(), this);
		getServer().getPluginManager().registerEvents(new ChangeWorldSettings(), this);
		getServer().getPluginManager().registerEvents(new SettingsChestClick(), this);
		getServer().getPluginManager().registerEvents(new LeaveSettings(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		getServer().getPluginManager().registerEvents(new CHEST_RightClickListener(), this);
		getServer().getPluginManager().registerEvents(new CHEST_LeftClickListener(), this);
		getServer().getPluginManager().registerEvents(new CHEST_Command(), this);
		getServer().getPluginManager().registerEvents(new CHEST_InventoryClick(), this);
		// System.out.println(version); git-Spigot-1.7.9-R0.2-205-g0a049fa (MC: 1.7.10) [ Protocol Patch ]
	}
	
	@SuppressWarnings("unused")
	private void registerTasks() {
		BukkitTask AntiOP = new AntiOp().runTaskTimer(getPlugin(), 300, 300);
		BossTime = getPlugin().getConfig().getInt("BossAnnouncer.Time");
		BukkitTask BossAnnouncer = new BossAnnouncer().runTaskTimer(getPlugin(), 20, BossTime * 20);
		ChatTime = getPlugin().getConfig().getInt("ChatAnnouncer.Time");
		BukkitTask ChatAnnouncer = new ChatAnnouncer().runTaskTimer(getPlugin(), 20, ChatTime * 20);
		//
		BukkitTask updater = new UpdateChecker().runTaskTimer(getPlugin(), 20, ((20*60)*60));
	}
	
	private void initMetrics() {
		try {
			MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		    System.out.println("[HubBasics] Metrics Started!");
		} catch (IOException e) {
		   System.out.println("[HubBasics] Error while trying to submit metrics!");
		}
	}
	
	@SuppressWarnings("unused")
	public void checkVersion() {
		try { // Check For Protocol Patch
			Class.forName( "net.minecraft.server.v1_7_R4.ChatSerializer" );
			Class.forName( "org.spigotmc.ProtocolInjector" );
			getLogger().log(Level.INFO, "[Startup] Protocol Patch Detected..");
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_7.TabListJoin(), this);
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_7.TitleJoin(), this);
			ActionTime = getPlugin().getConfig().getInt("ActionAnnouncer.Time");
			BukkitTask ActionAnnouncer = new me.Fabricio20.Bukkit.Runnables.V1_7.ActionAnnouncer().runTaskTimer(getPlugin(), 20, ActionTime * 20);
		} catch( ClassNotFoundException e ) {
			 check180();
		}
	}
	
	@SuppressWarnings("unused")
	public void check180() {
		try { // Check For 1.8.0
			Class.forName( "net.minecraft.server.v1_8_R1.ChatSerializer" );
			getLogger().log(Level.INFO, "[Startup] Spigot 1.8.0 Detected..");
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_8.v1.TabListJoin(), this);
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_8.v1.TitleJoin(), this);
			getServer().getPluginManager().registerEvents(new JoinListenerForTags(), this);
			ActionTime = getPlugin().getConfig().getInt("ActionAnnouncer.Time");
			BukkitTask ActionAnnouncer = new me.Fabricio20.Bukkit.Runnables.V1_8.v1.ActionAnnouncer().runTaskTimer(getPlugin(), 20, ActionTime * 20);
		} catch( ClassNotFoundException e ) {
			 check183();
		}
	}
	
	@SuppressWarnings("unused")
	public void check183() {
		try { // Check For 1.8.3
			Class.forName( "org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer" );
			getLogger().log(Level.INFO, "Spigot 1.8.3 Detected..");
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_8.v2.TabListJoin(), this);
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_8.v2.TitleJoin(), this);
			getServer().getPluginManager().registerEvents(new JoinListenerForTags(), this);
			ActionTime = getPlugin().getConfig().getInt("ActionAnnouncer.Time");
			BukkitTask ActionAnnouncer = new me.Fabricio20.Bukkit.Runnables.V1_8.v2.ActionAnnouncer().runTaskTimer(getPlugin(), 20, ActionTime * 20);
		} catch( ClassNotFoundException e ) {
			check186();
		}
	}
	
	@SuppressWarnings("unused")
	public void check186() {
		try { // Check For 1.8.6
			Class.forName("org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer" );
			getLogger().log(Level.INFO, "Spigot 1.8.6 Detected..");
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_8.v3.TabListJoin(), this);
			getServer().getPluginManager().registerEvents(new me.Fabricio20.Bukkit.Listeners.V1_8.v3.TitleJoin(), this);
			getServer().getPluginManager().registerEvents(new JoinListenerForTags(), this);
			ActionTime = getPlugin().getConfig().getInt("ActionAnnouncer.Time");
			BukkitTask ActionAnnouncer = new me.Fabricio20.Bukkit.Runnables.V1_8.v3.ActionAnnouncer().runTaskTimer(getPlugin(), 20, ActionTime * 20);
		} catch( ClassNotFoundException e ) {
			Bukkit.getLogger().log(Level.WARNING, "Unsuported Server Version Detected!");
			Bukkit.getLogger().log(Level.WARNING, "Some Options Where Disabled!");
		}
	}
	
	private void initConfigs() {
		this.manager = new SimpleConfigManager(this);
		String[] header = {"HubBasics Main Configuration File", "Documentation Can Be Found On GitHub Page", "Dont forget to add the world at the worlds section"};
		this.config = manager.getNewConfig("config.yml", header);
		this.config.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header2 = {"HubBasics Item Configuration File", "Documentation Can Be Found On GitHub Page", "Edit with caution! Malformed YAML can break the plugin!"};
		this.JoinItems = manager.getNewConfig("JoinItems.yml", header2);
		this.JoinItems.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header3 = {"HubBasics Storage File", "Please do not change anything in this file"};
		this.Hub = manager.getNewConfig("Storage/Hub.yml", header3);
		this.Hub.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header4 = {"HubBasics Warp Storage File", "Please do not change anything in this file"};
		this.Warps = manager.getNewConfig("Storage/Warps.yml", header4);
		this.Warps.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header5 = {"HubBasics Language File", "Change with caution / Color codes are supported!"};
		this.Language = manager.getNewConfig("Language.yml", header5);
		this.Language.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header6 = {"HubBasics Chest File", "Change with caution / Color codes are supported!"};
		this.QuickWarpChest = manager.getNewConfig("Chests/QuickWarp.yml", header6);
		this.QuickWarpChest.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header7 = {"HubBasics Tags System", "Only Works On 1.8 Servers!", "Change with caution / Color codes are supported!"};
		this.Tags = manager.getNewConfig("Tags.yml", header7);
		this.Tags.saveConfig();
		//
		this.manager = new SimpleConfigManager(this);
		String[] header8 = {"HubBasics Custom Items File", "These Items Are Displayed On Chests Like Settings Chest"};
		this.ChestItems = manager.getNewConfig("ChestItems.yml", header8);
		this.ChestItems.saveConfig();
	}
	
	public CustomEnchantment ench = new CustomEnchantment(69);
	
	private void fixEnchant() {
		try {
			try {
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Enchantment.registerEnchantment(ench);
			} catch (IllegalArgumentException e) {
				System.out.println("[HubBasics] Failed To Register Enchantment ID, Is this a reload?");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
