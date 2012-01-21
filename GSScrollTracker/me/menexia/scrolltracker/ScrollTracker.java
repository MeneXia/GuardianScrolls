package me.menexia.scrolltracker;

import java.util.logging.Logger;

import me.menexia.guardianscrolls.GuardianScrolls;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ScrollTracker extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	private final RecipeManager rm = new RecipeManager(this);
	private final STPlayerListener playerListener = new STPlayerListener(this);
	
	short metadata = 1;
    int A = 0;
    int B = 0;
    int C = 0;
    int D = 0;
    int E = 0;
    int F = 0;
    int G = 0;
    int H = 0;
    int I = 0;
    int result = 339;
    int amount = 1;
	
	@Override
	public void onDisable() {
		// this.logger.info("[ScrollTracker] disabled!");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		Plugin GSCore = getServer().getPluginManager().getPlugin("GuardianScrolls");
    	// checks if core is present. if not, disables all other plugins.
    	if (GSCore == null) {
    		this.logger.info("[GSScrollTracker] Did not detect plugin: GuardianScrolls.");
    		this.logger.info("[GSScrollTracker] Disabling all other add-ons to GuardianScrolls.");
    		for (Plugin plugin : pm.getPlugins()) {
    			if (plugin.getDescription().getName().startsWith("GS")) {
    				pm.disablePlugin(plugin);
    				return;
    			}
    		}
    	}
    	ivi();
    	getServer().addRecipe(rm.getTS());
    	pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
	}
	
	public void ivi() {
		GuardianScrolls plugin = (GuardianScrolls)getServer().getPluginManager().getPlugin("GuardianScrolls");
		A = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.A");
    	B = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.B");
    	C = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.C");
    	D = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.D");
    	E = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.E");
    	F = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.F");
    	G = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.G");
    	H = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.H");
    	I = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.I");
    	result = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.RESULT");
    	amount = plugin.getConfig().getInt("SCROLLS.SCROLL_TRACKER.AMOUNT_FROM_CRAFT");
	}

}
