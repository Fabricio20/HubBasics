package net.notfab.hubbasics.spigot.modules.v1_7;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.hubbasics.spigot.utils.ReflectionUtils;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class KeepHealth extends Module {

    public KeepHealth() {
        super(EnumModules.KeepHealth, CraftBukkitVersion.v1_7_X);
    }

    private Method maxHealthMethod;
    private Map<String, Double> worldHealthMap = new HashMap<>();
    private Map<String, Double> worldMaxHealthMap = new HashMap<>();

    @Override
    public void onEnable() {
        this.maxHealthMethod = ReflectionUtils.findMethod(Player.class, "setMaxHealth", 0d);
        Bukkit.getWorlds().stream().filter(this::isEnabledInWorld).forEach((world) -> {
            Section config = this.getWorldConfiguration(world.getName());
            double health = config.getDouble("Health", 20.0);
            double maxHealth = config.getDouble("MaxHealth", health);
            this.worldHealthMap.put(world.getName().toLowerCase(), health);
            this.worldMaxHealthMap.put(world.getName().toLowerCase(), maxHealth);
        });
    }

    @Override
    public void onDisable() {
        this.maxHealthMethod = null;
        this.worldHealthMap.clear();
        this.worldMaxHealthMap.clear();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntityType() != EntityType.PLAYER) return;
        String world = event.getEntity().getWorld().getName().toLowerCase();
        Player player = (Player) event.getEntity();
        if (this.worldHealthMap.containsKey(world)) {
            player.setHealth(this.worldHealthMap.get(world));
            ReflectionUtils.invokeMethod(player, this.maxHealthMethod, this.worldMaxHealthMap.get(world));
            event.setCancelled(true);
        }
    }

}