package me.Fabricio20.methods;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VecTor {
	
	public Vector VecTor(Location loc) {
	    double pitch = (loc.getPitch() + 90.0F) * 3.141592653589793D / 180.0D;
	    double yaw = (loc.getYaw() + 90.0F) * 3.141592653589793D / 180.0D;
	    
	    double x = Math.sin(pitch) * Math.cos(yaw);
	    double y = Math.sin(pitch) * Math.sin(yaw);
	    double z = Math.cos(pitch);
	    
	    Vector vector = new Vector(x, z, y);
	    
	    return vector;
	  }

}
