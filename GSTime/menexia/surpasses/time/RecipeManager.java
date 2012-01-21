package menexia.surpasses.time;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeManager {
	public static Time plugin;
	public RecipeManager(Time instance) {
		plugin = instance;
	}
	
public ShapedRecipe getDS() {
		
		String topRow = "ABC";
        String middleRow = "DEF";
        String bottomRow = "GHI";
        ShapedRecipe ts = new ShapedRecipe(new ItemStack(plugin.dsResult, plugin.dsAmount, plugin.dsMetaData));
        ts.shape(topRow, middleRow, bottomRow);
        
        if (plugin.dsA == 0) {
        	topRow.replace("A", " ");
        } else {
        	ts.setIngredient('A', Material.getMaterial(plugin.dsA));
        }
        
        if (plugin.dsB == 0) {
        	topRow.replace("B", " ");
        } else {
        	ts.setIngredient('B', Material.getMaterial(plugin.dsB));
        }
        
        if (plugin.dsC == 0) {
        	topRow.replace("C", " ");
        } else {
        	ts.setIngredient('C', Material.getMaterial(plugin.dsC));
        }
        
        if (plugin.dsD == 0) {
        	topRow.replace("D", " ");
        } else {
        	ts.setIngredient('D', Material.getMaterial(plugin.dsD));
        }
        
        if (plugin.dsE == 0) {
        	topRow.replace("E", " ");
        } else {
        	ts.setIngredient('E', Material.getMaterial(plugin.dsE));
        }
        
        if (plugin.dsF == 0) {
        	topRow.replace("F", " ");
        } else {
        	ts.setIngredient('F', Material.getMaterial(plugin.dsF));
        }
        
        if (plugin.dsG == 0) {
        	topRow.replace("G", " ");
        } else {
        	ts.setIngredient('G', Material.getMaterial(plugin.dsG));
        }
        
        if (plugin.dsH == 0) {
        	topRow.replace("H", " ");
        } else {
        	ts.setIngredient('H', Material.getMaterial(plugin.dsH));
        }
        
        if (plugin.dsI == 0) {
        	topRow.replace("I", " ");
        } else {
        	ts.setIngredient('I', Material.getMaterial(plugin.dsI));
        }
        
        ts.shape(topRow, middleRow, bottomRow);
        return ts;
	}

public ShapedRecipe getNS() {
	
	String topRow = "ABC";
    String middleRow = "DEF";
    String bottomRow = "GHI";
    ShapedRecipe ts = new ShapedRecipe(new ItemStack(plugin.nsResult, plugin.nsAmount, plugin.nsMetaData));
    ts.shape(topRow, middleRow, bottomRow);
    
    if (plugin.nsA == 0) {
    	topRow.replace("A", " ");
    } else {
    	ts.setIngredient('A', Material.getMaterial(plugin.nsA));
    }
    
    if (plugin.nsB == 0) {
    	topRow.replace("B", " ");
    } else {
    	ts.setIngredient('B', Material.getMaterial(plugin.nsB));
    }
    
    if (plugin.nsC == 0) {
    	topRow.replace("C", " ");
    } else {
    	ts.setIngredient('C', Material.getMaterial(plugin.nsC));
    }
    
    if (plugin.nsD == 0) {
    	topRow.replace("D", " ");
    } else {
    	ts.setIngredient('D', Material.getMaterial(plugin.nsD));
    }
    
    if (plugin.nsE == 0) {
    	topRow.replace("E", " ");
    } else {
    	ts.setIngredient('E', Material.getMaterial(plugin.nsE));
    }
    
    if (plugin.nsF == 0) {
    	topRow.replace("F", " ");
    } else {
    	ts.setIngredient('F', Material.getMaterial(plugin.nsF));
    }
    
    if (plugin.nsG == 0) {
    	topRow.replace("G", " ");
    } else {
    	ts.setIngredient('G', Material.getMaterial(plugin.nsG));
    }
    
    if (plugin.nsH == 0) {
    	topRow.replace("H", " ");
    } else {
    	ts.setIngredient('H', Material.getMaterial(plugin.nsH));
    }
    
    if (plugin.nsI == 0) {
    	topRow.replace("I", " ");
    } else {
    	ts.setIngredient('I', Material.getMaterial(plugin.nsI));
    }
    
    ts.shape(topRow, middleRow, bottomRow);
    return ts;
}

public ShapedRecipe getDSManual() {
	String topRow = "ABC";
	String middleRow = "DEF";
	String bottomRow = "GHI";
	ShapedRecipe ts = getDS();
	ts.setIngredient('E', Material.getMaterial(plugin.dsManual));
    ts.shape(topRow, middleRow, bottomRow);
    return ts;
}

public ShapedRecipe getNSManual() {
	String topRow = "ABC";
	String middleRow = "DEF";
	String bottomRow = "GHI";
	ShapedRecipe ts = getNS();
	ts.shape(topRow, middleRow, bottomRow);
	ts.setIngredient('E', Material.getMaterial(plugin.nsManual));
    ts.shape(topRow, middleRow, bottomRow);
    return ts;
}

}
