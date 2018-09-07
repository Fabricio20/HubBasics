package net.notfab.hubbasics.spigot.nms;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Simple class for working with NMS (Net.Minecraft.Server - reference to the package)
 * versions for Minecraft.
 */
public class NMSVersion {

    /**
     * All unsupported NMS versions
     */
    public static final String UNSUPPORTED = "Unsupported";
    /**
     * NMS Version 1.7 R1
     * Does NOT support these modules: HOLOGRAMS, BOSSBAR_MESSAGES
     */
    public static final String V1_7_R1 = "v1_7_R1";
    /**
     * NMS Version 1.7 R2
     * Does NOT support these modules: HOLOGRAMS, BOSSBAR_MESSAGES
     */
    public static final String V1_7_R2 = "v1_7_R2";
    /**
     * NMS Version 1.7 R3
     * Does NOT support these modules: HOLOGRAMS, BOSSBAR_MESSAGES
     */
    public static final String V1_7_R3 = "v1_7_R3";
    /**
     * NMS Version 1.7 R4
     * Does NOT support these modules: HOLOGRAMS, BOSSBAR_MESSAGES
     */
    public static final String V1_7_R4 = "v1_7_R4";
    /**
     * NMS Version 1.8 R1
     * Does NOT support these modules: BOSSBAR_MESSAGES
     */
    public static final String V1_8_R1 = "v1_8_R1";
    /**
     * NMS Version 1.8 R2
     * Does NOT support these modules: BOSSBAR_MESSAGES
     */
    public static final String V1_8_R2 = "v1_8_R2";
    /**
     * NMS Version 1.8 R3
     * Does NOT support these modules: BOSSBAR_MESSAGES
     */
    public static final String V1_8_R3 = "v1_8_R3";
    /**
     * NMS Version 1.9 R1
     */
    public static final String V1_9_R1 = "v1_9_R1";
    /**
     * NMS Version 1.9 R2
     */
    public static final String V1_9_R2 = "v1_9_R2";
    /**
     * NMS Version 1.10 R1
     */
    public static final String V1_10_R1 = "v1_10_R1";
    /**
     * NMS Version 1.11 R1
     */
    public static final String V1_11_R1 = "v1_11_R1";
    /**
     * NMS Version 1.12 R1
     */
    public static final String V1_12_R1 = "v1_12_R1";

    /**
     * NMS Version 1.13 R1
     */
    public static final String V1_13_R1 = "v1_13_R1";

    /**
     * NMS Version 1.13 R1
     */
    public static final String V1_13_R2 = "v1_13_R2";

    private Map<Integer, String> versionMap;
    @Getter private int versionID;

    public NMSVersion() {
        this.versionMap = new HashMap<>();
        this.loadVersions();

        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);
        if(this.versionMap.containsValue(version)) {
            this.versionID = getVersionID(version);
        } else {
            this.versionID = 0;
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "----------------------------------------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "YOU ARE RUNNING AN UNSUPPORTED VERSION OF SPIGOT!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "HubBasics functionality will at best be limited. Please don't come");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "complaining to us, the developers of HubBasics, when something breaks,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "because running an unsupported version will cause exactly this. We do");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "in no way accept responsibility for ANY damage caused to a server running");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "an unsupported version of Spigot. It is recommended that you change to");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "a supported version of Spigot. Supported versions are 1.7*, 1.8*, 1.9*,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "1.10, 1.11, 1.12, 1.13. Versions marked with an asterisk (*) may have limited functionality.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You are running NMS Version " + version);
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "----------------------------------------------------------");
        }
    }

    private void loadVersions() {
        registerVersion(UNSUPPORTED);
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
        registerVersion(V1_11_R1);
        registerVersion(V1_12_R1);
        registerVersion(V1_13_R1);
        registerVersion(V1_13_R2);
    }

    private void registerVersion(String string) {
        this.versionMap.put(this.versionMap.size(), string);
    }

    public String getVersionString() {
        if(this.getVersionID() == 0) {
            String packageName = Bukkit.getServer().getClass().getPackage().getName();
            return packageName.substring(packageName.lastIndexOf('.') + 1);
        } else {
            return this.getVersionString(this.getVersionID());
        }
    }

    private String getVersionString(int id) {
        return this.versionMap.get(id);
    }

    private int getVersionID(String version) {
        return this.versionMap.entrySet().parallelStream()
                .filter(e -> e.getValue().equalsIgnoreCase(version))
                .map(Entry::getKey).findFirst().orElse(0);
    }

    public boolean runningNewerThan(String version) {
        return this.getVersionID() >= this.getVersionID(version);
    }
}
