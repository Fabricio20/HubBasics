package net.notfab.HubBasics.Bukkit.Listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

import net.notfab.HubBasics.Bukkit.HubBasics;

public class MovementListener implements Listener {

	private List<Player> jumpers = new ArrayList<Player>();

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		JSONObject config = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/config.json"));
		if(config.getJSONObject("JumpPads").getBoolean("Enabled")) {
			JSONObject pads = config.getJSONObject("JumpPads");
			Material mat;
			try {
				mat = Material.valueOf(pads.getString("Material"));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
				return;
			}
			List<String> worlds = new ArrayList<String>();
			JSONArray w = config.getJSONArray("Worlds");
			for(int i = 0; i < w.length(); i++) {
				worlds.add(w.getString(i));
			}
			if(worlds.contains(e.getTo().getWorld().getName())) {
				if(e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(mat)) {
					if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && !e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
						e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
						e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
						jumpers.add(e.getPlayer());
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL && jumpers.contains(p)) {
				e.setDamage(0.0);
				jumpers.remove(p);
			}
		}
	}

}
