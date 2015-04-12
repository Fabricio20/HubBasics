package me.Fabricio20.BungeeCord.Runnables;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.Fabricio20.BungeeCord.API.MySQLAPI;

public class KeepAlive implements Runnable {

	@Override
	public void run() {
		try {
			PreparedStatement pst = MySQLAPI.connection.prepareStatement("SELECT 1 FROM `Friends`");
	        pst.executeQuery();
	        pst.close();
		} catch(SQLException ex) {
			System.out.println("[HubBasics] Error While Running KeepAlive! Connection Lost!");
		}
	}

}
