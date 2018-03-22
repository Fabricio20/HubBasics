package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.modules.Holograms;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Set;

public class HologramsCommand extends Command {

    private Holograms holograms = Holograms.getInstance();

    public HologramsCommand() {
        super("hb-holo");
        // -- Perms
        this.addPermission("hubbasics.holograms");
        // -- Docs
        this.setDescription("Hologram management");
        this.addUsage("/hb-holo &acreate &e<text>", "Creates a hologram with the specified text");
        this.addUsage("/hb-holo &areset &e<id>", "Resets a hologram to it's default state");
        this.addUsage("/hb-holo &aaddline &e<id> &e<text>", "Adds a line to an existing hologram");
        this.addUsage("/hb-holo &adelete &e<id>", "Deletes an hologram");
        this.addUsage("/hb-holo &alist", "Lists all available holograms and their ids");
    }

    @Override
    protected void execute(Player player, String[] args) {
        if(holograms == null) {
            HubBasics.getMessenger().send(player, "MODULE_NOT_ENABLED");
            return;
        }
        if (args.length == 0) {
            HubBasics.getMessenger().send(player, getHelp());
        } else {
            switch (args[0].toUpperCase()) {
                case "CREATE":
                    if (args.length == 1) {
                        HubBasics.getMessenger().send(player, getHelp());
                    } else {
                        String text = ChatColor.translateAlternateColorCodes('&', argsToString(args, 1));
                        String id = this.holograms.createHologram(player.getLocation(), text) + "";
                        String holoID = ChatColor.DARK_GREEN + id + ChatColor.GREEN;
                        HubBasics.getMessenger().send(player, Messages.get(player, "HOLOGRAM_CREATED", holoID));
                    }
                    return;
                case "RESET":
                    if (args.length == 1) {
                        HubBasics.getMessenger().send(player, getHelp());
                    } else if (!args[1].matches("\\d{1,5}")) {
                        HubBasics.getMessenger().send(player, Messages.get(player, "NOT_A_NUMBER"));
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            HubBasics.getMessenger().send(player, "HOLOGRAM_DOES_NOT_EXIST");
                        } else {
                            this.holograms.resetLines(index);
                            String holoID = ChatColor.DARK_GREEN + "" + index + ChatColor.GREEN;
                            HubBasics.getMessenger().send(player, Messages.get(player, "HOLOGRAM_RESET", holoID));
                        }
                    }
                    return;
                case "ADDLINE":
                    if (args.length < 3) {
                        HubBasics.getMessenger().send(player, getHelp());
                    } else if (!args[1].matches("\\d{1,5}")) {
                        HubBasics.getMessenger().send(player, Messages.get(player, "NOT_A_NUMBER"));
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            HubBasics.getMessenger().send(player, "HOLOGRAM_DOES_NOT_EXIST");
                        } else {
                            this.holograms.addLines(index, ChatColor.translateAlternateColorCodes('&', argsToString(args, 2)));
                            String holoID = ChatColor.DARK_GREEN + "" + index + ChatColor.GREEN;
                            HubBasics.getMessenger().send(player, "HOLOGRAM_ADDED_LINE", holoID);
                        }
                    }
                    return;
                case "DELETE":
                    if (args.length == 1) {
                        HubBasics.getMessenger().send(player, getHelp());
                    } else if (!args[1].matches("\\d{1,5}")) {
                        HubBasics.getMessenger().send(player, Messages.get(player, "NOT_A_NUMBER"));
                    } else {
                        Integer index = Integer.parseInt(args[1]);

                        if (!this.holograms.hologramExists(index)) {
                            HubBasics.getMessenger().send(player, "HOLOGRAM_DOES_NOT_EXIST");
                        } else {
                            this.holograms.deleteHologram(index);
                            String holoID = ChatColor.DARK_GREEN + "" + index + ChatColor.GREEN;
                            HubBasics.getMessenger().send(player, "HOLOGRAM_DELETED", holoID);
                        }
                    }
                    return;
                case "LIST":
                    Set<Integer> ids = this.holograms.getHolograms();
                    StringBuilder builder = new StringBuilder();
                    for (int id : ids) builder.append(ChatColor.GREEN).append(", ").append(ChatColor.DARK_GREEN).append(id);
                    String list = builder.toString()
                            .length() == 0 ? ChatColor.GREEN + Messages.get(player, "HOLOGRAM_EMPTY") : builder.toString().substring(4) + ".";
                    player.sendMessage(ChatColor.GREEN + Messages.get("HOLOGRAM_LIST") + ": " + list);
                    return;
                default:
                    HubBasics.getMessenger().send(player, "UNKNOWN_SUBCOMMAND");
                    return;
            }
        }
    }

}
