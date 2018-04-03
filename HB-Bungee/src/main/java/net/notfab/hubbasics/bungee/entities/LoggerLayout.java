package net.notfab.hubbasics.bungee.entities;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import net.md_5.bungee.api.ChatColor;

import java.util.EnumMap;
import java.util.Map;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-14.
 */
public class LoggerLayout extends LayoutBase<ILoggingEvent> {

    private final Map<ChatColor, String> replacements = new EnumMap<>(ChatColor.class);

    public LoggerLayout() {
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

    @Override
    public String doLayout(ILoggingEvent event) {
        String prefix = "&a[&eHubBasics&a] ";
        if(event.getLevel() == Level.TRACE)
            prefix += "&b[TRACE] ";
        else if(event.getLevel() == Level.DEBUG)
            prefix += "&d[DEBUG] ";
        else if(event.getLevel() == Level.INFO)
            prefix += "&a[INFO] ";
        else if(event.getLevel() == Level.WARN)
            prefix += "&6[WARN] ";
        else if(event.getLevel() == Level.ERROR)
            prefix += "&c[ERROR] ";
        else
            prefix += "&7[" + event.getLevel().toString() + "] ";
        return translateAlternateColorCodes(ChatColor.translateAlternateColorCodes('&', prefix + event.getMessage()));
    }

    private String translateAlternateColorCodes(String message) {
        for (ChatColor color : ChatColor.values()) {
            if (replacements.containsKey(color)) {
                message = message.replaceAll("(?i)" + color.toString(), replacements.get(color));
            } else {
                message = message.replaceAll("(?i)" + color.toString(), "");
            }
        }
        return message + "\u001B[m";
    }

}
