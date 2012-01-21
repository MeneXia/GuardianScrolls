package me.menexia.guardianscrolls;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public abstract class Scroll {
	public abstract void onRightClick(Player player);
	public abstract void onLeftClick(Player player);
	public abstract void onRightClick(Player player, PlayerInteractEntityEvent event);
	public String fullscrollName;
	public String shortscrollName;
	public String amtuses;
	public String permission;
	public String description;
	
	public void setFullName(String fullName) {
		fullscrollName = fullName;
	}
	
	public void getScrollUses(String config_usesPerScroll) {
		amtuses = config_usesPerScroll;
	}
	
	public void getPermission(String config_permission) {
		permission = config_permission;
	}
	
	public void getShortName(String shortName) {
		shortscrollName = shortName;
	}
	
	public void setDescription(String config_description) {
		description = config_description;
	}

}
