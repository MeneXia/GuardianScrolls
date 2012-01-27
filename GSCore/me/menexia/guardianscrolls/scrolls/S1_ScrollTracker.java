package me.menexia.guardianscrolls.scrolls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.menexia.guardianscrolls.GuardianScrolls;
import me.menexia.guardianscrolls.Scroll;

public class S1_ScrollTracker extends Scroll {
	
	public S1_ScrollTracker() {
		type(1);
		amtuses("SHOULDBEONE");
		permission("GuardianScrolls.use.ScrollTracker");
		fullscrollName("ScrollTracker");
		description("A quicker way to access your scrolls.");
		configBase("SCROLLS.SCROLL_TRACKER.");
		needsManual(false);
		canEquip(false);
	}
	
	public void onRightClick(Player player) {
		if (player.getItemInHand().getDurability() > 1) {
			player.chat("/gs use");
		} else {
			GuardianScrolls core = (GuardianScrolls)Bukkit.getServer().getPluginManager().getPlugin("GuardianScrolls");
			Set<Short> usesSet = core.getHM(player).keySet();
			List<Short> usesList = new ArrayList<Short>(usesSet);
			Iterator<Short> it = usesList.iterator();
			try {
				int cycled = (int)it.next();
				core.activeHM.put(player.getName(), cycled);
				Scroll toget = core.manager.scrollmatch.get(cycled);
				String scrollName = "null";
				if (toget != null) {
					scrollName = toget.fullscrollName;
				}
				player.sendMessage("Equipped " + scrollName + "."); //TODO: proper message
			} catch (Exception e1) { // NoSuchElementException
				try {
					int restart = usesList.get(0);
					core.activeHM.put(player.getName(), restart);
				} catch (IndexOutOfBoundsException e2) {
					player.sendMessage(ChatColor.RED + "You have no stored scrolls!"); //TODO: Proper message
				}
			}
		}
	}
	
	public void onLeftClick(Player player) {
		player.chat("/gs menu");
	}

	@Override
	public void onRightClick(Player player, PlayerInteractEntityEvent event) {
		// TODO Auto-generated method stub
		
	}

}
