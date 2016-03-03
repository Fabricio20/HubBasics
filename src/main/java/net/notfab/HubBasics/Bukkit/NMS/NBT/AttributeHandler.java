package net.notfab.HubBasics.Bukkit.NMS.NBT;

import org.bukkit.inventory.ItemStack;

import net.notfab.HubBasics.Bukkit.NMS.NBT.Attributes.Attribute;
import net.notfab.HubBasics.Bukkit.NMS.NBT.Attributes.AttributeType;

public class AttributeHandler {

	public static ItemStack addSpeed(ItemStack s, double i) {
		Attributes attributes = new Attributes(s);
        attributes.add(Attribute.newBuilder().
                name("Speed").
                type(AttributeType.GENERIC_MOVEMENT_SPEED).amount(i).
                build()
            );
        return attributes.getStack();
	}
	
	public static ItemStack addHealth(ItemStack s, double i) {
		Attributes attributes = new Attributes(s);
        attributes.add(Attribute.newBuilder().
                name("Health").
                type(AttributeType.GENERIC_MAX_HEALTH).amount(i).
                build()
            );
        return attributes.getStack();
	}
	
	public static ItemStack addKnockBackRes(ItemStack s, double i) {
		Attributes attributes = new Attributes(s);
        attributes.add(Attribute.newBuilder().
                name("KnockBack").
                type(AttributeType.GENERIC_KNOCKBACK_RESISTANCE).amount(i).
                build()
            );
        return attributes.getStack();
	}
	
	public static ItemStack addDamage(ItemStack s, double i) {
		Attributes attributes = new Attributes(s);
        attributes.add(Attribute.newBuilder().
                name("Damage").
                type(AttributeType.GENERIC_ATTACK_DAMAGE).amount(i).
                build()
            );
        return attributes.getStack();
	}
	
	public static ItemStack addFollowRange(ItemStack s, double i) {
		Attributes attributes = new Attributes(s);
        attributes.add(Attribute.newBuilder().
                name("FollowRange").
                type(AttributeType.GENERIC_FOLLOW_RANGE).amount(i).
                build()
            );
        return attributes.getStack();
	}
}
