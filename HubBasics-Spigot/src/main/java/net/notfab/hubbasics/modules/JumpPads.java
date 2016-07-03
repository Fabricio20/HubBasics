package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPads extends Module {

    double padPower;
    Material mat;

    public JumpPads() {
        super(ModuleEnum.JUMP_PADS);

        try {
            this.padPower = getDouble(ConfigurationKey.JUMP_PADS_FORCE);
        } catch (IllegalArgumentException ex) {
            HMessenger.printStackTrace(new IllegalArgumentException("Invalid force for jump pad"));
            return;
        }

        try {
            this.mat = Material.valueOf(getString(ConfigurationKey.JUMP_PADS_MATERIAL));
        } catch (IllegalArgumentException ex) {
            HMessenger.printStackTrace(new IllegalArgumentException("Invalid material name for jump pad"));
            return;
        }
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.PHYSICAL && !event.isCancelled() && isInWorld(player.getWorld(), ConfigurationKey.JUMP_PADS_ENABLED)) {
            if (event.getClickedBlock().getType() == Material.STONE_PLATE) {
                Location loc = event.getClickedBlock().getLocation().subtract(0, 1, 0);
                if (loc.getWorld().getBlockAt(loc).getType() == this.mat) {
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
