package net.notfab.hubbasics.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.notfab.hubbasics.bungee.HubBasics;
import net.notfab.hubbasics.bungee.entities.SimpleConfig;
import net.notfab.hubbasics.bungee.utils.Messages;

import java.util.List;

public class LobbyCommand extends Command {

    private net.notfab.hubbasics.bungee.HubBasics HubBasics = net.notfab.hubbasics.bungee.HubBasics.getInstance();

    public LobbyCommand() {
        super("lobby", null, "hub");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ServerInfo serverInfo = getLobby();
        if(serverInfo != null && commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(!player.getServer().getInfo().getName().equals(serverInfo.getName())) {
                player.connect(getLobby());
            } else {
                player.sendMessage(new TextComponent(Messages.get(player, "LOBBY_ALREADY_CONNECTED")));
            }
        } else if(commandSender instanceof ProxiedPlayer) {
            commandSender.sendMessage(new TextComponent(Messages.get(commandSender, "LOBBY_NOT_DEFINED")));
        } else {
            commandSender.sendMessage(new TextComponent(Messages.get(commandSender, "COMMAND_PLAYER")));
        }
    }

    public ServerInfo getLobby() {
        SimpleConfig config = HubBasics.getConfigManager().getNewConfig("config.yml");
        Boolean isList = config.get("Lobby") instanceof List;
        if(isList) {
            int lowest = Integer.MAX_VALUE -1;
            final ServerInfo[] serverInfo = {null};
            List<String> lobbies = config.getStringList("Lobby");
            lobbies.forEach(lobby -> {
                ServerInfo info = HubBasics.getProxy().getServerInfo(lobby);
                if(info.getPlayers().size() < lowest) {
                    serverInfo[0] = info;
                }
            });
            return serverInfo[0];
        } else {
            String name = config.getString("Lobby");
            return HubBasics.getProxy().getServerInfo(name);
        }
    }

}
