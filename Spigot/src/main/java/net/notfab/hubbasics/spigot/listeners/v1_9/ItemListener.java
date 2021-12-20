package net.notfab.hubbasics.spigot.listeners.v1_9;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.nms.nbt.NBTBackend;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("Duplicates")
public class ItemListener implements Listener {

    private final NBTBackend nbtBackend;

    public ItemListener(NBTBackend nbtBackend) {
        this.nbtBackend = nbtBackend;
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
        ItemStack item;
        if (event.getHand() == EquipmentSlot.HAND) {
            item = event.getPlayer().getInventory().getItemInMainHand();
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            item = event.getPlayer().getInventory().getItemInOffHand();
        } else {
            return;
        }
        if (item.getType() != Material.AIR) {
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
            if (event.getHand() == EquipmentSlot.OFF_HAND) {
                if (custom.getRunOnOffhand()) {
                    custom.onCommand(event.getPlayer());
                }
            } else {
                custom.onCommand(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        ItemStack item;
        if (event.getHand() == EquipmentSlot.HAND) {
            item = event.getPlayer().getInventory().getItemInMainHand();
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            item = event.getPlayer().getInventory().getItemInOffHand();
        } else {
            return;
        }
        if (item.getType() != Material.AIR) {
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
            if (event.getHand() == EquipmentSlot.OFF_HAND) {
                if (custom.getRunOnOffhand()) {
                    custom.onCommand(event.getPlayer());
                }
            } else {
                custom.onCommand(event.getPlayer());
            }
        }
    }

}
