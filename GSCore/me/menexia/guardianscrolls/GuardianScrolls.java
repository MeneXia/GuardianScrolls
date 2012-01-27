package me.menexia.guardianscrolls;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.menexia.guardianscrolls.listeners.GSPlayerListener;
import me.menexia.guardianscrolls.listeners.GSSpoutInventoryListener;
import me.menexia.guardianscrolls.scrolls.S2_ThiefExtra;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * GuardianScrolls.java - Main class for scrolls.
 * @author MeneXia - Xavier Luis Ablaza
 * @version 0.3.4
 */
public class GuardianScrolls extends JavaPlugin {
	public static GuardianScrolls plugin;
	public Map<String, Map<Short, Integer>> scrollHM = new HashMap<String, Map<Short, Integer>>();
	public Map<String, Integer> activeHM = new HashMap<String, Integer>();
	public HashMap<Plugin, String> ScrollNames = new HashMap<Plugin, String>();
	public final Logger logger = Logger.getLogger("Minecraft");
	
	static boolean ENABLED_FOR_ALL = false;
	protected static boolean SPOUT_ENABLED = false;
	protected static String STACK_BY_DATA_FN = "a";
	protected static boolean USE_SPOUT_FEATURES = true;
	
	private GSCommandExecutor cmdExecutor;
	public final GSManager manager = new GSManager();
	private final GSPlayerListener playerListener = new GSPlayerListener(this, manager);
	private final RecipeManager rm = new RecipeManager(this, manager);
	
