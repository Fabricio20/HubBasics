package net.notfab.hubbasics.modules;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPads extends Module {

    private double padPower;
    private Material material;
    private boolean requirePressurePlate;

    public JumpPads() {
        super(ModuleEnum.JUMP_PADS);

        this.padPower = getDouble(ConfigurationKey.JUMP_PADS_FORCE);
        this.material = Material.valueOf(getString(ConfigurationKey.JUMP_PADS_MATERIAL));
        this.requirePressurePlate = getBoolean(ConfigurationKey.JUMP_PADS_REQUIRE_PRESSUREPLATE);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (requirePressurePlate) return;
        Player player = event.getPlayer();
        if (!isEnabledInWorld(player.getWorld())) return;
        Location loc =  player.getLocation().subtract(0, 1, 0);
        if (loc.getBlock().getType() == this.material) {
            player.setVelocity(calculateVector(player));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!requirePressurePlate) return;
        Player player = event.getPlayer();
        if (event.getAction() == Action.PHYSICAL && !event.isCancelled() && isEnabledInWorld(player.getWorld())) {
            if (event.getClickedBlock().getType() == Material.STONE_PLATE) {
                Location loc = event.getClickedBlock().getLocation().subtract(0, 1, 0);
                if (loc.getWorld().getBlockAt(loc).getType() == this.material) {
                    player.setVelocity(calculateVector(player));
                    event.setCancelled(true);
                }
            }
        }
    }

    private Vector calculateVector(Player player) {
        double radians = Math.toRadians(player.getLocation().getYaw());
        double x = -Math.sin(radians)*padPower;
        double y = padPower/6;
        double z = Math.cos(radians)*padPower;
        return new Vector(x, y, z);
    }
}
