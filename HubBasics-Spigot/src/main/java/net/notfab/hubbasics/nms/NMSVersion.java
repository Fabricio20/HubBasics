package net.notfab.hubbasics.nms;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

import lombok.Getter;

public class NMSVersion {
    public static final String V1_7_R1 = "v1_7_R1";
    public static final String V1_7_R2 = "v1_7_R2";
    public static final String V1_7_R3 = "v1_7_R3";
    public static final String V1_7_R4 = "v1_7_R4";
    public static final String V1_8_R1 = "v1_8_R1";
    public static final String V1_8_R2 = "v1_8_R2";
    public static final String V1_8_R3 = "v1_8_R3";
    public static final String V1_9_R1 = "v1_9_R1";
    public static final String V1_9_R2 = "v1_9_R2";
    public static final String V1_10_R1 = "v1_10_R1";

    private Map<Integer, String> versionMap;
    @Getter private int versionID;

    public NMSVersion() {
        this.versionMap = new HashMap<>();
        this.loadVersions();

        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);
        if (this.versionMap.containsValue(version)) {
            this.versionID = getVersionID(version);
        } else {
            this.versionID = 0;
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "----------------------------------------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "YOU ARE RUNNING AN UNSUPPORTED VERSION OF CRAFTBUKKIT!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "HubBasics functionality will at best be limited. Please don't come");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "complaining to us, the developers of HubBasics, when something breaks,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "because running an unsupported version will cause exactly this. We do");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "in no way accept responsibility for ANY damage caused to a server running");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "an unsupported version of CraftBukkit. It is recommended that you change to");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "a supported version of CraftBukkit. Supported versions are 1.7*, 1.8*, 1.9,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "1.10. Versions marked with an asterisk (*) may have decreased functionality.");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "----------------------------------------------------------");
        }
    }

    private void loadVersions() {
        registerVersion("Unsupported");
        registerVersion(V1_7_R1);
        registerVersion(V1_7_R2);
        registerVersion(V1_7_R3);
        registerVersion(V1_7_R4);
        registerVersion(V1_8_R1);
        registerVersion(V1_8_R2);
        registerVersion(V1_8_R3);
        registerVersion(V1_9_R1);
        registerVersion(V1_9_R2);
        registerVersion(V1_10_R1);
    }

    private void registerVersion(String string) {
        this.versionMap.put(this.versionMap.size(), string);
    }

    public String getVersionString() {
        return this.getVersionString(this.getVersionID());
    }

    public String getVersionString(int id) {
        return this.versionMap.get(id);
    }

    public int getVersionID(String version) {
        try {
            return this.versionMap.entrySet().parallelStream()
                    .filter(e -> e.getValue().equalsIgnoreCase(version))
                    .map(e -> e.getKey()).collect(Collectors.toList()).get(0);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public boolean runningNewerThan(String version) {
        return this.getVersionID() >= this.getVersionID(version);
    }
}
