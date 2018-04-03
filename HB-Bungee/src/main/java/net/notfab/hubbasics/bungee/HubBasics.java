package net.notfab.hubbasics.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.notfab.hubbasics.bungee.commands.LobbyCommand;
import net.notfab.hubbasics.bungee.managers.Logger;
import net.notfab.hubbasics.bungee.managers.SimpleConfigManager;

public class HubBasics extends Plugin {

    @Getter private static HubBasics Instance;
    @Getter private Logger loggerManager;
    @Getter private SimpleConfigManager configManager;

    @Override
    public void onEnable() {
        Instance = this;
        this.loggerManager = new Logger(null);
        this.configManager = new SimpleConfigManager(this);
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand());
    }

    @Override
    public void onDisable() {
        getProxy().getPluginManager().unregisterCommands(this);
    }

}
