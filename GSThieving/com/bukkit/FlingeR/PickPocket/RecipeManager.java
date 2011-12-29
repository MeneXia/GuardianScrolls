package com.bukkit.FlingeR.PickPocket;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeManager {
	public static PickPocket plugin;
	public RecipeManager(PickPocket instance) {
		plugin = instance;
	}
	
	public ShapedRecipe getTS() {
		
		String topRow = "ABC";
        String middleRow = "DEF";
        String bottomRow = "GHI";
        ShapedRecipe ts = new ShapedRecipe(new ItemStack(plugin.result, plugin.amount, plugin.metadata));
        ts.shape(topRow, middleRow, bottomRow);
        
        if (plugin.A == 0) {
        	topRow.replace("A", " ");
        } else {
        	ts.setIngredient('A', Material.getMaterial(plugin.A));
        }
        
        if (plugin.B == 0) {
        	topRow.replace("B", " ");
        } else {
        	ts.setIngredient('B', Material.getMaterial(plugin.B));
        }
        
        if (plugin.C == 0) {
        	topRow.replace("C", " ");
        } else {
        	ts.setIngredient('C', Material.getMaterial(plugin.C));
        }
        
        if (plugin.D == 0) {
        	topRow.replace("D", " ");
        } else {
        	ts.setIngredient('D', Material.getMaterial(plugin.D));
        }
        
        if (plugin.E == 0) {
        	topRow.replace("E", " ");
        } else {
        	ts.setIngredient('E', Material.getMaterial(plugin.E));
        }
        
        if (plugin.F == 0) {
        	topRow.replace("F", " ");
        } else {
        	ts.setIngredient('F', Material.getMaterial(plugin.F));
        }
        
        if (plugin.G == 0) {
        	topRow.replace("G", " ");
        } else {
        	ts.setIngredient('G', Material.getMaterial(plugin.G));
        }
        
        if (plugin.H == 0) {
        	topRow.replace("H", " ");
        } else {
        	ts.setIngredient('H', Material.getMaterial(plugin.H));
        }
        
        if (plugin.I == 0) {
        	topRow.replace("I", " ");
        } else {
        	ts.setIngredient('I', Material.getMaterial(plugin.I));
        }
        
        ts.shape(topRow, middleRow, bottomRow);
        return ts;
	}

}
