package net.notfab.hubbasics.abstracts.command;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public abstract class HCommand implements TabExecutor {

    @Getter @Setter private Permission permission;
    @Getter @Setter private String[] names;

    public HCommand(String... names) {
        this.names = names;
        this.permission = null;
    }

    public HCommand(Permission perm, String... names) {
        this.permission = perm;
        this.names = names;
    }

    @Override
    @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if(sender instanceof Player) {
            if(this.permission == null) {
                onCommand((Player) sender, args);
            } else {
                if(!sender.hasPermission(this.permission)) {
                    HMessenger.errorNoPerms(sender);
                } else {
                    onCommand((Player) sender, args);
                }
            }
        } else {
            onCommand(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            if(this.permission == null) {
                return onTabComplete((Player) sender, args);
            } else {
                if(!sender.hasPermission(this.permission)) {
                    HMessenger.errorNoPerms(sender);
                    return Lists.newArrayList();
                } else {
                    List<String> list = onTabComplete((Player) sender, args);

                    if (list == null) return null;
                    if (list.contains("§raw")) {
                        list.remove("§raw");
                        return list;
                    }

                    List<String> returnList = list.stream().filter(str -> str.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
                    return returnList;
                }
            }
        } else {
            List<String> list = onTabComplete(sender, args);

            if (list == null) return null;
            if (list.contains("§raw")) {
                list.remove("§raw");
                return list;
            }

            List<String> returnList = list.stream().filter(str -> str.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
            return returnList;
        }
    }

    public abstract void onCommand(Player player, String[] args);

    public abstract void onCommand(CommandSender sender, String[] args);

    public abstract List<String> onTabComplete(Player player, String[] args);

    public abstract List<String> onTabComplete(CommandSender sender, String[] args);

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=ยง^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
                + "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public JSONObject getProfile(UUID uid, String name) {
        //return AncientCore.getInstance().getProfileManager().getProfile(uid, name); TODO
        return null;
    }

    public String argsToString(String[] args, int i) {
        String s = "";
        for(int d = i; d < args.length; d++) {
            s += " " + args[d];
        }
        return s.trim();
    }
}