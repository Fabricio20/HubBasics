package me.Fabricio20.BungeeCord.Commands;

import java.util.ArrayList;

import me.Fabricio20.BungeeCord.Main;
import me.Fabricio20.BungeeCord.API.FriendsAPI;
import me.Fabricio20.BungeeCord.API.LanguageAPI;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FriendsCommand extends Command {
	
	public FriendsCommand() {
		super("friends", Main.theClass.config.Friends_Permission, "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			LanguageAPI.sendFriends_Usage(sender);
		}
		if(args.length == 1) {
			String separator = ChatColor.translateAlternateColorCodes('&', Main.theClass.language.Friends_SeparatorColor);
			if(args[0].equalsIgnoreCase("list")) {
				ArrayList<String> now = new ArrayList<String>();
				for(ProxiedPlayer player: BungeeCord.getInstance().getPlayers()) {
					now.add(player.getName());
				}
				ArrayList<String> Online = new ArrayList<String>();
				ArrayList<String> Offline = new ArrayList<String>();
				for(String s: FriendsAPI.getFriends(sender.getName())) {
					if(now.contains(s)) {
						Online.add(s);
					} else {
						Offline.add(s);
					}
				}
				String msg = ChatColor.translateAlternateColorCodes('&', Main.theClass.language.Friends_List);
				TextComponent tC = new TextComponent(msg);
				int max = (Online.size() + Offline.size());
				int index = 0;
				for(String s: Online) {
					index++;
					if(index == max) {
						TextComponent other = new TextComponent("§a" + s);
						other.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, LanguageAPI.HoverOnline(s)));
						other.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friends follow " + s));
						tC.addExtra(other);
						TextComponent dot = new TextComponent(separator + ".");
						tC.addExtra(dot);
					} else {
						TextComponent other = new TextComponent("§a" + s);
						other.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, LanguageAPI.HoverOnline(s)));
						other.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friends follow " + s));
						tC.addExtra(other);
						TextComponent dot = new TextComponent(separator + ", ");
						tC.addExtra(dot);
					}
				}
				for(String s: Offline) {
					index++;
					if(index == max) {
						TextComponent other = new TextComponent("§c" + s);
						other.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, LanguageAPI.HoverOffline()));
						tC.addExtra(other);
						TextComponent dot = new TextComponent(separator + ".");
						tC.addExtra(dot);
					} else {
						TextComponent other = new TextComponent("§c" + s);
						other.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, LanguageAPI.HoverOffline()));
						tC.addExtra(other);
						TextComponent dot = new TextComponent(separator + ", ");
						tC.addExtra(dot);
					}
				}
				sender.sendMessage(tC);
			} else {
				LanguageAPI.sendFriends_Usage(sender);
			}
		}
		if(args.length >= 2) {
			if(args[0].equalsIgnoreCase("add")) {
				String who = args[1];
				if(FriendsAPI.friendsContains(who)) {
					if(!FriendsAPI.requestsContains(sender.getName(), who)) {
						if(!who.toLowerCase().equals(sender.getName().toLowerCase())) {
							FriendsAPI.addRequest(sender.getName(), who);
							ProxiedPlayer other = BungeeCord.getInstance().getPlayer(who);
							if(other != null) {
								for(String s: Main.theClass.language.Friends_RequestReceived) {
									s = s.replace("{who}", sender.getName());
									s = ChatColor.translateAlternateColorCodes('&', s);
									other.sendMessage(new TextComponent(s));
								}
							}
							LanguageAPI.sendFriends_RequestSent(sender);
						} else {
							LanguageAPI.sendFriends_CantAddYourSelf(sender);
						}
					} else {
						LanguageAPI.sendFriends_AlreadyAsked(sender);
					}
				} else {
					LanguageAPI.sendFriends_NotFound(sender);
				}
			} else if(args[0].equalsIgnoreCase("deny")) {
				String who = args[1];
				if(FriendsAPI.friendsContains(who)) {
					if(FriendsAPI.requestsContains(sender.getName(), who)) {
						FriendsAPI.removeRequest(sender.getName(), who);
						LanguageAPI.sendFriends_RequestRemoved(sender);
					} else {
						LanguageAPI.sendFriends_RequestNotThere(sender);
					}
				} else {
					LanguageAPI.sendFriends_NotFound(sender);
				}
			} else if(args[0].equalsIgnoreCase("accept")) {
				String who = args[1];
				if(FriendsAPI.friendsContains(who)) {
					if(FriendsAPI.requestsContains(sender.getName(), who)) {
						FriendsAPI.removeRequest(sender.getName(), who);
						FriendsAPI.addFriend(sender.getName(), who);
						ProxiedPlayer other = BungeeCord.getInstance().getPlayer(who);
						if(other != null) {
							LanguageAPI.sendFriends_Added(other, sender.getName());
						}
						LanguageAPI.sendFriends_Added(sender, who);
					}
				} else {
					LanguageAPI.sendFriends_NotFound(sender);
				}
			} else if(args[0].equalsIgnoreCase("remove")) {
				String who = args[1];
				if(FriendsAPI.friendsContains(who)) {
					if(FriendsAPI.areFriends(sender.getName(), who)) {
						FriendsAPI.removeFriend(sender.getName(), who);
						ProxiedPlayer other = BungeeCord.getInstance().getPlayer(who);
						if(other != null) {
							LanguageAPI.sendFriends_Removed(other, sender.getName());
						}
						LanguageAPI.sendFriends_Removed(sender, who);
					} else {
						LanguageAPI.sendFriends_NotFriend(sender);
					}
				} else {
					LanguageAPI.sendFriends_NotFound(sender);
				}
			} else if(args[0].equalsIgnoreCase("follow")) {
				if(!(sender instanceof ProxiedPlayer)) {
					LanguageAPI.sendGeneral_OnlyPlayers(sender);
				} else {
					ArrayList<String> servers = Main.theClass.config.Friends_AllowedServers;
					String who = args[1];
					ProxiedPlayer player = BungeeCord.getInstance().getPlayer(who);
					if(player != null) {
						ServerInfo info = player.getServer().getInfo();
						if(servers.contains(info.getName())) {
							ProxiedPlayer x = (ProxiedPlayer) sender;
							x.connect(info);
						} else {
							LanguageAPI.sendFriends_CannotFollow(sender);
						}
					} else {
						LanguageAPI.sendFriends_NotFound(sender);
					}
				}
			} else {
				LanguageAPI.sendFriends_Usage(sender);
			}
		}
	}
	
}
