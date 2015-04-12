package me.Fabricio20.Bukkit.Listeners.Player;

import java.util.Set;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Methods.ModuleManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class JoinListenerForTags implements Listener {
	
	ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.setScoreboard(board);
		if(ModuleManager.theClass.isEnabled("Tags")) {
			Set<String> config = Main.theClass.Tags.getConfigurationSection("TabList").getKeys(false);
			if(config.contains(e.getPlayer().getName())) {
				String prefix = "";
				String suffix = "";
				if(Main.theClass.Tags.getString("TabList." + player.getName() + ".Prefix") != null) {
					prefix = Main.theClass.Tags.getString("TabList." + player.getName() + ".Prefix").replace("&", "§");
				}
				if(Main.theClass.Tags.getString("TabList." + player.getName() + ".Suffix") != null) {
					suffix = Main.theClass.Tags.getString("TabList." + player.getName() + ".Suffix".replace("&", "§"));
				}
				createTeam(player, prefix, suffix);
			}
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		Team team = board.getPlayerTeam(player);
		if(team != null) {
			team.unregister();
		}
	}
	
	void createTeam(Player player, String prefix, String suffix) {
    	if(!player.getScoreboard().getTeams().contains(player.getName())) {
    	    Team team = board.registerNewTeam(player.getName());
    	    team.setPrefix(prefix);
    	    team.setSuffix(suffix);
    	    player.setScoreboard(board);
    	    if(!team.hasPlayer(player)) {
    			team.addPlayer(player);
    		}
    	}
    }
	
}
