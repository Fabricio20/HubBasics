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

import lombok.Getter;

import net.notfab.hubbasics.abstracts.module.ModuleEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.org.apache.xpath.internal.operations.Mod;

import org.apache.commons.lang.WordUtils;

public enum ConfigurationKey {
    ENABLE_DEBUG("Messages.Debug", false, false),

    CONNECT_MESSAGES_ENABLED(ModuleSetting.ENABLED_GLOBAL, false, true, ModuleEnum.CONNECTION_MESSAGES),
    CONNECT_MESSAGES_CONNECT("Join", false, "&8[&a+&8] &f<displayname> &7joined the game", ModuleEnum.CONNECTION_MESSAGES),
    CONNECT_MESSAGES_DISCONNECT("Leave", false, "&8[&c-&8] &f<displayname> &7quit the game", ModuleEnum.CONNECTION_MESSAGES),
    CONNECT_MESSAGES_FIRST_CONNECT("FirstJoin", false, "&9Welcome to the server, &f<displayname>&9!", ModuleEnum.CONNECTION_MESSAGES),

    FIXED_WEATHER_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.FIXED_WEATHER),
    FIXED_WEATHER_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.FIXED_WEATHER),

    KEEP_FOOD_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.KEEP_FOOD),
    KEEP_FOOD_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.KEEP_FOOD),

    KEEP_HEALTH_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.KEEP_HEALTH),
    KEEP_HEALTH_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.KEEP_HEALTH),

    ANTI_VOID_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.ANTI_VOID),
    ANTI_VOID_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.ANTI_VOID),
    ANTI_VOID_MESSAGE("Message", false, "&e<displayname> &9You got teleported to the spawn.", ModuleEnum.ANTI_VOID),

    JUMP_PADS_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.JUMP_PADS),
    JUMP_PADS_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.JUMP_PADS),
    JUMP_PADS_FORCE("Force", false, 0.3, ModuleEnum.JUMP_PADS),
    JUMP_PADS_MATERIAL("Material", false, "REDSTONE_BLOCK", ModuleEnum.JUMP_PADS),
    JUMP_PADS_REQUIRE_PRESSUREPLATE("RequirePressurePlate", false, false, ModuleEnum.JUMP_PADS),

    DOUBLE_JUMP_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.DOUBLE_JUMP),
    DOUBLE_JUMP_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.DOUBLE_JUMP),
    DOUBLE_JUMP_FORCE("Force", false, 0.3, ModuleEnum.DOUBLE_JUMP),
    DOUBLE_JUMP_SOUND("Sound", false, "ENTITY_BAT_TAKEOFF", ModuleEnum.DOUBLE_JUMP),

    ADVANCED_MOTD_ENABLED(ModuleSetting.ENABLED_GLOBAL, false, false, ModuleEnum.ADVANCED_MOTD),
    ADVANCED_MOTD_MOTDS("Motds", false, Arrays.asList("&aThis server has HubBasics!", "&2Maybe the server owner should change these messages?"), ModuleEnum.ADVANCED_MOTD),
    ADVANCED_MOTD_SWITCHRATE("Switchrate", false, 20, ModuleEnum.ADVANCED_MOTD),

    COMMAND_OVERRIDE_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.COMMAND_OVERRIDE),
    COMMAND_OVERRIDE_COMMANDS("Commands", false, Arrays.asList("{\"command\":\"plugin\",\"aliases\":\"pl\",\"permission\":\"hubbasics.bypass.plugin\",\"message\":\"&cYou don't have permission to execute this command\"}"), ModuleEnum.COMMAND_OVERRIDE),

    AUTOMATED_BROADCASTS_ENABLED(ModuleSetting.ENABLED_GLOBAL, false, false, ModuleEnum.AUTOMATED_BROADCASTS),
    AUTOMATED_BROADCASTS_MESSAGES("Messages", false, Arrays.asList("&3This server uses HubBasics!", "&2This is just an example!"), ModuleEnum.AUTOMATED_BROADCASTS),
    AUTOMATED_BROADCASTS_TIMING("Timing", false, 60, ModuleEnum.AUTOMATED_BROADCASTS),
    AUTOMATED_BROADCASTS_RANDOM("RandomOrder", false, false, ModuleEnum.AUTOMATED_BROADCASTS),

    BOSSBAR_MESSAGES_ENABLED(ModuleSetting.ENABLED_GLOBAL, false, false, ModuleEnum.BOSSBAR_MESSAGES),
    BOSSBAR_MESSAGES_MESSAGES("Messages", false, Arrays.asList("&3This server uses HubBasics!", "&2This is just an example!"), ModuleEnum.BOSSBAR_MESSAGES),
    BOSSBAR_MESSAGES_TIMING("Timing", false, 15, ModuleEnum.BOSSBAR_MESSAGES),

    JOIN_TELEPORT_LOAD(ModuleSetting.LOAD, false, false, ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_ENABLED(ModuleSetting.ENABLED_WORLD_LIST, false, Arrays.asList("world", "world_the_end"), ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_LOCATION_X("X", false, 0, ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_LOCATION_Y("Y", false, 0, ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_LOCATION_Z("Z", false, 0, ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_LOCATION_YAW("Yaw", false, 0, ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_LOCATION_PITCH("Pitch", false, 0, ModuleEnum.JOIN_TELEPORT),
    JOIN_TELEPORT_LOCATION_WORLD("World", false, "world", ModuleEnum.JOIN_TELEPORT);

    /**
     * This is the path the option will have in the config.yml file
     */
    @Getter private String path;

    /**
     * If this option is set to true, it will allow the user to change this setting per world.
     * The config section will look like this;
     * <p>
     * key_path:
     * global: value // This is the global value if there is no specific setting for the given world
     * world_name: value // This value will be used if the key is accessed and a world with the name "world_name" is given
     */
    @Getter private boolean perWorldAllowed;

    /**
     * The default value that will be set when the config file is created/someone removes the key in the config file
     */
    @Getter private Object defaultValue;

    /**
     * If the key is related to a module, this allows you to specify that. In addition, this creates a parent
     * section with the same name as the module. For example, if Module.DOUBLE_JUMP was specified, all options
     * with this module given, will have the parent section "double_jump". This option can be null.
     */
    @Getter private ModuleEnum attachedModule;

    @Getter private ModuleSetting moduleSetting;

    ConfigurationKey(String path, Boolean worldDependant, Object defaultValue) {
        this.path = path;
        this.perWorldAllowed = worldDependant;
        this.defaultValue = defaultValue;
    }

    ConfigurationKey(String path, Boolean worldDependant, Object defaultValue, ModuleEnum module) {
        this.path = WordUtils.capitalizeFully(module.name(), new char[]{'_'}) + "." + path;
        this.perWorldAllowed = worldDependant;
        this.defaultValue = defaultValue;
        this.attachedModule = module;
    }

    ConfigurationKey(ModuleSetting setting, Boolean worldDependant, Object defaultValue, ModuleEnum module) {
        this.path = WordUtils.capitalizeFully(module.name(), new char[]{'_'}) + "." + setting.getPath();
        this.perWorldAllowed = worldDependant;
        this.defaultValue = defaultValue;
        this.attachedModule = module;
        this.moduleSetting = setting;
    }

    public static ConfigurationKey getKey(ModuleEnum module, ModuleSetting setting) {
        List<ConfigurationKey> keyList = Arrays.stream(ConfigurationKey.values()).filter(key -> key.getAttachedModule() != null && key.getModuleSetting() != null)
                .filter(key -> key.getModuleSetting() == setting && key.getAttachedModule() == module).collect(Collectors.toList());

        if (keyList.size() == 0) return null;
        else return keyList.get(0);
    }

    /**
     * Same as getPath
     *
     * @return The path in config.yml
     */
    @Override
    public String toString() {
        return this.getPath();
    }

    public enum ModuleSetting {
        ENABLED_WORLD_LIST("Enabled"),
        ENABLED_GLOBAL("Enabled"),
        LOAD("Load");

        @Getter private String path;

        ModuleSetting(String path) {
            this.path = path;
        }
    }
}
