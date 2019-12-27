package net.notfab.hubbasics.spigot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.notfab.hubbasics.spigot.utils.PlaceHolderUtil;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class BossBarMessage {

    @Getter
    private String message;
    @Getter
    private BarStyle style;
    @Getter
    private BarColor color;

    public BossBar toBossBar(Player player) {
        return Bukkit.createBossBar(PlaceHolderUtil.replace(player, message), color, style);
    }

}
