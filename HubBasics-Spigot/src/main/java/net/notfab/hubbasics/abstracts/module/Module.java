package net.notfab.hubbasics.abstracts.module;

import lombok.Getter;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.World;
import org.bukkit.event.Listener;

public abstract class Module implements Listener {

    @Getter private final ModuleEnum moduleEnum;

    public Module(ModuleEnum moduleEnum) {
        this.moduleEnum = moduleEnum;
    }

    public Boolean isInWorld(World world, ConfigurationKey configurationKey) {
        //TODO: Implement this as Idk how Mister is doing it - Fab
        return false;
    }

}
