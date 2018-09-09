package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.BossBarMessage;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.spigot.simpleconfig.Section;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BossAnnouncer extends Module {

    private Map<String, List<BossBarMessage>> worldMessages = new HashMap<>();
    private Map<String, Integer> pointers = new HashMap<>();
    private Map<UUID, BossBar> playerBars = new HashMap<>();

    public BossAnnouncer() {
        super(EnumModules.BossAnnouncer, NMSVersion.V1_10_R1);
    }

    private List<ScheduledFuture> runners = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getWorlds().forEach(world -> {
            Section section = getConfig().getSection(world.getName());
            if(section == null) return;
            if(!section.getBoolean("Enabled", false)) return;
            if(!section.contains("Messages")) return;
            List<BossBarMessage> messages = new ArrayList<>();
            Section msgsSection = section.getSection("Messages");
            msgsSection.getKeys().forEach(key -> {
                Section sx = msgsSection.getSection(key);
                if(sx == null) return;
                String message = sx.getString("Message", null);
                if(message == null) return;
                BarStyle style;
                BarColor color;
                try {
                    style = BarStyle.valueOf(msgsSection.getString("Style", "SOLID"));
                } catch (IllegalArgumentException ex) {
                    style = BarStyle.SOLID;
                    Logger.warn("Invalid style for message '" + key + "' of world '" + world.getName() + "'.");
                }
                try {
                    color = BarColor.valueOf(msgsSection.getString("Color", "BLUE"));
                } catch (IllegalArgumentException ex) {
                    color = BarColor.BLUE;
                    Logger.warn("Invalid color for message '" + key + "' of world '" + world.getName() + "'.");
                }
                messages.add(new BossBarMessage(message, style, color));
            });
            this.worldMessages.put(world.getName().toLowerCase(), messages);
            int ticks = section.getInt("Time", 5) * 50;
            this.runners.add(HubBasics.getTaskManager().scheduleAtFixedRate(this::run, 0, ticks, TimeUnit.MILLISECONDS));
        });
    }

    @Override
    public void onDisable() {
        this.runners.forEach(runner -> runner.cancel(true));
        this.worldMessages.clear();
        this.pointers.clear();
        Bukkit.getOnlinePlayers().forEach(this::remBossBar);
    }

    private void run() {
        worldMessages.forEach((worldName, messages) -> {
            World world = Bukkit.getWorld(worldName);
            if(world == null) return;

            BossBarMessage bbm = this.getBossBar(world.getName(), getPointer(world.getName()));

            if(bbm != null) {
                world.getPlayers().forEach(player -> this.setBossBar(player, bbm.toBossBar(player)));
            }
            this.setPointer(world.getName(), getPointer(world.getName()) + 1);
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.remBossBar(event.getPlayer());
    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent event) {
        World world = event.getPlayer().getWorld();
        BossBarMessage bbm = getBossBar(world.getName(), getPointer(world.getName()));
        if(bbm != null) {
            this.setBossBar(event.getPlayer(), bbm.toBossBar(event.getPlayer()));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        World world = event.getPlayer().getWorld();
        BossBarMessage bbm = getBossBar(world.getName(), getPointer(world.getName()));
        if(bbm != null) {
            this.setBossBar(event.getPlayer(), bbm.toBossBar(event.getPlayer()));
        }
    }

    // Methods

    private int getPointer(String world) {
        return this.pointers.getOrDefault(world.toLowerCase(), 0);
    }

    private void setPointer(String world, int position) {
        this.pointers.put(world.toLowerCase(), position);
    }

    private BossBarMessage getBossBar(String world, int index) {
        try {
            List<BossBarMessage> messages = this.worldMessages
                    .getOrDefault(world.toLowerCase(), new ArrayList<>());
            return messages.get(index);
        } catch (IndexOutOfBoundsException ex) {
            if(index == 0) {
                return null;
            } else {
                this.setPointer(world, 0);
                return getBossBar(world, 0);
            }
        }
    }

    // API

    private void setBossBar(Player player, BossBar bossBar) {
        this.remBossBar(player);
        bossBar.addPlayer(player);
        this.playerBars.put(player.getUniqueId(), bossBar);
    }

    private void remBossBar(Player player) {
        BossBar bossBar = playerBars.getOrDefault(player.getUniqueId(), null);
        if(bossBar != null) {
            bossBar.removePlayer(player);
            playerBars.remove(player.getUniqueId());
        }
    }

}