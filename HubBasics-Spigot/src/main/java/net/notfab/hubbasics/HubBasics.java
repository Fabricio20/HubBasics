package net.notfab.hubbasics;

import lombok.Getter;
import net.notfab.hubbasics.managers.CommandManager;
import net.notfab.hubbasics.settings.HConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

public class HubBasics extends JavaPlugin {

    @Getter private static HubBasics Instance;

    @Getter private CommandManager commandManager;
	@Getter private HConfiguration hConfiguration;

    @Override
    public void onEnable() {
        Instance = this;
	    this.init();
        //TODO: Load
    }

    @Override
    public void onDisable() {
        //TODO: Unload
        Instance = null;
    }

	private void init() {
		this.commandManager = new CommandManager();
		this.hConfiguration = new HConfiguration();

		this.getHConfiguration().loadDefaults();
	}
}
