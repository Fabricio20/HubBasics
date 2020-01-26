package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.EnumModules;
import net.notfab.hubbasics.spigot.entities.Module;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ModuleRegistry {

    private Map<EnumModules, List<Module>> modules = new HashMap<>();

    ModuleRegistry() {
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.AntiVoid());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.DoubleJump());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.Holograms());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.JoinItems());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.JoinMessages());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.JoinTP());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.LobbyModule());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.NoWeather());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.KeepHealth());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_7.KeepFood());

        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_9.JumpPads());

        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_10.KeepHealth());
        this.addModule(new net.notfab.hubbasics.spigot.modules.v1_10.BossAnnouncer());
    }

    private void addModule(Module module) {
        this.addModule(module.getModule(), module);
    }

    private void addModule(EnumModules moduleName, Module module) {
        this.modules.compute(moduleName, (key, value) -> {
            if (value == null) {
                value = new ArrayList<>();
            }
            value.add(module);
            return value;
        });
    }

    Module findModule(EnumModules module, CraftBukkitVersion version) {
        return this.modules.getOrDefault(module, new ArrayList<>()).stream()
                .filter(m -> version.isNewer(m.getMinimumVersion()))
                .findFirst()
                .orElse(null);
    }

}