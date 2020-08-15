package net.notfab.hubbasics.spigot.nms;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

/**
 * Simple class for working with NMS (Net.Minecraft.Server - reference to the package)
 * versions for Minecraft.
 */
public class NMSVersion {

    private final CraftBukkitVersion running;

    public NMSVersion() {
        this.running = this.getRunningVersion();
        if (running == CraftBukkitVersion.UNSUPPORTED) {
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
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "1.10, 1.11, 1.12, 1.13+. Versions marked with an asterisk (*) may have limited functionality.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You are running NMS Version " + running.getNmsNames()[0]);
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "----------------------------------------------------------");
        }
    }

    public CraftBukkitVersion getRunningVersion() {
        if (running != null) {
            return running;
        }
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return CraftBukkitVersion.find(packageName);
    }

    public String getRunningNMS() {
        return this.getRunningVersion().getNmsNames()[0];
    }

}
