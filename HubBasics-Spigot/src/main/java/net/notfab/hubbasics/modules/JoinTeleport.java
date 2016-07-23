package net.notfab.hubbasics.modules;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

public class JoinTeleport extends Module {

    private Location location;

    public JoinTeleport() {
        super(ModuleEnum.JOIN_TELEPORT);
    }

    @Override
    public void onEnable() {
        double x = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_X);
        double y = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_Y);
        double z = getDouble(ConfigurationKey.JOIN_TELEPORT_LOCATION_Z);
        World world = Bukkit.getWorld(getString(ConfigurationKey.JOIN_TELEPORT_LOCATION_WORLD));
        this.location = new Location(world, x, y, z);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        if (isInWorld(event.getPlayer().getWorld(), ConfigurationKey.JOIN_TELEPORT_ENABLED)) event.getPlayer().teleport(this.location);
    }
}
