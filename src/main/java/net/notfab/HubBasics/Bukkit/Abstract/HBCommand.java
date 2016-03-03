package net.notfab.HubBasics.Bukkit.Abstract;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.json.JSONObject;

import com.google.common.base.Optional;

import lombok.Getter;
import lombok.Setter;
import net.notfab.HubBasics.Bukkit.HubBasics;

public abstract class HBCommand implements CommandExecutor {
	
	@Getter @Setter private Permission permission;
	@Getter @Setter private String[] names;
	
	/* -------------------------------------------------------------------- */
	
	public HBCommand(String... names) {
		this.names = names;
		this.permission = null;
	}
	
	public HBCommand(Permission perm, String... names) {
		this.permission = perm;
		this.names = names;
	}

	@Override
	@Deprecated
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			if(!arg0.hasPermission(this.permission)) {
				arg0.sendMessage("§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
				return false;
			}
			onCommand((Player) arg0, arg3);
		} else {
			onCommand((ConsoleCommandSender) arg0, arg3);
		}
		onCommand(arg0, arg3);
		return true;
	}
	
	/* ------------------------------------------------------------------------- */
	
	public abstract void onCommand(ConsoleCommandSender sender, String[] args);
	
	public abstract void onCommand(Player player, String[] args);
	
	public abstract void onCommand(CommandSender sender, String[] args);
	
	/* ------------------------------------------------------------------------- */
	
	public String Error(String s) {
		return "§cError§7: §o" + s + "§7§o.";
	}
	
	public String Success(String s) {
		return "§aSuccess§7: §o" + s + "§7§o.";
	}
	
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
        		+ "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public JSONObject getProfile(UUID uid, Optional<String> name) {
		return HubBasics.getInstance().getProfileManager().getProfile(uid, name);
	}
	
	public String argsToString(String[] args, int i) {
		String s = "";
		for(int d = i; d < args.length; d++) {
			s += " " + args[d];
		}
		return s.replaceFirst(" ", "");
	}
}