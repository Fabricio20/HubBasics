package net.notfab.HubBasics.Bukkit.NMS.NBT;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class NBTReflectionutil {
	
	public static String error;

	@SuppressWarnings("rawtypes")
	private static Class getCraftItemstack(){
		String Version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		try{
			Class c = Class.forName("org.bukkit.craftbukkit." + Version +".inventory.CraftItemStack");
			//Constructor<?> cons = c.getConstructor(ItemStack.class);
			//return cons.newInstance(item);
			return c;
		}catch(Exception ex){
			//System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
			error = ex.toString();
			return null;
		}
	}

	private static Object getnewNBTTag(){
		String Version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		try{
			@SuppressWarnings("rawtypes")
			Class c = Class.forName("net.minecraft.server." + Version +".NBTTagCompound");
			return c.newInstance();
		}catch(Exception ex){
			//System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
			error = ex.toString();
			return null;
		}
	}
	
	private static Object setNBTTag(Object NBTTag, Object NMSItem){
		try{
			java.lang.reflect.Method method;
			method = NMSItem.getClass().getMethod("setTag", NBTTag.getClass());
			method.invoke(NMSItem, NBTTag);
			return NMSItem;
		}catch(Exception ex){
			error = ex.toString();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object getNMSItemStack(ItemStack item) { // Disabled Due to Error Spam On Console
		Class cis = getCraftItemstack();
		java.lang.reflect.Method method;
		try {
			method = cis.getMethod("asNMSCopy", ItemStack.class);
			Object answer = method.invoke(cis, item);
			return answer;
		} catch (Exception e) {
			error = e.toString();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	private static ItemStack getBukkitItemStack(Object item){
		@SuppressWarnings("rawtypes")
		Class cis = getCraftItemstack();
		java.lang.reflect.Method method;
		try {
			method = cis.getMethod("asBukkitCopy", item.getClass());
			Object answer = method.invoke(cis, item);
			return (ItemStack) answer;
		} catch (Exception e) {
			error = e.toString();
		}	
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	private static Object getNBTTagCompound(Object nmsitem){
		@SuppressWarnings("rawtypes")
		Class c = nmsitem.getClass();
		java.lang.reflect.Method method;
		try {
			method = c.getMethod("getTag");
			Object answer = method.invoke(nmsitem);
			return answer;
		} catch (Exception e) {
			error = e.toString();
		}	
		return null;
	}


	public static ItemStack setString(ItemStack item, String key, String Text){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("setString", String.class, String.class);
			method.invoke(nbttag, key, Text);
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		}catch(Exception ex){
			error = ex.toString();
		}
		return item;
	}
	
	public static String getString(ItemStack item, String key){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("getString", String.class);
			return (String) method.invoke(nbttag, key);
		}catch(Exception ex){
			error = ex.toString();
		}
		return null;
	}
	
	public static ItemStack setInt(ItemStack item, String key, Integer i){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("setInt", String.class, int.class);
			method.invoke(nbttag, key, i);
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		}catch(Exception ex){
			error = ex.toString();
		}
		return item;
	}

	public static Integer getInt(ItemStack item, String key){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("getInt", String.class);
			return (Integer) method.invoke(nbttag, key);
		}catch(Exception ex){
			error = ex.toString();
		}
		return null;
	}
	
	public static ItemStack setDouble(ItemStack item, String key, Double d){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("setDouble", String.class, double.class);
			method.invoke(nbttag, key, d);
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		}catch(Exception ex){
			error = ex.toString();
		}
		return item;
	}
	
	public static Double getDouble(ItemStack item, String key){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("getDouble", String.class);
			return (Double) method.invoke(nbttag, key);
		}catch(Exception ex){
			error = ex.toString();
		}
		return null;
	}
	
	public static ItemStack setBoolean(ItemStack item, String key, Boolean d){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("setBoolean", String.class, boolean.class);
			method.invoke(nbttag, key, d);
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		}catch(Exception ex){
			error = ex.toString();
		}
		return item;
	}

	public static Boolean getBoolean(ItemStack item, String key){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("getBoolean", String.class);
			return (Boolean) method.invoke(nbttag, key);
		}catch(Exception ex){
			error = ex.toString();
		}
		return null;
	}
	
	public static Boolean hasKey(ItemStack item, String key){
		Object nmsitem = getNMSItemStack(item);
		if(nmsitem == null){
			//System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if(nbttag == null)
			nbttag = getnewNBTTag();
		java.lang.reflect.Method method;
		try{
			method = nbttag.getClass().getMethod("hasKey", String.class);
			return (Boolean) method.invoke(nbttag, key);
		}catch(Exception ex){
			error = ex.toString();
		}
		return null;
	}
	
}
