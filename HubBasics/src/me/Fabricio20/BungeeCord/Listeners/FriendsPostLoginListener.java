package me.Fabricio20.BungeeCord.Listeners;

import java.util.List;

import me.Fabricio20.BungeeCord.API.FriendsAPI;
import me.Fabricio20.BungeeCord.API.LanguageAPI;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class FriendsPostLoginListener implements Listener {
	
	@EventHandler
	public void onPostFriends(PostLoginEvent e) {
		ProxiedPlayer player = e.getPlayer();
		
		FriendsAPI.getFriends(player.getName());
		FriendsAPI.getRequests(player.getName());
		
		for(String s: FriendsAPI.Cache_Friends.keySet()) {
			List<String> friends = FriendsAPI.Cache_Friends.get(s);
			if(friends.contains(player.getName())) {
				if(BungeeCord.getInstance().getPlayer(s) != null) {
					LanguageAPI.sendFriends_Joined(BungeeCord.getInstance().getPlayer(s), player.getName());
				}
			}
		}
	}
	
}
