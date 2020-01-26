package net.notfab.hubbasics.spigot.modules.v1_7;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.HashMap;
import java.util.Map;

public class NoWeather extends Module {

    private Map<String, WeatherType> worldStates = new HashMap<>();

    public NoWeather() {
        super(EnumModules.NoWeather, CraftBukkitVersion.v1_7_X);
    }

    @Override
    public void onEnable() {
        Bukkit.getWorlds().forEach(world -> {
            Section section = getConfig().getSection(world.getName());
            if (section == null) return;
            if (!section.getBoolean("Enabled", false)) return;
            WeatherType type;
            try {
                type = WeatherType.valueOf(section.getString("State", "CLEAR"));
            } catch (IllegalArgumentException ex) {
                logger.warn("Invalid weather state '"
                        + section.getString("State") + "' for the world '" + world.getName() + "'.");
                type = WeatherType.CLEAR;
            }
            worldStates.put(world.getName(), type);
            if (type == WeatherType.CLEAR) {
                world.setWeatherDuration(0);
            } else {
                world.setWeatherDuration(100);
            }
        });
    }

    @Override
    public void onDisable() {
        this.worldStates.clear();
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        WeatherType type = this.worldStates.get(event.getWorld().getName());
        if (type == null) return;
        if (type == WeatherType.CLEAR && event.toWeatherState()) {
            event.setCancelled(true);
        } else if (type == WeatherType.DOWNFALL && !event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

}