package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.CustomItem;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Result;
import net.notfab.hubbasics.spigot.listeners.ItemListener;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import net.notfab.hubbasics.spigot.utils.Messages;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.util.*;

public class ItemManager extends Manager {

    private static final HBLogger logger = HBLogger.getLogger(ItemManager.class);
    private Map<String, CustomItem> items = new HashMap<>();
    private ItemListener itemListener = new ItemListener();

    public ItemManager() {
        this.onEnable();
    }

    @Override
    public void onDisable() {
        this.items.clear();
        InventoryClickEvent.getHandlerList().unregister(itemListener);
        PlayerDropItemEvent.getHandlerList().unregister(itemListener);
        PlayerInteractEvent.getHandlerList().unregister(itemListener);
    }

    public void onEnable() {
        this.items.clear();
        Bukkit.getPluginManager().registerEvents(this.itemListener, HubBasics);
        File folder = new File(HubBasics.getDataFolder(), "items/");
        if (!folder.exists()) return;
        File[] listFiles = folder.listFiles();
        if (listFiles == null) return;
        List<File> files = Arrays.asList(listFiles);
        files.forEach(file -> {
            SimpleConfig config = HubBasics.getConfigManager().getNewConfig("items/" + file.getName());
            Result result = this.read(config);
            if (!result.isSuccess()) {
                logger.warn("Error while loading custom item: " + config.getName() + " - " + Messages.get(result.getExtra(0)));
            } else {
                CustomItem item = result.getExtra(0);
                this.items.put(item.getId().toLowerCase(), item);
                logger.debug("Loaded item " + item.getId());
            }
        });
        logger.info("Loaded " + this.items.size() + " item(s).");
    }

    public CustomItem get(String id) {
        return this.items.get(id.toLowerCase());
    }

    private Result read(SimpleConfig config) {
        CustomItem item = new CustomItem(config.getName().replace(".yml", ""));
        if (config.contains("Material")) {
            Material material = FinderUtil.findMaterial(config.getString("Material"));
            if (material == null)
                return new Result(false, "INVALID_MATERIAL");
            item.setMaterial(material);
        }
        if (config.contains("Amount")) {
            item.setAmount(config.getInt("Amount"));
        }
        if (config.contains("Permission")) {
            item.setPermission(config.getString("Permission"));
        }
        if (config.contains("Name")) {
            item.setName(ChatColor.translateAlternateColorCodes('&', config.getString("Name")));
        }
        if (config.contains("Description")) {
            List<String> description = new ArrayList<>();
            config.getStringList("Description").forEach(text -> description.add(ChatColor.translateAlternateColorCodes('&', text)));
            item.setDescription(description);
        }
        if (config.contains("Flags")) {
            List<ItemFlag> flags = new ArrayList<>();
            config.getStringList("Flags").forEach(text -> {
                try {
                    ItemFlag flag = ItemFlag.valueOf(text);
                    flags.add(flag);
                } catch (IllegalArgumentException ignored) {
                }
            });
            item.setItemFlags(flags);
        }
        if (config.contains("Enchantments")) {
            List<String> enchantments = config.getStringList("Enchantments");
            enchantments.forEach(text -> {
                try {
                    String name = text.split(":")[0];
                    int level = Integer.parseInt(text.split(":")[1]);
                    Enchantment enchantment = FinderUtil.findEnchantment(name);
                    if (enchantment == null)
                        return;
                    item.addEnchantment(enchantment, level);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ignored) {
                }
            });
        }
        if (config.contains("Unbreakable")) {
            item.setUnbreakable(config.getBoolean("Unbreakable"));
        }
        if (config.contains("Durability")) {
            item.setDurability(Integer.valueOf(config.getInt("Durability")).shortValue());
        }
        if (config.contains("AllowDrop")) {
            item.setAllowDrop(config.getBoolean("AllowDrop"));
        }
        if (config.contains("AllowMove")) {
            item.setAllowMove(config.getBoolean("AllowMove"));
        }
        if (config.contains("Slot")) {
            item.setSlot(config.getInt("Slot"));
        }
        if (config.contains("Commands")) {
            item.setCommands(config.getStringList("Commands"));
        }
        if (config.contains("RunCommands")) {
            item.setRunOnOffhand(config.getBoolean("RunCommands.AllowOffhand", false));
            item.setRunOnInventory(config.getBoolean("RunCommands.Inventory", false));
            item.setRunOnLeftClick(config.getBoolean("RunCommands.LeftClick", false));
            item.setRunOnRightClick(config.getBoolean("RunCommands.RightClick", false));
        }
        return new Result(item);
    }

}