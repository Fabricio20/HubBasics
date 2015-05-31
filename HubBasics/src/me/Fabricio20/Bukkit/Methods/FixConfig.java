package me.Fabricio20.Bukkit.Methods;

import java.util.ArrayList;

import me.Fabricio20.Bukkit.Main;
import me.Fabricio20.Bukkit.Methods.Configs.SimpleConfig;

import org.bukkit.Bukkit;

public class FixConfig {
	
	public static SimpleConfig ChestItems = Main.theClass.ChestItems;
	
	public static void fix() {
		fixConfig();
		fixItems();
		fixStorage();
		fixLanguage();
		fixTags();
		fixChestItems();
	}
	
	public static void fixPlayer(SimpleConfig config) {
		if(!config.contains("IsPlayersEnabled")) {
			config.set("IsPlayersEnabled", true);
			config.saveConfig();
		}
		if(!config.contains("SpeedBoost.Enabled")) {
			config.set("SpeedBoost.Enabled", false);
			config.saveConfig();
		}
		if(!config.contains("SpeedBoost.Force")) {
			config.set("SpeedBoost.Force", 1);
			config.saveConfig();
		}
		if(!config.contains("JumpBoost.Enabled")) {
			config.set("JumpBoost.Enabled", false);
			config.saveConfig();
		}
		if(!config.contains("JumpBoost.Force")) {
			config.set("JumpBoost.Force", 1);
			config.saveConfig();
		}
	}
	
