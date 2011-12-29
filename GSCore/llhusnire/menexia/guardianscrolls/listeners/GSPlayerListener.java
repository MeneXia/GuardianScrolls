package llhusnire.menexia.guardianscrolls.listeners;

import java.io.File;

import llhusnire.menexia.guardianscrolls.GSDataHandler;
import llhusnire.menexia.guardianscrolls.GuardianScrolls;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GSPlayerListener extends PlayerListener {
	public static GuardianScrolls plugin;
	public GSPlayerListener(GuardianScrolls instance) {
		plugin = instance;
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		try {
			GSDataHandler.save(plugin.scrollHM, "plugins" + File.separator + "GuardianScrolls" + File.separator + "scrolls.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
