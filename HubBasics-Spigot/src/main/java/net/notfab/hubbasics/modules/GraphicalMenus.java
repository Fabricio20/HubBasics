package net.notfab.hubbasics.modules;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.objects.CommandItem;
import net.notfab.hubbasics.objects.Menu;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

import lombok.Getter;

public class GraphicalMenus extends Module {

    private HubBasics pl;
    @Getter private List<Menu> menus;

    public GraphicalMenus() {
        super(ModuleEnum.GRAPHICAL_MENUS);
        this.pl = HubBasics.getInstance();
        this.menus = new ArrayList<>();
    }

    @Override
    public void onEnable() {
        List<Map<?, ?>> mapList = pl.getPluginConfiguration().getMapList(ConfigurationKey.GRAPHICAL_MENUS_MENUS);
        this.menus.addAll(mapList.stream().map(data -> new Menu((Map<String, Object>) data)).collect(Collectors.toList()));
        HMessenger.sendDebugMessage("Found " + this.menus.size() + " menus.");
    }

    @Override
    public void onDisable() {

    }


}
