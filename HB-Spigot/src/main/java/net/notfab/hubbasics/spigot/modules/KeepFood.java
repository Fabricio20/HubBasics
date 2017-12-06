package net.notfab.hubbasics.spigot.modules;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.spigot.abstracts.module.Module;
import net.notfab.hubbasics.spigot.abstracts.module.ModuleEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class KeepFood extends Module {

    public KeepFood() {
        super(ModuleEnum.KEEP_FOOD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent e) {
        if(isEnabledInWorld(e.getEntity().getWorld())) {
            e.setCancelled(true);
            e.setFoodLevel(20);
        }
    }
}
