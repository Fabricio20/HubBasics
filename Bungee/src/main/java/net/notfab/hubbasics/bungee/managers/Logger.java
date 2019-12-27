package net.notfab.hubbasics.bungee.managers;

import ch.qos.logback.classic.Level;
import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.bungee.HubBasics;
import org.slf4j.LoggerFactory;

public class Logger {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HubBasics.getInstance().getClass());

    @Getter
    private final String prefix;

    @Getter
    @Setter
    private Level level = Level.DEBUG;

    @Getter
    @Setter
    private boolean enabled = true;

    public Logger(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
        this.info("[Logger] Started.");
    }

    public void onDisable() {
        this.LOGGER.info("[Logger] Shutting down.");
    }

    public void trace(String msg) {
        if (this.level.isGreaterOrEqual(Level.TRACE)) {
            LOGGER.trace(this.prefix + msg);
        }
    }

    public void trace(String msg, Throwable t) {
        if (this.level.isGreaterOrEqual(Level.TRACE)) {
            LOGGER.trace(this.prefix + msg, t);
        }
    }

    public void debug(String msg) {
        if (this.level.isGreaterOrEqual(Level.DEBUG)) {
            LOGGER.debug(this.prefix + msg);
        }
    }

    public void debug(String msg, Throwable t) {
        if (this.level.isGreaterOrEqual(Level.DEBUG)) {
            LOGGER.debug(this.prefix + msg, t);
        }
    }

    public void info(String msg) {
        if (this.level.isGreaterOrEqual(Level.INFO)) {
            LOGGER.info(this.prefix + msg);
        }
    }

    public void info(String msg, Throwable t) {
        if (this.level.isGreaterOrEqual(Level.INFO)) {
            LOGGER.info(this.prefix + msg, t);
        }
    }

    public void warn(String msg) {
        if (this.level.isGreaterOrEqual(Level.WARN)) {
            LOGGER.warn(this.prefix + msg);
        }
    }

    public void warn(String msg, Throwable t) {
        if (this.level.isGreaterOrEqual(Level.WARN)) {
            LOGGER.warn(this.prefix + msg, t);
        }
    }

    public void error(String msg) {
        if (this.level.isGreaterOrEqual(Level.ERROR)) {
            LOGGER.error(this.prefix + msg);
        }
    }

    public void error(String msg, Throwable t) {
        if (this.level.isGreaterOrEqual(Level.ERROR)) {
            LOGGER.error(this.prefix + msg, t);
        }
    }

}