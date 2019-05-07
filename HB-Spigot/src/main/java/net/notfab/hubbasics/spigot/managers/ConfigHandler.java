package net.notfab.hubbasics.spigot.managers;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import ch.qos.logback.classic.Level;
import lombok.Getter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import net.notfab.spigot.simpleconfig.spigot.ConfigLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigHandler implements Listener {

    @Getter
    private static ConfigHandler Instance;
    private Logger Logger = new Logger("ConfigHandler");
    private JavaPlugin plugin;

    /**
     * Manage custom configurations and files
     */
    public ConfigHandler(JavaPlugin plugin) {
        Instance = this;
        this.plugin = plugin;
        this.loadDefaults();
        this.setupLogger();
    }

    @EventHandler
    public void onLoad(ConfigLoadEvent event) {
        SimpleConfig config = event.getConfig();
        if (!config.getFile().getPath().contains("HubBasics")) {
            return;
        }
        SimpleConfig resources = getResource(config);
        if (config.contains("Version") && resources != null && resources.contains("Version")) {
            Double version = config.getDouble("Version", 1.0);
            Double latest = resources.getDouble("Version", 1.0);
            if (latest > version) {
                Logger.warn("Detected an outdated config - &7" + config.getName());
                Logger.warn("You have outdated configs! No support will be provided for outdated configs.");
            }
        }
    }

    // ---------------------- Legacy

    private void setupLogger() {
        SimpleConfig config = HubBasics.getInstance().getConfigManager().getNewConfig("config.yml");
        if (config.contains("Logger")) {
            if (config.contains("Logger.Enabled"))
                HubBasics.getInstance().getLoggerManager().setEnabled(config.getBoolean("Logger.Enabled"));
            if (config.contains("Logger.Level")) {
                try {
                    net.notfab.hubbasics.spigot.managers
                            .Logger.setLevel(Level.valueOf(config.getString("Logger.Level")));
                } catch (IllegalArgumentException ex) {
                    HubBasics.getInstance().getLoggerManager().error("Invalid logger Level found on config file", ex);
                }
            }
        }
    }

    private void loadDefaults() {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdirs();
        if (!new File(folder, "config.yml").exists()) {
            List<String> lines = getResource("config.yml");
            this.writeToFile(lines, new File(folder, "config.yml"));
        }
        if (!new File(folder, "messages.yml").exists()) {
            List<String> lines = getResource("messages.yml");
            this.writeToFile(lines, new File(folder, "messages.yml"));
        }
        if (!new File(folder, "items/").exists()) {
            new File(folder, "items/").mkdirs();
            List<String> lines = getResource("items/example-1.yml");
            this.writeToFile(lines, new File(folder, "items/example-1.yml"));
            lines = getResource("items/menu-opener.yml");
            this.writeToFile(lines, new File(folder, "items/menu-opener.yml"));
        }
        if (!new File(folder, "menus/").exists()) {
            new File(folder, "menus/").mkdirs();
            List<String> lines = getResource("menus/example-menu.yml");
            this.writeToFile(lines, new File(folder, "menus/example-menu.yml"));
        }
        if (!new File(folder, "warps/").exists()) {
            new File(folder, "warps/").mkdirs();
        }
        if (!new File(folder, "modules/").exists()) {
            new File(folder, "modules/").mkdirs();
        }
        Arrays.asList(EnumModules.values()).forEach(module -> {
            File file = new File(folder, "modules/" + module.name() + ".yml");
            if (file.exists()) {
                return;
            }
            List<String> lines = getResource("modules/" + module.name() + ".yml");
            this.writeToFile(lines, file);
        });
    }

    private SimpleConfig getResource(SimpleConfig onDisk) {
        StringBuilder fileName = new StringBuilder();
        File onDiskFile = onDisk.getFile();
        do {
            fileName.append("/").append(onDiskFile.getName());
            onDiskFile = onDiskFile.getParentFile();
        } while (!onDiskFile.getName().equalsIgnoreCase("HubBasics"));
        InputStream stream = this.getClass().getResourceAsStream("/" + fileName);
        if (stream == null) {
            return null;
        }
        return HubBasics.getInstance().getConfigManager().getFromResources(stream, onDisk.getFile());
    }

    private List<String> getResource(String fileName) {
        InputStream stream = this.getClass().getResourceAsStream("/" + fileName);
        List<String> lines = new ArrayList<>();
        if (stream == null) return lines;
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void writeToFile(List<String> lines, File file) {
        try {
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getPath());
            lines.forEach(line -> {
                try {
                    fw.write(line + "\n");
                } catch (IOException ignored) {
                }
            });
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
