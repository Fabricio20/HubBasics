package net.notfab.hubbasics.modules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.json.JSONObject;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

public class CommandOverride extends Module {
    private final String COMMAND = "command";
    private final String ALIASES = "aliases";
    private final String PERMISSION = "permission";
    private final String MESSAGE = "message";

    private Map<String, String> messages;
    private Map<String, String> permissions;

    public CommandOverride() {
        super(ModuleEnum.COMMAND_OVERRIDE);
        this.messages = new HashMap<>();
        this.permissions = new HashMap<>();
    }

    @Override
    public void onEnable() {
        List<String> rawCommands = getConfig().getStringList(ConfigurationKey.COMMAND_OVERRIDE_COMMANDS);
        for (String string : rawCommands) {
            JSONObject object = new JSONObject(string);
            String command = object.getString(COMMAND).toLowerCase();
            String message = ChatColor.translateAlternateColorCodes('&', object.getString(MESSAGE));
            String permission = object.getString(PERMISSION).toLowerCase();
            this.messages.put(command, message);
            this.permissions.put(command, permission);
            if (object.has(ALIASES)) {
                String[] aliases = object.getString(ALIASES).split(",");
                Arrays.stream(aliases).forEach(s -> {
                    this.messages.put(s, message);
                    this.permissions.put(s, permission);
                });
            }

            HMessenger.sendDebugMessage("Registered command override for command \"" + command + "\".");
        }
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].toLowerCase();
        if (this.messages.containsKey(command)) {
            if (!event.getPlayer().hasPermission(this.permissions.get(command))) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(this.messages.get(command));
            }
        }
    }
}
