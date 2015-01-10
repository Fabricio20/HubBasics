package me.Fabricio20;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Strings {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int MOTD;
	public static int ChatAnnIndex;
	public static int BossAnnIndex;
	public static int BossAnnIndexJoin;
	public static int RunnablesEnabled;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<Player> MagicClockActive = new ArrayList<Player>();
	public static String Prefix;
	public static String LaunchPadBlock;
	public static String StackerEnabled = Main.theClass.getPlugin().getConfig().getString("Others.StackerEnabledMessage").replace("&", "§");
	public static String StackerDisabled = Main.theClass.getPlugin().getConfig().getString("Others.StackerDisabledMessage").replace("&", "§");
	public static String PermissionError = "§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.";
	
	public static Boolean test = true;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
