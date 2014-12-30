package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Book {
	
	public static JavaPlugin plugin;

	public Book(JavaPlugin plugin) {
		Book.plugin = plugin;
	}
	
	private static String Author = "";
	private static String Name = "";
	private static List<String> Lore = new ArrayList<String>();
	private static List<String> newLore = new ArrayList<String>();
	private static List<String> Pages = new ArrayList<String>();
	private static List<String> newPages = new ArrayList<String>();
	
/* ----------------------------------------------------------------------------------------------------------------- */
	
	public static ItemStack build() {
		fixStrings();
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta Meta = (BookMeta) book.getItemMeta();
		Meta.setAuthor(Author);
		Meta.setDisplayName(Name);
		Meta.setLore(newLore);
		for(String line : newPages) {
			Meta.addPage(line);
		}
		book.setItemMeta(Meta);
		return book;
	}
	
	private static void fixStrings() {
		Author = plugin.getConfig().getString("BookSystem.Author");
		Name = plugin.getConfig().getString("BookSystem.Name").replace("&", "§");
		Lore = plugin.getConfig().getStringList("BookSystem.Lore");
		Pages = plugin.getConfig().getStringList("BookSystem.Pages");
		for(String string : Lore) {
			newLore.add(string.replace("&", "§"));
		}
		for(String string : Pages) {
			newPages.add(string.replace("&", "§"));
		}
	}
	
/* ----------------------------------------------------------------------------------------------------------------- */
	
}
