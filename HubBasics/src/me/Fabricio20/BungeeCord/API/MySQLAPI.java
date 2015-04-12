package me.Fabricio20.BungeeCord.API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.Fabricio20.BungeeCord.Main;
import net.md_5.bungee.BungeeCord;

public class MySQLAPI {
	
	public static Connection connection;
	    
    public static void shutdown() {
        try {
            if(!connection.isClosed() || connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
    	try {
			if(connection.isClosed() || connection == null) {
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
    
    public static boolean openConnection() {
        try {
        	String ip = Main.theClass.dbSettings.MySQL_Ip;
        	String port = Main.theClass.dbSettings.MySQL_Port;
        	String dbName = Main.theClass.dbSettings.MySQL_Database;
        	String user = Main.theClass.dbSettings.MySQL_Username;
        	String password = Main.theClass.dbSettings.MySQL_Password;
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName, user, password);
            createTables(connection);
            BungeeCord.getInstance().getLogger().info("[HubBasics] Database Connection Successful!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            BungeeCord.getInstance().getLogger().info("[HubBasics] Error While Connecting To The Database!");
            return false;
        }
    }
	
    public static void createTables(Connection con) {
    	String dbName = Main.theClass.dbSettings.MySQL_Database;
    	String friends = "CREATE TABLE IF NOT EXISTS `" + dbName + "`.`Friends` ( `Player` VARCHAR(50) NOT NULL ,"
    			+ " `Friends` TEXT NOT NULL , `Requests` TEXT NOT NULL , PRIMARY KEY (`Player`) ) "
    			+ "ENGINE = InnoDB;";
    	try {
			PreparedStatement friendsSql = con.prepareStatement(friends);
			friendsSql.execute();
			friendsSql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
}
