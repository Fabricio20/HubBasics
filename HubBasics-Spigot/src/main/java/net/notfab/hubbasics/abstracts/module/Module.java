package net.notfab.hubbasics.abstracts.module;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.World;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class Module implements Listener {

    @Getter private final ModuleEnum moduleEnum;

    public Module(ModuleEnum moduleEnum) {
        this.moduleEnum = moduleEnum;
    }

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
