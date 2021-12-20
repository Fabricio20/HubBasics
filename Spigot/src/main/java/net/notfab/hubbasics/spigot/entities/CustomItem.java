package net.notfab.hubbasics.spigot.entities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import lombok.Setter;
import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.nms.nbt.NBTBackend;
import net.notfab.hubbasics.spigot.utils.Messages;
import net.notfab.hubbasics.spigot.utils.PlaceHolderUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CustomItem {

    private final String id;
    private String name = null;
    private Material material = Material.EGG;
    private int amount = 1;
    private List<String> description = new ArrayList<>();
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private boolean unbreakable = false;
    private List<ItemFlag> itemFlags = new ArrayList<>();
    private short durability = 0;
    private String permission = null;
    private List<String> commands = new ArrayList<>();
    private Boolean allowDrop = true;
    private Boolean allowMove = true;
    private Integer slot = -1; // -1 is add instead of set
    private Boolean runOnInventory = false; // Run commands on Inventory click
    private Boolean runOnRightClick = false; // Run commands on Right click
    private Boolean runOnLeftClick = false; // Run commands on Left click
    private Boolean runOnOffhand = false; // Run commands on offhand?

    public CustomItem(String id) {
        this.id = id;
    }

    public void addEnchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
    }

    public ItemStack toItemStack(Player player) {
        ItemStack stack = new ItemStack(this.material);
        stack.setAmount(this.amount);

        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(PlaceHolderUtil.replace(player, this.name));

            List<String> lore = new ArrayList<>();
            this.description.forEach(text -> lore.add(PlaceHolderUtil.replace(player, text)));
            meta.setLore(lore);

            this.enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));
            this.itemFlags.forEach(meta::addItemFlags);

            stack.setItemMeta(meta);
        }
        stack.setDurability(this.durability);

        NBTBackend backend = HubBasics.getInstance().getItemManager().getNbtBackend();
        stack = backend.setString(stack, "HubBasics", this.getId());
        stack = backend.setBoolean(stack, "Unbreakable", this.unbreakable); // 1.10 Hack
        return stack;
    }

    public void onCommand(Player player) {
        if (this.permission != null && !player.hasPermission(this.permission)) {
            HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "NO_PERMISSION_ITEM"));
            return;
        }
        this.commands.forEach(rawCommand -> {
            String operator = rawCommand.contains(":") ? rawCommand.split(":")[0] : "nolmao";
            String command = rawCommand.contains(":") ? rawCommand.split(":")[1] : rawCommand;
            command = PlaceHolderUtil.replace(player, command);
            if (operator.equalsIgnoreCase("op")) {
                boolean op = player.isOp();
                player.setOp(true);
                try {
                    player.chat("/" + command);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    player.setOp(op);
                }
            } else if (operator.equalsIgnoreCase("console")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            } else if (operator.equalsIgnoreCase("msg")) {
                HubBasics.getInstance().getMessenger().send(player, command);
            } else if (operator.equalsIgnoreCase("warp")) {
                HLocation location = HubBasics.getInstance().getLocationManager().get(command);
                if (location == null)
                    HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "INVALID_WARP"));
                else
                    location.teleport(player);
            } else if (operator.equalsIgnoreCase("server")) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(command);
                player.sendPluginMessage(HubBasics.getInstance(), "BungeeCord", out.toByteArray());
            } else if (operator.equalsIgnoreCase("open")) {
                Menu menu = HubBasics.getInstance().getMenuManager().get(command);
                if (menu == null)
                    HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "INVALID_MENU"));
                else
                    menu.open(player);
            } else if (operator.equalsIgnoreCase("item")) {
                CustomItem item = HubBasics.getInstance().getItemManager().get(command);
                if (item == null) {
                    HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "ITEM_NOT_FOUND"));
                    return;
                }
                if (item.getPermission() != null && !player.hasPermission(item.getPermission())) {
                    HubBasics.getInstance().getMessenger().send(player, Messages.get(player, "NO_PERMISSION_ITEM"));
                    return;
                }
                if (item.getSlot() == null || item.getSlot() == -1) {
                    player.getInventory().addItem(item.toItemStack(player));
                } else {
                    player.getInventory().setItem(item.getSlot(), item.toItemStack(player));
                }
            } else {
                player.chat("/" + command);
            }
        });
    }

}
