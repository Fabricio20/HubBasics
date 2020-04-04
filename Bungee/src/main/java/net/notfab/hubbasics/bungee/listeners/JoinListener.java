package net.notfab.hubbasics.bungee.listeners;

import lombok.Getter;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.notfab.hubbasics.bungee.HubBasics;
import net.notfab.hubbasics.bungee.Module;
import net.notfab.spigot.simpleconfig.SimpleConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class JoinListener implements Listener, Module {

    private static final Logger logger = Logger.getLogger("HubBasics");
    private HubBasics hubBasics;

    @Getter
    private boolean enabled = false;
    private List<String> servers = new ArrayList<>();

    public JoinListener(HubBasics hubBasics) {
        this.hubBasics = hubBasics;
    }

    @Override
    public void setup(HubBasics hubBasics) {
        SimpleConfig config = hubBasics.getConfigManager().getNewConfig("config.yml");
        this.enabled = config.getBoolean("LobbyOnJoin");
        // List of servers
        boolean isList = config.get("Lobby") instanceof List;
        if (isList) {
            for (String server : config.getStringList("Lobby")) {
                if (hubBasics.getProxy().getServerInfo(server) == null) {
                    logger.warning("Unknown Server: " + server);
                    continue;
                }
                this.servers.add(server);
            }
        } else {
            String server = config.getString("Lobby");
            if (hubBasics.getProxy().getServerInfo(server) == null) {
                logger.warning("Unknown Server: " + server);
                return;
            }
            this.servers.add(server);
        }
    }

    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        if (event.getReason() == ServerConnectEvent.Reason.JOIN_PROXY
                || event.getReason() == ServerConnectEvent.Reason.LOBBY_FALLBACK) {
            ServerInfo serverInfo = this.getLobby();
            if (serverInfo != null) {
                event.setTarget(serverInfo);
            } else {
                logger.warning("No available lobby servers!");
            }
        }
    }

    private ServerInfo getLobby() {
        if (this.servers.size() == 1) {
            return hubBasics.getProxy().getServerInfo(this.servers.get(0));
        }
        int lowest = Integer.MAX_VALUE - 1;
        AtomicReference<ServerInfo> serverInfo = new AtomicReference<>();
        this.servers.forEach(lobby -> {
            ServerInfo info = hubBasics.getProxy().getServerInfo(lobby);
            if (info.getPlayers().size() < lowest) {
                serverInfo.set(info);
            }
        });
        return serverInfo.get();
    }
}
