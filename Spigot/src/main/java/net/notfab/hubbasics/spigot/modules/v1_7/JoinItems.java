package net.notfab.hubbasics.spigot.modules.v1_7;

import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.hubbasics.spigot.nms.nbt.NBTItem;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class JoinItems extends Module {

    private List<String> worlds = new ArrayList<>();
    private Map<String, List<String>> items = new HashMap<>();
    private Map<String, Boolean> clearInventory = new HashMap<>();
    private Map<String, Boolean> worldChange = new HashMap<>();

    public JoinItems() {
        super(EnumModules.JoinItems, CraftBukkitVersion.v1_7_X);
    }

    @Override
    public void onEnable() {
        SimpleConfig config = getConfig();
        Bukkit.getWorlds().forEach(world -> {
            if (!config.contains(world.getName())) return;
            if (!(config.contains(world.getName() + ".Enabled") && config.getBoolean(world.getName() + ".Enabled")))
                return;
            this.worlds.add(world.getName());
            this.clearInventory.put(world.getName(), config.getBoolean(world.getName() + ".ClearInventory", false));
            this.worldChange.put(world.getName(), config.getBoolean(world.getName() + ".WorldChange", true));
            if (config.contains(world.getName() + ".Items")) {
                List<String> items = new ArrayList<>();
                config.getStringList(world.getName() + ".Items").forEach(id -> {
                    CustomItem item = HubBasics.getItemManager().get(id);
                    if (item == null) return;
                    items.add(item.getId());
                });
                this.items.put(world.getName(), items);
            }
        });
    }

    @Override
    public void onDisable() {
        this.worlds.clear();
        this.items.clear();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!this.worlds.contains(event.getPlayer().getWorld().getName())) return;
        if (!this.items.containsKey(event.getPlayer().getWorld().getName())) return;
        if (this.clearInventory.get(event.getPlayer().getWorld().getName())) {
            event.getPlayer().getInventory().clear();
        }
        this.items.get(event.getPlayer().getWorld().getName()).forEach(id -> {
            CustomItem item = HubBasics.getItemManager().get(id);
            if (item == null) return;
            if (item.getPermission() != null && !event.getPlayer().hasPermission(item.getPermission())) return;
            this.removeItem(item, event.getPlayer().getInventory());
            if (item.getSlot() == null || item.getSlot() == -1) {
                event.getPlayer().getInventory().addItem(item.toItemStack(event.getPlayer()));
            } else {
                event.getPlayer().getInventory().setItem(item.getSlot(), item.toItemStack(event.getPlayer()));
            }
        });
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        if (!this.worlds.contains(event.getPlayer().getWorld().getName())) return;
        if (!this.items.containsKey(event.getPlayer().getWorld().getName())) return;
        if (!this.worldChange.get(event.getPlayer().getWorld().getName())) return;
        if (this.clearInventory.get(event.getPlayer().getWorld().getName())) {
            event.getPlayer().getInventory().clear();
        }
        this.items.get(event.getPlayer().getWorld().getName()).forEach(id -> {
            CustomItem item = HubBasics.getItemManager().get(id);
            if (item == null) return;
            if (item.getPermission() != null && !event.getPlayer().hasPermission(item.getPermission())) return;
            this.removeItem(item, event.getPlayer().getInventory());
            if (item.getSlot() == null || item.getSlot() == -1) {
                event.getPlayer().getInventory().addItem(item.toItemStack(event.getPlayer()));
            } else {
                event.getPlayer().getInventory().setItem(item.getSlot(), item.toItemStack(event.getPlayer()));
            }
        });
    }

    private void removeItem(CustomItem item, Inventory inventory) {
        for (ItemStack content : inventory.getContents()) {
            if (content == null || (content.getType() != item.getMaterial())) {
                continue;
            }
            NBTItem nbtItem = new NBTItem(content);
            if (nbtItem.hasKey("HubBasics")
                    && item.getId().equalsIgnoreCase(nbtItem.getString("HubBasics"))) {
                inventory.remove(content);
            }
        }
    }

}
