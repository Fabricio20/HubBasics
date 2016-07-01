package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
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
        if(!isInWorld(player.getWorld(), ConfigurationKey.DOUBLE_JUMP_ENABLED)) {
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
        if(player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if(player.isFlying()) {
            return;
        }
        if(!isInWorld(player.getWorld(), ConfigurationKey.DOUBLE_JUMP_ENABLED)) {
            return;
        }
        if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) {
            player.setAllowFlight(true);
        }
    }

}
