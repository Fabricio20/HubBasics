package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Copyright (C) Fabricio20 2018 - All Rights Reserved.
 * Created by Fabricio20 on 2018-01-14.
 */
public class DoubleJump extends Module {

    public DoubleJump() {
        super(EnumModules.DoubleJump, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE) return;
        if(player.isFlying()) return;
        if(!isEnabledInWorld(player.getWorld())) return;
        if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) player.setAllowFlight(true);
    }

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE) return;
        if(!isEnabledInWorld(player.getWorld())) return;

        Section section = getWorldConfiguration(player.getWorld().getName());

        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        Sound sound = FinderUtil.findSound(section.getString("Sound", "NOPE"));
        player.setVelocity(player.getLocation().getDirection().multiply(section.getDouble("Force", 1.0)).setY(1));
        if(sound != null) {
            player.playSound(player.getLocation(), sound, 1.0F, -5.0F);
        }
    }

}
