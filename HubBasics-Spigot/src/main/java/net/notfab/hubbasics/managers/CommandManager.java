
package net.notfab.hubbasics.managers;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.HCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandManager {

    @Getter private List<HCommand> Commands = new ArrayList<>();
    
    public CommandManager() {
        //
    }
    
    public void registerCommand(HCommand cmd, JavaPlugin plugin) {
        if(cmd.getNames().length > 1) {
            List<String> names = new LinkedList<>(Arrays.asList(cmd.getNames()));
            PluginCommand pcmd = plugin.getCommand(names.get(0));
            names.remove(0);
            pcmd.setAliases(names);
            pcmd.setExecutor(cmd);
        } else {
            plugin.getCommand(cmd.getNames()[0]).setExecutor(cmd);
        }
        Commands.add(cmd);
    }

}
