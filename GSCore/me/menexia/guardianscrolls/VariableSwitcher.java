package me.menexia.guardianscrolls;


import org.bukkit.entity.Player;

public class VariableSwitcher {
	private static GuardianScrolls plugin;
	public VariableSwitcher(GuardianScrolls instance) {
		plugin = instance;
	}
	
	public static Integer getScrollUses(Player player) {
		int amtuses = player.getItemInHand().getDurability();
		switch (amtuses) {
		case 2: amtuses = plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.USES_PER_SCROLL");
		break;
		case 3: amtuses = 3;
		break;
		case 4: amtuses = 4;
		//TODO: Add more cases.
		}
		return amtuses;
	}
	
	public static String getPermission(Player player) {
		int amtuses = player.getItemInHand().getDurability();
		String permVar = "null";
		switch (amtuses) {
		case 2: permVar = "GuardianScrolls.use.Thief";
		break;
		case 3: permVar = "GuardianScrolls.use.DayTime";
		break;
		case 4: permVar = "GuardianScrolls.use.NightTime";
		break;
		}
		return permVar;
	}
	
	public static String getFullScrollName(Player player, Short durability) {
		String fullName = "null";
		switch (durability) {
		case 1: fullName = "ScrollTracker";
		break;
		case 2: fullName = "Thief Scroll";
		break;
		case 3: fullName = "DayTime Scroll";
		break;
		case 4: fullName = "NightTime Scroll";
		break;
		}
		return fullName;
	}
	
	public static String getShortScrollName(Player player, Integer index) {
		String shortName = "null";
		switch (index) {
		case 2: shortName = "Thief";
		break;
		}
		return shortName;
	}

}
