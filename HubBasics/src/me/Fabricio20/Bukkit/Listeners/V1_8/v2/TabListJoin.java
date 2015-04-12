package me.Fabricio20.Bukkit.Listeners.V1_8.v2;

import java.lang.reflect.Field;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Storage.Strings;
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
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(isEnabled()) {
			Player p = e.getPlayer();
		    IChatBaseComponent header = ChatSerializer.a("'" + Strings.TabHeader.replace("%p", p.getName()) + "'");
			IChatBaseComponent footer = ChatSerializer.a("'" + Strings.TabFooter.replace("%p", p.getName()) + "'");
			setHeaderFooter(p, header, footer);
		}
	}
	
	public void setHeaderFooter(Player player, IChatBaseComponent header, IChatBaseComponent footer) {
		CraftPlayer cp = (CraftPlayer) player;
        PlayerConnection con = cp.getHandle().playerConnection;
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try{
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
            
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());
        }catch(Exception e){
            e.printStackTrace();
        }
        con.sendPacket(packet);
	}
	
	private boolean isEnabled() {
		return Main.theClass.config.getBoolean("TabList.Enabled");
	}
	
}
