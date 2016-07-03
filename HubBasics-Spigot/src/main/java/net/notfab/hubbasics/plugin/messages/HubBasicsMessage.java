package net.notfab.hubbasics.plugin.messages;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public enum HubBasicsMessage {
    NO_PERMISSION("noPermission", "You don't have permission to execute this command!"),
    COMMAND_PLAYERS_ONLY("commandPlayersOnly", "This command is limited to players only!"),
    COMMAND_USAGE_PREFIX("commandUsagePrefix", "Usage"),
    COMMAND_ERROR_OCCURRED("commandErrorOccurred", "An error occurred"),
    COMMAND_ERROR_NOTNUMBER("commandInputNotNumber", "<string> isn't a number!"),
    HOLOGRAMS_USAGE_CREATE("holograms.usage.create", "/holograms create <text>"),
    HOLOGRAMS_USAGE_RESET("holograms.usage.reset", "/holograms reset <hologramID>"),
    HOLOGRAMS_USAGE_ADDLINE("holograms.usage.addLine", "/holograms addline <hologramID> <text>"),
    HOLOGRAMS_USAGE_DELETE("holograms.usage.delete", "/holograms delete <hologramID>"),
    HOLOGRAMS_USAGE_LIST("holograms.usage.list", "/holograms list"),
    HOLOGRAMS_ERROR_NOTEXIST("holograms.error.notExist", "Hologram <hologramID> doesn't exist!"),
    HOLOGRAMS_SUCCESS_CREATED("holograms.success.create", "Created hologram <hologramID> successfully."),
    HOLOGRAMS_SUCCESS_RESET("holograms.success.reset", "Hologram <hologramID> was successfully reset."),
    HOLOGRAMS_SUCCESS_ADDLINE("holograms.success.addline", "Added line to <hologramID> successfully."),
    HOLOGRAMS_SUCCESS_DELETE("holograms.success.delete", "Deleted hologram <hologramID> successfully."),
    HOLOGRAMS_SUCCESS_LIST_PREFIX("holograms.success.list.prefix", "Holograms"),
    HOLOGRAMS_SUCCESS_LIST_EMPTY("holograms.success.list.empty", "No holograms found."),
    HAT_USAGE("hat.usage", "/hat <id> [meta]"),
    HAT_CHANGED("hat.success", "Hat updated!");

    @Getter private String filePath;
    @Getter private Object defaultValue;

    HubBasicsMessage(String filePath, Object defaultValue) {
        this.filePath = filePath;
        this.defaultValue = defaultValue;
    }

    public String getMessage() {
        return HubBasics.getInstance().getMessageManager().getMessage(this);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
