package net.notfab.hubbasics.spigot.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.modules.v1_7.LobbyModule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class BungeeListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        if (subChannel.equals("HubBasics")) {
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