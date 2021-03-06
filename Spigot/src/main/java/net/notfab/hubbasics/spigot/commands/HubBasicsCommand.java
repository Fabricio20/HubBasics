package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubBasicsCommand extends Command {

    public HubBasicsCommand() {
        super("hb");
        // -- Perms
        this.addPermission("hubbasics.admin");
        // -- Docs
        this.setDescription("HubBasics' main command");
        this.addUsage("/hb &areload", "Reloads the plugin");
        this.addUsage("/hb &aversion", "Shows the plugin's version");
        this.addUsage("/hb &aitem &e<name>", "Spawns the item with the given name (Player Only)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            HubBasics.getMessenger().send(sender, getHelp());
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                // Disable
                HubBasics.getConfigManager().onDisable();
                HubBasics.getModuleManager().onDisable();
                HubBasics.getItemManager().onDisable();
                HubBasics.getMenuManager().onDisable();
                // Enable
                HubBasics.getModuleManager().onEnable();
                HubBasics.getItemManager().onEnable();
                HubBasics.getMenuManager().onEnable();
                // Message
                HubBasics.getMessenger().send(sender, Messages.get(sender, "PLUGIN_RELOADED"));
            } else if (args[0].equalsIgnoreCase("version")) {
                HubBasics.getMessenger().send(sender, Messages.get(sender, "PLUGIN_VERSION",
                        HubBasics.getDescription().getVersion()));
            } else {
                HubBasics.getMessenger().send(sender, Messages.get(sender, "UNKNOWN_SUBCOMMAND"));
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("item") && (sender instanceof Player)) {
                Player player = (Player) sender;
                String name = args[1];
                CustomItem item = HubBasics.getItemManager().get(name);
                if (item == null) {
                    HubBasics.getMessenger().send(player, Messages.get(player, "ITEM_NOT_FOUND"));
                    return;
                }
                player.getInventory().addItem(item.toItemStack(player));
            } else {
                HubBasics.getMessenger().send(sender, Messages.get(sender, "UNKNOWN_SUBCOMMAND"));
            }
        }
    }

}
