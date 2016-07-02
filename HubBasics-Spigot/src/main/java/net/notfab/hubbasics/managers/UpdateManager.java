package net.notfab.hubbasics.managers;

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
        if(this.getHasUpdate()) {
            HMessenger.notifyAdmins("There is an update available!");
        }
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
