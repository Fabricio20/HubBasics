package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Book {
	
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
		Author = Main.getPlugin().getConfig().getString("BookSystem.Author");
		Name = Main.getPlugin().getConfig().getString("BookSystem.Name").replace("&", "§");
		Lore = Main.getPlugin().getConfig().getStringList("BookSystem.Lore");
		Pages = Main.getPlugin().getConfig().getStringList("BookSystem.Pages");
		for(String string : Lore) {
			newLore.add(string.replace("&", "§"));
		}
		for(String string : Pages) {
			newPages.add(string.replace("&", "§"));
		}
	}
	
/* ----------------------------------------------------------------------------------------------------------------- */
	
}
