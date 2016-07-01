package net.notfab.hubbasics.abstracts.module;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.World;
import org.bukkit.event.Listener;
import sun.security.ssl.HandshakeMessage;

import java.util.List;

public abstract class Module implements Listener {

    @Getter private final ModuleEnum moduleEnum;

    public Module(ModuleEnum moduleEnum) {
        this.moduleEnum = moduleEnum;
    }

    public void onEnableInternal() {
        HMessenger.sendDebugMessage("[ModuleManager] " + moduleEnum.name() + " Enabled");
        this.onEnable();
    }

    public void onDisableInternal() {
        HMessenger.sendDebugMessage("[ModuleManager] " + moduleEnum.name() + " Disabled");
        this.onDisable();
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public Boolean isInWorld(World world, ConfigurationKey configurationKey) {
        List<String> worlds = HubBasics.getInstance().getPluginConfiguration().getStringList(configurationKey);
        return worlds.contains(world.getName());
    }

    public String getString(ConfigurationKey configurationKey) {
        return HubBasics.getInstance().getPluginConfiguration().getString(configurationKey);
    }

    public Double getDouble(ConfigurationKey configurationKey) {
        return HubBasics.getInstance().getPluginConfiguration().getDouble(configurationKey);
    }

}
