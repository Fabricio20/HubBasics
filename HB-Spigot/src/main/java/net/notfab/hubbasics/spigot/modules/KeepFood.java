package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-18.
 */
public class KeepFood extends Module {

    public KeepFood() {
        super(EnumModules.KeepFood, NMSVersion.V1_10_R1);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        World world = event.getEntity().getWorld();
        ConfigurationSection worldConfiguration = getWorldConfiguration(world.getName());
        if(worldConfiguration != null && worldConfiguration.getBoolean("Enabled", false)) {
            int food = worldConfiguration.getInt("Food", 20);
            if(food > 0 && food <= 20)
                event.setFoodLevel(food);
            event.setCancelled(true);
        }
    }

}
