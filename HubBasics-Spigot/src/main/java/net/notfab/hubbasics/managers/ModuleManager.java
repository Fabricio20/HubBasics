package net.notfab.hubbasics.managers;

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.AntiVoid;
import net.notfab.hubbasics.modules.DoubleJump;
import net.notfab.hubbasics.modules.FixedWeather;
import net.notfab.hubbasics.modules.JumpPads;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Iterator;

public class ModuleManager {

    @Getter private HashMap<ModuleEnum, Module> moduleMap = new HashMap<>();

    public ModuleManager() {
        moduleMap.put(ModuleEnum.DOUBLE_JUMP, new DoubleJump());
        moduleMap.put(ModuleEnum.JUMP_PADS, new JumpPads());
        moduleMap.put(ModuleEnum.ANTI_VOID, new AntiVoid());
        moduleMap.put(ModuleEnum.FIXED_WEATHER, new FixedWeather());
        registerListeners();
        onEnable();
    }

    public void onEnable() {
        this.moduleMap.values().forEach(Module::onEnableInternal);
    }

    public void onDisable() {
        this.moduleMap.values().forEach(Module::onDisableInternal);
    }

    private void registerListeners() {
        this.moduleMap.values().forEach(module -> Bukkit.getPluginManager().registerEvents(module, HubBasics.getInstance()));
    }

    public Module getModule(ModuleEnum moduleEnum) {
        return this.moduleMap.get(moduleEnum);
    }

    public void registerModule(ModuleEnum moduleEnum, Module module) {
        this.moduleMap.put(moduleEnum, module);
    }

}
