package net.notfab.hubbasics.modules;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONObject;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

public class BossBarMessages extends Module {

    private HubBasics pl;
    private boolean enable;
    private long timing;
    private List<String> messages;
    private BossBar bossBar;

    public BossBarMessages() {
        super(ModuleEnum.BOSSBAR_MESSAGES);
        this.pl = HubBasics.getInstance();
    }

    @Override
    public void onEnable() {
        this.enable = getBoolean(ConfigurationKey.BOSSBAR_MESSAGES_ENABLED);
        this.timing = getConfig().getInt(ConfigurationKey.BOSSBAR_MESSAGES_TIMING)*20L;
        this.messages = getConfig().getStringList(ConfigurationKey.BOSSBAR_MESSAGES_MESSAGES).parallelStream()
                .map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
        if (!enable) return;
        if (timing <= 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[HubBasics] BossBar messages timings below or equal to 0 are not allowed!");
            return;
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int index = 0;
            int color = 0;

            @Override
            public void run() {
                if (bossBar == null) {
                    if (messages.get(index).startsWith("{") && messages.get(index).endsWith("}")) {
                        JSONObject object = new JSONObject(messages.get(index));
                        BarColor color = BarColor.values()[this.color];
                        BarStyle style = BarStyle.SOLID;
                        String message = object.getString("message");
                        if (object.has("style")) style = BarStyle.valueOf(object.getString("style").toUpperCase());
                        if (object.has("color")) color = BarColor.valueOf(object.getString("color").toUpperCase());
                        bossBar = Bukkit.createBossBar(message, color, style);
                    } else {
                        bossBar = Bukkit.createBossBar(messages.get(index), BarColor.values()[color], BarStyle.SOLID);
                    }

                    bossBar.setProgress(1);
                    Bukkit.getOnlinePlayers().parallelStream().forEach(bossBar::addPlayer);
                } else {
                    if (messages.get(index).startsWith("{") && messages.get(index).endsWith("}")) {
                        JSONObject object = new JSONObject(messages.get(index));
                        BarColor color = BarColor.values()[this.color];
                        BarStyle style = BarStyle.SOLID;
                        String message = object.getString("message");
                        if (object.has("style")) style = BarStyle.valueOf(object.getString("style").toUpperCase());
                        if (object.has("color")) color = BarColor.valueOf(object.getString("color").toUpperCase());
                        bossBar.setColor(color);
                        bossBar.setStyle(style);
                        bossBar.setTitle(message);
                    } else {
                        bossBar.setStyle(BarStyle.SOLID);
                        bossBar.setColor(BarColor.values()[color]);
                        bossBar.setTitle(messages.get(index));
                    }
                }

                color++;
                index++;
                if (index >= messages.size()) index = 0;
                if (color >= BarColor.values().length) color = 0;
            }
        }, 0L, timing);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        if (!this.enable) return;
        this.bossBar.addPlayer(event.getPlayer());
    }
}
