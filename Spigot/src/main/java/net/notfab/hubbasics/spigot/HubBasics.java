package net.notfab.hubbasics.spigot;

import lombok.Getter;
import net.notfab.hubbasics.spigot.listeners.BungeeListener;
import net.notfab.hubbasics.spigot.managers.*;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.spigot.simpleconfig.SimpleConfigManager;
import net.notfab.spigot.simpleconfig.spigot.SpigotConfigManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class HubBasics extends JavaPlugin {

    @Getter
    private static HubBasics instance;

    private HBLogger loggerManager;
    private TaskManager taskManager;
    private SimpleConfigManager configManager;
    private ConfigHandler configHandler;
    private Messenger messenger;
    private Metrics metrics;
    private CommandFramework commandFramework;
    private NMSVersion NMS;
    private ItemManager itemManager;
    private MenuManager menuManager;
    private LocationManager locationManager;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        instance = this;
        this.loggerManager = new HBLogger(null);
        HBLogger.setUnderlying(this.getLogger());
        this.taskManager = new TaskManager();
        this.configManager = new SpigotConfigManager(this);
        this.configHandler = new ConfigHandler(this);
        Bukkit.getPluginManager().registerEvents(this.configHandler, this);
        this.messenger = new Messenger();
        if (System.getenv("HubBasics.Spigot") == null) {
            this.metrics = new Metrics(this, 2353);
        } else {
            loggerManager.info("Running in Development mode, Metrics disabled.");
        }
        this.commandFramework = new CommandFramework();
        this.NMS = new NMSVersion();
        this.itemManager = new ItemManager(this.NMS.getRunningVersion());
        this.menuManager = new MenuManager();
        this.locationManager = new LocationManager();
        this.moduleManager = new ModuleManager(this.NMS.getRunningVersion());

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener());
    }

    @Override
    public void onDisable() {
        instance = null;
        this.moduleManager.onDisable();
    }

}