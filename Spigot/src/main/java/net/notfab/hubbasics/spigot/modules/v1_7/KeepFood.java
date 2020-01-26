package net.notfab.hubbasics.spigot.modules.v1_7;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.HashMap;
import java.util.Map;

public class KeepFood extends Module {

    private final Map<String, Integer> foodLevelMap = new HashMap<>();

    public KeepFood() {
        super(EnumModules.KeepFood, CraftBukkitVersion.v1_7_X);
    }

    @Override
    public void onEnable() {
        Bukkit.getWorlds().stream().filter(this::isEnabledInWorld).forEach((world) -> {
            Section config = this.getWorldConfiguration(world.getName());
            int foodLevel = config.getInt("Food", 20);
            this.foodLevelMap.put(world.getName().toLowerCase(), foodLevel);
        });
    }

    @Override
    public void onDisable() {
        this.foodLevelMap.clear();
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        String world = event.getEntity().getWorld().getName().toLowerCase();
        if (this.foodLevelMap.containsKey(world)) {
            int food = this.foodLevelMap.get(world);
            if (food > 0 && food <= 20)
                event.setFoodLevel(food);
        }
    }

}