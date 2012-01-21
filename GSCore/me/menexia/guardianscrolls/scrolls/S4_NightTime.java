package me.menexia.guardianscrolls.scrolls;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.menexia.guardianscrolls.Scroll;

public class S4_NightTime extends Scroll {
	
	public S4_NightTime() {
		setFullName("NightTime Scroll");
		getScrollUses("SHOULDBEONE");
		getPermission("GuardianScrolls.use.Nighttime");
		getShortName("Nighttime");
		setDescription("Makes it NightTime.");
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
