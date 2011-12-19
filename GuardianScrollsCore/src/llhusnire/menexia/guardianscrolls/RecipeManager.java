package llhusnire.menexia.guardianscrolls;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeManager {
	public static GuardianScrolls plugin;
	public RecipeManager(GuardianScrolls instance) {
		plugin = instance;
	}
	
	public ShapedRecipe getThiefScrollRecipe() {
		short metadata = (short) plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.METADATA");
	
	ShapedRecipe THIEF_SCROLL = new ShapedRecipe(new ItemStack(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.RESULT"), 1, metadata)).
			shape("abc", "def", "ghi").setIngredient('a', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.A"))).
			setIngredient('b', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.B"))).
			setIngredient('c', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.C"))).
			setIngredient('d', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.D"))).
			setIngredient('e', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.E"))).
			setIngredient('f', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.F"))).
			setIngredient('g', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.G"))).
			setIngredient('h', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.H"))).
			setIngredient('i', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.I")));
	return THIEF_SCROLL;
	}
	
	public ShapedRecipe getWarriorScrollRecipe() {
		short metadata = (short) plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.METADATA");
		
		ShapedRecipe WARRIOR_SCROLL = new ShapedRecipe(new ItemStack(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.RESULT"), 1, metadata)).
				shape("abc", "def", "ghi").setIngredient('a', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.A"))).
				setIngredient('b', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.B"))).
				setIngredient('c', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.C"))).
				setIngredient('d', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.D"))).
				setIngredient('e', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.E"))).
				setIngredient('f', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.F"))).
				setIngredient('g', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.G"))).
				setIngredient('h', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.H"))).
				setIngredient('i', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.WARRIOR_SCROLL.I")));
		return WARRIOR_SCROLL;
	}
	
	public ShapedRecipe getMageScrollRecipe() {
		short metadata = (short) plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.METADATA");
		
		ShapedRecipe MAGE_SCROLL = new ShapedRecipe(new ItemStack(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.RESULT"), 1, metadata)).
				shape("abc", "def", "ghi").setIngredient('a', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.A"))).
				setIngredient('b', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.B"))).
				setIngredient('c', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.C"))).
				setIngredient('d', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.D"))).
				setIngredient('e', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.E"))).
				setIngredient('f', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.F"))).
				setIngredient('g', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.G"))).
				setIngredient('h', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.H"))).
				setIngredient('i', Material.getMaterial(plugin.getConfig().getInt("SCROLLS.MAGE_SCROLL.I")));
		return MAGE_SCROLL;
	}
	
	// TODO: add more shaped recipes!
}
