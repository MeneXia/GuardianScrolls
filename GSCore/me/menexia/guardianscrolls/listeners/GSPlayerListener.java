package me.menexia.guardianscrolls.listeners;

import me.menexia.guardianscrolls.GSManager;
import me.menexia.guardianscrolls.GuardianScrolls;
import me.menexia.guardianscrolls.Scroll;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class GSPlayerListener extends PlayerListener {
	private GuardianScrolls plugin;
	private final GSManager manager;
	public GSPlayerListener(GuardianScrolls instance, GSManager manager) {
		plugin = instance;
		this.manager = manager;
	}
	
	public void onItemHeldChange(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItem(event.getNewSlot());
		if (item != null && item.getType() == Material.PAPER && item.getDurability() != 0) {
			short type = item.getDurability();
			Scroll ellinor = manager.scrollmatch.get(type);
			String nameofScroll = "null";
			if (ellinor != null) {
				nameofScroll = ellinor.fullscrollName;
			}
			player.sendMessage(ChatColor.GRAY + nameofScroll);
		}
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int type = event.getItem().getDurability();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Scroll found = manager.scrollEquipped.get(player);
			
			if (event.hasItem() && event.getItem().getTypeId() == 339 && type >= 1) { //including scrolltracker == 1
				Scroll ciara = manager.scrollmatch.get(type);
				if (ciara != null) {
					ciara.onRightClick(player);
				}
				
			} else if (event.hasItem() && event.getItem().getTypeId() == 340 && event.getItem().getDurability() >/*=*/ 1) {
				Scroll mica = manager.scrollmatch.get(type);
				String description = "The manual is empty.";
				if (mica != null) {
					description = mica.description;
				}
				player.sendMessage(description);
				
			}  else if (found != null) {
				found.onRightClick(event.getPlayer());
				// it is correct for this to be last check, it executes whatever is equipped.
			}
			
		} else if (event.getAction() == Action.LEFT_CLICK_AIR|event.getAction() == Action.LEFT_CLICK_BLOCK && event.hasItem() && event.getItem().getTypeId() == 339) {
			Scroll orchestra = manager.scrollmatch.get(type);
			if (orchestra != null) {
				orchestra.onLeftClick(player);
			}
			
			//TODO: Needs ideas for left click book... but if there isn't any, avoid this.
			
		}
	}
	
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) { //only for when a player right clicks another player/entity (i.e. thief scroll)
		Player player = event.getPlayer();
		Scroll found = manager.scrollEquipped.get(player);
		if (found != null) {
			found.onRightClick(player, event);
			// equipped scroll? do above.
		}
	}
	
	/**
	 * Saves the HashMap into a file when a player quits.
	 */
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.saveScrollData();
	}
	
}
