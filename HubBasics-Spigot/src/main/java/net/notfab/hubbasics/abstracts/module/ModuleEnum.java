package net.notfab.hubbasics.abstracts.module;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.nms.NMSVersion;

import lombok.Getter;

public enum ModuleEnum {
    DOUBLE_JUMP,
    JUMP_PADS,
    ANTI_VOID,
    FIXED_WEATHER,
    KEEP_FOOD,
    KEEP_HEALTH,
    HOLOGRAMS(NMSVersion.V1_8_R1, NMSVersion.V1_8_R2, NMSVersion.V1_8_R3, NMSVersion.V1_9_R1, NMSVersion.V1_9_R2, NMSVersion.V1_10_R1, NMSVersion.V1_11_R1, NMSVersion.V1_12_R1),
    CONNECTION_MESSAGES,
    ADVANCED_MOTD,
    COMMAND_OVERRIDE,
    AUTOMATED_BROADCASTS,
    BOSSBAR_MESSAGES(NMSVersion.V1_9_R1, NMSVersion.V1_9_R2, NMSVersion.V1_10_R1, NMSVersion.V1_11_R1, NMSVersion.V1_12_R1),
    JOIN_TELEPORT,
    GRAPHICAL_MENUS,
    JOIN_ITEMS,
    WARPS;

    /**
     * Supported versions. See {@link NMSVersion}.
     */
    @Getter
    private String[] versions;

    ModuleEnum(String... versions) {
        this.versions = versions;
    }
}
