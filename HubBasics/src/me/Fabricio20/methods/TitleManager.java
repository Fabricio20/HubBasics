package me.Fabricio20.methods;

import net.minecraft.util.io.netty.channel.Channel;

import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector.PacketTitle;

/**
 * <p><b>TitleLIB/TitleManager:</b> Class to send <b>Titles, Subtitles and Timings</b> to Players. <i>(Minecraft 1.8)</i><br/>
 * 
 * </p>
 * @author inventivetalent <b>-<b/> <a href="http://www.inventivegames.de"><i>www.inventivegames.de</i></a>
 * @version 2
 * 
 */
public class TitleManager {

	private static Class<?>	nmsChatSerializer	= Reflection.getNMSClass("ChatSerializer");
	private static int		VERSION				= 47;
	
	/**
	 * Send a Title
	 * 
	 * @param p
	 *            Player to send the Title to
	 * @param title
	 *            Json Title String
	 */
	public static void sendTitle(Player p, String title) {
		if (!(getVersion(p) >= VERSION)) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			final Object serialized = Reflection.getMethod(nmsChatSerializer, "a", String.class).invoke(null, title);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, Reflection.getNMSClass("IChatBaseComponent")).newInstance(PacketTitle.Action.TITLE, serialized);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a Subtitle
	 * 
	 * @param p
	 *            Player to send the SubTitle to
	 * @param subtitle
	 *            Json SubTitle String
	 */
	public static void sendSubTitle(Player p, String subtitle) {
		if (!(getVersion(p) >= VERSION)) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			final Object serialized = Reflection.getMethod(nmsChatSerializer, "a", String.class).invoke(null, subtitle);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, Reflection.getNMSClass("IChatBaseComponent")).newInstance(PacketTitle.Action.SUBTITLE, serialized);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the Title Timings
	 * 
	 * @param p
	 *            Player to Update the Timings
	 * @param fadeIn
	 *            Time it should take to fade In
	 * @param stay
	 *            Time the Title should stay on screen
	 * @param fadeOut
	 *            Time it should take to fade Out
	 */
	public static void sendTimings(Player p, int fadeIn, int stay, int fadeOut) {
		if (!(getVersion(p) >= VERSION)) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, int.class, int.class, int.class).newInstance(PacketTitle.Action.TIMES, fadeIn, stay, fadeOut);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reset the Players Timing, Title, SubTitle
	 * 
	 * @param p
	 *            Player to Reset
	 */
	public static void reset(Player p) {
		if (!(getVersion(p) >= VERSION)) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class).newInstance(PacketTitle.Action.RESET);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear the Players Title
	 * 
	 * @param p
	 *            Player to be cleared
	 */
	public static void clear(Player p) {
		if (!(getVersion(p) >= VERSION)) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class).newInstance(PacketTitle.Action.CLEAR);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static int getVersion(Player p) {
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			final Object network = Reflection.getField(connection.getClass(), "networkManager").get(connection);
			final Object channel = Reflection.getField(network.getClass(), "m").get(network);
			final Object version = Reflection.getMethod(network.getClass(), "getVersion", Channel.class).invoke(network, channel);
			return (int) version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}