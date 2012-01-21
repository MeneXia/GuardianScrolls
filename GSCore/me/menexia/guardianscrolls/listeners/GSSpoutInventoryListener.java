package me.menexia.guardianscrolls.listeners;

import me.menexia.guardianscrolls.GuardianScrolls;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryListener;

public class GSSpoutInventoryListener extends InventoryListener {
	
	public GSSpoutInventoryListener(GuardianScrolls plugin) {
		plugin.getServer().getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, this,
				Event.Priority.Monitor, plugin);
	}
	
	@Override
	public void onInventoryClick(InventoryClickEvent event) {
		ItemStack itemClicked = event.getItem();
		ItemStack itemHolding = event.getCursor();
		if (itemClicked != null && itemClicked.getType() == Material.PAPER) {
			if (itemHolding != null && itemHolding.getType() == Material.PAPER &&
					itemClicked.getDurability() != itemHolding.getDurability()) {
				event.setCancelled(true);
			}
		}
	}

}
