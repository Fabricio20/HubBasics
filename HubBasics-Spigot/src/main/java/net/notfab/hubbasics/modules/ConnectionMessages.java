package net.notfab.hubbasics.modules;

import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
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
        //TODO: Check if First Join
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent e) {

    }

}
