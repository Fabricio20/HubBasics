package me.Fabricio20.listeners.Player;

import java.util.List;

import me.Fabricio20.Main;
import me.Fabricio20.Storage.Strings;
import me.Fabricio20.methods.Items;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		if(Strings.MagicClockActive.contains(e.getPlayer())) {
			for (Player user : Bukkit.getOnlinePlayers()) {
				if (e.getPlayer().canSee(user) == false) {
					e.getPlayer().showPlayer(user);
				}
			}
		}
		if(Main.theClass.config.getBoolean("Others.ClearInventoryOnEveryWorld")) {
			e.getPlayer().getInventory().clear();
		}
		List<String> worlds = Main.theClass.config.getStringList("Worlds");
		if(worlds.contains(e.getPlayer().getWorld())) {
			Player player = e.getPlayer();
			if(Main.theClass.config.getBoolean("Others.JoinItemsChangeWorld") == true) {
				if(Main.theClass.ItemConfig.getBoolean("Item1.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item1(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item1.Slot"), Items.Item1(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 1 & Start Of 2
				if(Main.theClass.ItemConfig.getBoolean("Item2.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item2(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item2.Slot"), Items.Item2(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 2 & Start Of 3
				if(Main.theClass.ItemConfig.getBoolean("Item3.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item3(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item3.Slot"), Items.Item3(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 3 & Start Of 4
				if(Main.theClass.ItemConfig.getBoolean("Item4.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item4(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item4.Slot"), Items.Item4(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 4 & Start Of 5
				if(Main.theClass.ItemConfig.getBoolean("Item5.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item5(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item5.Slot"), Items.Item5(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 5 & Start Of 6
				if(Main.theClass.ItemConfig.getBoolean("Item6.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item6(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item6.Slot"), Items.Item6(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 6 & Start Of 7
				if(Main.theClass.ItemConfig.getBoolean("Item7.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item7(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item7.Slot"), Items.Item7(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 7 & Start Of 8
				if(Main.theClass.ItemConfig.getBoolean("Item8.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item8(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item8.Slot"), Items.Item8(e.getPlayer().getName()));
					}
				}
	////////////////////////////////////////////////////////////////////////////////////////- End Of 8 & Start Of 9
				if(Main.theClass.ItemConfig.getBoolean("Item9.Enabled") == true) {
					if(!player.getInventory().contains(Items.Item9(e.getPlayer().getName()))) {
						e.getPlayer().getInventory().setItem(Main.theClass.ItemConfig.getInt("Item9.Slot"), Items.Item9(e.getPlayer().getName()));
					}
				}
	///////////////////////////////////////////////////////////////////////////////////////- End Of Stuff, Start Of MagicClock
			}
			if(Main.theClass.config.getBoolean("MagicClock.GiveOnWorldChange") == true) {
				if(!e.getPlayer().getInventory().contains(Items.MagicClock(e.getPlayer().getName()))) {
					e.getPlayer().getInventory().setItem(Main.theClass.config.getInt("MagicClock.Slot"), Items.MagicClock(e.getPlayer().getName()));
				}
			}
		}
		
	}

}
