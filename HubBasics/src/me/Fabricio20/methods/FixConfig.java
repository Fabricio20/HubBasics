package me.Fabricio20.methods;

import java.util.ArrayList;
import java.util.List;

import me.Fabricio20.Main;

public class FixConfig {
	
	public static void fix() {
		if(!Main.getPlugin().getConfig().contains("JoinEvents.DisableMessage")) {
			Main.getPlugin().getConfig().set("JoinEvents.DisableMessage", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("JoinEvents.SilentOpJoin")) {
			Main.getPlugin().getConfig().set("JoinEvents.SilentOpJoin", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("JoinEvents.Message")) {
			Main.getPlugin().getConfig().set("JoinEvents.Message", "&8[&cHubBasics&8] &eWelcome &a%p &eto the server!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("JoinEvents.HubAtLogin")) {
			Main.getPlugin().getConfig().set("JoinEvents.HubAtLogin", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("JoinEvents.BossBarOnJoin")) {
			Main.getPlugin().getConfig().set("JoinEvents.BossBarOnJoin", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("LeaveEvents.DisableLeaveMessage")) {
			Main.getPlugin().getConfig().set("LeaveEvents.DisableLeaveMessage", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("LeaveEvents.SilentOpLeave")) {
			Main.getPlugin().getConfig().set("LeaveEvents.SilentOpLeave", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("LeaveEvents.Message")) {
			Main.getPlugin().getConfig().set("LeaveEvents.Message", "&8[&cHubBasics&8] &a%p &eLeft!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("VoidFall.Enabled")) {
			Main.getPlugin().getConfig().set("VoidFall.Enabled", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("VoidFall.Message")) {
			Main.getPlugin().getConfig().set("VoidFall.Message", "&8[&cHubBasics&8] &a%p &eYou were teleported back to spawn!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MotdSystem.CustomMotd")) {
			Main.getPlugin().getConfig().set("MotdSystem.CustomMotd", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MotdSystem.MoreMotds")) {
			Main.getPlugin().getConfig().set("MotdSystem.MoreMotds", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MotdSystem.Motds")) {
			ArrayList<String> motds = new ArrayList<String>();
			motds.add("&cThis is a default motd! Change it in the config.");
			Main.getPlugin().getConfig().set("MotdSystem.Motds", motds);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ChatAnnouncer.Enabled")) {
			Main.getPlugin().getConfig().set("ChatAnnouncer.Enabled", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ChatAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.getPlugin().getConfig().set("ChatAnnouncer.Msgs", chatann);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ChatAnnouncer.Perworld")) {
			Main.getPlugin().getConfig().set("ChatAnnouncer.Perworld", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ChatAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.getPlugin().getConfig().set("ChatAnnouncer.Worlds", Worlds);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ChatAnnouncer.Time")) {
			Main.getPlugin().getConfig().set("ChatAnnouncer.Time", 60);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BossAnnouncer.Enabled")) {
			Main.getPlugin().getConfig().set("BossAnnouncer.Enabled", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BossAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.getPlugin().getConfig().set("BossAnnouncer.Msgs", chatann);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BossAnnouncer.Perworld")) {
			Main.getPlugin().getConfig().set("BossAnnouncer.Perworld", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BossAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.getPlugin().getConfig().set("BossAnnouncer.Worlds", Worlds);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BossAnnouncer.Time")) {
			Main.getPlugin().getConfig().set("BossAnnouncer.Time", 60);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ActionAnnouncer.Enabled")) {
			Main.getPlugin().getConfig().set("ActionAnnouncer.Enabled", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ActionAnnouncer.Msgs")) {
			ArrayList<String> chatann = new ArrayList<String>();
			chatann.add("Announce 1");
			chatann.add("Announce 2");
			Main.getPlugin().getConfig().set("ActionAnnouncer.Msgs", chatann);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ActionAnnouncer.Perworld")) {
			Main.getPlugin().getConfig().set("ActionAnnouncer.Perworld", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ActionAnnouncer.Worlds")) {
			ArrayList<String> Worlds = new ArrayList<String>();
			Worlds.add("Example");
			Main.getPlugin().getConfig().set("ActionAnnouncer.Worlds", Worlds);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("ActionAnnouncer.Time")) {
			Main.getPlugin().getConfig().set("ActionAnnouncer.Time", 3);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("FakePlugins.Enabled")) {
			Main.getPlugin().getConfig().set("FakePlugins.Enabled", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("FakePlugins.Msg")) {
			Main.getPlugin().getConfig().set("FakePlugins.Msg", "&fPlugins (5): &aYou&f, &aCannot&f, &aSee&f, &aThe&f, &aPlugins");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BungeeCord.Enabled")) {
			Main.getPlugin().getConfig().set("BungeeCord.Enabled", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BungeeCord.LobbyServer")) {
			Main.getPlugin().getConfig().set("BungeeCord.LobbyServer", "lobby");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.Enabled")) {
			Main.getPlugin().getConfig().set("MagicClock.Enabled", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.Name")) {
			Main.getPlugin().getConfig().set("MagicClock.Name", "&cMagic Clock");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&7- &a&oMagicClock Lore");
			Main.getPlugin().getConfig().set("MagicClock.Lore", Lore);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.Slot")) {
			Main.getPlugin().getConfig().set("MagicClock.Slot", 5);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.Material")) {
			Main.getPlugin().getConfig().set("MagicClock.Material", "WATCH");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.EnabledMessage")) {
			Main.getPlugin().getConfig().set("MagicClock.EnabledMessage", "&cMagic Clock Is Now Enabled!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.DisabledMessage")) {
			Main.getPlugin().getConfig().set("MagicClock.DisabledMessage", "&cMagic Clock Is Now Disabled!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.AllowMove")) {
			Main.getPlugin().getConfig().set("MagicClock.AllowMove", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.NoDrop")) {
			Main.getPlugin().getConfig().set("MagicClock.NoDrop", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.Cooldown")) {
			Main.getPlugin().getConfig().set("MagicClock.Cooldown", 12);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("MagicClock.CooldownMSG")) {
			Main.getPlugin().getConfig().set("MagicClock.CooldownMSG", "&cCooldown! You Must Wait 12 Seconds!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("TitleSystem.Enabled")) {
			Main.getPlugin().getConfig().set("TitleSystem.Enabled", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("TitleSystem.Title")) {
			Main.getPlugin().getConfig().set("TitleSystem.Title", "&cThis Is A Title!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("TitleSystem.Subtitle")) {
			Main.getPlugin().getConfig().set("TitleSystem.Subtitle", "&9This is a Sub-Title!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BookSystem.Enabled")) {
			Main.getPlugin().getConfig().set("BookSystem.Enabled", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BookSystem.Author")) {
			Main.getPlugin().getConfig().set("BookSystem.Author", "HubBasics");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BookSystem.Name")) {
			Main.getPlugin().getConfig().set("BookSystem.Name", "&cTha Book");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BookSystem.Slot")) {
			Main.getPlugin().getConfig().set("BookSystem.Slot", 5);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BookSystem.Lore")) {
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add("&bTha lore&c.");
			Main.getPlugin().getConfig().set("BookSystem.Lore", Lore);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("BookSystem.Pages")) {
			ArrayList<String> Pages = new ArrayList<String>();
			Pages.add("&cThis is a page.");
			Main.getPlugin().getConfig().set("BookSystem.Pages", Pages);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.DoubleJump")) {
			Main.getPlugin().getConfig().set("Others.DoubleJump", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.Prefix")) {
			Main.getPlugin().getConfig().set("Others.Prefix", "&8[&cHubBasics&8] ");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.HatSet")) {
			Main.getPlugin().getConfig().set("Others.HatSet", "&cEnjoy your new hat!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.HatRemoved")) {
			Main.getPlugin().getConfig().set("Others.HatRemoved", "&cYour Hat Was Removed!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.HatAllowMove")) {
			Main.getPlugin().getConfig().set("Others.HatAllowMove", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.HatDrop")) {
			Main.getPlugin().getConfig().set("Others.HatDrop", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.NoPermissionForHat")) {
			Main.getPlugin().getConfig().set("Others.NoPermissionForHat", "&cYou do not have permission for this hat!");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.JumpPadBlock")) {
			Main.getPlugin().getConfig().set("Others.JumpPadBlock", "REDSTONE_BLOCK");
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.KeepFood")) {
			Main.getPlugin().getConfig().set("Others.KeepFood", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.KeepHealth")) {
			Main.getPlugin().getConfig().set("Others.KeepHealth", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.Stacker")) {
			Main.getPlugin().getConfig().set("Others.Stacker", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.JoinItems")) {
			Main.getPlugin().getConfig().set("Others.JoinItems", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.JoinItemsChangeWorld")) {
			Main.getPlugin().getConfig().set("Others.JoinItemsChangeWorld", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.ClearInventory")) {
			Main.getPlugin().getConfig().set("Others.ClearInventory", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.NoDrops")) {
			Main.getPlugin().getConfig().set("Others.NoDrops", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.NoDeathDrops")) {
			Main.getPlugin().getConfig().set("Others.NoDeathDrops", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.AllowItemMove")) {
			Main.getPlugin().getConfig().set("Others.AllowItemMove", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.AntiOP")) {
			Main.getPlugin().getConfig().set("Others.AntiOP", false);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Others.HubItems")) {
			Main.getPlugin().getConfig().set("Others.HubItems", true);
			Main.getPlugin().saveConfig();
		}
		if(!Main.getPlugin().getConfig().contains("Worlds")) {
			ArrayList<String> worlds = new ArrayList<String>();
			worlds.add("Example");
			Main.getPlugin().getConfig().set("Worlds", worlds);
			Main.getPlugin().saveConfig();
		}
		if(Main.getPlugin().getConfig().options().header() == "" || Main.getPlugin().getConfig().options().header() == null ) {
			List<String> Header = new ArrayList<String>();
			Header.add("##########################################");
			Header.add("# HubBasics Configuration File #");
			Header.add("# Documentation at: http://tinyurl.com/qgnx3ke ");
			Header.add("##########################################");
			Main.getPlugin().getConfig().options().header(Header.toString());
			Main.getPlugin().saveConfig();
		}
	}
	
}
