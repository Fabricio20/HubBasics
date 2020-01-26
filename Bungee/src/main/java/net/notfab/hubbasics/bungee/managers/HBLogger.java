package net.notfab.hubbasics.bungee.managers;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;

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
        return message + "\u001B[m";
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

    public static HBLogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static HBLogger getLogger(String name) {
        return new HBLogger("&2[&e" + name + "&2] &r");
    }

    static {
        replacements.put(ChatColor.BLACK, "\u001B[0;30;22m");
        replacements.put(ChatColor.DARK_BLUE, "\u001B[0;34;22m");
        replacements.put(ChatColor.DARK_GREEN, "\u001B[0;32;22m");
        replacements.put(ChatColor.DARK_AQUA, "\u001B[0;36;22m");
        replacements.put(ChatColor.DARK_RED, "\u001B[0;31;22m");
        replacements.put(ChatColor.DARK_PURPLE, "\u001B[0;35;22m");
        replacements.put(ChatColor.GOLD, "\u001B[0;33;22m");
        replacements.put(ChatColor.GRAY, "\u001B[0;37;22m");
        replacements.put(ChatColor.DARK_GRAY, "\u001B[0;30;1m");
        replacements.put(ChatColor.BLUE, "\u001B[0;34;1m");
        replacements.put(ChatColor.GREEN, "\u001B[0;32;1m");
        replacements.put(ChatColor.AQUA, "\u001B[0;36;1m");
        replacements.put(ChatColor.RED, "\u001B[0;31;1m");
        replacements.put(ChatColor.LIGHT_PURPLE, "\u001B[0;35;1m");
        replacements.put(ChatColor.YELLOW, "\u001B[0;33;1m");
        replacements.put(ChatColor.WHITE, "\u001B[0;37;1m");
        replacements.put(ChatColor.MAGIC, "\u001B[5m");
        replacements.put(ChatColor.BOLD, "\u001B[21m");
        replacements.put(ChatColor.STRIKETHROUGH, "\u001B[9m");
        replacements.put(ChatColor.UNDERLINE, "\u001B[4m");
        replacements.put(ChatColor.ITALIC, "\u001B[3m");
        replacements.put(ChatColor.RESET, "\u001B[m");
    }

}