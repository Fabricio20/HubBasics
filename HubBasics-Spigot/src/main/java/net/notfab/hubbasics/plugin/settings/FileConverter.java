package net.notfab.hubbasics.plugin.settings;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.objects.SimpleConfig;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.messages.HubBasicsMessage;
import net.notfab.hubbasics.plugin.utils.HubBasicsFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class FileConverter {
    public static void convert() {
        HubBasicsFile.MESSAGES.reloadConfig();
        HubBasicsFile.CONFIGURATION.reloadConfig();
        HubBasicsFile.HOLOGRAMS.reloadConfig();
        CONVERSIONS.stream().forEach(Conversion::convert);
        HubBasicsFile.MESSAGES.saveConfig();
        HubBasicsFile.CONFIGURATION.saveConfig();
        HubBasicsFile.HOLOGRAMS.saveConfig();
    }

    private static final List<Conversion> CONVERSIONS = ImmutableList.<Conversion>builder()
            /* Converted module configuration sections to follow first-letter-uppercase syntax used by HubBasics */
            .add(new MassConversion(() -> {
                SimpleConfig config = HubBasics.getInstance().getPluginConfiguration().getRawConfig();
                Arrays.stream(ModuleEnum.values()).filter(module -> config.contains(module.name().toLowerCase())).forEach(module -> {
                    String oldPath = module.name().toLowerCase();
                    String newPath = WordUtils.capitalizeFully(module.name(), new char[]{'_'});
                    config.set(newPath, config.getConfigurationSection(oldPath));
                    config.removeKey(oldPath);
                });
            }))
            /* Converted to MessageFormat for HubBasicsMessage */
            .add(new ReplaceConversion("commandInputNotNumber", HubBasicsFile.MESSAGES, "<string>", "{0}"))
            .add(new ReplaceConversion("holograms.error.notExist", HubBasicsFile.MESSAGES, "<hologramID>", "{0}"))
            .add(new ReplaceConversion("holograms.success.create", HubBasicsFile.MESSAGES, "<hologramID>", "{0}"))
            .add(new ReplaceConversion("holograms.success.reset", HubBasicsFile.MESSAGES, "<hologramID>", "{0}"))
            .add(new ReplaceConversion("holograms.success.addline", HubBasicsFile.MESSAGES, "<hologramID>", "{0}"))
            .add(new ReplaceConversion("holograms.success.delete", HubBasicsFile.MESSAGES, "<hologramID>", "{0}"))
            /* Converted messages.yml to follow first-letter-uppercase syntax used by HubBasics */
            .add(new PathConversion(HubBasicsFile.MESSAGES, "noPermission", "Command.NoPermissions"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "commandPlayersOnly", "Command.PlayersOnly"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "commandUsagePrefix", "Command.UsagePrefix"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "commandErrorOccurred", "Command.ErrorOccurred"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "commandInputNotNumber", "Command.NotNumber"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "commandNotFound", "Command.NotFound"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.usage.create", "Holograms.Usage.Create"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.usage.reset", "Holograms.Usage.Reset"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.usage.addLine", "Holograms.Usage.AddLine"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.usage.delete", "Holograms.Usage.Delete"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.usage.list", "Holograms.Usage.List"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.error.notExist", "Holograms.Error.NotExist"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.success.create", "Holograms.Success.Create"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.success.reset", "Holograms.Success.Reset"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.success.addline", "Holograms.Success.AddLine"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.success.delete", "Holograms.Success.Delete"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.success.list.prefix", "Holograms.Success.List.Prefix"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "holograms.success.list.empty", "Holograms.Success.List.Empty"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "hat.usage", "Hat.Usage"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "hat.success", "Hat.Success"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "hub.invalidWord", "Hub.InvalidWorld"))
            .add(new PathConversion(HubBasicsFile.MESSAGES, "hub.locationUpdated", "Hub.LocationUpdated"))
            .add(new RemoveConversion(HubBasicsFile.MESSAGES, "holograms"))
            .add(new RemoveConversion(HubBasicsFile.MESSAGES, "hat"))
            .add(new RemoveConversion(HubBasicsFile.MESSAGES, "hub"))
            .build();

    /**
     * Replaces parts of a variable stored in given file
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ReplaceConversion implements Conversion {
        @Getter private String path;
        @Getter private SimpleConfig file;
        @Getter private String from;
        @Getter private String to;

        @Override
        public void convert() {
            if (!file.contains(path)) {
                HMessenger.sendDebugMessage("Replace failed, path empty.");
                return;
            }

            file.set(path, file.getString(path).replace(from, to));
            HMessenger.sendDebugMessage("Replaced \"" + from + "\" with \"" + to + "\" in file " + file.getName() + ", path \"" + path + "\".");
        }
    }

    /**
     * Moves a section from given path to given path in given file
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class PathConversion implements Conversion {
        @Getter private SimpleConfig file;
        @Getter private String from;
        @Getter private String to;

        @Override
        public void convert() {
            if (!file.contains(from)) {
                HMessenger.sendDebugMessage("Move failed, from-path empty.");
                return;
            }

            file.set(to, file.get(from));
            file.set(from, null);
            HMessenger.sendDebugMessage("Moved section \"" + from + "\" to \"" + to + "\" in file " + file.getName() + "\".");
        }
    }

    /**
     * Simply removes the given key from the given file
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class RemoveConversion implements Conversion {
        @Getter private SimpleConfig file;
        @Getter private String path;

        @Override
        public void convert() {
            if (!file.contains(path)) {
                HMessenger.sendDebugMessage("Remove failed, path empty.");
                return;
            }

            file.removeKey(path);
            HMessenger.sendDebugMessage("Removed \"" + path + "\" from file " + file.getName() + ".");
        }
    }

    /**
     * Larger conversions
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class MassConversion implements Conversion {
        @Getter private Runnable runnable;

        @Override
        public void convert() {
            runnable.run();
        }
    }

    /**
     * Simple interface for conversion classes
     */
    private interface Conversion {
        /**
         * Method called when plugin performs conversion process
         */
        void convert();
    }
}
