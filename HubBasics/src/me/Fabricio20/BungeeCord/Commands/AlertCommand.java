package me.Fabricio20.BungeeCord.Commands;

import me.Fabricio20.BungeeCord.Main;
import me.Fabricio20.BungeeCord.API.CommandAPI;
import me.Fabricio20.BungeeCord.API.CommandAPI.Commands;
import me.Fabricio20.BungeeCord.API.LanguageAPI;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {
	
	private static Commands alert = CommandAPI.Commands.Alert;
	private static String cmd = CommandAPI.getCommand(alert);
	private static String perm = CommandAPI.getPermission(alert);
	private static String[] aliases = CommandAPI.getAliases(alert);
	
	public AlertCommand() {
		super(cmd, perm, aliases);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			LanguageAPI.sendAlert_Usage(sender);
		}
		if(args.length >= 1) {
			String myString = "";
			for(int i = 0; i < args.length; i++){
			    String arg = args[i] + " ";
			    myString = myString + arg;
			}
			String format = ChatColor.translateAlternateColorCodes('&', Main.theClass.language.Alert_Format);
			format = format.replace("{message}", myString.replace("&", "§"));
			BungeeCord.getInstance().broadcast(format);
			try {
				Class.forName("net.md_5.bungee.api.Title");
				net.md_5.bungee.api.Title title = BungeeCord.getInstance().createTitle();
				title.title(new ComponentBuilder("§c ").create());
				title.subTitle(new ComponentBuilder("§c" + myString.replace("&", "§")).create());
				for(ProxiedPlayer players: BungeeCord.getInstance().getPlayers()) {
					title.send(players);
				}
			} catch(ClassNotFoundException ex) {
				return;
			}
		}
	}

}
