package net.notfab.hubbasics.spigot.entities;

import net.notfab.hubbasics.spigot.HubBasics;

public abstract class Manager {

    public HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();

    public abstract void onDisable();

}