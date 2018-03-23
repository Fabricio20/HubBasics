package net.notfab.hubbasics.spigot.managers;

import com.jcabi.manifests.Manifests;
import lombok.Getter;
import net.notfab.hubbasics.spigot.entities.Manager;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UpdateManager extends Manager implements Runnable {

    @Getter private String latest = null;

    public UpdateManager() {}

    @Override
    public void onDisable() {}

    public String getVersion() {
        return Manifests.read("HubBasics-Version");
    }

    public String getBuild() {
        return Manifests.read("HubBasics-Build");
    }

    public Boolean isUpdateAvailable() {
        return this.latest != null;
    }

    @Override
    public void run() {
        Request request = new Request.Builder().url("https://api.notfab.net/version").get()
                .addHeader("Software", "HubBasics")
                .addHeader("Build", this.getBuild())
                .addHeader("Version", this.getVersion())
                .build();
        try {
            Response response = HubBasics.getHttpClient().newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody body = response.body();
                if(body != null) {
                    JSONObject object = new JSONObject(body.string());
                    if(object.has("error")) {
                        Logger.warn("Error while checking for updates - " + object.getString("error"));
                        return;
                    }
                    if(object.getBoolean("available")) {
                        this.latest = String.valueOf(object.getInt("latest"));
                    }
                }
            }
        } catch (IOException | JSONException e) {
            Logger.warn("Error while checking for updates", e);
        }
    }

}
