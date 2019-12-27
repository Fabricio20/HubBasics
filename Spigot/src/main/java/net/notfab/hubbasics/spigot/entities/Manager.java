package net.notfab.hubbasics.spigot.entities;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.managers.Logger;

public abstract class Manager {

    public HubBasics HubBasics = net.notfab.hubbasics.spigot.HubBasics.getInstance();
    public Logger Logger = HubBasics.getLoggerManager();

    public abstract void onDisable();

}