package me.Fabricio20;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import me.Fabricio20.listeners.RainListener;
import me.Fabricio20.listeners.RightClickListener;
import me.Fabricio20.listeners.ServerPingListener;
import me.Fabricio20.listeners.SignChangeListener;
import me.Fabricio20.listeners.VoidFallListener;
import me.Fabricio20.runnables.AntiOp;
import me.Fabricio20.runnables.BossAnnouncer;
import me.Fabricio20.runnables.ChatAnnouncer;
import me.Fabricio20.methods.MakeItemsConfig;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	public static FileConfiguration StorageConfig2 = null;
    public static File Storage2 = null;
	
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
		setupConfig();
		reloadCustomConfig();
		reloadCustomConfig2();
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
		getServer().getPluginManager().registerEvents(new ItemMoveListener(this), this);
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
		getCommand("hb").setExecutor(new Commands(this));
		getCommand("uuid").setExecutor(new Commands(this));
		plugin = this;
		Strings.Prefix = plugin.getConfig().getString("Others.Prefix").replace("&", "§");
		Strings.LaunchPadBlock = getConfig().getString("Others.JumpPadBlock");
		getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
		try {
			MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		    System.out.println("[HubBasics] Metrics Started!");
		} catch (IOException e) {
		   System.out.println("[HubBasics] Error while trying to submit metrics!");
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
		if(!getConfig().contains("Worlds")) {
			ArrayList<String> worlds = new ArrayList<String>();
			worlds.add("Example");
			getConfig().set("Worlds", worlds);
			saveConfig();
		}
	}
	
	/////////
	
	public void reloadCustomConfig() {
        if (Storage == null) {
        	Storage = new File(getDataFolder(), "Storage.yml");
        }
        StorageConfig = YamlConfiguration.loadConfiguration(Storage);
        if(!StorageConfig.contains("Hub.World")) {
            StorageConfig.set("Hub.World", Bukkit.getWorlds().get(0).getSpawnLocation().getWorld().getName());
            StorageConfig.set("Hub.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
            StorageConfig.set("Hub.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
            StorageConfig.set("Hub.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
            StorageConfig.set("Hub.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
            StorageConfig.set("Hub.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
        }
        saveCustomConfig();
    }
	
    public FileConfiguration getCustomConfig() {
        if (StorageConfig == null) {
            reloadCustomConfig();
        }
        return StorageConfig;
    }
	
    public void saveCustomConfig() {
        if (StorageConfig == null || Storage == null) {
            return;
        }
        try {
            getCustomConfig().save(Storage);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + Storage, ex);
        }
    }
    
    //////
    
    public void reloadCustomConfig2() {
        if (Storage2 == null) {
        	Storage2 = new File(getDataFolder(), "Items.yml");
        }
        StorageConfig2 = YamlConfiguration.loadConfiguration(Storage2);
        if(!StorageConfig2.contains("Item1.Enabled")) {
        	MakeItemsConfig.make1();
        }
        if(!StorageConfig2.contains("Item2.Enabled")) {
        	MakeItemsConfig.make2();
        }
        if(!StorageConfig2.contains("Item3.Enabled")) {
        	MakeItemsConfig.make3();
        }
        if(!StorageConfig2.contains("Item4.Enabled")) {
        	MakeItemsConfig.make4();
        }
        if(!StorageConfig2.contains("Item5.Enabled")) {
        	MakeItemsConfig.make5();
        }
        if(!StorageConfig2.contains("Item6.Enabled")) {
        	MakeItemsConfig.make6();
        }
        if(!StorageConfig2.contains("Item7.Enabled")) {
        	MakeItemsConfig.make7();
        }
        if(!StorageConfig2.contains("Item8.Enabled")) {
        	MakeItemsConfig.make8();
        }
        if(!StorageConfig2.contains("Item9.Enabled")) {
        	MakeItemsConfig.make9();
        }
        saveCustomConfig2();
    }
	
    public FileConfiguration getCustomConfig2() {
        if (StorageConfig2 == null) {
            reloadCustomConfig2();
        }
        return StorageConfig2;
    }
	
    public void saveCustomConfig2() {
        if (StorageConfig2 == null || Storage2 == null) {
            return;
        }
        try {
            getCustomConfig2().save(Storage2);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + Storage2, ex);
        }
    }
	
}
