package me.Fabricio20.methods;

import java.util.ArrayList;
import me.Fabricio20.Main;

public class FixConfig {
	
	public static void fix() {
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
			Main.theClass.config.set("BossAnnouncer.Enabled", false);
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
		if(!Main.theClass.config.contains("MagicClock.Name")) {
			Main.theClass.config.set("MagicClock.Name", "&cMagic Clock");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&7- &a&oMagicClock Lore");
			Main.theClass.config.set("MagicClock.Lore", Lore);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.Slot")) {
			Main.theClass.config.set("MagicClock.Slot", 5);
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.Material")) {
			Main.theClass.config.set("MagicClock.Material", "WATCH");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.EnabledMessage")) {
			Main.theClass.config.set("MagicClock.EnabledMessage", "&cMagic Clock Is Now Enabled!");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("MagicClock.DisabledMessage")) {
			Main.theClass.config.set("MagicClock.DisabledMessage", "&cMagic Clock Is Now Disabled!");
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
		if(!Main.theClass.config.contains("MagicClock.CooldownMSG")) {
			Main.theClass.config.set("MagicClock.CooldownMSG", "&cCooldown! You Must Wait 12 Seconds!");
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
			Main.theClass.config.set("ChatSystem.Enabled", true);
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
		if(!Main.theClass.config.contains("Others.Prefix")) {
			Main.theClass.config.set("Others.Prefix", "&8[&cHubBasics&8] ");
			Main.theClass.config.saveConfig();
		}
		if(!Main.theClass.config.contains("Others.JumpPadBlock")) {
			Main.theClass.config.set("Others.JumpPadBlock", "REDSTONE_BLOCK");
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
		if(!Main.theClass.config.contains("Others.GiveItems")) {
			Main.theClass.config.set("Others.GiveItems", true);
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
		if(!Main.theClass.config.contains("Others.HubItems")) {
			Main.theClass.config.set("Others.HubItems", true);
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
	
}
