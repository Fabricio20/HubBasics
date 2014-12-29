package me.Fabricio20.API;

import org.bukkit.entity.Player;
import me.Fabricio20.methods.TitleManager;

public class ActionAPI {
	
	public static void sendTitle(Player player, String message) {
		TitleManager.sendTitle(player, "'" + message + "'");
	}
	
	public static void sendSubtitle(Player player, String message) {
		TitleManager.sendSubTitle(player, "'" + message + "'");
	}
	
	/*public static void sendAction(Player player, String message) {
		if(isPlayerRightVersion(player)) {
			IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		    PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
		    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
		}
	}
	
	public static boolean isPlayerRightVersion(Player player) {
		return ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}
	*/
}
