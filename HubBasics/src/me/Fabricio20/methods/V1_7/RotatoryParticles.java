package me.Fabricio20.methods.V1_7;

import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class RotatoryParticles {
	
	public static void send(String particle, Player p) {
		Location loc = p.getLocation();
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(particle, (float) loc.getX(), (float) loc.getY()+2, (float) loc.getZ(), 1, 2, 1, 1, 10));
	}
	
}
