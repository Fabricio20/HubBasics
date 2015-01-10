package me.Fabricio20.listeners.V1_8;

import me.Fabricio20.Main;
import me.Fabricio20.Strings;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabListJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(isEnabled()) {
			Player p = e.getPlayer();
		    PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
		    IChatBaseComponent header = ChatSerializer.a("'" + Strings.TabHeader.replace("%p", p.getName()) + "'");
			IChatBaseComponent footer = ChatSerializer.a("'" + Strings.TabFooter.replace("%p", p.getName()) + "'");
		    connection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));
		    connection.sendPacket(new PacketTabHeader(header, footer));
		}
	}
	
	private boolean isEnabled() {
		return Main.theClass.getPlugin().getConfig().getBoolean("TabList.Enabled");
	}
	
}
