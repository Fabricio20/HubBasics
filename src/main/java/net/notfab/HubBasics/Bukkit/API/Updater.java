package net.notfab.HubBasics.Bukkit.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import net.notfab.HubBasics.Bukkit.HubBasics;

public class Updater {
	
	/*
	 * DISCLAIMER ABOUT THE NEW UPDATER:
	 * - It's pretty easy to know that the updater here can run OS-WIDE commands and file management
	 * but that is just part of the updating process, I SWEAR that I will NOT do sketchy things with it.
	 * Those actions are sometimes needed in case I add something that can really break HubBasics and asking for everyone to "remove it"
	 * doesn't seem like an option, since it's not everyone who reads update notes, and then I get billions of reports asking for help
	 * on that matter.
	 * 
	 * - How it works and what does it send?
	 * Basically it just connects to my Website, sending OS Version (For Statistics), Bukkit Version (For Debug/Bug Fixing) and the current
	 * running version, the ID variable means it's HubBasics, and not any other of my plugins (I'll have more soon ;).
	 * The reply is simply the update process, a JSONObject containing the new Version, the Update Notes, the Update Alert Message
	 * the update Commands (If any), and the Download URL.
	 * 
	 */
	
	private BukkitTask updater;
	
	private final String _Version = "4.0";
	private JSONObject _Update;
	private List<JSONObject> _LaterCommands = new ArrayList<JSONObject>();
	
	public Updater() {
		this.updater = Bukkit.getScheduler().runTaskTimer(HubBasics.getInstance(), new CheckUpdate(), 18000, 18000);
	}
	
	public void Stop() {
		if(updater != null) {
			updater.cancel();
			updater = null;
		}
	}
	
	public JSONObject getUpdate() {
		return this._Update;
	}
	
	public Boolean Update() {
		if(_Update == null) {
			Bukkit.getLogger().log(Level.SEVERE, "[Updater] Tried To Start An Update Without Update Info.");
			return false;
		}
		try {
			Bukkit.getLogger().log(Level.INFO, "[Updater] Downloading HubBasics Update...");
			//TODO: Download File From URL
			Jsoup.connect(_Update.getString("DownloadURL")).get();
		} catch (IOException ex) {
			Bukkit.getLogger().log(Level.SEVERE, "[Updater] Error While Updating -> '" + ex.toString() + ":" + ex.getMessage() + "'");
			return false;
		}
		Bukkit.getLogger().log(Level.INFO, "[Updater] HubBasics Update Downloaded And Installed, Now Running Post-Install Procedures...");
		if(_Update.has("Commands")) {
			JSONArray a = _Update.getJSONArray("Commands");
			for(int i = 0; i < a.length(); i++) {
				JSONObject o = a.getJSONObject(i);
				if(o.getBoolean("System")) {
					try {
						Process p = Runtime.getRuntime().exec(o.getString("RawCommand"));
						p.waitFor();
					} catch (IOException | InterruptedException ex) {
						Bukkit.getLogger().log(Level.SEVERE, "[Updater] Error While Updating -> '" + ex.toString() + ":" + ex.getMessage() + "'");
						return false;
					}
				} else {
					_LaterCommands.add(o);
				}
			}
		}
		return true;
	}
	
	public void CheckUpdate() {
		JSONObject _SoftwareInfo = new JSONObject();
		_SoftwareInfo.put("ID", 0);
		_SoftwareInfo.put("Version", _Version);
		_SoftwareInfo.put("ServerVersion", Bukkit.getServer().getVersion()); // Bukkit Version ?
		_SoftwareInfo.put("OS", System.getProperties().get("os.name"));
		try {
			String r = Jsoup.connect("http://updater.notfab.net").data("SoftwareInfo", _SoftwareInfo.toString()).ignoreContentType(true).post().text();
			JSONObject o = new JSONObject(r);
			if(o.has("error")) {
				Bukkit.getLogger().log(Level.WARNING, "[Updater] Update Server Returned An Error -> '" + o.getString("error") + "'");
				return;
			}
			_Update = new JSONObject();
			_Update.put("Updated", o.getBoolean("Updated"));
			_Update.put("Version", o.getString("Version"));
			_Update.put("ChangeLog", o.getString("ChangeLog"));
			_Update.put("Message", o.getString("Message"));
			_Update.put("DownloadURL", o.getString("DownloadURL"));
			if(_Update.has("Commands")) {
				_Update.put("Commands", o.getJSONArray("Commands"));
			}
		} catch (IOException ex) {
			Bukkit.getLogger().log(Level.WARNING, "[Updater] Error While Checking For Updates.");
		}
	}
	
	private class CheckUpdate implements Runnable {
		@Override
		public void run() { CheckUpdate(); }
	}
	
}
