package net.notfab.hubbasics.plugin.messages;

import net.notfab.hubbasics.HubBasics;

import lombok.Getter;

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
    COMMAND_ERROR_OCCURRED("commandErrorOccurred", "An error occurred");

    @Getter private String filePath;
    @Getter private Object defaultValue;

    HubBasicsMessage(String filePath, Object defaultValue) {
        this.filePath = filePath;
        this.defaultValue = defaultValue;
    }

    public String getMessage() {
        return HubBasics.getInstance().getMessageManager().getMessage(this);
    }
}
