package me.Fabricio20.BungeeCord.Configs;

import java.io.File;
import java.util.ArrayList;

import me.Fabricio20.BungeeCord.Main;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class LanguageFile extends Config {
	
	public LanguageFile(Plugin plugin) {
		CONFIG_HEADER = new String[]{"HubBasics Language File", "Change With Caution"};
	    CONFIG_FILE = new File(plugin.getDataFolder(), "Language.yml");
	}
	
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
