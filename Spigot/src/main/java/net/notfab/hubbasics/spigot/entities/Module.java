package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.managers.Logger;
import net.notfab.spigot.simpleconfig.Section;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.World;
import org.bukkit.event.Listener;

public abstract class Module implements Listener {

    protected final HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();
    protected final Logger logger;

    @Getter
    private final EnumModules module;

    @Getter
    private final String minimumVersion;

    public Module(EnumModules module, String version) {
        this.module = module;
        this.minimumVersion = version;
        this.logger = HubBasics.getLoggerManager().getLogger(module);
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public SimpleConfig getConfig() {
        return HubBasics.getConfigManager().getNewConfig("modules/" + this.module.name() + ".yml");
    }

    protected Section getWorldConfiguration(String world) {
        if (getConfig().contains(world)) {
            return getConfig().getSection(world);
        } else if (getConfig().contains(world.toLowerCase())) {
            return getConfig().getSection(world.toLowerCase());
        } else {
            return null;
        }
    }

    protected boolean isEnabledInWorld(World world) {
        Section section = getWorldConfiguration(world.getName());
        return getConfig().getBoolean("Enabled", true) && (section != null && section.getBoolean("Enabled", false));
    }

    public Boolean isEnabled() {
        return getConfig().getBoolean("Enabled", true);
    }

}