package net.notfab.hubbasics.spigot.modules;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.commands.LobbyCommand;
import net.notfab.hubbasics.spigot.commands.SetLobbyCommand;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.HLocation;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyModule extends Module {

    @Getter
    @Setter
    private boolean tpOnLogin;

    @Getter
    @Setter
    private HLocation location;

    private LobbyCommand command = new LobbyCommand();
    private SetLobbyCommand setLobbyCommand = new SetLobbyCommand();

    public LobbyModule() {
        super(EnumModules.Lobby, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {
        String locationName = getConfig().getString("Location", "default");
        this.location = HubBasics.getLocationManager().get(locationName);
        if (this.location == null) {
            Logger.warn("[LobbyModule] Invalid spawn teleport location.");
        }
        this.tpOnLogin = getConfig().getBoolean("TpOnLogin", false);
        HubBasics.getCommandFramework().register(command);
        HubBasics.getCommandFramework().register(setLobbyCommand);
    }

    @Override
    public void onDisable() {
        HubBasics.getCommandFramework().unregister(command);
        HubBasics.getCommandFramework().unregister(setLobbyCommand);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (this.tpOnLogin && this.location != null) {
            this.location.teleport(event.getPlayer());
        }
    }

}