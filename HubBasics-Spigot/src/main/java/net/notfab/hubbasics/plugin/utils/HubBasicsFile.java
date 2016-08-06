package net.notfab.hubbasics.plugin.utils;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.SimpleConfig;

public class HubBasicsFile {
    public static final SimpleConfig CONFIGURATION = get("config");
    public static final SimpleConfig HOLOGRAMS = get("holograms");
    public static final SimpleConfig MESSAGES = get("messages");

    private static SimpleConfig get(String string) {
        return HubBasics.getInstance().getConfigManager().getNewConfig(string + ".yml");
    }
}
