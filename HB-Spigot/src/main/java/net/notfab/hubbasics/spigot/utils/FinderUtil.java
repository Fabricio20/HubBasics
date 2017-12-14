package net.notfab.hubbasics.spigot.utils;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) HubBasics 2018.
 * <p>
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 * <p>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 * <p>
 * File Created by Fabricio20 on 13/12/2017.
 */
@SuppressWarnings("Duplicates")
public class FinderUtil {

    public static Material findMaterial(String rawQuery) {
        List<Material> found = findMaterials(rawQuery);
        return found.isEmpty() ? null : found.get(0);
    }

    public static List<Material> findMaterials(String rawQuery) {
        List<Material> exact = new ArrayList<>();
        List<Material> wrongcase = new ArrayList<>();
        List<Material> startswith = new ArrayList<>();
        List<Material> contains = new ArrayList<>();
        Arrays.asList(Material.values()).forEach(material -> {
            String name = material.name();
            if(name.equals(rawQuery))
                exact.add(material);
            else if(name.equalsIgnoreCase(rawQuery) && exact.isEmpty())
                wrongcase.add(material);
            else if(name.toLowerCase().startsWith(rawQuery.toLowerCase()) && wrongcase.isEmpty())
                startswith.add(material);
            else if(name.toLowerCase().contains(rawQuery) && startswith.isEmpty())
                contains.add(material);
        });
        if(!exact.isEmpty())
            return exact;
        if(!wrongcase.isEmpty())
            return wrongcase;
        if(!startswith.isEmpty())
            return startswith;
        return contains;
    }

    public static Enchantment findEnchantment(String rawQuery) {
        List<Enchantment> found = findEnchantments(rawQuery);
        return found.isEmpty() ? null : found.get(0);
    }

    public static List<Enchantment> findEnchantments(String rawQuery) {
        List<Enchantment> exact = new ArrayList<>();
        List<Enchantment> wrongcase = new ArrayList<>();
        List<Enchantment> startswith = new ArrayList<>();
        List<Enchantment> contains = new ArrayList<>();
        Arrays.asList(Enchantment.values()).forEach(enchantment -> {
            String name = enchantment.getName();
            if(name.equals(rawQuery))
                exact.add(enchantment);
            else if(name.equalsIgnoreCase(rawQuery) && exact.isEmpty())
                wrongcase.add(enchantment);
            else if(name.toLowerCase().startsWith(rawQuery.toLowerCase()) && wrongcase.isEmpty())
                startswith.add(enchantment);
            else if(name.toLowerCase().contains(rawQuery) && startswith.isEmpty())
                contains.add(enchantment);
        });
        if(!exact.isEmpty())
            return exact;
        if(!wrongcase.isEmpty())
            return wrongcase;
        if(!startswith.isEmpty())
            return startswith;
        return contains;
    }

    public static Sound findSound(String rawQuery) {
        List<Sound> found = findSounds(rawQuery);
        return found.isEmpty() ? null : found.get(0);
    }

    public static List<Sound> findSounds(String rawQuery) {
        List<Sound> exact = new ArrayList<>();
        List<Sound> wrongcase = new ArrayList<>();
        List<Sound> startswith = new ArrayList<>();
        List<Sound> contains = new ArrayList<>();
        Arrays.asList(Sound.values()).forEach(sound -> {
            String name = sound.name();
            if(name.equals(rawQuery))
                exact.add(sound);
            else if(name.equalsIgnoreCase(rawQuery) && exact.isEmpty())
                wrongcase.add(sound);
            else if(name.toLowerCase().startsWith(rawQuery.toLowerCase()) && wrongcase.isEmpty())
                startswith.add(sound);
            else if(name.toLowerCase().contains(rawQuery) && startswith.isEmpty())
                contains.add(sound);
        });
        if(!exact.isEmpty())
            return exact;
        if(!wrongcase.isEmpty())
            return wrongcase;
        if(!startswith.isEmpty())
            return startswith;
        return contains;
    }

}
