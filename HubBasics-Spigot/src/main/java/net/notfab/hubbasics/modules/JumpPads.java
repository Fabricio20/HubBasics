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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPads extends Module {

    public JumpPads() {
        super(ModuleEnum.JUMP_PADS);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        Material mat;
        try {
            mat = Material.valueOf(getString(ConfigurationKey.JUMP_PADS_MATERIAL));
        } catch (IllegalArgumentException ex) {
            HMessenger.printStackTrace(new IllegalArgumentException("Invalid material name for jump pad"));
            return;
        }
        if(!(getBlockBelow(player.getLocation()) == mat)) {
            return;
        }
        Double padPower;
        try {
            padPower = getDouble(ConfigurationKey.JUMP_PADS_FORCE);
        } catch (IllegalArgumentException ex) {
            HMessenger.printStackTrace(new IllegalArgumentException("Invalid force for jump pad"));
            return;
        }
        player.setVelocity(calculateVector(player, padPower));
    }

    private Material getBlockBelow(Location loc) {
        Location block = new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ(), loc.getYaw(), loc.getPitch());
        return block.getBlock().getType();
    }

    private Vector calculateVector(Player player, Double padPower) {
        double radians = Math.toRadians(player.getLocation().getYaw());
        double x = -Math.sin(radians)*padPower;
        double y = padPower/6;
        double z = Math.cos(radians)*padPower;
        return new Vector(x, y, z);
    }

}
