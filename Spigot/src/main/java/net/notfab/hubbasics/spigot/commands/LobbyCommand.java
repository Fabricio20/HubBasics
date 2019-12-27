package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.modules.LobbyModule;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand extends Command {

    public LobbyCommand() {
        super("lobby", "hub");
        // -- Perms
        this.addPermission("hubbasics.lobby");
        // -- Docs
        this.setDescription("Teleports to Lobby");
        this.addUsage("/lobby", "Teleports you to the lobby.");
        this.addUsage("/lobby &e<player>", "Teleports the specified player to the lobby.");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                LobbyModule module = (LobbyModule) HubBasics.getModuleManager().getModule(EnumModules.Lobby);
                HLocation location = module.getLocation();
                if (location != null) {
                    location.teleport(player);
                } else {
                    HubBasics.getMessenger().send(player, Messages.get(player, "INVALID_WARP"));
                }
            } else {
                HubBasics.getMessenger().send(sender, getHelp());
            }
        } else if (sender.hasPermission("hubbasics.lobby.others")) {
            Player target = FinderUtil.findPlayer(argsToString(args, 0));
            if (target == null) {
                HubBasics.getMessenger().send(sender, Messages.get(sender, "INVALID_PLAYER"));
                return;
            }
            LobbyModule module = (LobbyModule) HubBasics.getModuleManager().getModule(EnumModules.Lobby);
            HLocation location = module.getLocation();
            if (location != null) {
                location.teleport(target);
            } else {
                HubBasics.getMessenger().send(sender, Messages.get(sender, "INVALID_WARP"));
            }
        }
    }

}