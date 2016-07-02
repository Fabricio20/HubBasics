package net.notfab.hubbasics.commands;

import net.notfab.hubbasics.abstracts.command.HCommand;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.messages.HubBasicsMessage;
import net.notfab.hubbasics.plugin.utils.HPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HatCommand extends HCommand {

    public HatCommand() {
        super(HPermissions.HAT_COMMAND, "hat");
    }

    @Override
    @SuppressWarnings("deprecated")
    public void onCommand(Player player, String[] args) {
        if(args.length > 2 || args.length == 0) {
            HMessenger.sendCommandUsageMessage(player, HubBasicsMessage.COMMAND_HAT_USAGE.getMessage());
        }
        if(args.length == 1) {
            if (args[0].matches("[0-999]+")) {
                Integer id = Integer.parseInt(args[0]);
                player.getInventory().setHelmet(new ItemStack(Material.getMaterial(id)));
                HMessenger.sendMessage(player, HubBasicsMessage.COMMAND_HAT_CHANGED.getMessage());
            } else {
                HMessenger.sendErrorMessage(player, "ID Must Be A Number");
            }
        }
        if(args.length == 2) {
            if(!args[0].matches("[0-999]+") || !args[1].matches("[0-999]+")) {
                HMessenger.sendErrorMessage(player, "ID Must Be A Number");
                return;
            }
            int id = Integer.parseInt(args[0]);
            int meta = Integer.parseInt(args[1]);
            player.getInventory().setHelmet(new ItemStack(id, 1, (short) meta));
            HMessenger.sendMessage(player, HubBasicsMessage.COMMAND_HAT_CHANGED.getMessage());
        }
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if(args.length <= 1 || args.length > 3) {
            HMessenger.sendCommandUsageMessage(sender, HubBasicsMessage.COMMAND_HAT_USAGE.getMessage());
        } else if(args.length == 2) {
            if(!args[0].matches("[0-999]+")) {
                HMessenger.sendErrorMessage(sender, "ID Must Be A Number");
                return;
            }
            Integer id = Integer.parseInt(args[0]);
            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                HMessenger.sendErrorMessage(sender, HubBasicsMessage.COMMAND_ERROR_OCCURRED.getMessage());
                return;
            }
            target.getInventory().setHelmet(new ItemStack(id));
            HMessenger.sendMessage(sender, HubBasicsMessage.COMMAND_HAT_CHANGED.getMessage());
        } else if(args.length == 3) {
            if(!args[0].matches("[0-999]+") || !args[1].matches("[0-999]+")) {
                HMessenger.sendErrorMessage(sender, "ID Must Be A Number");
                return;
            }
            Integer id = Integer.parseInt(args[0]);
            int meta = Integer.parseInt(args[1]);
            Player target = Bukkit.getPlayer(args[2]);
            if(target == null) {
                HMessenger.sendErrorMessage(sender, HubBasicsMessage.COMMAND_ERROR_OCCURRED.getMessage());
                return;
            }
            target.getInventory().setHelmet(new ItemStack(id, 1, (short) meta));
            HMessenger.sendMessage(sender, HubBasicsMessage.COMMAND_HAT_CHANGED.getMessage());
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

}
