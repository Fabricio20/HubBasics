package net.notfab.hubbasics.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.notfab.hubbasics.bungee.commands.LobbyCommand;
import net.notfab.hubbasics.bungee.managers.ConfigHandler;
import net.notfab.hubbasics.bungee.managers.Logger;
import net.notfab.spigot.simpleconfig.SimpleConfigManager;
import net.notfab.spigot.simpleconfig.bungee.BungeeConfigManager;
import org.bstats.bungeecord.Metrics;

public class HubBasics extends Plugin {

    @Getter
    private static HubBasics Instance;
    @Getter
    private Logger loggerManager;
    @Getter
    private SimpleConfigManager configManager;
    @Getter
    private Metrics metrics;

    @Override
    public void onEnable() {
        Instance = this;
        this.loggerManager = new Logger(null);
        this.configManager = new BungeeConfigManager(this);
        new ConfigHandler(getDataFolder());
        if (System.getenv("HubBasics.Develop") == null) {
            this.metrics = new Metrics(this);
        }
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand());
    }

    @Override
    public void onDisable() {
        getProxy().getPluginManager().unregisterCommands(this);
    }

}