	private FileConfiguration skillsconfig = null;
	private File skillsconfigfile = null;
	private File mainConfigfile = new File(getDataFolder(), "config.yml");
	
@Override
public void onDisable() {
	this.ScrollNames.clear();
	saveScrollData();
	this.logger.info("[GuardianScrolls] is now disabled!");
}


// onEnable is called when the plugin is being enabled.
@Override
public void onEnable() {
	try {
		if (!mainConfigfile.exists() || mainConfigfile == null) {
			getDataFolder().mkdir();
			new File(getDataFolder(), "config.yml");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			if (skillsconfigfile == null) {
				getDataFolder().mkdir();
				getskillsconfig().options().copyDefaults(true);
				saveskillsconfig();
			}
		}
	} catch (Exception e1){
		e1.printStackTrace();
	}
	
	loadScrollData();
	
	ENABLED_FOR_ALL = this.getConfig().getBoolean("GENERAL_OPTIONS.ENABLED_FOR_ALL");
	USE_SPOUT_FEATURES = this.getConfig().getBoolean("GENERAL_OPTIONS.USE_SPOUT_FEATURES");
	
	PluginManager a = this.getServer().getPluginManager();
	
	for (Plugin plugin : a.getPlugins()) {
		if (plugin.getDescription().getName().startsWith("GS")) {
			if (!getServer().getPluginManager().isPluginEnabled(plugin)) {
			a.enablePlugin(plugin);
			}
			String name = (String)plugin.getDescription().getName();
			this.ScrollNames.put(plugin, name);
		}
	}
	
	if (USE_SPOUT_FEATURES) {
		Plugin spout = getServer().getPluginManager().getPlugin("Spout");
		if (spout != null) {
			SPOUT_ENABLED = true;
			new GSSpoutInventoryListener(this);
			this.logger.info("[GuardianScrolls] Spout detected, using extra features!");
		} else {
			SPOUT_ENABLED = false;
		}
	}
	
	try {
		boolean ok = false;
		try {
			// attempt to make paper with different damage/durability values stack separately
			Method method = net.minecraft.server.Item.class.getDeclaredMethod(STACK_BY_DATA_FN, boolean.class);
            if (method.getReturnType() == net.minecraft.server.Item.class) {
                    method.setAccessible(true);
                    method.invoke(net.minecraft.server.Item.PAPER, true);
                    ok = true;
            }
    } catch (Exception e) {
    }
    if (!ok) {
            // otherwise limit stack size to 1
            Field field = net.minecraft.server.Item.class.getDeclaredField("maxStackSize");
            field.setAccessible(true);
            field.setInt(net.minecraft.server.Item.PAPER, 1);
    }
} catch (Exception e) {
    e.printStackTrace();
}
	
	cmdExecutor = new GSCommandExecutor(this, manager);
	getCommand("gs").setExecutor(cmdExecutor);
	
	// input recipes here
	for (int recipeNumber = 1; recipeNumber<5; recipeNumber++) {
		rm.getRecipe((short) recipeNumber);
		if (rm.needsManual(recipeNumber) == false) continue;
		rm.getRecipeManual((short) recipeNumber);
	}
	
	a.registerEvent(Event.Type.PLAYER_ITEM_HELD, playerListener, Event.Priority.Normal, this);
	a.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
	a.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
	
	/*for (Object o : getConfig().getList("ListofStuff.NotUsableScrolls")) {
		if (o instanceof String) {
			notUsable.add((Integer) o);
		}
	}*/
	
	try {
		S2_ThiefExtra.readProperties();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.logger.info("[GuardianScrolls] Alpha version " + this.getDescription().getVersion() + " by MeneXia is enabled!");
	
} // end of onEnable

/**
 * Gets the player's name and puts him into the HashMap.
 * @param playername Player's name.
 * @return A Map<Short, Integer> as a value from key: playername.
 */
public Map<Short, Integer> getHM(String playername) {
    Map<Short, Integer> map = scrollHM.get(playername);
    if (map == null) {
        map = new HashMap<Short, Integer>();
        scrollHM.put(playername, map);
    }
    return map;
}

/**
 * Gets the player's name and puts him into the HashMap.
 * @param player
 * @return The name of a player as a String.
 */
public Map<Short, Integer> getHM(Player player) {
    return getHM(player.getName());
}

/**
 * Gets a certain player's Integer from the Durability (Short).
 * @param player The player referenced.
 * @param durability The type of scroll you want to grab the Integer from.
 * @return A value of how many uses of that scroll (durability) you have left.
 */
public int getHMCount(Player player, short durability) {
    Integer value = getHM(player).get(durability); //not sure if this can be null, may need contains check above
    if (value != null) return (int) value;
    return 0;
}

/**
 * Gets the durability of the player's item in hand.
 * @param player The player referenced.
 * @return The durability (short) of the item held.
 */
public short getTypeScroll(Player player) {
	return player.getItemInHand().getDurability();
}

/**
 * Loads the "skills.yml" file. If it does not exist, it copies defaults from GuardianScrolls.jar
 */
public void reloadskillsconfig() {
	if (skillsconfigfile == null) {
		skillsconfigfile = new File(getDataFolder(), "skills.yml");
	}
	skillsconfig = YamlConfiguration.loadConfiguration(skillsconfigfile);
	
	// Look for defaults in the jar
	InputStream defConfigStream = getResource("skills.yml");
	if (defConfigStream != null) {
		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		skillsconfig.setDefaults(defConfig);
	}
}

/**
 * Gets the skills configuration.
 * If it does not exist, executes reloadskillsconfig()
 * @return skillsconfig. You can get variables similar to getConfig() from this method.
 */
public FileConfiguration getskillsconfig() {
	if (skillsconfig == null) {
		reloadskillsconfig();
	}
	return skillsconfig;
}

/**
 * Saves the skills config.
 * @throws IOException if it is not able to save the file.
 */
public void saveskillsconfig() {
	if (skillsconfig == null || skillsconfigfile == null) {
		return;
	}
	try {
		skillsconfig.save(skillsconfigfile);
	} catch (IOException ex) {
		Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to "
				+ skillsconfigfile, ex);
	}
}

/**
 * Loads the scroll data from "scrolls.dat"
 * The data is an Object - HashMap<String, Map<Short, Integer>>, which is scrollsHM
 * If the file does not exist, renders this method completely void
 */
@SuppressWarnings("unchecked")
public void loadScrollData() {
	try {
		File scrollData = new File(getDataFolder(), "scrolls.dat");
		if (scrollData.exists()) {
			scrollHM = (HashMap<String, Map<Short, Integer>>)GSDataHandler.load(
					"plugins" + File.separator + "GuardianScrolls" + File.separator + "scrolls.dat");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // end of checking if scroll data exists. if not, will do nothing.
}

/**
 * Saves the scroll data to "scrolls.dat"
 */
public void saveScrollData() {
	try {
	GSDataHandler.save(scrollHM, "plugins" + File.separator + "GuardianScrolls" + File.separator + "scrolls.dat");
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
