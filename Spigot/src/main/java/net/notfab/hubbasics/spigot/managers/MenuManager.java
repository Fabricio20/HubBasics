package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.Command;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Menu;
import net.notfab.hubbasics.spigot.entities.Result;
import net.notfab.hubbasics.spigot.utils.FinderUtil;
import net.notfab.hubbasics.spigot.utils.Messages;
import net.notfab.spigot.simpleconfig.SimpleConfig;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuManager extends Manager {

    private static final HBLogger logger = HBLogger.getLogger(MenuManager.class);
    private Map<String, Menu> menuMap;

    public MenuManager() {
        this.menuMap = new HashMap<>();
        this.onEnable();
    }

    public void onEnable() {
        this.menuMap.clear();
        File folder = new File(HubBasics.getDataFolder(), "menus/");
        if (!folder.exists()) return;
        File[] listFiles = folder.listFiles();
        if (listFiles == null) return;
        List<File> files = Arrays.asList(listFiles);
        files.forEach(file -> {
            SimpleConfig config = HubBasics.getConfigManager().getNewConfig("menus/" + file.getName());
            Result result = this.read(config);
            if (!result.isSuccess()) {
                logger.warn("Error while loading menu: " + config.getName() + " - " + Messages.get(result.getExtra(0)));
            } else {
                Menu menu = result.getExtra(0);
                this.menuMap.put(menu.getId(), menu);
                logger.debug("Loaded menu " + menu.getId());
            }
        });
        logger.info("Loaded " + this.menuMap.size() + " menu(s).");
    }

    @Override
    public void onDisable() {
        this.menuMap.clear();
    }

    public Menu get(String id) {
        return this.menuMap.get(id);
    }

    private Result read(SimpleConfig config) {
        Menu menu = new Menu(config.getName().replace(".yml", ""));
        if (config.contains("Size")) {
            int size = config.getInt("Size");
            if (size <= 0 || size > 54 || (size % 9 != 0)) return new Result(false, "INVALID_SIZE");
            menu.setSize(size);
        }
        if (config.contains("Name")) {
            menu.setName(ChatColor.translateAlternateColorCodes('&', config.getString("Name")));
        }
        if (config.contains("Command")) {
            HubBasics.getCommandFramework().register(new Command(config.getString("Command")) {
                @Override
                public void execute(CommandSender sender, String[] args) {
                    if (sender instanceof ConsoleCommandSender) {
                        HubBasics.getMessenger().send(sender, Messages.get(sender, "COMMAND_NO_CONSOLE"));
                        return;
                    }
                    Player player = (Player) sender;
                    Menu m = HubBasics.getMenuManager().get(menu.getId());
                    if (m != null)
                        m.open(player);
                    else
                        HubBasics.getMessenger().send(player, "Something happened while executing that HubBasics command.");
                }
            });
        }
        if (config.contains("Permission")) {
            menu.setPermission(config.getString("Permission"));
        }
        if (config.contains("Sound")) {
            Sound sound = FinderUtil.findSound(config.getString("Sound"));
            if (sound == null) return new Result(false, "INVALID_SOUND");
            menu.setSound(sound);
        }
        if (config.contains("Items")) {
            config.getStringList("Items").forEach(item -> {
                if (HubBasics.getItemManager().get(item) == null) return;
                menu.addItem(item);
            });
        }
        return new Result(menu);
    }

}