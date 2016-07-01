package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class KeepFood extends Module {

    public KeepFood() {
        super(ModuleEnum.KEEP_FOOD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent e) {
        if(isInWorld(e.getEntity().getWorld(), ConfigurationKey.KEEP_FOOD_ENABLED)) {
            e.setCancelled(true);
            e.setFoodLevel(20);
        }
    }

}
