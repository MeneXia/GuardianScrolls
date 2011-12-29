package llhusnire.menexia.guardianscrolls;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import llhusnire.menexia.guardianscrolls.listeners.GSPlayerListener;
import llhusnire.menexia.guardianscrolls.listeners.GSSpoutInventoryListener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GuardianScrolls extends JavaPlugin {
	public static GuardianScrolls plugin;
	
//	public HashMap<String, Short> scrollHM = new HashMap<String, Short>();
	public Map<String, Map<Short, Integer>> scrollHM = new HashMap<String, Map<Short, Integer>>();
	// player name, which scroll? - short metadata/durability
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public HashMap<Plugin, String> ScrollNames = new HashMap<Plugin, String>();
	
	static boolean ENABLED_FOR_ALL = false;
	private GSCommandExecutor cmdExecutor;
	private FileConfiguration skillsconfig = null;
	private File skillsconfigfile = null;

	protected static boolean SPOUT_ENABLED = false;
	protected static String STACK_BY_DATA_FN = "a";
	protected static boolean USE_SPOUT_FEATURES = true;
	
	File mainConfigfile = new File(getDataFolder(), "config.yml");
	
@Override
public void onDisable() {
	this.ScrollNames.clear();
		try {
			GSDataHandler.save(scrollHM, "plugins" + File.separator + "GuardianScrolls" + File.separator + "scrolls.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	this.logger.info("[GuardianScrolls] and add-ons successfully disabled.");
}

@SuppressWarnings("unchecked")
@Override
public void onEnable() {
	try {
		if (!mainConfigfile.exists()) {
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
	i_wish_we_couldve();
	try {
		File scrollData = new File(getDataFolder(), "scrolls.dat");
		if (scrollData.exists()) {
			scrollHM = (HashMap<String, Map<Short, Integer>>)GSDataHandler.load(
					"plugins" + File.separator + "GuardianScrolls" + File.separator + "scrolls.dat");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // end of checking if scroll data exists. if not, will do nothing.
	
	PluginManager a = this.getServer().getPluginManager();
	/*
	 * Code for plugin dependencies aka add-ons
	 * 
	Plugin thief = (Plugin)a.getPlugin("PickPocket");
	if (thief == null) {
		this.logger.info("[GuardianScrolls] ThiefScroll plugin disabled or not found!");
	} */
	
	// enables all other addons for guardianscrolls
	for (Plugin plugin : a.getPlugins()) {
		if (plugin.getDescription().getName().startsWith("GS")) {
			if (!getServer().getPluginManager().isPluginEnabled(plugin)) {
			a.enablePlugin(plugin);
			}
			String name = (String)plugin.getDescription().getName();
			this.ScrollNames.put(plugin, name);
		}
	}
	
	// if spout is on, enable features
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
	
	// prevent paper stacking
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
	
	// get command executor
	cmdExecutor = new GSCommandExecutor(this);
	getCommand("gs").setExecutor(cmdExecutor);
	// register events
	a.registerEvent(Event.Type.PLAYER_QUIT, new GSPlayerListener(this), Event.Priority.Normal, this);
	this.logger.info("[GuardianScrolls] Alpha version " + this.getDescription().getVersion() + " by MeneXia is enabled!");
	this.logger.info("[GuardianScrolls] Loaded scrolls:" + this.ScrollNames.values());
	
	
} // end of onEnable

public void i_wish_we_couldve() {
	ENABLED_FOR_ALL = this.getConfig().getBoolean("GENERAL_OPTIONS.ENABLED_FOR_ALL");
	USE_SPOUT_FEATURES = this.getConfig().getBoolean("GENERAL_OPTIONS.USE_SPOUT_FEATURES");
}

public Map<Short, Integer> getHM(String playername) {
    Map<Short, Integer> map = scrollHM.get(playername);
    if (map == null) {
        map = new HashMap<Short, Integer>();
        scrollHM.put(playername, map);
    }
    return map;
}
public Map<Short, Integer> getHM(Player player) {
    return getHM(player.getName());
}
public int getHMCount(Player player, short durability) {
    Integer value = getHM(player).get(durability); //not sure if this can be null, may need contains check above
    if (value != null) return (int) value;
    return 0;
}

public short getTypeScroll(Player player) {
	return player.getItemInHand().getDurability();
}

public static Object load(String path) throws Exception {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
	Object result = ois.readObject();
	ois.close();
	return result;
}

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

public FileConfiguration getskillsconfig() {
	if (skillsconfig == null) {
		reloadskillsconfig();
	}
	return skillsconfig;
}

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

}
