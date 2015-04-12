package me.Fabricio20.BungeeCord.Commands;

import me.Fabricio20.BungeeCord.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class ListCommand extends Command {

	public ListCommand() {
		super("list", "", "glist");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		String fromConfig = Main.theClass.config.List_Command;
		fromConfig = fromConfig.replace("&", "§");
		fromConfig = fromConfig.replace("{onlineplayers}", BungeeCord.getInstance().getOnlineCount() + "");
		fromConfig = fromConfig.replace("{maxplayers}", BungeeCord.getInstance().getConfig().getPlayerLimit() + "");
		fromConfig = fromConfig.replace("{playername}", sender.getName());
		sender.sendMessage(new ComponentBuilder(fromConfig).create());
	}
	
}
