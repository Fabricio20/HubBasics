package net.notfab.hubbasics.modules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.abstracts.module.Module;
import net.notfab.hubbasics.abstracts.module.ModuleEnum;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;

public class AdvancedMOTD extends Module {

	private List<String> motds;
	private HubBasics pl;
	private int current;
	private boolean enabled;

	public AdvancedMOTD() {
		super(ModuleEnum.ADVANCED_MOTD);
		this.motds = new ArrayList<>();
		this.pl = HubBasics.getInstance();
		this.current = 0;
	}

	@EventHandler
	public void onPlayerPing(ServerListPingEvent event) {
		if (!enabled) return;
		event.setMotd(this.motds.get(this.current));
	}

	@Override
	public void onEnable() {
		this.enabled = getBoolean(ConfigurationKey.ADVANCED_MOTD_ENABLED);
		if (!enabled) return;
		List<String> rawMotds = pl.getPluginConfiguration().getStringList(ConfigurationKey.ADVANCED_MOTD_MOTDS);
		rawMotds.stream().forEach(s -> this.motds.add(ChatColor.translateAlternateColorCodes('&', s)));
		int switchRate = pl.getPluginConfiguration().getInt(ConfigurationKey.ADVANCED_MOTD_SWITCHRATE)*20;

		if (this.motds.size() > 1) Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, () -> {
			if (this.current + 1 == motds.size()) {
				this.current = 0;
			} else {
				this.current++;
			}
		}, switchRate, switchRate);
	}

	@Override
	public void onDisable() {

	}
}
