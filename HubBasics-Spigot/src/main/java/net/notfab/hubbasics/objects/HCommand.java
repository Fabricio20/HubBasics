package net.notfab.hubbasics.objects;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.utils.HMessenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.json.JSONObject;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public abstract class HCommand implements CommandExecutor {

    @Getter @Setter private Permission permission;
    @Getter @Setter
    private String[] names;

	/* -------------------------------------------------------------------- */

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
            onCommand((Player) sender, args);
        } else {
            onCommand((ConsoleCommandSender) sender, args);
        }
        onCommand(sender, args);
        return true;
    }

	/* ------------------------------------------------------------------------- */

    public abstract void onCommand(ConsoleCommandSender sender, String[] args);

    public abstract void onCommand(Player player, String[] args);

    public abstract void onCommand(CommandSender sender, String[] args);

	/* ------------------------------------------------------------------------- */

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
        return s.replaceFirst(" ", "");
    }

}