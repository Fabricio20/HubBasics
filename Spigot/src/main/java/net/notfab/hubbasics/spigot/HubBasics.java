package net.notfab.hubbasics.spigot;

import lombok.Getter;
import net.notfab.hubbasics.spigot.entities.EnumModules;
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

    private Logger loggerManager;
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
        this.loggerManager = new Logger(null);
        this.taskManager = new TaskManager();
        this.configManager = new SpigotConfigManager(this);
        this.configHandler = new ConfigHandler(this);
        Bukkit.getPluginManager().registerEvents(this.configHandler, this);
        this.messenger = new Messenger();
        this.metrics = new Metrics(this);
        this.commandFramework = new CommandFramework();
        this.NMS = new NMSVersion();
        this.itemManager = new ItemManager();
        this.menuManager = new MenuManager();
        this.locationManager = new LocationManager();
        this.moduleManager = new ModuleManager();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener());
    }

    @Override
    public void onDisable() {
        instance = null;
        this.moduleManager.getModule(EnumModules.BossAnnouncer).onDisable();
    }

}