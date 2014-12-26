package me.Fabricio20.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinListenerForItems implements Listener {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final JavaPlugin plugin;

	public JoinListenerForItems(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	

	public static File f2 = new File("plugins/HubBasics/Items.yml");
	public static YamlConfiguration items = YamlConfiguration.loadConfiguration(f2);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@EventHandler
	public void PlayerJoinGetItem(PlayerJoinEvent e) {
		 if(plugin.getConfig().getBoolean("Others.JoinItems") == true) {
////////////////////////////////////////////////////////////////////////////////////////- Start Of 1
			if(items.getBoolean("Item1.Enabled") == true) {
				ItemStack is1 = new ItemStack(Material.getMaterial(items.getString("Item1.Material")));
				ItemMeta meta1 = is1.getItemMeta();
				meta1.setDisplayName(items.getString("Item1.Name").replace("&", "§"));
				List<String> list1 = items.getStringList("Item1.Lore");
				List<String> newList1 = new ArrayList<String>();
				for (String string : list1) {
					newList1.add(string.replace("&", "§"));
				}
				meta1.setLore(newList1);
				is1.setItemMeta(meta1);
				e.getPlayer().getInventory().setItem(0, is1);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 1 & Start Of 2
			if(items.getBoolean("Item2.Enabled") == true) {
				ItemStack is2 = new ItemStack(Material.getMaterial(items.getString("Item2.Material")));
				ItemMeta meta2 = is2.getItemMeta();
				meta2.setDisplayName(items.getString("Item2.Name").replace("&", "§"));
				List<String> list2 = items.getStringList("Item2.Lore");
				List<String> newList2 = new ArrayList<String>();
				for (String string : list2) {
					newList2.add(string.replace("&", "§"));
				}
				meta2.setLore(newList2);
				is2.setItemMeta(meta2);
				e.getPlayer().getInventory().setItem(1, is2);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 2 & Start Of 3
			if(items.getBoolean("Item3.Enabled") == true) {
				ItemStack is3 = new ItemStack(Material.getMaterial(items.getString("Item3.Material")));
				ItemMeta meta3 = is3.getItemMeta();
				meta3.setDisplayName(items.getString("Item3.Name").replace("&", "§"));
				List<String> list3 = items.getStringList("Item3.Lore");
				List<String> newList3 = new ArrayList<String>();
				for (String string : list3) {
					newList3.add(string.replace("&", "§"));
				}
				meta3.setLore(newList3);
				is3.setItemMeta(meta3);
				e.getPlayer().getInventory().setItem(2, is3);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 3 & Start Of 4
			if(items.getBoolean("Item4.Enabled") == true) {
				ItemStack is4 = new ItemStack(Material.getMaterial(items.getString("Item4.Material")));
				ItemMeta meta4 = is4.getItemMeta();
				meta4.setDisplayName(items.getString("Item4.Name").replace("&", "§"));
				List<String> list4 = items.getStringList("Item4.Lore");
				List<String> newList4 = new ArrayList<String>();
				for (String string : list4) {
					newList4.add(string.replace("&", "§"));
				}
				meta4.setLore(newList4);
				is4.setItemMeta(meta4);
				e.getPlayer().getInventory().setItem(3, is4);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 4 & Start Of 5
			if(items.getBoolean("Item5.Enabled") == true) {
				ItemStack is5 = new ItemStack(Material.getMaterial(items.getString("Item5.Material")));
				ItemMeta meta5 = is5.getItemMeta();
				meta5.setDisplayName(items.getString("Item5.Name").replace("&", "§"));
				List<String> list5 = items.getStringList("Item5.Lore");
				List<String> newList5 = new ArrayList<String>();
				for (String string : list5) {
					newList5.add(string.replace("&", "§"));
				}
				meta5.setLore(newList5);
				is5.setItemMeta(meta5);
				e.getPlayer().getInventory().setItem(4, is5);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 5 & Start Of 6
			if(items.getBoolean("Item6.Enabled") == true) {
				ItemStack is6 = new ItemStack(Material.getMaterial(items.getString("Item6.Material")));
				ItemMeta meta6 = is6.getItemMeta();
				meta6.setDisplayName(items.getString("Item6.Name").replace("&", "§"));
				List<String> list6 = items.getStringList("Item6.Lore");
				List<String> newList6 = new ArrayList<String>();
				for (String string : list6) {
					newList6.add(string.replace("&", "§"));
				}
				meta6.setLore(newList6);
				is6.setItemMeta(meta6);
				e.getPlayer().getInventory().setItem(5, is6);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 6 & Start Of 7
			if(items.getBoolean("Item7.Enabled") == true) {
				ItemStack is7 = new ItemStack(Material.getMaterial(items.getString("Item7.Material")));
				ItemMeta meta7 = is7.getItemMeta();
				meta7.setDisplayName(items.getString("Item7.Name").replace("&", "§"));
				List<String> list7 = items.getStringList("Item7.Lore");
				List<String> newList7 = new ArrayList<String>();
				for (String string : list7) {
					newList7.add(string.replace("&", "§"));
				}
				meta7.setLore(newList7);
				is7.setItemMeta(meta7);
				e.getPlayer().getInventory().setItem(6, is7);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 7 & Start Of 8
			if(items.getBoolean("Item8.Enabled") == true) {
				ItemStack is8 = new ItemStack(Material.getMaterial(items.getString("Item8.Material")));
				ItemMeta meta8 = is8.getItemMeta();
				meta8.setDisplayName(items.getString("Item8.Name").replace("&", "§"));
				List<String> list8 = items.getStringList("Item8.Lore");
				List<String> newList8 = new ArrayList<String>();
				for (String string : list8) {
					newList8.add(string.replace("&", "§"));
				}
				meta8.setLore(newList8);
				is8.setItemMeta(meta8);
				e.getPlayer().getInventory().setItem(7, is8);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 8 & Start Of 9
			if(items.getBoolean("Item9") == true) {
				ItemStack is9 = new ItemStack(Material.getMaterial(items.getString("Item9.Material")));
				ItemMeta meta9 = is9.getItemMeta();
				meta9.setDisplayName(items.getString("Item9.Name").replace("&", "§"));
				List<String> list9 = items.getStringList("Item9.Lore");
				List<String> newList9 = new ArrayList<String>();
				for (String string : list9) {
					newList9.add(string.replace("&", "§"));
				}
				meta9.setLore(newList9);
				is9.setItemMeta(meta9);
				e.getPlayer().getInventory().setItem(8, is9);
			}
////////////////////////////////////////////////////////////////////////////////////////- End Of 9
		}
	}

}
