package net.notfab.hubbasics.spigot.listeners.v1_7;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.nms.nbt.NBTBackend;
import net.notfab.hubbasics.spigot.utils.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

@SuppressWarnings("Duplicates")
public class ItemListener implements Listener {

    private final NBTBackend nbtBackend;
    private final Method getItemInHandMethod;

    public ItemListener(NBTBackend nbtBackend) {
        this.nbtBackend = nbtBackend;
        this.getItemInHandMethod = ReflectionUtils.findMethod(Player.class, "getItemInHand");
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemMove(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) {
            return;
        } else if (!this.nbtBackend.hasKey(item, "HubBasics")) {
            return;
        }
        CustomItem custom = HubBasics.getInstance().getItemManager()
                .get(this.nbtBackend.getString(item, "HubBasics"));
        if (custom == null) {
            item.setType(Material.AIR);
            return;
        }
        if (custom.getRunOnInventory()) {
            custom.onCommand((Player) event.getWhoClicked());
        }
        if (!custom.getAllowMove()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.getType() == Material.AIR) {
            return;
        } else if (!this.nbtBackend.hasKey(item, "HubBasics")) {
            return;
        }
        CustomItem custom = HubBasics.getInstance().getItemManager()
                .get(this.nbtBackend.getString(item, "HubBasics"));
        if (custom == null) {
            item.setType(Material.AIR);
            return;
        }
        if (!custom.getAllowDrop()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onLeftClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        ItemStack item = ReflectionUtils.invokeMethod(event.getPlayer(), this.getItemInHandMethod);
        if (item != null && item.getType() != Material.AIR) {
            if (!this.nbtBackend.hasKey(item, "HubBasics")) {
                return;
            }
            event.setCancelled(true);
            CustomItem custom = HubBasics.getInstance().getItemManager()
                    .get(this.nbtBackend.getString(item, "HubBasics"));
            if (custom == null) {
                item.setType(Material.AIR);
                return;
            } else if (!custom.getRunOnLeftClick()) {
                return;
            }
            custom.onCommand(event.getPlayer());
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        ItemStack item = ReflectionUtils.invokeMethod(event.getPlayer(), this.getItemInHandMethod);
        if (item != null && item.getType() != Material.AIR) {
            if (!this.nbtBackend.hasKey(item, "HubBasics")) {
                return;
            }
            event.setCancelled(true);
            CustomItem custom = HubBasics.getInstance().getItemManager()
                    .get(this.nbtBackend.getString(item, "HubBasics"));
            if (custom == null) {
                item.setType(Material.AIR);
                return;
            } else if (!custom.getRunOnRightClick()) {
                return;
            }
            custom.onCommand(event.getPlayer());
        }
    }

}
