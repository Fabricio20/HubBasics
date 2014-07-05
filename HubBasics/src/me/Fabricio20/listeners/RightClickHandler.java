package me.Fabricio20.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RightClickHandler implements Listener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public RightClickHandler(JavaPlugin plugin) {
		this.plugin = plugin;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				 Sign sign = (Sign) e.getClickedBlock().getState();
				 if(sign.getLine(0).equals("§1[Website]")){
					 e.getPlayer().sendMessage(plugin.getConfig().getString("WebSiteMessage").replace("&", "§"));
				 }
				 if(sign.getLine(0).equals("§1[TS3]")) {
					 e.getPlayer().sendMessage(plugin.getConfig().getString("TS3Message").replace("&", "§"));
				 }
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
