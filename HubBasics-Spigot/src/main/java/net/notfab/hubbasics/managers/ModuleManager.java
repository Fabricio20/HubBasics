package net.notfab.hubbasics.managers;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.DoubleJump;
import net.notfab.hubbasics.modules.JumpPads;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Iterator;

public class ModuleManager {

    @Getter private HashMap<ModuleEnum, Module> moduleMap = new HashMap<>();

    public ModuleManager() {
        moduleMap.put(ModuleEnum.DOUBLE_JUMP, new DoubleJump());
        moduleMap.put(ModuleEnum.JUMP_PADS, new JumpPads());
        registerListeners();
    }

    private void registerListeners() {
        for(Iterator<Module> iterator = this.moduleMap.values().iterator(); iterator.hasNext();) {
            Module module = iterator.next();
            Bukkit.getPluginManager().registerEvents(module, HubBasics.getInstance());
        }
    }

    public Module getModule(ModuleEnum moduleEnum) {
        return this.moduleMap.get(moduleEnum);
    }

    public void registerModule(ModuleEnum moduleEnum, Module module) {
        this.moduleMap.put(moduleEnum, module);
    }

}
