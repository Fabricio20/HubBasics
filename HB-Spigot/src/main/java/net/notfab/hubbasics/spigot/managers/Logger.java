package net.notfab.hubbasics.spigot.managers;

import ch.qos.logback.classic.Level;
import lombok.Getter;
import net.notfab.hubbasics.spigot.entities.Manager;
import org.slf4j.LoggerFactory;

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
public class Logger extends Manager {

    private final org.slf4j.Logger LOGGER;
    @Getter private Level level;

    public Logger() {
        this.LOGGER = LoggerFactory.getLogger(HubBasics.getClass());
        this.level = Level.INFO;
        this.LOGGER.info("[Logger] Started.");
    }

    @Override
    public void onDisable() {
        this.LOGGER.info("[Logger] Shutting down.");
    }

    public void trace(String msg) {
        LOGGER.trace(msg);
    }

    public void trace(String msg, Throwable t) {
        LOGGER.trace(msg, t);
    }

    public void debug(String msg) {
        LOGGER.debug(msg);
    }

    public void debug(String msg, Throwable t) {
        LOGGER.debug(msg, t);
    }

    public void info(String msg) {
        LOGGER.info(msg);
    }

    public void info(String msg, Throwable t) {
        LOGGER.info(msg, t);
    }

    public void warn(String msg) {
        LOGGER.warn(msg);
    }

    public void warn(String msg, Throwable t) {
        LOGGER.warn(msg, t);
    }

    public void error(String msg) {
        LOGGER.error(msg);
    }

    public void error(String msg, Throwable t) {
        LOGGER.error(msg, t);
    }

}
