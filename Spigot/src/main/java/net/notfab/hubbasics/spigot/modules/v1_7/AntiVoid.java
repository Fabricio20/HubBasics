package net.notfab.hubbasics.spigot.modules.v1_7;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiVoid extends Module {

    public AntiVoid() {
        super(EnumModules.AntiVoid, CraftBukkitVersion.v1_7_X);
    }

    @Override
    public void onEnable() {
        // Nothing
    }

    @Override
    public void onDisable() {
        // Nothing
    }

    @EventHandler
    public void onVoidDamage(EntityDamageEvent event) {
        World world = event.getEntity().getWorld();
        if (event.getCause() != EntityDamageEvent.DamageCause.VOID) return;
        if (event.getEntityType() != EntityType.PLAYER) return;
        Section worldConfiguration = getWorldConfiguration(world.getName());
        if (worldConfiguration != null && isEnabledInWorld(world)) {
            HLocation location = HubBasics.getLocationManager().get(worldConfiguration.getString("Warp", null));
            if (location == null) {
                event.getEntity().teleport(event.getEntity().getWorld().getSpawnLocation());
            } else {
                location.teleport((Player) event.getEntity());
            }
        }
    }

}