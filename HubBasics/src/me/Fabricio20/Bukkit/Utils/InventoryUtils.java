package me.Fabricio20.Bukkit.Utils;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfig;

import org.bukkit.Material;

public class InventoryUtils {
	
	@SuppressWarnings("deprecation")
	public static Material parseMaterial(String location, SimpleConfig config) {
		Material material = Material.AIR;
		if(config.contains(location)) {
			try {
				material = Material.getMaterial(config.getInt(location));
			} catch(IllegalArgumentException e) {
				try {
					material = Material.getMaterial(config.getString(location));
				} catch(IllegalArgumentException ex) {
					material = Material.BEDROCK;
				}
			}
		}
		return material;
	}
	
	public static Boolean parseBoolean(String location, SimpleConfig config) {
		Boolean bol = false;
		if(config.contains(location)) {
			bol = config.getBoolean(location);
		}
		return bol;
	}
	
	public static Integer parseInteger(String location, SimpleConfig config) {
		Integer integer = 1;
		if(config.contains(location)) {
			integer = config.getInt(location);
		} else {
			integer = 1;
		}
		return integer;
	}
	
	public static String parseString(String location, SimpleConfig config) {
		String string = "";
		if(config.contains(location)) {
			string = config.getString(location).replace("&", "§");
		} else {
			string = "";
		}
		return string;
	}
	
	public static List<String> parseList(String location, SimpleConfig config) {
		List<String> list = new ArrayList<String>();
		if(config.contains(location)) {
			list = config.getStringList(location);
			for(String s: list) {
				list.remove(s);
				list.add(s.replace("&", "§"));
			}
		}
		return list;
	}
	
}
