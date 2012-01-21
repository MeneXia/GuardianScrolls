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
import me.menexia.guardianscrolls.VariableSwitcher;

public class S1_ScrollTracker extends Scroll {
	
	public S1_ScrollTracker() {
		setFullName("ScrollTracker");
		getScrollUses("someconfig.getsomething"); // N/A
		getPermission("GuardianScrolls.use.ScrollTracker");
		getShortName("ScrollTracker");
		setDescription("ScrollTracker is a tool used to organize scrolls.");
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
				String scrollName = VariableSwitcher.getShortScrollName(player, cycled);
				core.activeHM.put(player.getName(), scrollName);
				player.sendMessage("Equipped " + scrollName + " scroll."); //TODO: proper message
			} catch (Exception e1) { // NoSuchElementException
				try {
					int restart = usesList.get(0);
					String scrollName = VariableSwitcher.getShortScrollName(player, restart);
					core.activeHM.put(player.getName(), scrollName);
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
