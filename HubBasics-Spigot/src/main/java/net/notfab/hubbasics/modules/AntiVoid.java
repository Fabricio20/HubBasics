package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
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
        if(e.getCause() != EntityDamageEvent.DamageCause.VOID) {
            return;
        }
        if(!(e.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getEntity();
        if(!isInWorld(player.getWorld(), ConfigurationKey.ANTI_VOID_ENABLED)) {
            return;
        }
        e.setCancelled(true);
        e.setDamage(0.0);
        HMessenger.sendMessage(player, ConfigurationKey.ANTI_VOID_MESSAGE);
        player.teleport(player.getWorld().getSpawnLocation());
    }

}
