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
import net.notfab.hubbasics.spigot.plugin.messages.HMessenger;
import net.notfab.hubbasics.spigot.plugin.settings.ConfigurationKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiVoid extends Module {

    public AntiVoid() {
        super(ModuleEnum.ANTI_VOID);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getCause() != EntityDamageEvent.DamageCause.VOID) return;
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if(!isEnabledInWorld(player.getWorld())) return;
        e.setCancelled(true);
        e.setDamage(0.0);
        HMessenger.sendMessage(player, ConfigurationKey.ANTI_VOID_MESSAGE);
        player.teleport(player.getWorld().getSpawnLocation());
    }

}
