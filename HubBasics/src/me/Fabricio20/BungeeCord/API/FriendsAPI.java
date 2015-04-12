package me.Fabricio20.BungeeCord.API;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Fabricio20.BungeeCord.Main;

public class FriendsAPI {
	
	public static HashMap<String, List<String>> Cache_Friends = new HashMap<String, List<String>>();
	
	public static HashMap<String, List<String>> Cache_Requests = new HashMap<String, List<String>>();
	
	public static List<String> getFriends(String player) {
    	if(Cache_Friends.containsKey(player)) {
    		return Cache_Friends.get(player);
    	} else {
    		try {
				PreparedStatement contains = MySQLAPI.getConnection().prepareStatement("SELECT * FROM `Friends` WHERE Player='" + player + "'");
				
				ResultSet rsContains = contains.executeQuery();
				boolean isThere = rsContains.next();
				
				if(isThere) {
					String result = rsContains.getString("Friends");
					if(result != "{}") {
						result = result.replace("{", "");
						result = result.replace("}", "");
						result = result.replace(",", "");
						ArrayList<String> friends = new ArrayList<String>();
						if(result.contains(" ")) {
							for(String s: result.split(" ")) {
								friends.add(s);
							}
						} else {
							friends.add(result);
						}
						rsContains.close();
						contains.close();
						Cache_Friends.put(player, friends);
						return friends;
					} else {
						rsContains.close();
						contains.close();
						Cache_Friends.put(player, new ArrayList<String>());
						return new ArrayList<String>();
					}
				} else {
					createFriendsData(player);
					rsContains.close();
					contains.close();
					Cache_Friends.put(player, new ArrayList<String>());
					return new ArrayList<String>();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return new ArrayList<String>();
			}
    	}
    }

    public static List<String> getRequests(String player) {
    	if(Cache_Requests.containsKey(player)) {
    		return Cache_Requests.get(player);
    	} else {
    		try {
				PreparedStatement contains = MySQLAPI.getConnection().prepareStatement("SELECT * FROM `Friends` WHERE Player='" + player + "'");
				
				ResultSet rsContains = contains.executeQuery();
				boolean isThere = rsContains.next();
				
				if(isThere) {
					String result = rsContains.getString("Requests");
					if(result != "{}") {
						result = result.replace("{", "");
						result = result.replace("}", "");
						result = result.replace(",", "");
						ArrayList<String> friends = new ArrayList<String>();
						if(result.contains(" ")) {
							for(String s: result.split(" ")) {
								friends.add(s);
							}
						} else {
							friends.add(result);
						}
						rsContains.close();
						contains.close();
						Cache_Requests.put(player, friends);
						return friends;
					} else {
						rsContains.close();
						contains.close();
						Cache_Requests.put(player, new ArrayList<String>());
						return new ArrayList<String>();
					}
				} else {
					createFriendsData(player);
					rsContains.close();
					contains.close();
					Cache_Requests.put(player, new ArrayList<String>());
					return new ArrayList<String>();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return new ArrayList<String>();
			}
    	}
    }
    
	private static void createFriendsData(String player) {
    	String dbName = Main.theClass.dbSettings.MySQL_Database;
		try {
			PreparedStatement contains = MySQLAPI.getConnection().prepareStatement("SELECT * FROM `Friends` WHERE Player='" + player + "'");
			
			ResultSet rsContains = contains.executeQuery();
			boolean isThere = rsContains.next();
			
			if(!isThere) {
				String sqlCreate = "INSERT INTO `" + dbName + "`.`Friends` (`Player`, `Friends`, `Requests`) VALUES ('" + player + "', '{}', '{}')";
				PreparedStatement create = MySQLAPI.getConnection().prepareStatement(sqlCreate);
				create.executeUpdate();
				
				create.close();
			}
			
			rsContains.close();
			contains.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
    
	public static boolean friendsContains(String player) {
		try {
			PreparedStatement contains = MySQLAPI.getConnection().prepareStatement("SELECT * FROM `Friends` WHERE Player='" + player + "'");
			
			ResultSet rsContains = contains.executeQuery();
			boolean isThere = rsContains.next();

			rsContains.close();
			contains.close();
			return isThere;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean requestsContains(String player, String other) {
		return getRequests(player).contains(other);
	}
	
	public static void addRequest(String player, String other) {
		List<String> requestsOldp = getRequests(player);
		List<String> requestsOldo = getRequests(other);
		if(requestsOldp.contains("")) {
			requestsOldp.remove("");
		}
		if(requestsOldo.contains("")) {
			requestsOldo.remove("");
		}
		if(!requestsOldp.contains(other)) {
			requestsOldp.add(other);
		}
		if(!requestsOldo.contains(player)) {
			requestsOldo.add(player);
		}
		Cache_Requests.put(player, requestsOldp);
		Cache_Requests.put(other, requestsOldo);
		
		String newp = "{";
		int newpi = 0;
		for(String s: requestsOldp) {
			newpi++;
			if(newpi == requestsOldp.size()) {
				newp = newp + s + "}";
			} else {
				newp = newp + s + ", ";
			}
		}
		
		String newo = "{";
		int newoi = 0;
		for(String s: requestsOldo) {
			newoi++;
			if(newoi == requestsOldo.size()) {
				newo = newo + s + "}";
			} else {
				newo = newo + s + ", ";
			}
		}
		
		String sqlUpdate1 = "UPDATE `Friends` SET `Requests`='" + newp + "' WHERE Player='" + player + "'";
		String sqlUpdate2 = "UPDATE `Friends` SET `Requests`='" + newo + "' WHERE Player='" + other + "'";
		try {
			PreparedStatement u1 = MySQLAPI.getConnection().prepareStatement(sqlUpdate1);
			PreparedStatement u2 = MySQLAPI.getConnection().prepareStatement(sqlUpdate2);
			
			u1.executeUpdate();
			u2.executeUpdate();
			u1.close();
			u2.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void removeRequest(String player, String other) {
		List<String> requestsOldp = getRequests(player);
		List<String> requestsOldo = getRequests(other);
		
		if(requestsOldp.contains("")) {
			requestsOldp.remove("");
		}
		if(requestsOldo.contains("")) {
			requestsOldo.remove("");
		}
		
		if(requestsOldp.contains(other)) {
			requestsOldp.remove(other);
		}
		
		if(requestsOldo.contains(player)) {
			requestsOldo.remove(player);
		}
		
		String newp = "{";
		int newpi = 0;
		for(String s: requestsOldp) {
			newpi++;
			if(newpi == requestsOldp.size()) {
				newp = newp + s + "}";
			} else {
				newp = newp + s + ", ";
			}
		}
		
		String newo = "{";
		int newoi = 0;
		for(String s: requestsOldo) {
			newoi++;
			if(newoi == requestsOldo.size()) {
				newo = newo + s + "}";
			} else {
				newo = newo + s + ", ";
			}
		}
		
		Cache_Requests.put(player, requestsOldp);
		Cache_Requests.put(other, requestsOldo);
		
		String sqlUpdate1 = "UPDATE `Friends` SET `Requests`='" + newp + "' WHERE Player='" + player + "'";
		String sqlUpdate2 = "UPDATE `Friends` SET `Requests`='" + newo + "' WHERE Player='" + other + "'";
		try {
			PreparedStatement u1 = MySQLAPI.getConnection().prepareStatement(sqlUpdate1);
			PreparedStatement u2 = MySQLAPI.getConnection().prepareStatement(sqlUpdate2);
			
			u1.executeUpdate();
			u2.executeUpdate();
			u1.close();
			u2.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void addFriend(String player, String other) {
		List<String> requestsOldp = getFriends(player);
		List<String> requestsOldo = getFriends(other);
		if(requestsOldp.contains("")) {
			requestsOldp.remove("");
		}
		if(requestsOldo.contains("")) {
			requestsOldo.remove("");
		}
		if(!requestsOldp.contains(other)) {
			requestsOldp.add(other);
		}
		if(!requestsOldo.contains(player)) {
			requestsOldo.add(player);
		}
		Cache_Friends.put(player, requestsOldp);
		Cache_Friends.put(other, requestsOldo);
		
		String newp = "{";
		int newpi = 0;
		for(String s: requestsOldp) {
			newpi++;
			if(newpi == requestsOldp.size()) {
				newp = newp + s + "}";
			} else {
				newp = newp + s + ", ";
			}
		}
		
		String newo = "{";
		int newoi = 0;
		for(String s: requestsOldo) {
			newoi++;
			if(newoi == requestsOldo.size()) {
				newo = newo + s + "}";
			} else {
				newo = newo + s + ", ";
			}
		}
		
		String sqlUpdate1 = "UPDATE `Friends` SET `Friends`='" + newp + "' WHERE Player='" + player + "'";
		String sqlUpdate2 = "UPDATE `Friends` SET `Friends`='" + newo + "' WHERE Player='" + other + "'";
		try {
			PreparedStatement u1 = MySQLAPI.getConnection().prepareStatement(sqlUpdate1);
			PreparedStatement u2 = MySQLAPI.getConnection().prepareStatement(sqlUpdate2);
			
			u1.executeUpdate();
			u2.executeUpdate();
			u1.close();
			u2.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static boolean areFriends(String player, String other) {
		return getFriends(player).contains(other);
	}
	
	public static void removeFriend(String player, String other) {
		List<String> requestsOldp = getFriends(player);
		List<String> requestsOldo = getFriends(other);
		
		if(requestsOldp.contains("")) {
			requestsOldp.remove("");
		}
		if(requestsOldo.contains("")) {
			requestsOldo.remove("");
		}
		
		if(requestsOldp.contains(other)) {
			requestsOldp.remove(other);
		}
		
		if(requestsOldo.contains(player)) {
			requestsOldo.remove(player);
		}
		
		String newp = "{";
		int newpi = 0;
		for(String s: requestsOldp) {
			newpi++;
			if(newpi == requestsOldp.size()) {
				newp = newp + s + "}";
			} else {
				newp = newp + s + ", ";
			}
		}
		
		String newo = "{";
		int newoi = 0;
		for(String s: requestsOldo) {
			newoi++;
			if(newoi == requestsOldo.size()) {
				newo = newo + s + "}";
			} else {
				newo = newo + s + ", ";
			}
		}
		
		Cache_Friends.put(player, requestsOldp);
		Cache_Friends.put(other, requestsOldo);
		
		String sqlUpdate1 = "UPDATE `Friends` SET `Friends`='" + newp + "' WHERE Player='" + player + "'";
		String sqlUpdate2 = "UPDATE `Friends` SET `Friends`='" + newo + "' WHERE Player='" + other + "'";
		try {
			PreparedStatement u1 = MySQLAPI.getConnection().prepareStatement(sqlUpdate1);
			PreparedStatement u2 = MySQLAPI.getConnection().prepareStatement(sqlUpdate2);
			
			u1.executeUpdate();
			u2.executeUpdate();
			u1.close();
			u2.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
}
