package menexia.surpasses.time;

import java.util.logging.Logger;

import me.menexia.guardianscrolls.GuardianScrolls;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Time extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	private final RecipeManager rm = new RecipeManager(this);
	
	int dsA = 0;	int nsA = 0;
    int dsB = 0;	int nsB = 0;
    int dsC = 0;	int nsC = 0;
    int dsD = 0;	int nsD = 0;
    int dsE = 0;	int nsE = 0;
    int dsF = 0;	int nsF = 0;
    int dsG = 0;	int nsG = 0;
    int dsH = 0;	int nsH = 0;
    int dsI = 0;	int nsI = 0;
    int dsResult = 339;	int nsResult = 339;
    int dsAmount = 1;	int nsAmount = 1;
    short dsMetaData = 3; short nsMetaData = 4;
    int dsManual = 340; int nsManual = 340;
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		Plugin GSCore = getServer().getPluginManager().getPlugin("GuardianScrolls");
    	// checks if core is present. if not, disables all other plugins.
    	if (GSCore == null) {
    		this.logger.info("[GSTime] Did not detect plugin: GuardianScrolls.");
    		this.logger.info("[GSTime] Disabling all other add-ons to GuardianScrolls.");
    		for (Plugin plugin : pm.getPlugins()) {
    			if (plugin.getDescription().getName().startsWith("GS")) {
    				pm.disablePlugin(plugin);
    				return;
    			}
    		}
    	}
    	
    	
		pm.registerEvent(Event.Type.PLAYER_INTERACT, new TimePlayerListener(this), Event.Priority.Normal, this);
		sweet_kiwis(); // always remember to restate your variables according to config
		getServer().addRecipe(rm.getDS());
		getServer().addRecipe(rm.getNS());
		getServer().addRecipe(rm.getDSManual());
		getServer().addRecipe(rm.getNSManual());
	}
	
	public void sweet_kiwis() {
    	GuardianScrolls plugin = (GuardianScrolls)getServer().getPluginManager().getPlugin("GuardianScrolls");
    	if(plugin != null && plugin.isEnabled()) {
    	
    	dsA = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.A");
    	dsB = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.B");
    	dsC = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.C");
    	dsD = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.D");
    	dsE = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.E");
    	dsF = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.F");
    	dsG = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.G");
    	dsH = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.H");
    	dsI = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.I");
    	dsResult = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.RESULT");
    	dsAmount = plugin.getConfig().getInt("SCROLLS.DAYTIME_SCROLL.AMOUNT_FROM_CRAFT");
    	
    	nsA = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.A");
    	nsB = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.B");
    	nsC = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.C");
    	nsD = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.D");
    	nsE = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.E");
    	nsF = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.F");
    	nsG = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.G");
    	nsH = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.H");
    	nsI = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.I");
    	nsResult = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.RESULT");
    	nsAmount = plugin.getConfig().getInt("SCROLLS.NIGHTTIME_SCROLL.AMOUNT_FROM_CRAFT");
    	}
    }

}
