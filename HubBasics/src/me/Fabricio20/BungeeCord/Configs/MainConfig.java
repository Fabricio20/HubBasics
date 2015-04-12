package me.Fabricio20.BungeeCord.Configs;

import java.io.File;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class MainConfig extends Config {
	
	public MainConfig(Plugin plugin) {
		CONFIG_HEADER = new String[]{"HubBasics Main Configuration File", "Change With Caution"};
	    CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
	}
	
	@Comment("If true, the custom /list command will be used")
	public Boolean List_Enabled = true;
	
	@Comment("Change here how the /list commands looks like when used")
	public String List_Command = "&6Players Online&8: &7{onlineplayers}&6/&7{maxplayers}";
	
	//
	
	@Comment("If true, the Friends System Will Be Used! [WARNING: MYSQL CONNECTION REQUIRED]")
	public Boolean Friends_Enabled = false;
	
	@Comment("Here you can change the permission to use the Friends System")
	public String Friends_Permission = "Friends.Use";
	
	//
	
	@Comment("If true, Alert command will be enabled")
	public Boolean Alert_Enabled = true;
	
	@Comment("Here you can change the command permission.")
	public String Alert_Permission = "Alert.Send";
	
	@Comment("Here you can change the appearence of the Alert system")
	public String Alert_Format = "&7[&cALERT&7]: &c{message}";
	
}
