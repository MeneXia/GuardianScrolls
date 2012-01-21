package me.menexia.guardianscrolls;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class TestRecipeManager {
	public static GuardianScrolls plugin;
	public TestRecipeManager(GuardianScrolls instance) {
		plugin = instance;
	}
	
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
	int manual = 340;
	int amount = 1;
	short metadata = 0;
	String base = "base";
	boolean needsManual = true;
	boolean resultSwitched = false;
	
	public void getRecipeManual(Integer scrollAbility) {
		result = manual;
		resultSwitched = true;
		getRecipe(scrollAbility);
	}
	
	public boolean needsManual(Integer amazingness) {
		switch (amazingness) {
		case 1: return false;
		default: return true;
		}
	}
	
	
public ShapedRecipe getRecipe(Integer scrollAbility) {
	switch (scrollAbility) {
	case 1: base = "SCROLLS.SCROLL_TRACKER."; needsManual = false; metadata = 1;
	break;
	case 2: base = "SCROLLS.THIEF_SCROLL."; needsManual = true; metadata = 2;
	break;
	case 3: base = "SCROLLS.DAYTIME_SCROLL."; needsManual = true; metadata = 3;
	break;
	case 4: base = "SCROLLS.NIGHTTIME_SCROLL."; needsManual = true; metadata = 4;
	break;
	}
	
	A = plugin.getConfig().getInt(base+"A"); B = plugin.getConfig().getInt(base+"B"); C = plugin.getConfig().getInt(base+"C");
	D = plugin.getConfig().getInt(base+"D"); E = plugin.getConfig().getInt(base+"E"); F = plugin.getConfig().getInt(base+"F");
	G = plugin.getConfig().getInt(base+"G"); H = plugin.getConfig().getInt(base+"H"); I = plugin.getConfig().getInt(base+"I");
	
		String topRow = "ABC";
        String middleRow = "DEF";
        String bottomRow = "GHI";
        ShapedRecipe ts = new ShapedRecipe(new ItemStack(result, amount, metadata));
        ts.shape(topRow, middleRow, bottomRow);
        
        if (A == 0) {
        	topRow.replace("A", " ");
        } else {
        	ts.setIngredient('A', Material.getMaterial(A));
        }
        
        if (B == 0) {
        	topRow.replace("B", " ");
        } else {
        	ts.setIngredient('B', Material.getMaterial(B));
        }
        
        if (C == 0) {
        	topRow.replace("C", " ");
        } else {
        	ts.setIngredient('C', Material.getMaterial(C));
        }
        
        if (D == 0) {
        	topRow.replace("D", " ");
        } else {
        	ts.setIngredient('D', Material.getMaterial(D));
        }
        
        if (resultSwitched == true) {
        	E = result;
        }
        
        if (E == 0) {
        	topRow.replace("E", " ");
        } else {
        	ts.setIngredient('E', Material.getMaterial(E));
        }
        
        if (F == 0) {
        	topRow.replace("F", " ");
        } else {
        	ts.setIngredient('F', Material.getMaterial(F));
        }
        
        if (G == 0) {
        	topRow.replace("G", " ");
        } else {
        	ts.setIngredient('G', Material.getMaterial(G));
        }
        
        if (H == 0) {
        	topRow.replace("H", " ");
        } else {
        	ts.setIngredient('H', Material.getMaterial(H));
        }
        
        if (I == 0) {
        	topRow.replace("I", " ");
        } else {
        	ts.setIngredient('I', Material.getMaterial(I));
        }
        
        ts.shape(topRow, middleRow, bottomRow);
        result = 339;
        resultSwitched = false;
        Bukkit.getServer().addRecipe(ts);
        return ts;
	}

}
