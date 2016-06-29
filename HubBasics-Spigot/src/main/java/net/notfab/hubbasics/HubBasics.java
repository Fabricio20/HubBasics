package net.notfab.hubbasics;

import lombok.Getter;
import net.notfab.hubbasics.managers.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HubBasics extends JavaPlugin {

    @Getter
    private static HubBasics Instance;

    @Getter private CommandManager commandManager;

    @Override
    public void onEnable() {
        Instance = this;
        commandManager = new CommandManager(this);
        //TODO: Load
    }

    @Override
    public void onDisable() {
        //TODO: Unload
        Instance = null;
    }

}
