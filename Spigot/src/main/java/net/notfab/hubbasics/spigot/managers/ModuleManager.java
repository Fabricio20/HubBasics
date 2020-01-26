package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ModuleManager extends Manager {

    private static final HBLogger logger = HBLogger.getLogger(ModuleManager.class);
    private Map<EnumModules, Module> modules = new HashMap<>();

    public ModuleManager(CraftBukkitVersion version) {
        ModuleRegistry registry = new ModuleRegistry();

        for (EnumModules moduleName : EnumModules.values()) {
            Module module = registry.findModule(moduleName, version);
            if (module == null) {
                logger.warn("The " + moduleName.name() + " module requires at least version " +
                        moduleName.getMinimumVersion().getName() + ", which means it will not be enabled.");
                continue;
            }
            this.modules.put(moduleName, module);
        }

        this.onEnable();
    }

    public void onEnable() {
        AtomicInteger loaded = new AtomicInteger();
        this.modules.forEach((enumModule, module) -> {
            if (!module.getConfig().getBoolean("Enabled", true)) return;
            try {
                module.onEnable();
                Bukkit.getPluginManager().registerEvents(module, HubBasics);
            } catch (Exception ex) {
                logger.error("Failed to start module " + enumModule.name(), ex);
                return;
            }
            loaded.getAndIncrement();
            logger.debug(enumModule.name() + " is now enabled.");
        });
        logger.info("Loaded " + loaded.get() + "/" + EnumModules.values().length + " module(s).");
    }

    @Override
    public void onDisable() {
        this.modules.forEach(((enumModules, module) -> {
            module.onDisable();
            HandlerList.unregisterAll(module);
        }));
    }

    public Module getModule(EnumModules module) {
        return this.modules.get(module);
    }

    public Boolean isEnabled(EnumModules module) {
        return getModule(module) != null ? getModule(module).isEnabled() : false;
    }

}