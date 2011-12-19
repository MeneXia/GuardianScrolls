/*
 * PickPocket v0.2 by MeneXia, forked from FlingeR's GitHub.
 */
package com.bukkit.FlingeR.PickPocket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import llhusnire.menexia.guardianscrolls.GuardianScrolls;

public class PickPocket extends JavaPlugin {
		public static PickPocket plugin;
	
		public final Logger logger = Logger.getLogger("Minecraft");
	    public static HashMap<Material, Double> IList = new HashMap<Material, Double>();
	    private final PickPocketPlayerListener playerListener = new PickPocketPlayerListener(this);

	    int timmhartel = 325;
	    int and = 1;
	    boolean alannahsymes = true;
	    String aredating = "[GS] Attempt successful.";
	    String omg_seriously = "[GS] %n tried to pickpocket you!";
	    String whatsupwithlife = "[GS] Your hand slipped.";
	    short metadata = 26;
	    
	    public void onEnable() {
	    	PluginManager pm = getServer().getPluginManager();
	    	Plugin GSCore = getServer().getPluginManager().getPlugin("GuardianScrolls");
	    	// checks if core is present. if not, disables all other plugins.
	    	if (GSCore == null) {
	    		this.logger.info("[GSThieving] Did not detect plugin: GuardianScrolls.");
	    		this.logger.info("[GSThieving] Disabling all other add-ons to GuardianScrolls.");
	    		for (Plugin plugin : pm.getPlugins()) {
	    			if (plugin.getDescription().getName().startsWith("GS")) {
	    				pm.disablePlugin(plugin);
	    				// no need for return, because this plugin is already disabled.
	    			}
	    		}
	    	}
	    	
	    	// enables the core
	    	if (!GSCore.isEnabled()) {
	    		pm.enablePlugin(GSCore);
	    		// technically, this plugin is already enabled. going into the core, it will first enable the core then enable all other addons.
	    		// then it will return to the method here and enable it.
	    	}
	    	
	    	
	    		File fileconfig = new File(GSCore.getDataFolder(), "skills.yml");
	    		if (!fileconfig.exists()) {
	    			this.logger.info("[GSThieving] Did not find skills.yml");
	    			this.logger.info("[GSThieving] has been disabled.");
	    			this.getPluginLoader().disablePlugin(this);
	    		}
	    		
	    	sweet_kiwis();
	        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, playerListener, Event.Priority.Normal, this);
	    	try {
	    		readProperties();
	    	} catch (Exception e1) {
	    		this.logger.info("[GSThieving] Exception while reading thief.chances.");
	    	}
	    	//this.logger.info( "[PickPocket] version " + pdf.getVersion() + " by MeneXia is enabled!" );
	    }
	    
	    public void onDisable() {
	        // this.logger.info("[GSThieving] disabled!");
	    }
	    
	    public void sweet_kiwis() {
	    	GuardianScrolls plugin = (GuardianScrolls)getServer().getPluginManager().getPlugin("GuardianScrolls");
	    	if(plugin != null && plugin.isEnabled()) {
	    	timmhartel = plugin.getskillsconfig().getInt("PickPocketTool");
	    	and = plugin.getskillsconfig().getInt("DamageOnFail");
	    	alannahsymes = plugin.getskillsconfig().getBoolean("LoseToolonFail");
	    	whatsupwithlife = plugin.getskillsconfig().getString("FailToSteal");
	    	aredating = plugin.getskillsconfig().getString("ItemStolen");
	    	omg_seriously = plugin.getskillsconfig().getString("TryToStealYou");
	    	metadata = (short) plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.METADATA");
	    	}
	    }
	    
	    public static void readProperties() throws Exception {
			String fileName = "plugins/GuardianScrolls/thief.chances";
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				if ((line.trim().length() == 0) || 
						(line.charAt(0) == '#')) {
					continue;
				}
				int keyPosition = line. indexOf('=');
				Material item = Material.getMaterial(line.substring(0, keyPosition).trim());
				double value = Double.parseDouble(line.substring(keyPosition+1, line.length()).trim());
				IList.put(item, value);
			}
	        System.out.println("[GSThieving] No problems loading thief.chances!");			
		}
		
		public static boolean canBeStolen(Material type){
			return(IList.containsKey(type));
		}

		public static double getProb(Material type) {
			return(IList.get(type));
		} 
	}