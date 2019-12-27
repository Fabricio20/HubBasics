package net.notfab.hubbasics.spigot.entities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.HubBasics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Getter
@Setter
public class HLocation {

    private final String id;
    private String server;
    private String world;
    private Double X;
    private Double Y;
    private Double Z;
    private Float Yaw;
    private Float Pitch;

    public HLocation(String id) {
        this.id = id;
    }

    public HLocation(String id, Location location) {
        this.id = id;
        this.server = null;
        this.world = location.getWorld().getName();
        this.X = location.getX();
        this.Y = location.getY();
        this.Z = location.getZ();
        this.Yaw = location.getYaw();
        this.Pitch = location.getPitch();
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public void setWorld(World world) {
        this.world = world.getName();
    }

    public void teleport(Player player) {
        if (this.server != null) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(this.server);
            player.sendPluginMessage(HubBasics.getInstance(), "BungeeCord", out.toByteArray());
        } else {
            player.teleport(this.toBukkitLocation());
        }
    }

    public Location toBukkitLocation() {
        if (getWorld() == null) return Bukkit.getWorlds().get(0).getSpawnLocation();
        double X = (this.X == null) ? getWorld().getSpawnLocation().getX() : this.X;
        double Y = (this.Y == null) ? getWorld().getSpawnLocation().getY() : this.Y;
        double Z = (this.Z == null) ? getWorld().getSpawnLocation().getZ() : this.Z;
        float Yaw = (this.Yaw == null) ? getWorld().getSpawnLocation().getYaw() : this.Yaw;
        float Pitch = (this.Pitch == null) ? getWorld().getSpawnLocation().getPitch() : this.Pitch;
        return new Location(getWorld(), X, Y, Z, Yaw, Pitch);
    }

}
