package net.notfab.hubbasics.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.command.HPlayerCommand;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.CustomHolograms;
import net.notfab.hubbasics.plugin.messages.HMessenger;

public class HologramsCmd extends HPlayerCommand {

    private HubBasics pl;
    private CustomHolograms holograms;

    public HologramsCmd() {
        super(new Permission("hubbasics.command.holograms"), "holograms");
        this.pl = HubBasics.getInstance();
        this.holograms = (CustomHolograms) pl.getModuleManager().getModule(ModuleEnum.HOLOGRAMS);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            HMessenger.sendCommandUsageMessage(player, new String[]{"/holograms create <text>",
                    "/holograms reset <index>",
                    "/holograms addline <index> <line>",
                    "/holograms delete <index>",
                    "/holograms list"});
        } else {
            switch (args[0].toUpperCase()) {
                case "CREATE":
                    if (args.length == 1) {
                        HMessenger.sendCommandUsageMessage(player, new String[]{"/holograms create <text>"});
                    } else {
                        String text = ChatColor.translateAlternateColorCodes('&', argsToString(args, 1));
                        int id = this.holograms.createHologram(player.getLocation(), text);
                        player.sendMessage(ChatColor.GREEN + "Created hologram " + ChatColor.DARK_GREEN + id + ChatColor.GREEN + " successfully!");
                    }
                    return;
                case "RESET":
                    if (args.length == 1) {
                        HMessenger.sendCommandUsageMessage(player, new String[]{"/holograms reset <index>"});
                    } else if (!args[1].matches("\\d{1,5}")) {
                        player.sendMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED + " isn't a number!");
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            player.sendMessage(ChatColor.RED + "Hologram " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " doesn't exist!");
                        } else {
                            this.holograms.resetLines(index);
                            player.sendMessage(ChatColor.GREEN + "Removed all lines from hologram " + ChatColor.DARK_GREEN + index + ChatColor.GREEN + " successfully!");
                        }
                    }
                    return;
                case "ADDLINE":
                    if (args.length < 3) {
                        HMessenger.sendCommandUsageMessage(player, new String[]{"/holograms addline <index> <text>"});
                    } else if (!args[1].matches("\\d{1,5}")) {
                        player.sendMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED + " isn't a number!");
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            player.sendMessage(ChatColor.RED + "Hologram " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " doesn't exist!");
                        } else {
                            this.holograms.addLines(index, ChatColor.translateAlternateColorCodes('&', argsToString(args, 2)));
                            player.sendMessage(ChatColor.GREEN + "Added line successfully!");
                        }
                    }
                    return;
                case "DELETE":
                    if (args.length == 1) {
                        HMessenger.sendCommandUsageMessage(player, new String[]{"/holograms delete <index>"});
                    } else if (!args[1].matches("\\d{1,5}")) {
                        player.sendMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED + " isn't a number!");
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            player.sendMessage(ChatColor.RED + "Hologram " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " doesn't exist!");
                        } else {
                            this.holograms.deleteHologram(index);
                            player.sendMessage(ChatColor.GREEN + "Removed hologram " + ChatColor.DARK_GREEN + index + ChatColor.GREEN + " successfully!");
                        }
                    }
                    return;
                case "LIST":
                    Set<Integer> ids = this.holograms.getHolograms();
                    StringBuilder builder = new StringBuilder();
                    for (int id : ids) {
                        builder.append(ChatColor.GREEN + ", " + ChatColor.DARK_GREEN + id);
                    }
                    String list = builder.toString().length() == 0 ? ChatColor.GREEN + "No holograms found." : builder.toString().substring(4) + ".";
                    player.sendMessage(ChatColor.GREEN + "Holograms: " + list);
                    return;
                default:
                    HMessenger.sendCommandUsageMessage(player, new String[]{"/holograms create <text>",
                            "/holograms reset <index>",
                            "/holograms addline <index> <line>",
                            "/holograms delete <index>",
                            "/holograms list"});
                    return;
            }
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("create", "reset", "addline", "delete", " list");
        } else {
            return null;
        }
    }
}
