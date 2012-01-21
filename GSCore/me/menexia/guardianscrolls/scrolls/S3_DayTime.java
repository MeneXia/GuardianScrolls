package me.menexia.guardianscrolls.scrolls;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.menexia.guardianscrolls.Scroll;

public class S3_DayTime extends Scroll {
	
	public S3_DayTime() {
		setFullName("DayTime Scroll");
		getScrollUses("SHOULDBEONE");
		getPermission("GuardianScrolls.use.Daytime");
		getShortName("Daytime");
		setDescription("Makes it Daytime.");
	}

	@Override
	public void onRightClick(Player player) {
		if (!player.hasPermission("GuardianScrolls.use.Daytime")) return;
		ItemStack item = player.getItemInHand();
		
		if (item.getTypeId() == 339 && item.getDurability() == 3) {
			player.sendMessage(ChatColor.YELLOW + "DayTime Scroll used, altered time mechanics!");
			player.getWorld().setTime(1000);
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
