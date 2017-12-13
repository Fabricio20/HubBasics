package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.HubBasics;

import java.util.logging.Level;

/**
 * Copyright (c) HubBasics 2018.
 * <p>
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 * <p>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 * <p>
 * File Created by Fabricio20 on 13/12/2017.
 */
public class Logger {

    private HubBasics hubBasics;

    public Logger(HubBasics hubBasics) {
        this.hubBasics = hubBasics;
    }

    public void info(String message) {
        hubBasics.getLogger().log(Level.INFO, message);
    }

    public void info(String message, Throwable throwable) {
        hubBasics.getLogger().log(Level.INFO, message, throwable);
    }

    public void warn(String message) {
        hubBasics.getLogger().log(Level.WARNING, message);
    }

    public void warn(String message, Throwable throwable) {
        hubBasics.getLogger().log(Level.WARNING, message, throwable);
    }

    public void error(String message) {
        hubBasics.getLogger().log(Level.SEVERE, message);
    }

    public void error(String message, Throwable throwable) {
        hubBasics.getLogger().log(Level.SEVERE, message, throwable);
    }

    public void debug(String message) {
        hubBasics.getLogger().log(Level.FINEST, message);
    }

    public void debug(String message, Throwable throwable) {
        hubBasics.getLogger().log(Level.FINEST, message, throwable);
    }

}
