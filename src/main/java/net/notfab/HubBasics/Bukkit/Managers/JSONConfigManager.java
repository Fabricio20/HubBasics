package net.notfab.HubBasics.Bukkit.Managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import net.notfab.HubBasics.Bukkit.Abstract.EnumStorage;

public class JSONConfigManager {
	
	public JSONConfigManager() {
		createDefaultConfigs();
	}
	
	private void createDefaultConfigs() {
		File HubBasics = new File("plugins/HubBasics/Profiles");
		if(!HubBasics.exists()) {
			HubBasics.mkdirs();
		}
		//
		File redis = new File("plugins/HubBasics/redis.json");
		if(!redis.exists()) {
			writeFile(redis, new JSONObject().put("IP", "127.0.0.1")
					.put("Port", "6379")
					.put("Password", "")
					.put("Timeout", "2000")
					.put("Database", "0")
					.put("Prefix", "HubBasics:"));
		}
		//
		File mysql = new File("plugins/HubBasics/mysql.json");
		if(!mysql.exists()) {
			writeFile(mysql, new JSONObject().put("IP", "127.0.0.1")
					.put("Port", "3306")
					.put("Database", "HubBasics")
					.put("Username", "HubBasics")
					.put("Password", "pass")
					.put("TablePrefix", "HubBasics-"));
		}
		//
		File flatFile = new File("plugins/HubBasics/flatfile.json");
		if(!flatFile.exists()) {
			writeFile(flatFile, new JSONObject().put("ProfilesFolder", "Profiles"));
		}
		//
		File defaultConfig = new File("plugins/HubBasics/config.json");
		if(!defaultConfig.exists()) {
			JSONObject cfg = new JSONObject();
			cfg.put("Storage", EnumStorage.FLATFILE.name());
			cfg.put("JoinMessage", new JSONObject().put("Enabled", true).put("Message", "Welcome %player%!"));
			cfg.put("LeaveMessage", new JSONObject().put("Enabled", true).put("Message", "Goodbye %player%!"));
			cfg.put("JoinTitle", new JSONObject().put("Enabled", true).put("Title", "Welcome %player%!").put("Subtitle", "HubBasics Installed!"));
			
			JSONObject jumppads = new JSONObject();
			jumppads.put("Enabled", false);
			jumppads.put("Material", Material.SPONGE.name()); // @Sponges Is A Cool Kid
			jumppads.put("Worlds", new JSONArray().put(Bukkit.getWorlds().get(0).getName()));
			cfg.put("JumpPads", jumppads);
			
			JSONObject hub = new JSONObject();
			hub.put("Enabled", true);
			hub.put("BungeeCord", false);
			hub.put("Server", "Lobby");
			hub.put("Location", new JSONObject()
					.put("World", Bukkit.getWorlds().get(0).getName())
					.put("X", Bukkit.getWorlds().get(0).getSpawnLocation().getX())
					.put("Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY())
					.put("Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ())
					.put("Yaw", 0.0)
					.put("Pitch", 0.0));
			cfg.put("Hub", hub);
			
			writeFile(defaultConfig, cfg);
		}
		//
		File BossAnnouncer = new File("plugins/HubBasics/BossAnnouncer.json");
		if(!BossAnnouncer.exists()) {
			writeFile(BossAnnouncer, new JSONObject().put("Enabled", true)
					.put("Interval", "15")
					.put("Random", true)
					.put("Messages", new JSONArray()
							.put(new JSONObject().put("Message", "Welcome!").put("Color", BarColor.GREEN.name()).put("Style", BarStyle.SEGMENTED_20.name()))
							.put(new JSONObject().put("Message", "HubBasics Installed!").put("Color", BarColor.RED.name()).put("Style", BarStyle.SOLID.name()))));
		}
	}

	public void writeFile(File file, JSONObject content) {
		try (FileWriter fw = new FileWriter(file.getPath())) {
			if(!file.exists()) {
				file.createNewFile();
			}
			fw.write(content.toString(1));
		} catch (IOException e) {
			System.out.println("[HubBasics] Error While Saving Config Contents:");
			e.printStackTrace();
		}
	}

	public JSONObject readFile(File file) {
		try {
			JSONTokener tokener = new JSONTokener(file.toURI().toURL().openStream());
			JSONObject jsonObject = new JSONObject(tokener);
			return jsonObject;
		} catch (Exception e) {
			System.out.println("[HubBasics] Error While Reading JSON File Contents:");
			e.printStackTrace();
		}
		return new JSONObject();
	}

}
