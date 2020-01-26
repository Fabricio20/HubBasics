package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;

@Getter
public enum EnumModules {

    KeepHealth(CraftBukkitVersion.v1_7_X),
    KeepFood(CraftBukkitVersion.v1_7_X),
    JoinItems(CraftBukkitVersion.v1_7_X),
    AntiVoid(CraftBukkitVersion.v1_7_X),
    DoubleJump(CraftBukkitVersion.v1_7_X),
    NoWeather(CraftBukkitVersion.v1_7_X),
    Holograms(CraftBukkitVersion.v1_7_X),
    JoinMessages(CraftBukkitVersion.v1_7_X),
    Lobby(CraftBukkitVersion.v1_7_X),
    JoinTP(CraftBukkitVersion.v1_7_X),

    JumpPads(CraftBukkitVersion.v1_9_X),

    BossAnnouncer(CraftBukkitVersion.v1_10_X);

    private CraftBukkitVersion minimumVersion;

    EnumModules(CraftBukkitVersion version) {
        this.minimumVersion = version;
    }

}