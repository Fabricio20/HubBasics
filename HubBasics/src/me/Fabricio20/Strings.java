package me.Fabricio20;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Strings {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @author Fabricio20
	 * @since 2014
	 * 
	 * Referent Integers And Stuff
	 * 
	 */
	
	public static int MOTD;
	public static int ChatAnnIndex;
	public static int BossAnnIndex;
	public static int BossAnnIndexJoin;
	public static int RunnablesEnabled;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @author Fabricio20
	 * @since 2014
	 * 
	 * Referent Strings And Stuff
	 * 
	 */
	
	public static ArrayList<Player> MagicClockActive = new ArrayList<Player>();
	public static String Prefix;
	public static String LaunchPadBlock;
	public static String StackerEnabled = Main.theClass.getPlugin().getConfig().getString("Others.StackerEnabledMessage").replace("&", "§");
	public static String StackerDisabled = Main.theClass.getPlugin().getConfig().getString("Others.StackerDisabledMessage").replace("&", "§");
	public static String TabHeader = Main.theClass.getPlugin().getConfig().getString("TabList.Header").replace("&", "§");
	public static String TabFooter = Main.theClass.getPlugin().getConfig().getString("TabList.Footer").replace("&", "§");
	public final static String PermissionError = "§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.";
	public final static String Version = "3.1.0.0";
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
