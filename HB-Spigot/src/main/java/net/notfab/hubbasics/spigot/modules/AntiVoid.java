package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Copyright (C) Fabricio20 2018 - All Rights Reserved.
 * Created by Fabricio20 on 2018-01-14.
 */
public class AntiVoid extends Module {

    public AntiVoid() {
        super(EnumModules.AntiVoid, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onVoidDamage(EntityDamageEvent event) {
        World world = event.getEntity().getWorld();
        if(event.getCause() != EntityDamageEvent.DamageCause.VOID) return;
        if(event.getEntityType() != EntityType.PLAYER) return;
        ConfigurationSection worldConfiguration = getWorldConfiguration(world.getName());
        if(worldConfiguration != null && worldConfiguration.getBoolean("Enabled", false)) {
            HLocation location = HubBasics.getLocationManager().get(worldConfiguration.getString("Warp", null));
            if(location == null) {
                event.getEntity().teleport(event.getEntity().getWorld().getSpawnLocation());
            } else {
                location.teleport((Player) event.getEntity());
            }
        }
    }
}
