package menexia.surpasses.time;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class TimePlayerListener extends PlayerListener {
	public static Time plugin;
	public TimePlayerListener(Time instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("GuardianScrolls.use.Daytime")) return;
		if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
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
		} else
		
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

}
