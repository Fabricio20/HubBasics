package me.Fabricio20.BungeeCord.Listeners;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PostLoginEvent e) {
		if(e.getPlayer().hasPermission("HubBasics.ReceiveUpdate")) {
			e.getPlayer().sendMessage(new TextComponent("§9HubBasics > §eThere's an update aliable!"));
		}
	}
	
}
