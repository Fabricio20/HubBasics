package net.notfab.hubbasics.managers;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.messages.HMessenger;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class UpdateManager {

    private final String version;
    @Getter private Boolean hasUpdate;

    public UpdateManager() {
        version = HubBasics.getInstance().getDescription().getVersion();
        this.hasUpdate = hasUpdate();
        if(this.getHasUpdate()) HMessenger.notifyUpdate();
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
        } catch (IOException ex) {
            HMessenger.printStackTrace(ex);
        }
        return false;
    }

}
