package net.notfab.hubbasics.plugin.settings;

import lombok.Getter;

import net.notfab.hubbasics.abstracts.module.ModuleEnum;

import java.util.Arrays;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public enum ConfigurationKey {
    ENABLE_DEBUG("Messages.Debug", false, false),

    CONNECT_MESSAGES_ENABLED("Enabled", false, true, ModuleEnum.CONNECTION_MESSAGES),
    PLAYER_CONNECT("Join", false, "&8[&a+&8] &f<displayname> &7joined the game", ModuleEnum.CONNECTION_MESSAGES),
    PLAYER_DISCONNECT("Leave", false, "&8[&c-&8] &f<displayname> &7quit the game", ModuleEnum.CONNECTION_MESSAGES),
    PLAYER_FIRST_CONNECT("FirstJoin", false, "&9Welcome to the server, &f<displayname>&9!", ModuleEnum.CONNECTION_MESSAGES),

    FIXED_WEATHER_ENABLED("Enabled", false, Arrays.asList("world", "world_the_end"), ModuleEnum.FIXED_WEATHER),
    KEEP_FOOD_ENABLED("Enabled", false, Arrays.asList("world", "world_the_end"), ModuleEnum.KEEP_FOOD),
    KEEP_HEALTH_ENABLED("Enabled", false, Arrays.asList("world", "world_the_end"), ModuleEnum.KEEP_HEALTH),

    ANTI_VOID_ENABLED("Enabled", false, Arrays.asList("world", "world_the_end"), ModuleEnum.ANTI_VOID),
    ANTI_VOID_MESSAGE("Message", false, "&e<displayname> &9You got teleported to the spawn.", ModuleEnum.ANTI_VOID),

    JUMP_PADS_ENABLED("Enabled", false, Arrays.asList("world", "world_the_end"), ModuleEnum.JUMP_PADS),
    JUMP_PADS_FORCE("Force", false, 0.3, ModuleEnum.JUMP_PADS),
    JUMP_PADS_MATERIAL("Material", false, "REDSTONE_BLOCK", ModuleEnum.JUMP_PADS),
    JUMP_PADS_REQUIRE_PRESSUREPLATE("RequirePressurePlate", false, false, ModuleEnum.JUMP_PADS),

    DOUBLE_JUMP_ENABLED("Enabled", false, Arrays.asList("world", "world_the_end"), ModuleEnum.DOUBLE_JUMP),
    DOUBLE_JUMP_FORCE("Force", false, 0.3, ModuleEnum.DOUBLE_JUMP),
    DOUBLE_JUMP_SOUND("Sound", false, "ENTITY_BAT_TAKEOFF", ModuleEnum.DOUBLE_JUMP),

    ADVANCED_MOTD_MOTDS("Motds", false, Arrays.asList("&aThis server has HubBasics!", "&2Maybe the server owner should change these messages?"), ModuleEnum.ADVANCED_MOTD),
    ADVANCED_MOTD_SWITCHRATE("Switchrate", false, 20, ModuleEnum.ADVANCED_MOTD),

    COMMAND_OVERRIDE_COMMANDS("Commands", false, Arrays.asList("{\"command\":\"plugin\",\"aliases\":\"pl\",\"permission\":\"hubbasics.bypass.plugin\",\"message\":\"&cYou don't have permission to execute this command\"}"), ModuleEnum.COMMAND_OVERRIDE),

    AUTOMATED_BROADCASTS_ENABLED("Enabled", false, false, ModuleEnum.AUTOMATED_BROADCASTS),
    AUTOMATED_BROADCASTS_MESSAGES("Messages", false, Arrays.asList("&3This server uses HubBasics!", "&2This is just an example!"), ModuleEnum.AUTOMATED_BROADCASTS),
    AUTOMATED_BROADCASTS_TIMING("Timing", false, 60, ModuleEnum.AUTOMATED_BROADCASTS),
    AUTOMATED_BROADCASTS_RANDOM("RandomOrder", false, false, ModuleEnum.AUTOMATED_BROADCASTS);

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

    ConfigurationKey(String path, Boolean worldDependant, Object defaultValue) {
        this.path = path;
        this.perWorldAllowed = worldDependant;
        this.defaultValue = defaultValue;
    }

    ConfigurationKey(String path, Boolean worldDependant, Object defaultValue, ModuleEnum moduleEnum) {
        this.path = moduleEnum.name().toLowerCase() + "." + path;
        this.perWorldAllowed = worldDependant;
        this.defaultValue = defaultValue;
        this.attachedModule = moduleEnum;
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
}
