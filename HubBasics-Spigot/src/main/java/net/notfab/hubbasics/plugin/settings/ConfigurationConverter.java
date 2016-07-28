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

import org.apache.commons.lang.WordUtils;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.objects.SimpleConfig;

public class ConfigurationConverter {
    public static void convert() {
        convertV1();
    }

    private static void convertV1() {
        SimpleConfig config = HubBasics.getInstance().getPluginConfiguration().getRawConfig();
        Arrays.stream(ModuleEnum.values()).filter(module -> config.contains(module.name().toLowerCase())).forEach(module -> {
            String oldPath = module.name().toLowerCase();
            String newPath = WordUtils.capitalizeFully(module.name(), new char[]{'_'});
            config.set(newPath, config.getConfigurationSection(oldPath));
            config.removeKey(oldPath);
        });
    }
}
