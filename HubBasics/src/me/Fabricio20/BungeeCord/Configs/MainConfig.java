package me.Fabricio20.BungeeCord.Configs;

import java.io.File;
import java.util.ArrayList;

import me.Fabricio20.BungeeCord.Main;
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
	
	@Comment("Here you can change the permission to use the Friends System")
	public String Friends_Permission = "Friends.Use";
	
	@Comment("Friends Command Usage Message")
	public String Friends_Usage = "&7Usage: &a/friends [add/remove/list/accept/deny] <player>";
	
	@Comment("Here you can change the friends list message")
	public String Friends_List = "&aFriends: &9{friends}";
	
	@Comment("Here you can change the separator color for list and online commands!")
	public String Friends_SeparatorColor = "&e";
	
	@Comment("Here you can change the friend added message")
	public String Friends_Added = "&aFriends: &9{who} &aIs Now Your Friend!";
	
	@Comment("Here you can change the friend added message")
	public String Friends_Removed = "&aFriends: &9{who} &aIs Not Your Friend Anymore!";
	
	@Comment("Here you can change the friend request sent message")
	public String Friends_RequestSent = "&aFriends: &9{who} &aWas Asked To Be Your Friend!";
	
	@Comment("Here you can change the 'this player is not your friend' message")
	public String Friends_NotFriend = "&aFriends: &9This player is not your friend!";
	
	@Comment("Here you can change the friend request received message")
	public ArrayList<String> Friends_RequestReceived = Main.theClass.WantsToBe;
	
	@Comment("Here you can edit the already sent request message")
	public String Friends_AlreadyAsked = "&aFriends: &9You already asked that player to be your friend!";
	
	@Comment("Here you can edit the request not found message")
	public String Friends_RequestNotThere = "&aFriends: &9That player didn't ask to be your friend!";
	
	@Comment("Here you can change the request removed message")
	public String Friends_RequestRemoved = "&aFriends: &9Friend request denied!";
	
	@Comment("Here you can change the friend not found message")
	public String Friends_NotFound = "&aFriends: &cThat player has never joined the server.";
	
}
