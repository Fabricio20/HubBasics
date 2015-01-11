package me.Fabricio20.listeners.V1_7;

import me.Fabricio20.Main;
import me.Fabricio20.Strings;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.ProtocolInjector;

public class TabListJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (isPlayerRightVersion(p)) {
			if(isEnabled()) {
				PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
				IChatBaseComponent header = ChatSerializer.a("'" + Strings.TabHeader.replace("%p", p.getName()) + "'");
				IChatBaseComponent footer = ChatSerializer.a("'" + Strings.TabFooter.replace("%p", p.getName()) + "'");
				connection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));
			}
		}
	}

	public boolean isPlayerRightVersion(Player player) {
		return ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}
	
	private boolean isEnabled() {
		return Main.theClass.config.getBoolean("TabList.Enabled");
	}

}
