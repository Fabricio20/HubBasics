package net.notfab.hubbasics.spigot.modules.v1_9;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import net.notfab.hubbasics.spigot.utils.ParticleEffect;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.*;
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
    private Map<String, Particle> effectMap = new HashMap<>();

    private ParticleEffect particleEffect = ParticleEffect.getInstance();

    public JumpPads() {
        super(EnumModules.JumpPads, CraftBukkitVersion.v1_9_X);
    }

    @Override
    public void onEnable() {
        Bukkit.getWorlds().forEach(world -> {
            Section section = getConfig().getSection(world.getName());
            if (section == null) return;
            if (!section.getBoolean("Enabled", false)) return;

            if (section.contains("Material")) {
                Material material;
                try {
                    material = Material.getMaterial(section.getString("Material"));
                } catch (IllegalArgumentException ex) {
                    logger.warn("Invalid Material for pad", ex);
                    return;
                }
                worldMaterials.put(world.getName(), material);
            }
            if (section.contains("Power")) {
                if (section.get("Power") instanceof Double) {
                    padPower.put(world.getName(), section.getDouble("Power"));
                } else {
                    padPower.put(world.getName(), (double) section.getInt("Power"));
                }
            }
            if (section.contains("VerticalPower")) {
                if (section.get("VerticalPower") instanceof Double) {
                    verticalPower.put(world.getName(), section.getDouble("VerticalPower"));
                } else {
                    verticalPower.put(world.getName(), (double) section.getInt("VerticalPower"));
                }
            }
            if (section.contains("PressurePlate")) {
                Section pp = section.getSection("PressurePlate");
                if (pp == null) return;
                if (pp.contains("Type")) {
                    try {
                        plateTypes.put(world.getName(), FinderUtil.findMaterial(pp.getString("Type")));
                    } catch (IllegalArgumentException ex) {
                        logger.warn("Invalid Material [" + pp.getString("Type") + "] for Jump Pad Plate", ex);
                        return;
                    }
                }
                requirePressurePlate.put(world.getName(), pp.getBoolean("Required", false));
                noBlockRequired.put(world.getName(), pp.getBoolean("NoBlockRequired", false));
            }
            if (section.contains("Sound") && (section.get("Sound") instanceof String)) {
                try {
                    soundMap.put(world.getName().toLowerCase(),
                            Sound.valueOf(section.getString("Sound")));
                } catch (IllegalArgumentException ex) {
                    logger.warn("Invalid Sound [" + section.getString("Sound") + "] for Jump Pad");
                }
            }
            if (section.contains("Effect") && (section.get("Effect") instanceof String)) {
                Particle particle = FinderUtil.findParticle(section.getString("Effect"));
                if (particle != null) {
                    this.effectMap.put(world.getName(), particle);
                } else {
                    logger.warn("Invalid Particle Effect [" + section.getString("Effect") + "] for Jump Pad");
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
        return this.plateTypes.getOrDefault(player.getWorld().getName(), null);
    }

    private boolean isBlockRequired(Player player) {
        return !this.noBlockRequired.getOrDefault(player.getWorld().getName(), false);
    }

    private Sound getSound(Player player) {
        return this.soundMap.getOrDefault(player.getWorld().getName(), null);
    }

    private Particle getEffect(Player player) {
        return this.effectMap.getOrDefault(player.getWorld().getName(), null);
    }

    // ----------------------------------------------------------

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (needsPressurePlate(event.getPlayer()) || !isBlockRequired(event.getPlayer())) return;
        Player player = event.getPlayer();
        if (!isEnabledInWorld(player.getWorld())) return;
        Location loc = player.getLocation().subtract(0, 1, 0);
        if (loc.getBlock().getType() == getMaterial(player)) {
            player.setVelocity(calculateVector(player));
            if (getSound(player) != null) {
                player.playSound(player.getLocation(), getSound(player), 1, 1);
            }
            if (getEffect(player) != null) {
                particleEffect.display(getEffect(player), player.getLocation(), 1, 1, 1, 1, 40);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!needsPressurePlate(event.getPlayer())) return;
        Player player = event.getPlayer();
        if (event.getAction() == Action.PHYSICAL && !event.isCancelled() && isEnabledInWorld(player.getWorld())) {
            if (event.getClickedBlock().getType() == getPlateType(player)) {
                boolean apply = true;
                if (isBlockRequired(player)) {
                    Location loc = event.getClickedBlock().getLocation().subtract(0, 1, 0);
                    apply = loc.getWorld().getBlockAt(loc).getType() == getMaterial(player);
                }
                if (apply) {
                    player.setVelocity(calculateVector(player));
                    if (getSound(player) != null) {
                        player.playSound(player.getLocation(), getSound(player), 1, 1);
                    }
                    if (getEffect(player) != null) {
                        particleEffect.display(getEffect(player), player.getLocation(), 1, 1, 1, 1, 40);
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
