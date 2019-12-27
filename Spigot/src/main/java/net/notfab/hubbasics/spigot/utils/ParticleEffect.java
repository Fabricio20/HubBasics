package net.notfab.hubbasics.spigot.utils;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleEffect {

    private static ParticleEffect Instance;

    public static ParticleEffect getInstance() {
        if (Instance == null)
            Instance = new ParticleEffect();
        return Instance;
    }

    public void display(Particle particle, Location center,
                        float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        center.getWorld().spawnParticle(particle, center.getX(), center.getY(), center.getZ(),
                amount, offsetX, offsetY, offsetZ, speed);
    }

}