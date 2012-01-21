package me.menexia.guardianscrolls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.menexia.guardianscrolls.scrolls.S1_ScrollTracker;
import me.menexia.guardianscrolls.scrolls.S2_Thief;
import me.menexia.guardianscrolls.scrolls.S3_DayTime;
import me.menexia.guardianscrolls.scrolls.S4_NightTime;

import org.bukkit.entity.Player;

public class GSManager {
	public HashMap<String, Scroll> scrollmatch = new HashMap<String, Scroll>();
	public ArrayList<Scroll> scrolls = new ArrayList<Scroll>();
	
	public GSManager() {
		generate();
	}
	
	public void put(Scroll scroll) {
		scrollmatch.put(scroll.fullscrollName, scroll);
		scrolls.add(scroll);
	}
	
	public ArrayList<Scroll> getScrolls() {
		return scrolls;
	}
	
	// Place all new scrolls here in the same format
	private void generate() {
		//TODO: add scrolls.
		put(new S1_ScrollTracker());
		put(new S2_Thief());
		put(new S3_DayTime());
		put(new S4_NightTime());
	}
	
	public Map<Player, Scroll> scrollEquipped = new HashMap<Player, Scroll>();
	
	public void setEquipped(Player player, String[] args) {
		Scroll found = scrollmatch.get(args[1].toLowerCase());
		if (found != null) {
			scrollEquipped.put(player, found);
		}
	}

}
