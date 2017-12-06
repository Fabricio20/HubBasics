package net.notfab.hubbasics.plugin.messages;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.text.MessageFormat;

import org.bukkit.command.CommandSender;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;

public enum HubBasicsMessage {
    COMMAND_NO_PERMISSION("Command.NoPermission", "You don't have permission to execute this command!"),
    COMMAND_PLAYERS_ONLY("Command.PlayersOnly", "This command is limited to players only!"),
    COMMAND_USAGE_PREFIX("Command.UsagePrefix", "Usage"),
    COMMAND_ERROR_OCCURRED("Command.ErrorOccurred", "An error occurred"),
    COMMAND_ERROR_NOTNUMBER("Command.InputNotNumber", "{0} isn't a number!"),
    COMMAND_ERROR_NOTFOUND("Command.NotFound", "Unknown command, type /help for help."),

    HOLOGRAMS_USAGE_CREATE("Holograms.Usage.Create", "/holograms create <text>"),
    HOLOGRAMS_USAGE_RESET("Holograms.Usage.Reset", "/holograms reset <hologramID>"),
    HOLOGRAMS_USAGE_ADDLINE("Holograms.Usage.AddLine", "/holograms addline <hologramID> <text>"),
    HOLOGRAMS_USAGE_DELETE("Holograms.Usage.Delete", "/holograms delete <hologramID>"),
    HOLOGRAMS_USAGE_LIST("Holograms.Usage.List", "/holograms list"),
    HOLOGRAMS_ERROR_NOTEXIST("Holograms.Error.NotExist", "Hologram {0} doesn't exist!"),
    HOLOGRAMS_SUCCESS_CREATED("Holograms.Success.Create", "Created hologram {0} successfully."),
    HOLOGRAMS_SUCCESS_RESET("Holograms.Success.Reset", "Hologram {0} was successfully reset."),
    HOLOGRAMS_SUCCESS_ADDLINE("Holograms.Success.AddLine", "Added line to {0} successfully."),
    HOLOGRAMS_SUCCESS_DELETE("Holograms.Success.Delete", "Deleted hologram {0} successfully."),
    HOLOGRAMS_SUCCESS_LIST_PREFIX("Holograms.Success.List.Prefix", "Holograms"),
    HOLOGRAMS_SUCCESS_LIST_EMPTY("Holograms.Success.List.Empty", "No holograms found."),

    HAT_USAGE("Hat.Usage", "/hat <id> [meta]"),
    HAT_CHANGED("Hat.Success", "Hat updated!"),

    HUB_INVALID_WORLD("Hub.InvalidWorld", "Invalid lobby world!"),
    HUB_LOCATION_UPDATED("Hub.LocationUpdated", "Lobby location updated!"),

    WARP_USAGE("Warp.Usage", "/warps <name>"),
    WARP_NOT_EXIST("Warp.NotExist", "Warp {0} does not exist!"),
    WARP_SUCCESS("Warp.Success", "Teleported you to warp {0}."),
    WARP_DISABLED_GLOBAL("Warp.DisabledGlobally", "Warp module is disabled."),
    WARP_DISABLED_WORLD("Warp.DisabledInWorld", "Warp module is disabled in this world."),

    SETWARP_USAGE("SetWarp.Usage", "/setwarp <name>"),
    SETWARP_ALREADY_EXIST("SetWarp.AlreadyExist", "Warp {0} already exists."),
    SETWARP_SUCCESS("SetWarp.Success", "Warp {0} set to your current location."),

    DELWARP_USAGE("DelWarp.Usage", "/delwarp <name>"),
    DELWARP_NOT_EXIST("DelWarp.NotExist", "Warp {0} does not exist!"),
    DELWARP_SUCCESS("DelWarp.Success", "Warp {0} deleted successfully."),

    WARPS_PREFIX("Warps.Prefix", "Warps");

    @Getter private String path;
    @Getter private Object defaultValue;

    HubBasicsMessage(String filePath, Object defaultValue) {
        this.path = filePath;
        this.defaultValue = defaultValue;
    }

    /**
     * Get the message, and format it using MessageFormat
     * @param strings Strings to insert in message
     * @return Formatted message
     */
    public String getMessage(String... strings) {
        return MessageFormat.format(HubBasics.getInstance().getMessageManager().getMessage(this), strings);
    }

    /**
     * Send the message to given {@link CommandSender}, and format using given strings
     * @param sender Receiver of message
     * @param strings Strings to insert in message
     */
    public void send(CommandSender sender, String... strings) {
        sender.sendMessage(this.getMessage(strings));
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
