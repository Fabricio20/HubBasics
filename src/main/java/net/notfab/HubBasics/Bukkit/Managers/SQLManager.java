package net.notfab.HubBasics.Bukkit.Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.notfab.HubBasics.Bukkit.Abstract.SQLConnector;

public class SQLManager {
	
	private HashMap<String, SQLConnector> connections = new HashMap<String, SQLConnector>();
	
	private JavaPlugin plugin;
	
	/**
	 * 
	 * @param plugin - Instance of your plugin, can be the Main class.
	 * 
	 * Creates the instance of this class.
	 * 
	 */
	public SQLManager(JavaPlugin plugin) {
		this.plugin = plugin;
		Bukkit.getScheduler().runTaskTimer(this.plugin, new Runnable(){
			@Override
			public void run() {
				for(Connection con: getConnections()) {
					try {
						PreparedStatement px = con.prepareStatement("SELECT 1;");
						px.execute();
						px.close();
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}, 20, 20*60);
	}
	
	/**
	 * 
	 * @param name - The connection name to store
	 * @param connection - The connection instance
	 * 
	 * Method used to cache/store a connection
	 * 
	 */
	public void addConnection(String name, SQLConnector connection) {
		connections.put(name, connection);
	}
	
	/**
	 * 
	 * @param name - The stored connector name
	 * 
	 * Will return a instance of Connection which was cached
	 * NULL will be returned if its not cached
	 * 
	 */
	public Connection getConnection(String name) {
		if(connections.containsKey(name)) {
			return connections.get(name).getConnection();
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param name - The stored connector name
	 * 
	 * Will return the cache instance of the CONNECTOR
	 * or NULL if its not cached
	 * 
	 */
	public SQLConnector getConnector(String name) {
		if(connections.containsKey(name)) {
			return connections.get(name);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * Returns a List with all the connections
	 * 
	 */
	public List<Connection> getConnections() {
		List<Connection> con = new ArrayList<Connection>();
		for(SQLConnector conc: this.connections.values()) {
			con.add(conc.getConnection());
		}
		return con;
	}
	
}
