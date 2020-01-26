package net.notfab.hubbasics.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.notfab.hubbasics.bungee.commands.LobbyCommand;
import net.notfab.hubbasics.bungee.managers.ConfigHandler;
import net.notfab.hubbasics.bungee.managers.HBLogger;
import net.notfab.spigot.simpleconfig.SimpleConfigManager;
import net.notfab.spigot.simpleconfig.bungee.BungeeConfigManager;
import org.bstats.bungeecord.Metrics;

public class HubBasics extends Plugin {

    @Getter
    private static HubBasics Instance;

    @Getter
    private HBLogger loggerManager;

    @Getter
    private SimpleConfigManager configManager;

    @Getter
    private Metrics metrics;

    @Override
    public void onEnable() {
        Instance = this;
        this.loggerManager = new HBLogger(null);
        HBLogger.setUnderlying(this.getLogger());
        this.configManager = new BungeeConfigManager(this);
        new ConfigHandler(getDataFolder());
        if (System.getenv("HubBasics.Bungee") == null) {
            this.metrics = new Metrics(this, 2353);
        } else {
            loggerManager.info("Running in Development mode, Metrics disabled.");
        }
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand());
    }

    @Override
    public void onDisable() {
        this.loggerManager.onDisable();
        getProxy().getPluginManager().unregisterCommands(this);
    }

}