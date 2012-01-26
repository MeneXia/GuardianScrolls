package me.menexia.guardianscrolls.scrolls;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.menexia.guardianscrolls.Scroll;

public class S4_NightTime extends Scroll {
	
	public S4_NightTime() {
		type(4);
		amtuses("SHOULDBEONE");
		permission("GuardianScrolls.use.Nighttime");
		fullscrollName("Nighttime Scroll");
		description("Makes it NightTime.");
		configBase("SCROLLS.NIGHTTIME_SCROLL.");
		needsManual(true);
		canEquip(false);
	}

	@Override
	public void onRightClick(Player player) {
		if (!player.hasPermission("GuardianScrolls.use.Daytime")) return;
		ItemStack item = player.getItemInHand();
		
		if (item.getTypeId() == 339 && item.getDurability() == 4) {
			player.sendMessage(ChatColor.YELLOW + "NightTime Scroll used, altered time mechanics!");
			player.getWorld().setTime(14000);
			if (player.getItemInHand().getAmount() == 1) {
				player.setItemInHand(null);
				
				// If not one, subtracts one from the current amount.
			} else {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}
		}
		
	}

	@Override
	public void onLeftClick(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRightClick(Player player, PlayerInteractEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
}
