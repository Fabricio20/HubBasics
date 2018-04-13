package net.notfab.hubbasics.spigot.modules;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.hubbasics.spigot.utils.PlaceHolderUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class JoinMessages extends Module {

    private String joinMessage = "&6[&2+&6] &9${Player.Name} &6has joined the server.";
    private String quitMessage = "&6[&4-&6] &9${Player.Name} &6has left the server.";
    private Map<String, String> worldMessages = new HashMap<>();

    public JoinMessages() {
        super(EnumModules.JoinMessages, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {
        String joinMessage = getConfig().getString("Join", "null");
        if("null".equalsIgnoreCase(joinMessage)) {
            this.joinMessage = null;
        } else {
            this.joinMessage = joinMessage;
        }
        String quitMessage = getConfig().getString("Leave", "null");
        if("null".equalsIgnoreCase(quitMessage)) {
            this.quitMessage = null;
        } else {
            this.quitMessage = quitMessage;
        }
        if(getConfig().contains("Worlds")) {
            Bukkit.getWorlds().forEach(world -> {
                String message = getConfig().getString("Worlds." + world.getName(), "null");
                if(!"null".equalsIgnoreCase(message)) {
                    worldMessages.put(world.getName(), message);
                }
            });
        }
    }

    @Override
    public void onDisable() {
        this.worldMessages.clear();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(PlaceHolderUtil.replace(event.getPlayer(), this.joinMessage));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(PlaceHolderUtil.replace(event.getPlayer(), this.quitMessage));
    }

    @EventHandler
    public void onChange(PlayerChangedWorldEvent event) {
        if(this.worldMessages.containsKey(event.getPlayer().getWorld().getName())) {
            HubBasics.getMessenger().send(event.getPlayer().getWorld(),
                    this.worldMessages.get(event.getPlayer().getWorld().getName()));
        }
    }

}
