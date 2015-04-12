package me.Fabricio20.BungeeCord.Commands;

import me.Fabricio20.BungeeCord.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {

	public AlertCommand() {
		super("alert", Main.theClass.config.Alert_Permission, "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', Main.theClass.language.Alert_Usage)));
		}
		if(args.length >= 1) {
			String myString = "";
			for(int i = 0; i < args.length; i++){
			    String arg = args[i] + " ";
			    myString = myString + arg;
			}
			String format = ChatColor.translateAlternateColorCodes('&', Main.theClass.config.Alert_Format);
			format = format.replace("{message}", myString.replace("&", "§"));
			BungeeCord.getInstance().broadcast(format);
		}
	}

}
