package net.notfab.hubbasics.managers;

import lombok.Getter;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.modules.DoubleJump;

import java.util.HashMap;

public class ModuleManager {

    @Getter private HashMap<ModuleEnum, Module> moduleMap = new HashMap<>();

    public ModuleManager() {
        moduleMap.put(ModuleEnum.DOUBLE_JUMP, new DoubleJump());
    }

    public Module getModule(ModuleEnum moduleEnum) {
        return this.moduleMap.get(moduleEnum);
    }

    public void registerModule(ModuleEnum moduleEnum, Module module) {
        this.moduleMap.put(moduleEnum, module);
    }

}
