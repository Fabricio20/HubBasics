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
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJump extends Module {

    public DoubleJump() {
        super(ModuleEnum.DOUBLE_JUMP);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onJump(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if(!isEnabledInWorld(player.getWorld())) {
            return;
        }
        e.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        Sound sound;
        try {
            sound = Sound.valueOf(getString(ConfigurationKey.DOUBLE_JUMP_SOUND));
        } catch (IllegalArgumentException ex) {
            HMessenger.printStackTrace(new IllegalArgumentException("Invalid sound name for DoubleJump sound"));
            return;
        }
        player.setVelocity(player.getLocation().getDirection().multiply(getDouble(ConfigurationKey.DOUBLE_JUMP_FORCE)).setY(1));
        player.playSound(player.getLocation(), sound, 1.0F, -5.0F);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE) return;
        if(player.isFlying()) return;
        if(!isEnabledInWorld(player.getWorld())) return;
        if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) player.setAllowFlight(true);
    }
}
