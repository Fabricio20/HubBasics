package net.notfab.hubbasics.spigot.modules.v1_7;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinTP extends Module {

    private List<String> worlds = new ArrayList<>();
    private Map<String, Boolean> worldChange = new HashMap<>();
    private Map<String, String> worldLocations = new HashMap<>();

    private boolean globalWorldChange = false;
    private boolean globalTP = false;
    private String globalTarget = null;

    public JoinTP() {
        super(EnumModules.JoinTP, CraftBukkitVersion.v1_7_X);
    }

    @Override
    public void onEnable() {
        SimpleConfig config = getConfig();
        if (config.contains("Global.Enabled")) {
            globalTP = config.getBoolean("Global.Enabled");
            globalTarget = config.getString("Global.Location", "default");
            globalWorldChange = config.getBoolean("Global.WorldChange", false);
        }
        Bukkit.getWorlds().forEach(world -> {
            if (!config.contains(world.getName())) return;
            if (!(config.contains(world.getName() + ".Enabled") && config.getBoolean(world.getName() + ".Enabled")))
                return;
            this.worlds.add(world.getName());
            this.worldChange.put(world.getName(), config.getBoolean(world.getName() + ".WorldChange", true));
            this.worldLocations.put(world.getName(), config.getString(world.getName() + ".Location", "default"));
        });
    }

    @Override
    public void onDisable() {
        this.worlds.clear();
        this.worldChange.clear();
        this.worldLocations.clear();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (globalTP) {
            HLocation location = HubBasics.getLocationManager().get(globalTarget);
            location.teleport(event.getPlayer());
        } else {
            if (!worlds.contains(event.getPlayer().getWorld().getName())) {
                return;
            }
            HLocation location = HubBasics.getLocationManager().get(worldLocations.get(event.getPlayer().getWorld().getName()));
            location.teleport(event.getPlayer());
        }
    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent event) {
        if (globalTP && globalWorldChange) {
            HLocation location = HubBasics.getLocationManager().get(globalTarget);
            location.teleport(event.getPlayer());
        } else {
            if (!worlds.contains(event.getPlayer().getWorld().getName())) {
                return;
            }
            if (!worldChange.containsKey(event.getPlayer().getWorld().getName())) {
                return;
            }
            HLocation location = HubBasics.getLocationManager().get(worldLocations.get(event.getPlayer().getWorld().getName()));
            location.teleport(event.getPlayer());
        }
    }

}