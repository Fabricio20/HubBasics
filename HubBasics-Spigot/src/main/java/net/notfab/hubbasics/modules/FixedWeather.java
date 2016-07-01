package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class FixedWeather extends Module {

    public FixedWeather() {
        super(ModuleEnum.FIXED_WEATHER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        if(isInWorld(e.getWorld(), ConfigurationKey.FIXED_WEATHER_ENABLED)) {
            e.setCancelled(true);
        }
    }

}
