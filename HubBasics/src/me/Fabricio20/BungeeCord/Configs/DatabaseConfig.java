package me.Fabricio20.BungeeCord.Configs;

import java.io.File;

import net.cubespace.Yamler.Config.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class DatabaseConfig extends Config {
	
	public DatabaseConfig(Plugin plugin) {
		CONFIG_HEADER = new String[]{"HubBasics Database Configuration File", "Change With Caution"};
	    CONFIG_FILE = new File(plugin.getDataFolder(), "db.yml");
	}
	
	public String MySQL_Ip = "localhost";
	
	public String MySQL_Port = "3306";
	
	public String MySQL_Database = "HubBasics";
	
	public String MySQL_Username = "user";
	
	public String MySQL_Password = "password";
	
}
