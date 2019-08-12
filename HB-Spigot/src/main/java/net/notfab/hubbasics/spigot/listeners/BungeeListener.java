package net.notfab.hubbasics.spigot.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.modules.LobbyModule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("HubBasics")) {
            String action = in.readUTF();
            if (action.equalsIgnoreCase("Lobby")) {
                LobbyModule module = (LobbyModule) HubBasics.getInstance()
                        .getModuleManager().getModule(EnumModules.Lobby);
                HLocation location = module.getLocation();
                if (location != null) {
                    location.teleport(player);
                }
            }
        }
    }

}