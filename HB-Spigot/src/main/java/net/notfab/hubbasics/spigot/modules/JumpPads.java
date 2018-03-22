package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class JumpPads extends Module {

    private Map<String, Material> worldMaterials = new HashMap<>();
    private Map<String, Double> padPower = new HashMap<>();
    private Map<String, Boolean> requirePressurePlate = new HashMap<>();
    private Map<String, Material> plateTypes = new HashMap<>();
    private Map<String, Sound> soundMap = new HashMap<>();

    public JumpPads() {
        super(EnumModules.JumpPads, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {
        Bukkit.getWorlds().forEach(world -> {
            ConfigurationSection section = getConfig().getConfigurationSection(world.getName());
            if(section == null) return;
            if(!section.getBoolean("Enabled", false)) return;

            if(section.contains("Material")) {
                Material material;
                try {
                    material = Material.getMaterial(section.getString("Material"));
                } catch (IllegalArgumentException ex) {
                    Logger.warn("Invalid Material for pad", ex);
                    return;
                }
                worldMaterials.put(world.getName(), material);
            }
            if(section.contains("Power") && section.isDouble("Power")) {
                padPower.put(world.getName(), section.getDouble("Power"));
            }
            if(section.contains("PressurePlate")) {
                ConfigurationSection pp = section.getConfigurationSection("PressurePlate");
                if(pp == null) return;
                if(pp.contains("Type")) {
                    Material material;
                    try {
                        material = Material.getMaterial(pp.getString("Type"));
                    } catch (IllegalArgumentException ex) {
                        Logger.warn("Invalid Material for pad plate", ex);
                        return;
                    }
                    plateTypes.put(world.getName(), material);
                }
                requirePressurePlate.put(world.getName(), pp.getBoolean("Required", false));
            }
            if(section.contains("Sound") && section.isString("Sound")) {
                Sound sound;
                try {
                    sound = Sound.valueOf(section.getString("Sound"));
                } catch (IllegalArgumentException ex) {
                    Logger.warn("Invalid Sound for jump pad", ex);
                    return;
                }
                soundMap.put(world.getName(), sound);
            }
        });
    }

    @Override
    public void onDisable() {
        this.worldMaterials.clear();
        this.padPower.clear();
        this.requirePressurePlate.clear();
        this.plateTypes.clear();
    }

    private Double getPower(Player player) {
        return padPower.getOrDefault(player.getWorld().getName(), 2.0);
    }

    private boolean needsPressurePlate(Player player) {
        return this.requirePressurePlate.getOrDefault(player.getWorld().getName(), false);
    }

    private Material getMaterial(Player player) {
        return this.worldMaterials.getOrDefault(player.getWorld().getName(), Material.REDSTONE_BLOCK);
    }

    private Material getPlateType(Player player) {
        return this.plateTypes.getOrDefault(player.getWorld().getName(), Material.GOLD_PLATE);
    }

    // ----------------------------------------------------------

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(needsPressurePlate(event.getPlayer())) return;
        Player player = event.getPlayer();
        if (!isEnabledInWorld(player.getWorld())) return;
        Location loc =  player.getLocation().subtract(0, 1, 0);
        if (loc.getBlock().getType() == getMaterial(player)) {
            player.setVelocity(calculateVector(player));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!needsPressurePlate(event.getPlayer())) return;
        Player player = event.getPlayer();
        if(event.getAction() == Action.PHYSICAL && !event.isCancelled() && isEnabledInWorld(player.getWorld())) {
            if(event.getClickedBlock().getType() == getPlateType(player)) {
                Location loc = event.getClickedBlock().getLocation().subtract(0, 1, 0);
                if (loc.getWorld().getBlockAt(loc).getType() == getMaterial(player)) {
                    player.setVelocity(calculateVector(player));
                    event.setCancelled(true);
                }
            }
        }
    }

    private Vector calculateVector(Player player) {
        double radians = Math.toRadians(player.getLocation().getYaw());
        double x = -Math.sin(radians) * getPower(player);
        double y = getPower(player)/6;
        double z = Math.cos(radians) * getPower(player);
        return new Vector(x, y, z);
    }

}
