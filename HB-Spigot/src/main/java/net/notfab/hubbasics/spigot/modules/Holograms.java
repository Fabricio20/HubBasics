package net.notfab.hubbasics.spigot.modules;

import lombok.Getter;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Holograms extends Module {

    private SimpleConfig file = getConfig();
    private final String ARMORSTAND_TEXT = "text";
    private final String ARMORSTAND_UUID = "uuid";
    private final String ARMORSTAND_LINE = "line";

    @Getter public static Holograms Instance = null;

    public Holograms() {
        super(EnumModules.Holograms, NMSVersion.V1_7_R1);
    }

    @Override
    public void onEnable() {
        Instance = this;
    }

    @Override
    public void onDisable() {
        Instance = null;
    }

    /**
     * @return All holograms stored in file
     */
    public Set<Integer> getHolograms() {
        if (file.getSection("hologram") != null) {
            return file.getSection("hologram").getKeys()
                    .stream().map(Integer::parseInt)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }

    /**
     * Check if a hologram with given ID exists
     *
     * @param hologram The ID
     * @return Exists
     */
    public boolean hologramExists(int hologram) {
        return this.file.getSection("hologram." + hologram) != null;
    }

    /**
     * Create a new hologram at given location with given lines
     *
     * @param loc   The location
     * @param lines The lines
     * @return The ID of the created hologram
     */
    public int createHologram(Location loc, String... lines) {
        int index = 0;
        while (file.getSection("hologram." + (index = index + 1)) != null) ;

        JSONObject object = new JSONObject();
        object.put("x", loc.getX());
        object.put("y", loc.getY());
        object.put("z", loc.getZ());
        object.put("w", loc.getWorld().getName());
        file.set("hologram." + index + ".location", object.toString());

        JSONArray array = spawnLines(loc, lines);
        file.set("hologram." + index + ".lines", array.toString());
        file.save();
        return index;
    }

    /**
     * Delete a hologram
     *
     * @param hologram The ID of the hologram
     */
    public void deleteHologram(int hologram) {
        deleteSpawnedLines(hologram);
        file.set("hologram." + hologram, null);
        file.save();
    }

    /**
     * Get the lines of given hologram
     *
     * @param hologram The ID of the hologram
     * @return JSONArray of lines
     */
    public JSONArray getLines(int hologram) {
        return new JSONArray(file.getString("hologram." + hologram + ".lines"));
    }

    /**
     * Get the lines of given hologram
     *
     * @param hologram The ID of the hologram
     * @return String array of lines
     */
    public String[] getLineStrings(int hologram) {
        JSONArray array = getLines(hologram);
        String[] strings = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            strings[i] = array.getJSONObject(i).getString(ARMORSTAND_TEXT);
        }

        return strings;
    }

    /**
     * Get the location of given hologram
     *
     * @param hologram The ID of the hologram
     * @return Location of hologram
     */
    public Location getLocation(int hologram) {
        JSONObject object = new JSONObject(file.getString("hologram." + hologram + ".location"));
        double x = object.getDouble("x");
        double y = object.getDouble("y");
        double z = object.getDouble("z");
        World world = Bukkit.getWorld(object.getString("w"));
        return new Location(world, x, y, z);
    }

    /**
     * Add lines to already existing hologram
     *
     * @param hologram The ID of the hologram
     * @param lines    The lines to add
     */
    public void addLines(int hologram, String... lines) {
        JSONArray array = getLines(hologram);
        String[] strings = new String[array.length() + lines.length];
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) stringList.add(array.getJSONObject(i).getString(ARMORSTAND_TEXT));
        Collections.addAll(stringList, lines);
        stringList.toArray(strings);

        deleteSpawnedLines(hologram);
        file.set("hologram." + hologram + ".lines", spawnLines(getLocation(hologram), strings).toString());
        file.save();
    }

    private JSONArray spawnLines(Location loc, String[] lines) {
        int lineIndex = 0;
        JSONArray array = new JSONArray();

        for (String str : lines) {
            ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', str));
            armorStand.setCustomNameVisible(true);
            armorStand.setRemoveWhenFarAway(false);
            loc.setY(loc.getY() - 0.25);

            JSONObject object = new JSONObject();
            object.put(ARMORSTAND_LINE, lineIndex);
            object.put(ARMORSTAND_TEXT, str);
            object.put(ARMORSTAND_UUID, armorStand.getUniqueId().toString());
            array.put(lineIndex, object);
            lineIndex++;
        }

        return array;
    }

    private void deleteSpawnedLines(int hologram) {
        JSONArray array = getLines(hologram);
        List<UUID> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            UUID uuid = UUID.fromString(object.getString(ARMORSTAND_UUID));
            list.add(uuid);
        }

        getLocation(hologram).getWorld().getEntities().stream().filter(entity -> list.contains(entity.getUniqueId())).forEach(Entity::remove);
    }

    /**
     * Reset an existing hologram
     *
     * @param hologram
     */
    public void resetLines(int hologram) {
        deleteSpawnedLines(hologram);
        JSONArray array = new JSONArray();
        file.set("hologram." + hologram + ".lines", array.toString());
        file.save();
    }

}
