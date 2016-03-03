package net.notfab.HubBasics.Bukkit.Runnables;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import lombok.Getter;
import net.notfab.HubBasics.Bukkit.HubBasics;

public class BossAnnouncer implements Runnable {
	
	private JSONObject[] _Messages;
	
	private Integer _Index = 0; //_Index osu! <3
	private Boolean _Random = false;
	@Getter private Boolean Enabled = false;
	
	@Getter private BossBar BossBar;
	
	public BossAnnouncer() {
		JSONObject _BossAnnouncer = HubBasics.getInstance().getConfigManager().readFile(new File("plugins/HubBasics/BossAnnouncer.json"));
		this.Enabled = _BossAnnouncer.getBoolean("Enabled");
		JSONArray _M = _BossAnnouncer.getJSONArray("Messages");
		List<JSONObject> list = new ArrayList<JSONObject>();
		for(int i = 0; i < _M.length(); i++) {
			list.add(_M.getJSONObject(i));
		}
		this._Messages = list.toArray(new JSONObject[list.size()]);
		this._Random = _BossAnnouncer.getBoolean("Random");
		this._Index = 0;
		this.BossBar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SOLID, new BarFlag[]{});
	}
	
	@Override
	public void run() {
		if(Enabled) {
			if(_Random) {
				Random rnd = new Random();
				JSONObject _Bar = _Messages[rnd.nextInt(_Messages.length)];
				BarColor color = BarColor.valueOf(_Bar.getString("Color"));
				BarStyle style = BarStyle.valueOf(_Bar.getString("Style"));
				this.BossBar.setColor(color);
				this.BossBar.setStyle(style);
				this.BossBar.setTitle(_Bar.getString("Message"));
				this.BossBar.removeAll();
				for(Player p: Bukkit.getOnlinePlayers()) {
					this.BossBar.addPlayer(p);
				}
				this.BossBar.show();
			} else {
				if(_Index > this._Messages.length) {
					_Index = 0;
				} else {
					_Index++;
				}
				JSONObject _Bar = _Messages[this._Index];
				BarColor color = BarColor.valueOf(_Bar.getString("Color"));
				BarStyle style = BarStyle.valueOf(_Bar.getString("Style"));
				this.BossBar.setColor(color);
				this.BossBar.setStyle(style);
				this.BossBar.setTitle(_Bar.getString("Message"));
				this.BossBar.removeAll();
				for(Player p: Bukkit.getOnlinePlayers()) {
					this.BossBar.addPlayer(p);
				}
				this.BossBar.show();
			}
		}
	}
	
}