	private static void fixConfig() {
		/**
		 * ------------- TODO: JoinEvents System   ------------- *
		 */
		if(!Main.theClass.config.contains("JoinEvents.DisableMessage")) {
			Main.theClass.config.set("JoinEvents.DisableMessage", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("JoinEvents.SilentOpJoin")) {
			Main.theClass.config.set("JoinEvents.SilentOpJoin", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("JoinEvents.Message")) {
			Main.theClass.config.set("JoinEvents.Message", "&8[&cHubBasics&8] &eWelcome &a%p &eto the server!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("JoinEvents.HubAtLogin")) {
			Main.theClass.config.set("JoinEvents.HubAtLogin", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("JoinEvents.BossBarOnJoin")) {
			Main.theClass.config.set("JoinEvents.BossBarOnJoin", false);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: LeaveEvents System   ------------- *
		 */
		if(!Main.theClass.config.contains("LeaveEvents.DisableLeaveMessage")) {
			Main.theClass.config.set("LeaveEvents.DisableLeaveMessage", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("LeaveEvents.SilentOpLeave")) {
			Main.theClass.config.set("LeaveEvents.SilentOpLeave", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("LeaveEvents.Message")) {
			Main.theClass.config.set("LeaveEvents.Message", "&8[&cHubBasics&8] &a%p &eLeft!");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: VoidFall System   ------------- *
		 */
		if(!Main.theClass.config.contains("VoidFall.Enabled")) {
			Main.theClass.config.set("VoidFall.Enabled", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("VoidFall.OnlyHub")) {
			Main.theClass.config.set("VoidFall.OnlyHub", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("VoidFall.Message")) {
			Main.theClass.config.set("VoidFall.Message", "&8[&cHubBasics&8] &a%p &eYou were teleported back to spawn!");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Motd System   ------------- *
		 */
		if(!Main.theClass.config.contains("MotdSystem.CustomMotd")) {
			Main.theClass.config.set("MotdSystem.CustomMotd", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MotdSystem.MoreMotds")) {
			Main.theClass.config.set("MotdSystem.MoreMotds", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MotdSystem.Motds")) {
			ArrayList<String> motds = new ArrayList<String>();
			motds.add("&cThis is a default motd! Change it in the config.");
			Main.theClass.config.set("MotdSystem.Motds", motds);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: ChatAnnouncer System   ------------- *
		 */
		if(!Main.theClass.config.contains("ChatAnnouncer.Enabled")) {
			Main.theClass.config.set("ChatAnnouncer.Enabled", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.theClass.config.set("ChatAnnouncer.Msgs", chatann);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatAnnouncer.Perworld")) {
			Main.theClass.config.set("ChatAnnouncer.Perworld", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.theClass.config.set("ChatAnnouncer.Worlds", Worlds);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatAnnouncer.Time")) {
			Main.theClass.config.set("ChatAnnouncer.Time", 60);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: BossAnnouncer System   ------------- *
		 */
		if(!Main.theClass.config.contains("BossAnnouncer.Enabled")) {
			Main.theClass.config.set("BossAnnouncer.Enabled", false, "WARNING This can generate errors since BarAPI is broken.");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BossAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.theClass.config.set("BossAnnouncer.Msgs", chatann);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BossAnnouncer.Perworld")) {
			Main.theClass.config.set("BossAnnouncer.Perworld", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BossAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.theClass.config.set("BossAnnouncer.Worlds", Worlds);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BossAnnouncer.Time")) {
			Main.theClass.config.set("BossAnnouncer.Time", 60);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: ActionAnnouncer System   ------------- *
		 */
		if(!Main.theClass.config.contains("ActionAnnouncer.Enabled")) {
			Main.theClass.config.set("ActionAnnouncer.Enabled", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ActionAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.theClass.config.set("ActionAnnouncer.Msgs", chatann);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ActionAnnouncer.Perworld")) {
			Main.theClass.config.set("ActionAnnouncer.Perworld", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ActionAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.theClass.config.set("ActionAnnouncer.Worlds", Worlds);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ActionAnnouncer.Time")) {
			Main.theClass.config.set("ActionAnnouncer.Time", 3);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: FakePlugins System   ------------- *
		 */
		if(!Main.theClass.config.contains("FakePlugins.Enabled")) {
			Main.theClass.config.set("FakePlugins.Enabled", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("FakePlugins.Msg")) {
			Main.theClass.config.set("FakePlugins.Msg", "&fPlugins (5): &aYou&f, &aCannot&f, &aSee&f, &aThe&f, &aPlugins");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: BungeeCord System   ------------- *
		 */
		if(!Main.theClass.config.contains("BungeeCord.Enabled")) {
			Main.theClass.config.set("BungeeCord.Enabled", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BungeeCord.LobbyServer")) {
			Main.theClass.config.set("BungeeCord.LobbyServer", "lobby");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: MagicClock System   ------------- *
		 */
		if(!Main.theClass.config.contains("MagicClock.Enabled")) {
			Main.theClass.config.set("MagicClock.Enabled", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.OnDeath")) {
			Main.theClass.config.set("MagicClock.OnDeath", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.EnabledInChest")) {
			Main.theClass.config.set("MagicClock.EnabledInChest", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.IgnoreBypass")) {
			Main.theClass.config.set("MagicClock.IgnoreBypass", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.Slot")) {
			Main.theClass.config.set("MagicClock.Slot", 5);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.AllowMove")) {
			Main.theClass.config.set("MagicClock.AllowMove", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.NoDrop")) {
			Main.theClass.config.set("MagicClock.NoDrop", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.Cooldown")) {
			Main.theClass.config.set("MagicClock.Cooldown", 12);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.GiveOnWorldChange")) {
			Main.theClass.config.set("MagicClock.GiveOnWorldChange", false);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Title System   ------------- *
		 */
		if(!Main.theClass.config.contains("TitleSystem.Enabled")) {
			Main.theClass.config.set("TitleSystem.Enabled", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("TitleSystem.Title")) {
			Main.theClass.config.set("TitleSystem.Title", "&cThis Is A Title!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("TitleSystem.Subtitle")) {
			Main.theClass.config.set("TitleSystem.Subtitle", "&9This is a Sub-Title!");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Book System   ------------- *
		 */
		if(!Main.theClass.config.contains("BookSystem.Enabled")) {
			Main.theClass.config.set("BookSystem.Enabled", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.Author")) {
			Main.theClass.config.set("BookSystem.Author", "HubBasics");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.Name")) {
			Main.theClass.config.set("BookSystem.Name", "&cTha Book");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.Slot")) {
			Main.theClass.config.set("BookSystem.Slot", 5);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.Give")) {
			Main.theClass.config.set("BookSystem.Give", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.FirstJoinOnly")) {
			Main.theClass.config.set("BookSystem.FirstJoinOnly", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&bTha lore&c.");
			Main.theClass.config.set("BookSystem.Lore", Lore);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("BookSystem.Pages")) {
			ArrayList<String> Pages = new ArrayList<String>();
			Pages.add("&cThis is a page.");
			Main.theClass.config.set("BookSystem.Pages", Pages);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Tab List System   ------------- *
		 */
		if(!Main.theClass.config.contains("TabList.Enabled")) {
			Main.theClass.config.set("TabList.Enabled", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("TabList.Header")) {
			Main.theClass.config.set("TabList.Header", "&cWelcome %p!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("TabList.Footer")) {
			Main.theClass.config.set("TabList.Footer", "&aBuy &bViP &aOn Our Website!");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Hat System   ------------- * 
		 */
		if(!Main.theClass.config.contains("HatSystem.Enabled")) {
			Main.theClass.config.set("HatSystem.Enabled", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.Name")) {
			Main.theClass.config.set("HatSystem.Name", "&aJust A Hat!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&bTha lore&c.");
			Main.theClass.config.set("HatSystem.Lore", Lore);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.Set")) {
			Main.theClass.config.set("HatSystem.Set", "&cEnjoy your new hat!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.Removed")) {
			Main.theClass.config.set("HatSystem.Removed", "&cYour Hat Was Removed!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.HatAllowMove")) {
			Main.theClass.config.set("HatSystem.HatAllowMove", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.AllowDrops")) {
			Main.theClass.config.set("HatSystem.AllowDrops", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("HatSystem.NoPerm")) {
			Main.theClass.config.set("HatSystem.NoPerm", "&cYou do not have permission for this hat!");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Chat System   ------------- *
		 */
		if(!Main.theClass.config.contains("ChatSystem.Enabled")) {
			Main.theClass.config.set("ChatSystem.Enabled", true, "FOR NOW THIS ONLY WORKS WITH PEX! SORRY!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatSystem.PerWorld")) {
			Main.theClass.config.set("ChatSystem.PerWorld", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatSystem.AllowColors")) {
			Main.theClass.config.set("ChatSystem.AllowColors", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("ChatSystem.Format")) {
			Main.theClass.config.set("ChatSystem.Format", "&e[%w] &f[&2$group&f] &e%p&f: &c%m");
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Warp System   ------------- *
		 */
		if(!Main.theClass.config.contains("WarpSystem.Message")) {
			Main.theClass.config.set("WarpSystem.Message", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("WarpSystem.Effect")) {
			Main.theClass.config.set("WarpSystem.Effect", true);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Others System   ------------- *
		 */
		if(!Main.theClass.config.contains("Others.DisableRain")) {
			Main.theClass.config.set("Others.DisableRain", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.DoubleJump")) {
			Main.theClass.config.set("Others.DoubleJump", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.JumpPadBlock")) {
			Main.theClass.config.set("Others.JumpPadBlock", "REDSTONE_BLOCK");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.JumpPadEnabled")) {
			Main.theClass.config.set("Others.JumpPadEnabled", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.KeepFood")) {
			Main.theClass.config.set("Others.KeepFood", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.KeepHealth")) {
			Main.theClass.config.set("Others.KeepHealth", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.Stacker")) {
			Main.theClass.config.set("Others.Stacker", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.JoinItems")) {
			Main.theClass.config.set("Others.JoinItems", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.JoinItemsChangeWorld")) {
			Main.theClass.config.set("Others.JoinItemsChangeWorld", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.JoinItemsDeath")) {
			Main.theClass.config.set("Others.JoinItemsDeath", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.ClearInventoryOnEveryWorld")) {
			Main.theClass.config.set("Others.ClearInventoryOnEveryWorld", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.ClearInventory")) {
			Main.theClass.config.set("Others.ClearInventory", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.NoDrops")) {
			Main.theClass.config.set("Others.NoDrops", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.NoDeathDrops")) {
			Main.theClass.config.set("Others.NoDeathDrops", true);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.AllowItemMove")) {
			Main.theClass.config.set("Others.AllowItemMove", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.AntiOP")) {
			Main.theClass.config.set("Others.AntiOP", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.StackerEnabledMessage")) {
			Main.theClass.config.set("Others.StackerEnabledMessage", "&aStacker Enabled!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.StackerDisabledMessage")) {
			Main.theClass.config.set("Others.StackerDisabledMessage", "&cStacker Disabled!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.FirstJoinItemsOnly")) {
			Main.theClass.config.set("Others.FirstJoinItemsOnly", false);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.AllowBuilding")) {
			Main.theClass.config.set("Others.AllowBuilding", true);
			Main.theClass.config.saveConfig();
		}
		/** 
		 * ------------ TODO: Disabled Commands    ------------- *
		 */
		if(!Main.theClass.config.contains("DisabledCommands")) {
			ArrayList<String> commands = new ArrayList<String>();
			commands.add("example");
			Main.theClass.config.set("DisabledCommands", commands);
			Main.theClass.config.saveConfig();
		}
		/**
		 * ------------- TODO: Worlds System   ------------- *
		 */
		if(!Main.theClass.config.contains("Worlds")) {
			ArrayList<String> worlds = new ArrayList<String>();
			worlds.add("Example");
			Main.theClass.config.set("Worlds", worlds);
			Main.theClass.config.saveConfig();
		}
	}
	
	private static void fixItems() {
		if(!Main.theClass.JoinItems.contains("Items")) {
			Main.theClass.JoinItems.set("Items.AllNodes.Material", "COMPASS");
			Main.theClass.JoinItems.set("Items.AllNodes.Data", 0);
			Main.theClass.JoinItems.set("Items.AllNodes.Quantity", 1);
			Main.theClass.JoinItems.set("Items.AllNodes.Displayname", "&cQuick-Warp");
			Main.theClass.JoinItems.set("Items.AllNodes.Skull", false);
			Main.theClass.JoinItems.set("Items.AllNodes.Glow", false);
			Main.theClass.JoinItems.set("Items.AllNodes.Slot", 0);
			Main.theClass.JoinItems.set("Items.AllNodes.Owner", "Fabricio20");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8Lore");
			Main.theClass.JoinItems.set("Items.AllNodes.Lore", lore);
			Main.theClass.JoinItems.set("Items.AllNodes.Command", "TELL:&8[&eHubBasics&8] &aIt Works!");
			Main.theClass.JoinItems.set("Items.AllNodes.Permission", "");
			Main.theClass.JoinItems.saveConfig();
		}
	}
	
	private static void fixStorage() {
		if(!Main.theClass.Hub.contains("Hub.World")) {
			Main.theClass.Hub.set("Hub.World", Bukkit.getWorlds().get(0).getSpawnLocation().getWorld().getName());
			Main.theClass.Hub.saveConfig();
		}
		if(!Main.theClass.Hub.contains("Hub.X")) {
			Main.theClass.Hub.set("Hub.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
			Main.theClass.Hub.saveConfig();
		}
		if(!Main.theClass.Hub.contains("Hub.Y")) {
			Main.theClass.Hub.set("Hub.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
			Main.theClass.Hub.saveConfig();
		}
		if(!Main.theClass.Hub.contains("Hub.Z")) {
			Main.theClass.Hub.set("Hub.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
			Main.theClass.Hub.saveConfig();
		}
		if(!Main.theClass.Hub.contains("Hub.Yaw")) {
			Main.theClass.Hub.set("Hub.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
			Main.theClass.Hub.saveConfig();
		}
		if(!Main.theClass.Hub.contains("Hub.Pitch")) {
			Main.theClass.Hub.set("Hub.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
			Main.theClass.Hub.saveConfig();
		}
	}
	
	private static void fixLanguage() {
		if(!Main.theClass.Language.contains("WarpUsage")) {
			Main.theClass.Language.set("WarpUsage", "&cUsage: /warp <name>");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("WarpMessage")) {
			Main.theClass.Language.set("WarpMessage", "&6Woosh!");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("WarpNoPermission")) {
			Main.theClass.Language.set("WarpNoPermission", "&cYou do not have permission for this warp!");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("QuickWarpChestName")) {
			Main.theClass.Language.set("QuickWarpChestName", "&cQuick-Warp");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("ServerSelectorNoPerm")) {
			Main.theClass.Language.set("ServerSelectorNoPerm", "&cError: &7&oYou Do Not Have Permission To Use This!");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Effects.SpeedEnabled")) {
			Main.theClass.Language.set("Effects.SpeedEnabled", "&eInfo: &7&oSpeed Boost Is Now &a&oEnabled");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Effects.SpeedDisabled")) {
			Main.theClass.Language.set("Effects.SpeedDisabled", "&eInfo: &7&oSpeed Boost Is Now &c&oDisabled");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Effects.JumpEnabled")) {
			Main.theClass.Language.set("Effects.JumpEnabled", "&eInfo: &7&oJump Boost Is Now &a&oEnabled");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Effects.JumpDisabled")) {
			Main.theClass.Language.set("Effects.JumpDisabled", "&eInfo: &7&oJump Boost Is Now &c&oDisabled");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Effects.SpeedSet")) {
			Main.theClass.Language.set("Effects.SpeedSet", "&eInfo: &7&oSpeed Boost Set To &e&o");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Effects.JumpSet")) {
			Main.theClass.Language.set("Effects.JumpSet", "&eInfo: &7&oJump Boost Set To &e&o");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("Chests.SettingsName")) {
			Main.theClass.Language.set("Chests.SettingsName", "&8Settings");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("General.NoArgs")) {
			Main.theClass.Language.set("General.NoArgs", "&cError: &7&oYou Must Specify A Value!");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("General.NotNumber")) {
			Main.theClass.Language.set("General.NotNumber", "&cError: &7&oThe value you specified is not a number!");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("MagicClock.Enabled")) {
			Main.theClass.Language.set("MagicClock.Enabled", "&eInfo: &7&oPlayers Are Now &aEnabled");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("MagicClock.Disabled")) {
			Main.theClass.Language.set("MagicClock.Disabled", "&eInfo: &7&oPlayers Are Now &cDisabled");
			Main.theClass.Language.saveConfig();
		}
		if(!Main.theClass.Language.contains("MagicClock.Cooldown")) {
			Main.theClass.Language.set("MagicClock.Cooldown", "&cYou Must Wait 12 Seconds! ");
			Main.theClass.Language.saveConfig();
		}
	}
	
	private static void fixTags() {
		if(!Main.theClass.Tags.contains("Enabled")) {
			Main.theClass.Tags.set("Enabled", true);
			Main.theClass.Tags.saveConfig();
		}
		if(!Main.theClass.Tags.contains("TabList")) {
			Main.theClass.Tags.set("TabList.Fabricio20.Prefix", "&8[&bDev&8]&7 ");
			Main.theClass.Tags.saveConfig();
		}
	}
	
	private static void fixChestItems() {
		// Magic Clock Enabled
		if(!ChestItems.contains("MagicClockEnabled.Name")) {
			ChestItems.set("MagicClockEnabled.Name", "&aPlayers");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockEnabled.Lore")) {
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8A Basic Lore");
			ChestItems.set("MagicClockEnabled.Lore", lore);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockEnabled.Material")) {
			ChestItems.set("MagicClockEnabled.Material", "WATCH");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockEnabled.Data")) {
			ChestItems.set("MagicClockEnabled.Data", 0);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockEnabled.Glow")) {
			ChestItems.set("MagicClockEnabled.Glow", true);
			ChestItems.saveConfig();
		}
		// Magic Clock Disabled
		if(!ChestItems.contains("MagicClockDisabled.Name")) {
			ChestItems.set("MagicClockDisabled.Name", "&cPlayers");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockDisabled.Lore")) {
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8A Basic Lore");
			ChestItems.set("MagicClockDisabled.Lore", lore);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockDisabled.Material")) {
			ChestItems.set("MagicClockDisabled.Material", "WATCH");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockDisabled.Data")) {
			ChestItems.set("MagicClockDisabled.Data", 0);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("MagicClockDisabled.Glow")) {
			ChestItems.set("MagicClockDisabled.Glow", false);
			ChestItems.saveConfig();
		}
		// Speed Boost Enabled
		if(!ChestItems.contains("SpeedBoostEnabled.Name")) {
			ChestItems.set("SpeedBoostEnabled.Name", "&aDisable Speed Boost");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostEnabled.Lore")) {
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8A Basic Lore");
			ChestItems.set("SpeedBoostEnabled.Lore", lore);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostEnabled.Material")) {
			ChestItems.set("SpeedBoostEnabled.Material", "POTION");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostEnabled.Data")) {
			ChestItems.set("SpeedBoostEnabled.Data", 0);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostEnabled.Glow")) {
			ChestItems.set("SpeedBoostEnabled.Glow", true);
			ChestItems.saveConfig();
		}
		// Speed Boost Disabled
		if(!ChestItems.contains("SpeedBoostDisabled.Name")) {
			ChestItems.set("SpeedBoostDisabled.Name", "&cEnable Speed Boost");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostDisabled.Lore")) {
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8A Basic Lore");
			ChestItems.set("SpeedBoostDisabled.Lore", lore);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostDisabled.Material")) {
			ChestItems.set("SpeedBoostDisabled.Material", "POTION");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostDisabled.Data")) {
			ChestItems.set("SpeedBoostDisabled.Data", 0);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("SpeedBoostDisabled.Glow")) {
			ChestItems.set("SpeedBoostDisabled.Glow", false);
			ChestItems.saveConfig();
		}
		// Jump Boost Enabled
		if(!ChestItems.contains("JumpBoostEnabled.Name")) {
			ChestItems.set("JumpBoostEnabled.Name", "&aDisable Jump Boost");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostEnabled.Lore")) {
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8A Basic Lore");
			ChestItems.set("JumpBoostEnabled.Lore", lore);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostEnabled.Material")) {
			ChestItems.set("JumpBoostEnabled.Material", "POTION");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostEnabled.Data")) {
			ChestItems.set("JumpBoostEnabled.Data", 0);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostEnabled.Glow")) {
			ChestItems.set("JumpBoostEnabled.Glow", true);
			ChestItems.saveConfig();
		}
		// Jump Boost Disabled
		if(!ChestItems.contains("JumpBoostDisabled.Name")) {
			ChestItems.set("JumpBoostDisabled.Name", "&cEnable Jump Boost");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostDisabled.Lore")) {
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("&7- &8A Basic Lore");
			ChestItems.set("JumpBoostDisabled.Lore", lore);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostDisabled.Material")) {
			ChestItems.set("JumpBoostDisabled.Material", "POTION");
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostDisabled.Data")) {
			ChestItems.set("JumpBoostDisabled.Data", 0);
			ChestItems.saveConfig();
		}
		if(!ChestItems.contains("JumpBoostDisabled.Glow")) {
			ChestItems.set("JumpBoostDisabled.Glow", false);
			ChestItems.saveConfig();
		}
	}
}
