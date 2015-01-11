package me.Fabricio20.API;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;

public class HoverAPI {
	
	/**
	 * 
	 * @param player
	 * @param message
	 * @param bold
	 * @param strike
	 * @param underline
	 * @param hoverMessage
	 * @param hMessage
	 * @param clickLink
	 * @param cLink
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildComplete(Player player, String message, boolean bold, boolean strike, boolean underline, boolean hoverMessage, String hMessage, boolean clickLink, String cLink) {
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setColor( ChatColor.RED );
		msg.setBold(bold);
		msg.setStrikethrough(strike);
		msg.setUnderlined(underline);
		if(hoverMessage) {
			msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hMessage).create() ) );
		}
		if(clickLink) {
			msg.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, cLink ) );
		}
		return msg;
	}
	
	/**
	 * 
	 * @param player
	 * @param msgNormal
	 * @param message
	 * @param link
	 * @param hover
	 * @param msgNormal2
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildTextLinkText(Player player, String msgNormal, String message, String link, String hover, String msgNormal2) {
		TextComponent msg1 = new TextComponent( msgNormal.replace("%p", player.getName()) );
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, link ) );
		msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create() ) );
		msg1.addExtra(msg);
		TextComponent msg2 = new TextComponent( msgNormal2.replace("%p", player.getName()) );
		msg1.addExtra(msg2);
		msg.setColor( ChatColor.RED );
		return msg1;
	}
	
	/**
	 * 
	 * @param player
	 * @param msgNormal
	 * @param message
	 * @param command
	 * @param hover
	 * @param msgNormal2
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildTextCommandText(Player player, String msgNormal, String message, String command, String hover, String msgNormal2) {
		TextComponent msg1 = new TextComponent( msgNormal.replace("%p", player.getName()) );
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, command ) );
		msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create() ) );
		msg1.addExtra(msg);
		TextComponent msg2 = new TextComponent( msgNormal2.replace("%p", player.getName()) );
		msg1.addExtra(msg2);
		msg.setColor( ChatColor.RED );
		return msg1;
	}
	
	/**
	 * 
	 * @param player
	 * @param msgNormal
	 * @param message
	 * @param link
	 * @param hover
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildTextLink(Player player, String msgNormal, String message, String link, String hover) {
		TextComponent msg1 = new TextComponent( msgNormal.replace("%p", player.getName()) );
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, link ) );
		msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create() ) );
		msg1.addExtra(msg);
		msg.setColor( ChatColor.RED );
		return msg1;
	}
	
	/**
	 * 
	 * @param player
	 * @param msgNormal
	 * @param message
	 * @param command
	 * @param hover
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildTextCommand(Player player, String msgNormal, String message, String command, String hover) {
		TextComponent msg1 = new TextComponent( msgNormal.replace("%p", player.getName()) );
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, command ) );
		msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create() ) );
		msg1.addExtra(msg);
		msg.setColor( ChatColor.RED );
		return msg1;
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 * @param hover
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildText(Player player, String message, String hover) {
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create() ) );
		msg.setColor( ChatColor.RED );
		return msg;
	}
	
	/**
	 * 
	 * @param player
	 * @param msgNormal
	 * @param message
	 * @param hover
	 * @return TextComponent
	 * 
	 * - Method used to send a message to a Player;
	 * 
	 */
	
	public static TextComponent buildTextText(Player player, String msgNormal, String message, String hover) {
		TextComponent msg1 = new TextComponent( msgNormal.replace("%p", player.getName()) );
		TextComponent msg = new TextComponent( message.replace("%p", player.getName()) );
		msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create() ) );
		msg1.addExtra(msg);
		msg.setColor( ChatColor.RED );
		return msg1;
	}
	
	
}
