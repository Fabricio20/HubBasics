package net.notfab.hubbasics.spigot.modules;

import lombok.Getter;
import net.notfab.hubbasics.spigot.commands.LobbyCommand;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-18.
 */
public class Lobby extends Module {

    public Lobby() {
        super(EnumModules.Lobby, NMSVersion.V1_7_R1);
    }

    @Getter private Location location;
    private LobbyCommand lobbyCommand = new LobbyCommand();

    @Override
    public void onEnable() {
        SimpleConfig config = getConfig();
        if (config.contains("Location")) {
            this.reloadLocation(config);
        } else {
            this.location = Bukkit.getWorlds().get(0).getSpawnLocation();
        }
        HubBasics.getCommandFramework().register(this.lobbyCommand);
    }

    public void reloadLocation(SimpleConfig config) {
        Location worldSpawn = Bukkit.getWorlds().get(0).getSpawnLocation();
        double x = config.getDouble("Location.X", worldSpawn.getX());
        double y = config.getDouble("Location.Y", worldSpawn.getY());
        double z = config.getDouble("Location.Z", worldSpawn.getZ());
        float yaw = Float.valueOf(String.valueOf(config.getDouble("Location.Yaw", worldSpawn.getYaw())));
        float pitch = Float.valueOf(String.valueOf(config.getDouble("Location.Pitch", worldSpawn.getPitch())));
        World world = FinderUtil.findWorld(config.getString("Location.World", worldSpawn.getWorld().getName()));
        this.location = new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public void onDisable() {
        HubBasics.getCommandFramework().unregister(this.lobbyCommand);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (getConfig().getBoolean("Lobby.TpOnJoin", false)) {
            event.getPlayer().teleport(this.location);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (getConfig().getBoolean("Lobby.TpOnRespawn", false)) {
            event.getPlayer().teleport(this.location);
        }
    }

}
