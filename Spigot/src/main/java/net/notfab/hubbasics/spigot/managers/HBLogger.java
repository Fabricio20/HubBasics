package net.notfab.hubbasics.spigot.managers;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import org.bukkit.ChatColor;
import org.fusesource.jansi.Ansi;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HBLogger {

    @Setter
    @Getter
    private static Logger underlying;

    @Getter
    @Setter
    private static Level level = Level.INFO;

    @Getter
    @Setter
    private static boolean enabled = true;

    @Getter
    private final String prefix;

    private static final Map<ChatColor, String> replacements = new EnumMap<>(ChatColor.class);

    public HBLogger(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
    }

    public void onDisable() {
        this.info("[Logger] Shutting down.");
    }

    private String translateColors(String message) {
        for (ChatColor color : ChatColor.values()) {
            if (replacements.containsKey(color)) {
                message = message.replaceAll("(?i)" + color.toString(), replacements.get(color));
            } else {
                message = message.replaceAll("(?i)" + color.toString(), "");
            }
        }
        return message + Ansi.ansi().reset().toString();
    }

    // ------------------------------------------------------------------

    private void log(Level logLevel, String message) {
        if (logLevel.intValue() >= level.intValue()) {
            underlying.log(logLevel,
                    this.translateColors(ChatColor.translateAlternateColorCodes('&', message)));
        }
    }

    private void log(Level logLevel, String message, Throwable throwable) {
        if (logLevel.intValue() >= level.intValue()) {
            underlying.log(logLevel,
                    this.translateColors(ChatColor.translateAlternateColorCodes('&', message)), throwable);
        }
    }

    // -------------------------------------------------------------------

    public void trace(String msg) {
        this.log(Level.FINER, this.prefix + msg);
    }

    public void trace(String msg, Throwable throwable) {
        this.log(Level.FINER, this.prefix + msg, throwable);
    }

    // Debug

    public void debug(String msg) {
        this.log(Level.FINE, this.prefix + msg);
    }

    public void debug(String msg, Throwable throwable) {
        this.log(Level.FINE, this.prefix + msg, throwable);
    }

    // Info

    public void info(String msg) {
        this.log(Level.INFO, this.prefix + msg);
    }

    public void info(String msg, Throwable throwable) {
        this.log(Level.INFO, this.prefix + msg, throwable);
    }

    // Warn

    public void warn(String msg) {
        this.log(Level.WARNING, this.prefix + msg);
    }

    public void warn(String msg, Throwable throwable) {
        this.log(Level.WARNING, this.prefix + msg, throwable);
    }

    // Error

    public void error(String msg) {
        this.log(Level.SEVERE, this.prefix + msg);
    }

    public void error(String msg, Throwable throwable) {
        this.log(Level.SEVERE, this.prefix + msg, throwable);
    }

    // ---------------------------------------------------------------

    public static HBLogger getLogger(EnumModules module) {
        return new HBLogger("&2[&a" + module.name() + "&2] &r");
    }

    public static HBLogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static HBLogger getLogger(String name) {
        return new HBLogger("&2[&e" + name + "&2] &r");
    }

    static {
        replacements.put(ChatColor.BLACK, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLACK).boldOff().toString());
        replacements.put(ChatColor.DARK_BLUE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLUE).boldOff().toString());
        replacements.put(ChatColor.DARK_GREEN, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.GREEN).boldOff().toString());
        replacements.put(ChatColor.DARK_AQUA, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.CYAN).boldOff().toString());
        replacements.put(ChatColor.DARK_RED, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.RED).boldOff().toString());
        replacements.put(ChatColor.DARK_PURPLE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.MAGENTA).boldOff().toString());
        replacements.put(ChatColor.GOLD, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.YELLOW).boldOff().toString());
        replacements.put(ChatColor.GRAY, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.WHITE).boldOff().toString());
        replacements.put(ChatColor.DARK_GRAY, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLACK).bold().toString());
        replacements.put(ChatColor.BLUE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLUE).bold().toString());
        replacements.put(ChatColor.GREEN, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.GREEN).bold().toString());
        replacements.put(ChatColor.AQUA, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.CYAN).bold().toString());
        replacements.put(ChatColor.RED, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.RED).bold().toString());
        replacements.put(ChatColor.LIGHT_PURPLE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.MAGENTA).bold().toString());
        replacements.put(ChatColor.YELLOW, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.YELLOW).bold().toString());
        replacements.put(ChatColor.WHITE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.WHITE).bold().toString());
        replacements.put(ChatColor.MAGIC, Ansi.ansi().a(Ansi.Attribute.BLINK_SLOW).toString());
        replacements.put(ChatColor.BOLD, Ansi.ansi().a(Ansi.Attribute.UNDERLINE_DOUBLE).toString());
        replacements.put(ChatColor.STRIKETHROUGH, Ansi.ansi().a(Ansi.Attribute.STRIKETHROUGH_ON).toString());
        replacements.put(ChatColor.UNDERLINE, Ansi.ansi().a(Ansi.Attribute.UNDERLINE).toString());
        replacements.put(ChatColor.ITALIC, Ansi.ansi().a(Ansi.Attribute.ITALIC).toString());
        replacements.put(ChatColor.RESET, Ansi.ansi().a(Ansi.Attribute.RESET).toString());
    }

}