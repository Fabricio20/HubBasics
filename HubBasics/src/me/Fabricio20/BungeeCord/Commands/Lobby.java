package me.Fabricio20.BungeeCord.Commands;

import me.Fabricio20.BungeeCord.Main;
import me.Fabricio20.BungeeCord.API.CommandAPI;
import me.Fabricio20.BungeeCord.API.CommandAPI.Commands;
import me.Fabricio20.BungeeCord.API.LanguageAPI;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Lobby extends Command {
	
	private static Commands lobby = CommandAPI.Commands.Lobby;
	private static String cmd = CommandAPI.getCommand(lobby);
	private static String perm = CommandAPI.getPermission(lobby);
	private static String[] aliases = CommandAPI.getAliases(lobby);
	
	public Lobby() {
		super(cmd, perm, aliases);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ServerInfo server = BungeeCord.getInstance().getServerInfo(Main.theClass.config.Lobby_Server);
		if(server != null) {
			if(sender instanceof ProxiedPlayer) {
				ProxiedPlayer p = (ProxiedPlayer) sender;
				if(!p.getServer().getInfo().equals(server)) {
					p.connect(server);
					LanguageAPI.sendLobby_Teleporting(sender);
				} else {
					((ProxiedPlayer) sender).chat("/lobby");
				}
			} else {
				LanguageAPI.sendGeneral_OnlyPlayers(sender);
			}
		} else {
			LanguageAPI.sendGeneral_UnkownServer(sender);
		}
	}

}
