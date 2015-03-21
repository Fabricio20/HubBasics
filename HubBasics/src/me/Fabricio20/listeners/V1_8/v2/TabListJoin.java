package me.Fabricio20.listeners.V1_8.v2;

import me.Fabricio20.Main;
import me.Fabricio20.Storage.Strings;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R2.PlayerConnection;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabListJoin implements Listener {
	
	@SuppressWarnings("unused")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(isEnabled()) {
			Player p = e.getPlayer();
		    PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
		    IChatBaseComponent header = ChatSerializer.a("'" + Strings.TabHeader.replace("%p", p.getName()) + "'");
			IChatBaseComponent footer = ChatSerializer.a("'" + Strings.TabFooter.replace("%p", p.getName()) + "'");
		    /*connection.sendPacket(new PacketTabHeader(header, footer));
		    connection.sendPacket(new PacketTabHeader(header, footer));*/
			connection.sendPacket(new PacketPlayOutPlayerListHeaderFooter(header));
		}
	}
	
	private boolean isEnabled() {
		return Main.theClass.config.getBoolean("TabList.Enabled");
	}
	
}
