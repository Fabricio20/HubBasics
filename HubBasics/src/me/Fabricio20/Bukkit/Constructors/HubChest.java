package me.Fabricio20.Bukkit.Constructors;

import java.util.HashMap;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfig;
import me.Fabricio20.Bukkit.Utils.InventoryUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubChest {
	
	private SimpleConfig config;
	
	private String displayName;
	private Integer slots;
	private Inventory inv;
	
	private String commandToOpen;
	private Material material;
	private Boolean leftClick;
	private Boolean rightClick;
	private HashMap<String, ItemStack> contents;
	
	public HubChest(String name) {
		name = name.replace(".yml", "");
		config = Main.theClass.manager.getNewConfig("Chests/" + name + ".yml");
		//
		this.displayName = config.getString("Displayname");
		this.slots = config.getInt("Slots");
		//
		if(!(this.slots % 9 == 0)) {
		     this.slots = 54;
		}
		//
		this.inv = Bukkit.createInventory(null, this.slots, this.displayName);
		if(config.contains("Chest.Command")) {
			this.commandToOpen = config.getString("Chest.Command");
		}
		this.material = InventoryUtils.parseMaterial("Chest.Material", config);
		if(config.contains("Chest.OpenLeftClick")) {
			this.leftClick = config.getBoolean("Chest.OpenLeftClick");
		}
		if(config.contains("Chest.OpenRightClick")) {
			this.rightClick = config.getBoolean("Chest.OpenRightClick");
		}
		// ** CHEST CONTENTS ** //
		if(config.contains("Items")) {
			for(String s: config.getConfigurationSection("Items").getKeys(false)) {
				if(config.contains("Items." + s + ".Material")) {
					Material mat = InventoryUtils.parseMaterial("Items." + s + ".Material", config);
					Integer amount = InventoryUtils.parseInteger("Items." + s + ".Amount", config);
					ItemStack stack = new ItemStack(mat, amount);
					ItemMeta meta = stack.getItemMeta();
					meta.setDisplayName(InventoryUtils.parseString("Items." + s + ".Displayname", config));
					meta.setLore(InventoryUtils.parseList("Items." + s + ".Lore", config));
					stack.setItemMeta(meta);
					if(InventoryUtils.parseBoolean("Items." + s + ".Glow", config)) {
						stack.addEnchantment(Main.theClass.ench, 1);
					}
					this.contents.put(s, stack);
				} else {
					System.out.println("[HubBasics] Invalid Item Called '" + s + "' At " + getDisplayName() + " Config!");
				}
			}
		}
		for(ItemStack stack: getContents().values()) {
			getInventory().addItem(stack);
		}
	}
	
	public SimpleConfig getConfig() {
		return this.config;
	}
	
	public String getCommand() {
		return this.commandToOpen;
	}
	
	public Boolean getLeftClick() {
		return this.leftClick;
	}
	
	public Boolean getRightClick() {
		return this.rightClick;
	}
	
	public Material getItem() {
		return this.material;
	}
	
	public Integer getSlots() {
		return this.slots;
	}
	
	public String getDisplayName() {
		return this.displayName.replace("&", "§");
	}
	
	public Inventory getInventory() {
		return this.inv;
	}
	
	public HashMap<String, ItemStack> getContents() {
		return this.contents;
	}
	
}
