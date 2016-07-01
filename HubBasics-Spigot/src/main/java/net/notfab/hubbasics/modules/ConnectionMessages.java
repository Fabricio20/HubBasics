package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionMessages extends Module {

    public ConnectionMessages() {
        super(ModuleEnum.CONNECTION_MESSAGES);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!getBoolean(ConfigurationKey.CONNECT_MESSAGES_ENABLED)) {
            return;
        }
        //TODO: Check if First Join
        e.setJoinMessage(HMessenger.format(getString(ConfigurationKey.PLAYER_CONNECT), e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(!getBoolean(ConfigurationKey.CONNECT_MESSAGES_ENABLED)) {
            return;
        }
        e.setQuitMessage(HMessenger.format(getString(ConfigurationKey.PLAYER_DISCONNECT), e.getPlayer()));
    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent e) {

    }

}
