package me.Fabricio20.BungeeCord.Configs;

import java.io.File;
import java.util.ArrayList;

import me.Fabricio20.BungeeCord.FixConfig;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class MainConfig extends Config {
	
	public MainConfig(Plugin plugin) {
		CONFIG_HEADER = new String[]{"HubBasics Main Configuration File", "Change With Caution"};
	    CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
	}
	
	@Comment("If true, list commands will be enabled")
	public Boolean List_Enabled = true;
	
	@Comment("Change here how the /list commands looks like when used")
	public String List_Command = "&6Players Online&8: &7{onlineplayers}&6/&7{maxplayers}";
	
	//
	
	@Comment("If true, the Friends System Will Be Used! [WARNING: MYSQL CONNECTION REQUIRED]")
	public Boolean Friends_Enabled = false;
	
	@Comment("Permission to use the friends system [Leave empty for no perm]")
	public String Friends_Permission = "Friends.Use";
	
	@Comment("Here you can set the main command for the friends system!")
	public String Friends_Command = "friends";
	
	@Comment("Here you can set aliases for the friends command")
	public ArrayList<String> Friends_Aliases = new ArrayList<String>();
	
	@Comment("Here you can change the servers that the player is allowed to be followed")
	public ArrayList<String> Friends_AllowedServers = FixConfig.Friends_AllowedServers();
	
	//
	
	@Comment("If true, alert commands will be enabled")
	public Boolean Alert_Enabled = true;
	
	@Comment("Permission to use the alert command [Leave empty for no perm]")
	public String Alert_Permission = "Alert.Send";
	
	@Comment("Here you can set the main command for the alerts system!")
	public String Alert_Command = "alert";
	
	@Comment("Here you can set aliases for the alert command")
	public ArrayList<String> Alert_Aliases = new ArrayList<String>();
	
	//
	
	@Comment("If true, lobby commands will be enabled")
	public Boolean Lobby_Enabled = true;
	
	@Comment("Here is your lobby server, case sensitive")
	public String Lobby_Server = "lobby";
	
	@Comment("Permission to use the lobby command [Leave empty for no perm]")
	public String Lobby_Permission = "";
	
	@Comment("Here you can set the main command for the lobby system!")
	public String Lobby_Command = "lobby";
	
	@Comment("Here you can set aliases for the lobby command")
	public ArrayList<String> Lobby_Aliases = new ArrayList<String>();
	
	//
	
}
