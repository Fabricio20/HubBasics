package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class KeepHealth extends Module {

    public KeepHealth() {
        super(ModuleEnum.KEEP_HEALTH);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player && isInWorld(e.getEntity().getWorld(), ConfigurationKey.KEEP_HEALTH_ENABLED)) {
            e.setCancelled(true);
            e.setDamage(0.0);
        }
    }

}
