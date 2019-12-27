package net.notfab.hubbasics.spigot.commands;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.modules.LobbyModule;
import net.notfab.hubbasics.spigot.utils.Messages;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.command.CommandSender;

public class SetLobbyCommand extends Command {

    public SetLobbyCommand() {
        super("setlobby", "sethub");
        // -- Perms
        this.addPermission("hubbasics.sethub");
        // -- Docs
        this.setDescription("Defines the Lobby");
        this.addUsage("/sethub &e<warp name>", "Defines the lobby.");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            HubBasics.getMessenger().send(sender, getHelp());
        } else {
            HLocation location = HubBasics.getLocationManager().get(args[0]);
            if (location != null) {
                LobbyModule module = (LobbyModule) HubBasics.getModuleManager().getModule(EnumModules.Lobby);
                SimpleConfig config = module.getConfig();
                config.set("Location", location.getId());
                config.save();
                module.setLocation(location);
                HubBasics.getMessenger().send(sender, Messages.get(sender, "LOBBY_DEFINED"));
            } else {
                HubBasics.getMessenger().send(sender, Messages.get(sender, "INVALID_WARP"));
            }
        }
    }

}