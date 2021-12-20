package net.notfab.hubbasics.spigot.nms.nbt;

import net.notfab.hubbasics.spigot.HubBasics;
import net.notfab.hubbasics.spigot.nms.CraftBukkitVersion;
import net.notfab.hubbasics.spigot.nms.NMSVersion;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

@SuppressWarnings("ConstantConditions")
public class NbtReflectionBackend implements NBTBackend {

    @SuppressWarnings("rawtypes")
    private Class getCraftItemstack() {
        try {
            return Class.forName("org.bukkit.craftbukkit." +
                    HubBasics.getInstance().getNMS().getRunningNMS() + ".inventory.CraftItemStack");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    private Object getNewNBTTag() {
        try {
            NMSVersion version = HubBasics.getInstance().getNMS();
            Class clazz;
            if (version.getRunningVersion().isOlder(CraftBukkitVersion.v1_17_X)) {
                // 1.17 added NBTTag outside of NMS.
                clazz = Class.forName("net.minecraft.server." + version.getRunningNMS() + ".NBTTagCompound");
            } else {
                clazz = Class.forName("net.minecraft.nbt.NBTTagCompound");
            }
            return clazz.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Object setNBTTag(Object nbtTag, Object nmsItem) {
        try {
            Method method;
            method = nmsItem.getClass().getMethod("setTag", nbtTag.getClass());
            method.invoke(nmsItem, nbtTag);
            return nmsItem;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object getNMSItemStack(ItemStack item) {
        Class cis = getCraftItemstack();
        try {
            Method method = cis.getMethod("asNMSCopy", ItemStack.class);
            return method.invoke(cis, item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private ItemStack getBukkitItemStack(Object item) {
        Class cis = getCraftItemstack();
        try {
            Method method = cis.getMethod("asBukkitCopy", item.getClass());
            Object answer = method.invoke(cis, item);
            return (ItemStack) answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object getNBTTagCompound(Object nmsitem) {
        Class c = nmsitem.getClass();
        java.lang.reflect.Method method;
        try {
            method = c.getMethod("getTag");
            return method.invoke(nmsitem);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemStack setString(ItemStack item, String key, String Text) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("setString", String.class, String.class);
            method.invoke(nbttag, key, Text);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    @Override
    public String getString(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("getString", String.class);
            return (String) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemStack setInt(ItemStack item, String key, Integer i) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("setInt", String.class, int.class);
            method.invoke(nbttag, key, i);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    @Override
    public Integer getInt(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("getInt", String.class);
            return (Integer) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemStack setDouble(ItemStack item, String key, Double d) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("setDouble", String.class, double.class);
            method.invoke(nbttag, key, d);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    @Override
    public Double getDouble(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("getDouble", String.class);
            return (Double) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemStack setBoolean(ItemStack item, String key, Boolean d) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("setBoolean", String.class, boolean.class);
            method.invoke(nbttag, key, d);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    @Override
    public Boolean getBoolean(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return null;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;

        try {
            method = nbttag.getClass().getMethod("getBoolean", String.class);
            return (Boolean) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasKey(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) return false;
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) nbttag = getNewNBTTag();
        Method method;
        try {
            method = nbttag.getClass().getMethod("hasKey", String.class);
            return (Boolean) method.invoke(nbttag, key);
        } catch (Exception ex) {
            return false;
        }
    }

}
