package me.menexia.guardianscrolls.listeners;

import me.menexia.guardianscrolls.GSManager;
import me.menexia.guardianscrolls.GuardianScrolls;
import me.menexia.guardianscrolls.Scroll;
import me.menexia.guardianscrolls.VariableSwitcher;

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
			short scrollType = item.getDurability();
			String nameofScroll = VariableSwitcher.getFullScrollName(player, scrollType);
			player.sendMessage(ChatColor.GRAY + nameofScroll);
		}
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Scroll found = manager.scrollEquipped.get(player);
			
			if (event.hasItem() && event.getItem().getTypeId() == 339 && event.getItem().getDurability() > 1) {
				String durability = VariableSwitcher.getFullScrollName(player, event.getItem().getDurability());
				Scroll ciara = manager.scrollmatch.get(durability);
				if (ciara != null) {
					ciara.onRightClick(player);
				}
				
			} else if (event.hasItem() && event.getItem().getTypeId() == 339 && event.getItem().getDurability() == 1) {  // whoa, a scrolltracker?
				Scroll st = manager.scrollmatch.get("ScrollTracker");
				if (st != null) {
					st.onRightClick(player);
				}
				
			} else if (found != null) {
				found.onRightClick(event.getPlayer());
				// equipped scroll? do above.
				
				
				
			} else if (event.hasItem() && event.getItem().getTypeId() == 340 && event.getItem().getDurability() > 1) {
				//TODO: show info on book
				String toGet = VariableSwitcher.getFullScrollName(player, event.getItem().getDurability());
				Scroll mica = manager.scrollmatch.get(toGet);
				String description = "The manual is empty.";
				if (mica != null) {
					description = mica.description;
				}
				player.sendMessage(description);
			}
			
			// else if (!manager.getScrolls().contains(found)) { // if the scroll right clicked has another use asides from right click, it will not show below message
			//	player.sendMessage(ChatColor.RED + "No scroll equipped.");
			//}
			
		} else if (event.getAction() == Action.LEFT_CLICK_AIR|event.getAction() == Action.LEFT_CLICK_BLOCK && event.hasItem() && event.getItem().getTypeId() == 339) {
			String durability = VariableSwitcher.getFullScrollName(player, event.getItem().getDurability());
			Scroll orchestra = manager.scrollmatch.get(durability);
			if (orchestra != null) {
				orchestra.onLeftClick(player);
			}
		}
	}
	
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Scroll found = manager.scrollEquipped.get(player);
		if (found != null) {
			found.onRightClick(player, event);
			// equipped scroll? do above.
		}
	}
	
/* CRAZY FREAKING DEBUG...	public void onItemHeldChange(PlayerItemHeldEvent event) { - used to be onPlayerHeldItem.. i learned that methods in a listener must be correct in order to register properly o.O
 * inspired by nisovin's BookWorm plugin
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItem(event.getNewSlot());
		if (item != null) {player.sendMessage("passed null check");
		if (item.getType() == Material.PAPER) {player.sendMessage("passed material check");
		if (item.getDurability() != 0) {player.sendMessage("passed durability check");
		if (item.getDurability() == 26) {player.sendMessage(ChatColor.RED + "it's a thief scroll!");
		}}}}}*/
	
	/**
	 * Saves the HashMap into a file when a player quits.
	 */
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.saveScrollData();
	}
	
}
