package net.notfab.hubbasics.spigot.objects;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.Map;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.Setter;

public class CommandItem extends CustomItem implements ConfigurationSerializable {
    private final ItemInteractionHandler handler = new ItemInteractionHandler() {
        @Override
        public Boolean onInventoryClick(Player player, ItemStack stack, InventoryAction action, Inventory inv) {
            if (getClickCommand() != null) executeCommand(isClickConsoleExecutor() ? Bukkit.getConsoleSender() : player, getClickCommand());
            return getClickCommand() != null;
        }

        @Override
        public Boolean onInteract(Player player, ItemStack stack, Action action, Block block) {
            switch (action) {
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    if (getInteractCommand() != null) executeCommand(isInteractConsoleExecutor() ? Bukkit.getConsoleSender() : player, getInteractCommand());
                    break;
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                    // Can be added if requested
                    break;
                default:
                    // Ignore
                    break;
            }
            return getInteractCommand() != null;
        }

        @Override
        public Boolean onDrop(Player player, ItemStack stack) {
            return !isDroppable();
        }
    };

    @Getter @Setter private String clickCommand = null;
    @Getter @Setter private boolean clickConsoleExecutor = false;
    @Getter @Setter private String interactCommand = null;
    @Getter @Setter private boolean interactConsoleExecutor = false;
    @Getter @Setter private boolean droppable = true;

    public CommandItem() {
        super(null, null, (byte) 0);
        super.setItemInteractionHandler(this.handler);
    }

    public CommandItem(ItemStack stack) {
        super(stack);
        super.setItemInteractionHandler(this.handler);
    }

    @Override
    public Map<String, Object> serialize() {
        return ItemUtils.serialize(this);
    }

    private void executeCommand(CommandSender sender, String command) {
        String[] cmd = command.split(" ");
        switch (cmd[0].toUpperCase()) {
            case "SERVER":
                if (!(sender instanceof Player) || cmd.length < 2) return;
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(cmd[1]);
                ((Player) sender).sendPluginMessage(HubBasics.getInstance(), "BungeeCord", out.toByteArray());
                break;
            default:
                Bukkit.dispatchCommand(sender, command);
                break;
        }
    }
}
