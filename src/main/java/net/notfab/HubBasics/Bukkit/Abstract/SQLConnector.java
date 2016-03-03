package net.notfab.HubBasics.Bukkit.Abstract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.notfab.HubBasics.Bukkit.HubBasics;

public class SQLConnector {
	
	private Connection connection;
	
	private String ip;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private String init;
	
	public SQLConnector(String customName, String ip, String port, String user, String password, String dbName) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.password = password;
		this.dbName = dbName;
		HubBasics.getInstance().getMySQL().addConnection(customName, this);
	}
	
	public void setInitQuery(String s) {
		this.init = s;
	}
    
	/**
	 * 
	 * Method used to shutdown the connection
	 * 
	 */
    public void shutdown() {
        try {
            if(!connection.isClosed() || connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * Will return a java.sql.Connection instance, NULL if an error happens.
     * This is what you will use to do your queries
     * 
     */
    public Connection getConnection() {
    	try {
			if(connection == null || connection.isClosed()) {
				if(openConnection()) {
					return connection;
				} else {
					return null;
				}
			} else {
				return connection;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 
     * Method used to open the connection
     * 
     */
    public boolean openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName, user, password);
            if(init != null) {
            	PreparedStatement ps = connection.prepareStatement(init);
                ps.execute();
                ps.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
