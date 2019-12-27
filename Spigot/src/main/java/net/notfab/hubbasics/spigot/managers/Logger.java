package net.notfab.hubbasics.spigot.managers;

import ch.qos.logback.classic.Level;
import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Manager;
import org.slf4j.LoggerFactory;

public class Logger extends Manager {

    private static Level level = Level.DEBUG;
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HubBasics.getClass());

    @Getter
    private final String prefix;

    @Getter
    @Setter
    private boolean enabled = true;

    public Logger(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
    }

    public static Level getLevel() {
        return level;
    }

    static void setLevel(Level l) {
        level = l;
    }

    @Override
    public void onDisable() {
        this.LOGGER.info("[Logger] Shutting down.");
    }

    public void trace(String msg) {
        if (level.levelInt <= Level.TRACE.levelInt) {
            LOGGER.trace(this.prefix + msg);
        }
    }

    public void trace(String msg, Throwable t) {
        if (level.levelInt <= Level.TRACE.levelInt) {
            LOGGER.trace(this.prefix + msg, t);
        }
    }

    public void debug(String msg) {
        if (level.levelInt <= Level.DEBUG.levelInt) {
            LOGGER.debug(this.prefix + msg);
        }
    }

    public void debug(String msg, Throwable t) {
        if (level.levelInt <= Level.DEBUG.levelInt) {
            LOGGER.debug(this.prefix + msg, t);
        }
    }

    public void info(String msg) {
        if (level.levelInt <= Level.INFO.levelInt) {
            LOGGER.info(this.prefix + msg);
        }
    }

    public void info(String msg, Throwable t) {
        if (level.levelInt <= Level.INFO.levelInt) {
            LOGGER.info(this.prefix + msg, t);
        }
    }

    public void warn(String msg) {
        if (level.levelInt <= Level.WARN.levelInt) {
            LOGGER.warn(this.prefix + msg);
        }
    }

    public void warn(String msg, Throwable t) {
        if (level.levelInt <= Level.WARN.levelInt) {
            LOGGER.warn(this.prefix + msg, t);
        }
    }

    public void error(String msg) {
        if (level.levelInt <= Level.ERROR.levelInt) {
            LOGGER.error(this.prefix + msg);
        }
    }

    public void error(String msg, Throwable t) {
        if (level.levelInt <= Level.ERROR.levelInt) {
            LOGGER.error(this.prefix + msg, t);
        }
    }

    public Logger getLogger(EnumModules module) {
        return new Logger("&2[&a" + module.name() + "&2] &r");
    }

}