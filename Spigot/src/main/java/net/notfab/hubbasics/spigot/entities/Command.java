package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.managers.HBLogger;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command extends BukkitCommand {

    protected HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();
    protected HBLogger logger = HubBasics.getLoggerManager();

    @Getter
    private List<String> permissions = new ArrayList<>();
    private List<String> help = new ArrayList<>();

    public Command(String... names) {
        super(names[0]);
        if (names.length > 1)
            this.setAliases(Arrays.asList(names).subList(1, names.length));
        this.help.add(ChatColor.translateAlternateColorCodes('&', "&9=======[ Usage ]======="));
    }

    protected void execute(CommandSender sender, String[] args) {
        // Nothing
    }

    protected void execute(Player player, String[] args) {
        // Nothing
    }

    protected void execute(ConsoleCommandSender sender, String[] args) {
        // Nothing
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String name, @NotNull String[] args) {
        if (this.permissions.size() > 0) {
            for (String perm : this.permissions) {
                if (!sender.hasPermission(perm)) {
                    HubBasics.getMessenger().send(sender, Messages.get(sender, "NO_PERMISSION"));
                    return true;
                }
            }
        }
        try {
            if (sender instanceof Player) {
                this.execute((Player) sender, args);
            } else if (sender instanceof ConsoleCommandSender) { // Is commandblock a Console?
                this.execute((ConsoleCommandSender) sender, args);
            }
            this.execute(sender, args); // Always execute the "universal" one
        } catch (Exception ex) {
            logger.error("Error while executing command (" + getName() + ")", ex);
        }
        return true;
    }

    protected void addPermission(String name) {
        this.permissions.add(name);
    }

    protected void addUsage(String usage, String description) {
        this.help.add(ChatColor.translateAlternateColorCodes('&', "&b" + usage));
        this.help.add(ChatColor.translateAlternateColorCodes('&', " &7&o" + description));
    }

    protected String[] getHelp() {
        return this.help.toArray(new String[0]);
    }

    protected String argsToString(String[] args, int i) {
        StringBuilder s = new StringBuilder();
        for (int d = i; d < args.length; d++) {
            s.append(" ").append(args[d]);
        }
        return s.toString().trim();
    }

}
