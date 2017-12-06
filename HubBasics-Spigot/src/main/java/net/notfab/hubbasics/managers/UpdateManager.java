package net.notfab.hubbasics.managers;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.messages.HMessenger;

public class UpdateManager {

    private final String version;
    public UpdateManager() {
        version = HubBasics.getInstance().getDescription().getVersion();

    }

    public boolean hasUpdate() {
        try {
            Document doc = Jsoup.connect("http://notfab.net/_Updater").cookie("Software", "HubBasics").get();
            if(doc != null) {
                try {
                    JSONObject o = new JSONObject(doc.text());
                    if(o.has("Version")) {
                        return !o.getString("Version").equalsIgnoreCase(this.version);
                    } else {
                        return false;
                    }
                } catch (JSONException ex) {}
            }
        } catch (IOException ignored) {
            HMessenger.notifyAdmins("Failed to fetch for updates.");
        }
        return false;
    }

}
