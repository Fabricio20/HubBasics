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
	
	@Comment("If true, the Friends System Will Be Used! [WARNING: MYSQL CONNECTION REQUIRED]")
	public Boolean Friends_Enabled = false;
	
	@Comment("Friends Command Usage Message")
	public String Friends_Usage = "&7Usage: &a/friends [add/remove/online/list] <player>";
	
}
