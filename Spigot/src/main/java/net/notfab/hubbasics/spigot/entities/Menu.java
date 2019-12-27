package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Menu {

    private final String id;
    private String name;
    private int size = 27;
    private String command;
    private String permission;
    private Sound sound;

    private List<String> items = new ArrayList<>();

    public Menu(String id) {
        this.id = id;
    }

    public void addItem(String id) {
        this.items.add(id);
    }

    public void open(Player player) {
        if (this.permission != null && !player.hasPermission(this.permission)) {
            HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "NO_PERMISSION_MENU"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, size, this.name);

        this.items.forEach(id -> {
            CustomItem item = HubBasics.getInstance().getItemManager().get(id);
            if (item == null) return;
            if (item.getPermission() != null && !player.hasPermission(item.getPermission())) return;
            if (item.getSlot() == -1 || item.getSlot() >= this.size) {
                inventory.addItem(item.toItemStack(player));
            } else {
                inventory.setItem(item.getSlot(), item.toItemStack(player));
            }
        });

        player.openInventory(inventory);
        if (this.sound != null)
            player.playSound(player.getLocation(), sound, 1, 1);
    }

}