package me.Fabricio20.API.V1_7;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector.PacketTitle;
import org.spigotmc.ProtocolInjector.PacketTitle.Action;

public class ActionAPI {
	
	public static void sendTitle(Player player, String message) {
		if(isPlayerRightVersion(player)) {
			IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
			PacketTitle title = new PacketTitle(Action.TITLE, icbc);
		    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(title);
		}
	}
	
	public static void sendSubtitle(Player player, String message) {
		if(isPlayerRightVersion(player)) {
			IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
			PacketTitle subtitle = new PacketTitle(Action.SUBTITLE, icbc);
		    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(subtitle);
		}
	}
	
	public static void sendAction(Player player, String message) {
		if(isPlayerRightVersion(player)) {
			IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
			PacketPlayOutChat bar = new PacketPlayOutChat(icbc/*, (byte)2*/);
		    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
		}
	}
	
	public static boolean isPlayerRightVersion(Player player) {
		return ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}
	
}
