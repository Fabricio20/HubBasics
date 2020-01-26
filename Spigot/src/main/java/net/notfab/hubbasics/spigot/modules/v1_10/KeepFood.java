package net.notfab.hubbasics.spigot.modules.v1_10;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class KeepFood extends Module {

    public KeepFood() {
        super(EnumModules.KeepFood, CraftBukkitVersion.v1_10_X);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        World world = event.getEntity().getWorld();
        Section worldConfiguration = getWorldConfiguration(world.getName());
        if (worldConfiguration != null && isEnabledInWorld(world)) {
            int food = worldConfiguration.getInt("Food", 20);
            if (food > 0 && food <= 20)
                event.setFoodLevel(food);
            event.setCancelled(true);
        }
    }

}
