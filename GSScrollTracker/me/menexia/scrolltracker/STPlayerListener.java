package me.menexia.scrolltracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import me.menexia.guardianscrolls.GuardianScrolls;
import me.menexia.guardianscrolls.VariableSwitcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class STPlayerListener extends PlayerListener {
	public static ScrollTracker plugin;
    public STPlayerListener(ScrollTracker instance) {
        plugin = instance;
    }
    
    public void onPlayerInteract(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
    	GuardianScrolls core = (GuardianScrolls)Bukkit.getServer().getPluginManager().getPlugin("GuardianScrolls");
    	if (!player.hasPermission("GuardianScrolls.use.ScrollTracker")) return;
    	
    		if (event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getTypeId() == 339 && player.getItemInHand().getDurability() > 1) {
    			player.chat("/gs use");
    		} else if (event.getAction() == Action.LEFT_CLICK_AIR && /*player.getItemInHand() != null &&*/ player.getItemInHand().getTypeId() == 339 && player.getItemInHand().getDurability() == 1) {
    			player.chat("/gs menu");
    		} else if (event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getTypeId() == 339 && player.getItemInHand().getDurability() == 1) {
    			Set<Short> usesSet = core.getHM(player).keySet();
				List<Short> usesList = new ArrayList<Short>(usesSet);
    			Iterator<Short> it = usesList.iterator();
    			try {
    				int cycled = (int)it.next();
    				String scrollName = VariableSwitcher.getShortScrollName(player, cycled);
    				core.activeHM.put(player.getName(), scrollName);
    				player.sendMessage("Equipped " + scrollName + " scroll."); //TODO: proper message
    			} catch (Exception e1) { // NoSuchElementException
    				try {
    					int restart = usesList.get(0);
    					String scrollName = null;
    					switch (restart) {
    					case 2: scrollName = "Thief";
    					}
    					core.activeHM.put(player.getName(), scrollName);
    				} catch (IndexOutOfBoundsException e2) {
    					player.sendMessage(ChatColor.RED + "You have no stored scrolls!"); //TODO: Proper message
    				}
    			}
    		}
    		
    }

}
