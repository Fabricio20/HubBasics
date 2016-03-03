package net.notfab.HubBasics.Bukkit.Managers;

import java.io.File;
import java.util.UUID;

import org.json.JSONObject;

import com.google.common.base.Optional;

import lombok.Getter;
import net.notfab.HubBasics.Bukkit.HubBasics;
import net.notfab.HubBasics.Bukkit.Abstract.EnumStorage;
import net.notfab.HubBasics.Bukkit.Abstract.SQLConnector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ProfileManager {
	
	private EnumStorage _StorageType;
	
	@Getter private File ProfilesFolder;
	
	@Getter private SQLConnector SQLConnector;
	@Getter private final String SQL_PREFIX;
	
	@Getter private JedisPool JedisPool;
	@Getter private final String REDIS_PREFIX;
	
	public ProfileManager() {
		JSONObject config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
		try {
			this._StorageType = EnumStorage.valueOf(config.getString("Storage").toUpperCase());
		} catch (IllegalArgumentException ex) {
			System.out.println("[HubBasics] [FATAL ERROR] Invalid Storage Type " + config.getString("Storage") + " at config.json");
			this.SQL_PREFIX = "";
			this.REDIS_PREFIX = "";
			HubBasics.getInstance().getPluginLoader().disablePlugin(HubBasics.getInstance());
			return;
		}
		if(_StorageType.equals(EnumStorage.FLATFILE)) {
			JSONObject _FlatFileConfig = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/flatfile.json"));
			this.ProfilesFolder = new File(_FlatFileConfig.getString("ProfilesFolder"));
			this.SQL_PREFIX = "";
			this.REDIS_PREFIX = "";
		} else if(_StorageType.equals(EnumStorage.MYSQL)) {
			JSONObject _SQLConfig = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/mysql.json"));
			this.SQLConnector = new SQLConnector("HubBasics",
					_SQLConfig.getString("IP"), _SQLConfig.getString("Port"),
					_SQLConfig.getString("Username"), _SQLConfig.getString("Password"), _SQLConfig.getString("Database"));
			this.SQL_PREFIX = _SQLConfig.getString("TablePrefix");
			this.REDIS_PREFIX = "";
		} else {
			JSONObject _RedisConfig = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/redis.json"));
			this.JedisPool = new JedisPool(new JedisPoolConfig(), 
					_RedisConfig.getString("IP"), _RedisConfig.getInt("Port"),
					_RedisConfig.getInt("Timeout"), _RedisConfig.getString("Password"),
					_RedisConfig.getInt("Database"));
			this.REDIS_PREFIX = _RedisConfig.getString("Prefix");
			this.SQL_PREFIX = "";
		}
	}
	
	public JSONObject getProfile(UUID uid, Optional<String> name) {
		if(_StorageType.equals(EnumStorage.FLATFILE)) {
			if(!hasProfile(uid)) {
				createProfile(uid, name.get());
			}
			File file = new File("plugins/HubBasics/" + this.ProfilesFolder + "/" + uid.toString() + ".json");
			return HubBasics.getInstance().getConfigManager().readFile(file);
		} else if(_StorageType.equals(EnumStorage.MYSQL)) {
			return null; //TODO
		} else {
			if(hasProfile(uid)) {
				Jedis jedis = JedisPool.getResource();
				String s = jedis.get(this.REDIS_PREFIX + "Profile:" + uid.toString());
				jedis.close();
				return new JSONObject(s);
			} else if(name.isPresent()) {
				createProfile(uid, name.get());
				return getProfile(uid, name);
			} else {
				return new JSONObject().put("error", "Cannot Find User Profile");
			}
		}
	}
	
	public Boolean hasProfile(UUID uid) {
		if(_StorageType.equals(EnumStorage.FLATFILE)) {
			File file = new File("plugins/HubBasics/" + this.ProfilesFolder + "/" + uid.toString() + ".json");
			return file.exists();
		} else if(_StorageType.equals(EnumStorage.MYSQL)) {
			return false; //TODO
		} else {
			Jedis jedis = JedisPool.getResource();
			String s = jedis.get(this.REDIS_PREFIX + "Profile:" + uid.toString());
			jedis.close();
			return s != null;
		}
	}
	
	private void createProfile(UUID uid, String name) {
		JSONObject _Profile = new JSONObject();
		if(_StorageType.equals(EnumStorage.FLATFILE)) {
			File file = new File("plugins/HubBasics/" + this.ProfilesFolder + "/" + uid.toString() + ".json");
			HubBasics.getInstance().getConfigManager().writeFile(file, _Profile);
		} else if(_StorageType.equals(EnumStorage.MYSQL)) {
			
		} else {
			Jedis jedis = JedisPool.getResource();
			jedis.set(this.REDIS_PREFIX + "Profile:" + uid.toString(), _Profile.toString());
			jedis.close();
		}
	}
	
}
