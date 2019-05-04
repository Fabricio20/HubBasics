package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-18.
 */
public class KeepHealth extends Module {

    public KeepHealth() {
        super(EnumModules.KeepHealth, NMSVersion.V1_10_R1);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntityType() != EntityType.PLAYER) return;
        World world = event.getEntity().getWorld();
        Section worldConfiguration = getWorldConfiguration(world.getName());
        if(worldConfiguration != null && isEnabledInWorld(world)) {
            double maxHealth = getWorldConfiguration(world.getName()).getDouble("MaxHealth", 20.0);
            double health = getWorldConfiguration(world.getName()).getDouble("Health", 20.0);
            ((Player) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
            ((Player) event.getEntity()).setHealth(health);
            event.setCancelled(true);
        }
    }

}
