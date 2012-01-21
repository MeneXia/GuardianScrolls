package me.menexia.guardianscrolls.scrolls;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import org.bukkit.Material;

public class S2_ThiefExtra {
	public static HashMap<Material, Double> IList = new HashMap<Material, Double>();
	
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
