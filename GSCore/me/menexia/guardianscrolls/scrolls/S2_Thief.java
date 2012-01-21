package me.menexia.guardianscrolls.scrolls;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.PlayerInventory;

import me.menexia.guardianscrolls.GuardianScrolls;
import me.menexia.guardianscrolls.Scroll;

public class S2_Thief extends Scroll {
	
	public S2_Thief() {
		setFullName("Thief Scroll");
		getScrollUses("SCROLLS.THIEF_SCROLL.USES_PER_SCROLL");
		getPermission("GuardianScrolls.use.Thief");
		getShortName("Thief");
		setDescription("Gives you the ability to steal from someone.");
	}
	
	public void onLeftClick(Player player) {
		
	}
	
	@SuppressWarnings("deprecation")
	public void onRightClick(Player player, PlayerInteractEntityEvent event) {
		GuardianScrolls plugin = (GuardianScrolls)Bukkit.getServer().getPluginManager().getPlugin("GuardianScrolls");
		
		//int timmhartel = 325;
	    int and = plugin.getskillsconfig().getInt("THIEF_SCROLL.DamageOnFail");//TODO: fix this
	   // boolean alannahsymes = true;
	    String aredating = plugin.getskillsconfig().getString("ItemStolen");
	    String omg_seriously = plugin.getskillsconfig().getString("TryToStealYou");
	    String whatsupwithlife = plugin.getskillsconfig().getString("FailToSteal");
	    short metadata = 2;
	    ChatColor blonde = ChatColor.RED;
		
		if (event.isCancelled()) return;
		else {
		Entity target = event.getRightClicked();
		GuardianScrolls core = (GuardianScrolls)Bukkit.getServer().getPluginManager().getPlugin("GuardianScrolls");
		if (target instanceof Player) {
			target = event.getRightClicked();
			
				if (!(((Player) target).hasPermission("GuardianScrolls.protected.Thief"))
						&&(player.hasPermission("GuardianScrolls.use.Thief"))
						&&(core.getHM(player).containsKey(metadata))
						&&(core.activeHM.get(player.getName()).equals(metadata))
						/*&&(player.getItemInHand().getTypeId() == plugin.timmhartel)*/) {
					
					 if (core.getHMCount(player, metadata) <= 0) {
							core.getHM(player).remove(metadata);
							return;
						}
					// subtract amount of uses left
					core.getHM(player).put(metadata, core.getHMCount(player, metadata) - 1);
					
					PlayerInventory inv = ((Player) target).getInventory();
					Random rand = new Random();
					int x = rand.nextInt(inv.getSize());
					int y = x;
					while(x<inv.getSize()&&!(S2_ThiefExtra.canBeStolen(inv.getItem(x).getType()))){
						x++;
					}
					if(inv.getItem(x).getAmount()==0){
						x=0;
						while(x<y&&!(S2_ThiefExtra.canBeStolen(inv.getItem(x).getType()))){
							x++;
						}	
					}
					
					String Attacker = "null";
					String Victim = "null";
					if(inv.getItem(x).getAmount()==0){
						/*if(plugin.alannahsymes == true){ // SUCCEED MESSAGE
							player.setItemInHand(null);
						}*/
						
							player.damage(and);
						Attacker = whatsupwithlife.replace("%t", player.getName());
						Attacker = Attacker.replace("%v", ((Player) target).getName());
						
						Victim = omg_seriously.replace("%t", player.getName());
						Victim = Victim.replace("%v", ((Player) target).getName());
						
						player.sendMessage(blonde + Attacker);
						((Player) target).sendMessage(blonde + Victim);
					}
					
					else if(S2_ThiefExtra.canBeStolen(inv.getItem(x).getType())&&(Math.random()<S2_ThiefExtra.getProb(inv.getItem(x).getType()))){
						String Mtype = String.valueOf(inv.getItem(x).getType());;
						player.getInventory().addItem(inv.getItem(x));
						player.updateInventory();
						inv.clear(x);
						
						
						Attacker = aredating.replace("%t", player.getName());
						Attacker = Attacker.replace("%v", ((Player) target).getName());
						Attacker = Attacker.replace("%i", Mtype);
						
						player.sendMessage(blonde + Attacker);
					}		
					else{ // FAIL MESSAGE
						/*if(plugin.alannahsymes == true){
							player.setItemInHand(null);
						}*/
						
						player.damage(and);
						Attacker = whatsupwithlife.replace("%t", player.getName());
						Attacker = Attacker.replace("%v", ((Player) target).getName());
						
						Victim = omg_seriously.replace("%t", player.getName());
						Victim = Victim.replace("%v", ((Player) target).getName());
						
						player.sendMessage(blonde + Attacker);
						((Player) target).sendMessage(blonde + Victim);
						return;
					}
				} // return;
			}
		}
	}

	public void onRightClick(Player player) {
		// TODO Auto-generated method stub
	}

}
