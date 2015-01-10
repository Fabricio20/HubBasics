package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;

public class FixConfig {
	
	public static void fix() {
		if(!Main.theClass.getPlugin().getConfig().contains("JoinEvents.DisableMessage")) {
			Main.theClass.getPlugin().getConfig().set("JoinEvents.DisableMessage", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("JoinEvents.SilentOpJoin")) {
			Main.theClass.getPlugin().getConfig().set("JoinEvents.SilentOpJoin", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("JoinEvents.Message")) {
			Main.theClass.getPlugin().getConfig().set("JoinEvents.Message", "&8[&cHubBasics&8] &eWelcome &a%p &eto the server!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("JoinEvents.HubAtLogin")) {
			Main.theClass.getPlugin().getConfig().set("JoinEvents.HubAtLogin", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("JoinEvents.BossBarOnJoin")) {
			Main.theClass.getPlugin().getConfig().set("JoinEvents.BossBarOnJoin", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("LeaveEvents.DisableLeaveMessage")) {
			Main.theClass.getPlugin().getConfig().set("LeaveEvents.DisableLeaveMessage", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("LeaveEvents.SilentOpLeave")) {
			Main.theClass.getPlugin().getConfig().set("LeaveEvents.SilentOpLeave", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("LeaveEvents.Message")) {
			Main.theClass.getPlugin().getConfig().set("LeaveEvents.Message", "&8[&cHubBasics&8] &a%p &eLeft!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("VoidFall.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("VoidFall.Enabled", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("VoidFall.Message")) {
			Main.theClass.getPlugin().getConfig().set("VoidFall.Message", "&8[&cHubBasics&8] &a%p &eYou were teleported back to spawn!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MotdSystem.CustomMotd")) {
			Main.theClass.getPlugin().getConfig().set("MotdSystem.CustomMotd", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MotdSystem.MoreMotds")) {
			Main.theClass.getPlugin().getConfig().set("MotdSystem.MoreMotds", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MotdSystem.Motds")) {
			ArrayList<String> motds = new ArrayList<String>();
			motds.add("&cThis is a default motd! Change it in the config.");
			Main.theClass.getPlugin().getConfig().set("MotdSystem.Motds", motds);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ChatAnnouncer.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("ChatAnnouncer.Enabled", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ChatAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.theClass.getPlugin().getConfig().set("ChatAnnouncer.Msgs", chatann);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ChatAnnouncer.Perworld")) {
			Main.theClass.getPlugin().getConfig().set("ChatAnnouncer.Perworld", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ChatAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.theClass.getPlugin().getConfig().set("ChatAnnouncer.Worlds", Worlds);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ChatAnnouncer.Time")) {
			Main.theClass.getPlugin().getConfig().set("ChatAnnouncer.Time", 60);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BossAnnouncer.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("BossAnnouncer.Enabled", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BossAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.theClass.getPlugin().getConfig().set("BossAnnouncer.Msgs", chatann);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BossAnnouncer.Perworld")) {
			Main.theClass.getPlugin().getConfig().set("BossAnnouncer.Perworld", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BossAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.theClass.getPlugin().getConfig().set("BossAnnouncer.Worlds", Worlds);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BossAnnouncer.Time")) {
			Main.theClass.getPlugin().getConfig().set("BossAnnouncer.Time", 60);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ActionAnnouncer.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("ActionAnnouncer.Enabled", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ActionAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.theClass.getPlugin().getConfig().set("ActionAnnouncer.Msgs", chatann);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ActionAnnouncer.Perworld")) {
			Main.theClass.getPlugin().getConfig().set("ActionAnnouncer.Perworld", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ActionAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.theClass.getPlugin().getConfig().set("ActionAnnouncer.Worlds", Worlds);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("ActionAnnouncer.Time")) {
			Main.theClass.getPlugin().getConfig().set("ActionAnnouncer.Time", 3);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("FakePlugins.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("FakePlugins.Enabled", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("FakePlugins.Msg")) {
			Main.theClass.getPlugin().getConfig().set("FakePlugins.Msg", "&fPlugins (5): &aYou&f, &aCannot&f, &aSee&f, &aThe&f, &aPlugins");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BungeeCord.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("BungeeCord.Enabled", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BungeeCord.LobbyServer")) {
			Main.theClass.getPlugin().getConfig().set("BungeeCord.LobbyServer", "lobby");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.Enabled", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.Name")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.Name", "&cMagic Clock");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&7- &a&oMagicClock Lore");
			Main.theClass.getPlugin().getConfig().set("MagicClock.Lore", Lore);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.Slot")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.Slot", 5);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.Material")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.Material", "WATCH");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.EnabledMessage")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.EnabledMessage", "&cMagic Clock Is Now Enabled!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.DisabledMessage")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.DisabledMessage", "&cMagic Clock Is Now Disabled!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.AllowMove")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.AllowMove", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.NoDrop")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.NoDrop", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.Cooldown")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.Cooldown", 12);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.CooldownMSG")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.CooldownMSG", "&cCooldown! You Must Wait 12 Seconds!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("MagicClock.GiveOnWorldChange")) {
			Main.theClass.getPlugin().getConfig().set("MagicClock.GiveOnWorldChange", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("TitleSystem.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("TitleSystem.Enabled", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("TitleSystem.Title")) {
			Main.theClass.getPlugin().getConfig().set("TitleSystem.Title", "&cThis Is A Title!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("TitleSystem.Subtitle")) {
			Main.theClass.getPlugin().getConfig().set("TitleSystem.Subtitle", "&9This is a Sub-Title!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BookSystem.Enabled")) {
			Main.theClass.getPlugin().getConfig().set("BookSystem.Enabled", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BookSystem.Author")) {
			Main.theClass.getPlugin().getConfig().set("BookSystem.Author", "HubBasics");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BookSystem.Name")) {
			Main.theClass.getPlugin().getConfig().set("BookSystem.Name", "&cTha Book");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BookSystem.Slot")) {
			Main.theClass.getPlugin().getConfig().set("BookSystem.Slot", 5);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BookSystem.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&bTha lore&c.");
			Main.theClass.getPlugin().getConfig().set("BookSystem.Lore", Lore);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("BookSystem.Pages")) {
			ArrayList<String> Pages = new ArrayList<String>();
			Pages.add("&cThis is a page.");
			Main.theClass.getPlugin().getConfig().set("BookSystem.Pages", Pages);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.DoubleJump")) {
			Main.theClass.getPlugin().getConfig().set("Others.DoubleJump", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.Prefix")) {
			Main.theClass.getPlugin().getConfig().set("Others.Prefix", "&8[&cHubBasics&8] ");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.HatSet")) {
			Main.theClass.getPlugin().getConfig().set("Others.HatSet", "&cEnjoy your new hat!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.HatRemoved")) {
			Main.theClass.getPlugin().getConfig().set("Others.HatRemoved", "&cYour Hat Was Removed!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.HatAllowMove")) {
			Main.theClass.getPlugin().getConfig().set("Others.HatAllowMove", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.HatDrop")) {
			Main.theClass.getPlugin().getConfig().set("Others.HatDrop", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.NoPermissionForHat")) {
			Main.theClass.getPlugin().getConfig().set("Others.NoPermissionForHat", "&cYou do not have permission for this hat!");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.JumpPadBlock")) {
			Main.theClass.getPlugin().getConfig().set("Others.JumpPadBlock", "REDSTONE_BLOCK");
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.KeepFood")) {
			Main.theClass.getPlugin().getConfig().set("Others.KeepFood", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.KeepHealth")) {
			Main.theClass.getPlugin().getConfig().set("Others.KeepHealth", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.Stacker")) {
			Main.theClass.getPlugin().getConfig().set("Others.Stacker", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.JoinItems")) {
			Main.theClass.getPlugin().getConfig().set("Others.JoinItems", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.JoinItemsChangeWorld")) {
			Main.theClass.getPlugin().getConfig().set("Others.JoinItemsChangeWorld", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.ClearInventory")) {
			Main.theClass.getPlugin().getConfig().set("Others.ClearInventory", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.NoDrops")) {
			Main.theClass.getPlugin().getConfig().set("Others.NoDrops", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.NoDeathDrops")) {
			Main.theClass.getPlugin().getConfig().set("Others.NoDeathDrops", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.AllowItemMove")) {
			Main.theClass.getPlugin().getConfig().set("Others.AllowItemMove", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.AntiOP")) {
			Main.theClass.getPlugin().getConfig().set("Others.AntiOP", false);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Others.HubItems")) {
			Main.theClass.getPlugin().getConfig().set("Others.HubItems", true);
			Main.theClass.getPlugin().saveConfig();
		}
		if(!Main.theClass.getPlugin().getConfig().contains("Worlds")) {
			ArrayList<String> worlds = new ArrayList<String>();
			worlds.add("Example");
			Main.theClass.getPlugin().getConfig().set("Worlds", worlds);
			Main.theClass.getPlugin().saveConfig();
		}
		if(Main.theClass.getPlugin().getConfig().options().header() == "" || Main.theClass.getPlugin().getConfig().options().header() == null ) {
			List<String> Header = new ArrayList<String>();
			Header.add("##########################################");
			Header.add("# HubBasics Configuration File #");
			Header.add("# Documentation at: http://tinyurl.com/qgnx3ke ");
			Header.add("##########################################");
			Main.theClass.getPlugin().getConfig().options().header(Header.toString());
			Main.theClass.getPlugin().saveConfig();
		}
	}
	
}
