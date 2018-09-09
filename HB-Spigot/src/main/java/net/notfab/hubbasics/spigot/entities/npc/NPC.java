package net.notfab.hubbasics.spigot.entities.npc;

import lombok.Getter;
import net.notfab.spigot.simpleconfig.Section;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class NPC {

    @Getter private String name;
    @Getter private EntityType entityType;

    @Getter private UUID uniqueId;
    @Getter private Location location;

    private Map<String, String> attributes = new HashMap<>();

    public NPC(String name, EntityType type) {
        this.name = name;
        this.entityType = type;
        this.attributes.put("AI", "false");
        this.attributes.put("Silent", "true");
        this.attributes.put("Gravity", "false");
        this.attributes.put("Collision", "false");
        this.attributes.put("Invulnerable", "true");
    }

    public boolean isSpawned() {
        return this.uniqueId != null;
    }

    public Entity getEntity() {
        return location.getWorld()
                .getNearbyEntities(location, 4, 4, 4).stream()
                .filter(entity -> entity.getUniqueId().equals(uniqueId))
                .findFirst().orElseGet(null);
    }

    public void spawn() {
    }

    public void saveYAML(SimpleConfig config) {
        Section section = config.getSection(this.uniqueId.toString());
        if(section == null) {
            config.createSection(this.uniqueId.toString());
            section = config.getSection(this.uniqueId.toString());
        }
        // --
        Section finalSection = section;
        finalSection.set("Name", this.name);
        finalSection.set("EntityType", this.entityType.name());
        this.attributes.forEach(finalSection::set);
        config.save();
    }

}
