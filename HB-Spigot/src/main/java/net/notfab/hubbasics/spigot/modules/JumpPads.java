package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.hubbasics.spigot.nms.particle.ParticleEffect;
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
    private Map<String, Double> verticalPower = new HashMap<>();
    private Map<String, Boolean> requirePressurePlate = new HashMap<>();
    private Map<String, Material> plateTypes = new HashMap<>();
    private Map<String, Boolean> noBlockRequired = new HashMap<>();
    private Map<String, Sound> soundMap = new HashMap<>();
    private Map<String, ParticleEffect> effectMap = new HashMap<>();

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
            if(section.contains("VerticalPower") && section.isDouble("VerticalPower")) {
                verticalPower.put(world.getName(), section.getDouble("VerticalPower"));
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
                noBlockRequired.put(world.getName(), pp.getBoolean("NoBlockRequired", false));
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
            if(section.contains("Effect") && section.isString("Effect")) {
                ParticleEffect effect = ParticleEffect.fromName(section.getString("Effect"));
                if(effect != null) {
                    effectMap.put(world.getName(), effect);
                }
            }
        });
    }

    @Override
    public void onDisable() {
        this.worldMaterials.clear();
        this.padPower.clear();
        this.verticalPower.clear();
        this.requirePressurePlate.clear();
        this.plateTypes.clear();
        this.noBlockRequired.clear();
    }

    private Double getPower(Player player) {
        return padPower.getOrDefault(player.getWorld().getName(), 2.0);
    }

    private Double getVerticalPower(Player player) {
        return verticalPower.getOrDefault(player.getWorld().getName(), 1.0);
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

    private boolean isBlockRequired(Player player) {
        return !this.noBlockRequired.getOrDefault(player.getWorld().getName(), false);
    }

    private Sound getSound(Player player) {
        return this.soundMap.getOrDefault(player.getWorld().getName(), null);
    }

    private ParticleEffect getEffect(Player player) {
        return this.effectMap.getOrDefault(player.getWorld().getName(), null);
    }

    // ----------------------------------------------------------

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(needsPressurePlate(event.getPlayer()) || !isBlockRequired(event.getPlayer())) return;
        Player player = event.getPlayer();
        if (!isEnabledInWorld(player.getWorld())) return;
        Location loc =  player.getLocation().subtract(0, 1, 0);
        if (loc.getBlock().getType() == getMaterial(player)) {
            player.setVelocity(calculateVector(player));
            if(getSound(player) != null) {
                player.playSound(player.getLocation(), getSound(player), 1, 1);
            }
            if(getEffect(player) != null) {
                getEffect(player).display(1, 1, 1, 1, 40, player.getLocation(), player);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!needsPressurePlate(event.getPlayer())) return;
        Player player = event.getPlayer();
        if(event.getAction() == Action.PHYSICAL && !event.isCancelled() && isEnabledInWorld(player.getWorld())) {
            if(event.getClickedBlock().getType() == getPlateType(player)) {
                boolean apply = true;
                if(isBlockRequired(player)) {
                    Location loc = event.getClickedBlock().getLocation().subtract(0, 1, 0);
                    apply = loc.getWorld().getBlockAt(loc).getType() == getMaterial(player);
                }
                if(apply) {
                    player.setVelocity(calculateVector(player));
                    if(getSound(player) != null) {
                        player.playSound(player.getLocation(), getSound(player), 1, 1);
                    }
                    if(getEffect(player) != null) {
                        getEffect(player).display(1, 1, 1, 1, 40, player.getLocation(), player);
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    private Vector calculateVector(Player player) {
        double radians = Math.toRadians(player.getLocation().getYaw());
        double x = -Math.sin(radians) * getPower(player);
        double y = getVerticalPower(player);
        double z = Math.cos(radians) * getPower(player);
        return new Vector(x, y, z);
    }

}
