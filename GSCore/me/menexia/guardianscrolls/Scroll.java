package me.menexia.guardianscrolls;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public abstract class Scroll {
	public abstract void onRightClick(Player player);
	public abstract void onLeftClick(Player player);
	public abstract void onRightClick(Player player, PlayerInteractEntityEvent event);
	public Integer type;
	public Integer amtuses;
	public String permission;
	public String fullscrollName;
	public String description;
	public String configBase;
	public Boolean needsManual;
	public Boolean canEquip;
	
	public void type(Integer durability) {
		type = durability;
	}
	
	public void amtuses(String config_usesPerScroll) {
		amtuses = GuardianScrolls.plugin.getConfig().getInt(config_usesPerScroll);
	}
	
	public void permission(String config_permission) {
		permission = config_permission;
	}
	
	public void fullscrollName(String shortName) {
		fullscrollName = shortName;
	}
	
	public void description(String config_description) {
		description = config_description;
	}
	
	public void configBase(String configBase1) {
		configBase = configBase1;
	}
	
	public void needsManual(Boolean needsManual1) {
		needsManual = needsManual1;
	}
	
	public void canEquip(Boolean equippable) {
		canEquip = equippable;
	}

}
