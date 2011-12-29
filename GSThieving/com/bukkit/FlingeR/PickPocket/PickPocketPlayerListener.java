package com.bukkit.FlingeR.PickPocket;

import java.util.Random;

import llhusnire.menexia.guardianscrolls.GuardianScrolls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.PlayerInventory;

	public class PickPocketPlayerListener extends PlayerListener {
	    public static PickPocket plugin;
	    public PickPocketPlayerListener(PickPocket instance) {
	        plugin = instance;
	    }
	    
	    String blonde = ChatColor.RED + "[GS] ";
	    
		@SuppressWarnings("deprecation")
		public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
			if (event.isCancelled()) return;
			else {
			Player player = event.getPlayer();
			Entity target = event.getRightClicked();
			GuardianScrolls core = (GuardianScrolls)Bukkit.getServer().getPluginManager().getPlugin("GuardianScrolls");
			if (target instanceof Player) {
				target = event.getRightClicked();
				
					if (!(((Player) target).hasPermission("GuardianScrolls.protected.Thief"))
							&&(player.hasPermission("GuardianScrolls.use.Thief"))
							&&(core.getHM(player).containsKey(plugin.metadata))
							/*&&(player.getItemInHand().getTypeId() == plugin.timmhartel)*/) {
						
						 if (core.getHMCount(player, plugin.metadata) <= 0) {
								core.getHM(player).remove(plugin.metadata);
								return;
							}
						// subtract amount of uses left
						core.getHM(player).put(plugin.metadata, core.getHMCount(player, plugin.metadata) - 1);
						
						PlayerInventory inv = ((Player) target).getInventory();
						Random rand = new Random();
						int x = rand.nextInt(inv.getSize());
						int y = x;
						while(x<inv.getSize()&&!(PickPocket.canBeStolen(inv.getItem(x).getType()))){
							x++;
						}
						if(inv.getItem(x).getAmount()==0){
							x=0;
							while(x<y&&!(PickPocket.canBeStolen(inv.getItem(x).getType()))){
								x++;
							}	
						}
						
						String Attacker = "null";
						String Victim = "null";
						if(inv.getItem(x).getAmount()==0){
							/*if(plugin.alannahsymes == true){ // SUCCEED MESSAGE
								player.setItemInHand(null);
							}*/
							
								player.damage(plugin.and);
							Attacker = plugin.whatsupwithlife.replace("%t", player.getName());
							Attacker = Attacker.replace("%v", ((Player) target).getName());
							
							Victim = plugin.omg_seriously.replace("%t", player.getName());
							Victim = Victim.replace("%v", ((Player) target).getName());
							
							player.sendMessage(blonde + Attacker);
							((Player) target).sendMessage(blonde + Victim);
						}
						
						else if(PickPocket.canBeStolen(inv.getItem(x).getType())&&(Math.random()<PickPocket.getProb(inv.getItem(x).getType()))){
							String Mtype = String.valueOf(inv.getItem(x).getType());;
							player.getInventory().addItem(inv.getItem(x));
							player.updateInventory();
							inv.clear(x);
							
							
							Attacker = plugin.aredating.replace("%t", player.getName());
							Attacker = Attacker.replace("%v", ((Player) target).getName());
							Attacker = Attacker.replace("%i", Mtype);
							
							player.sendMessage(blonde + Attacker);
						}		
						else{ // FAIL MESSAGE
							/*if(plugin.alannahsymes == true){
								player.setItemInHand(null);
							}*/
							
							player.damage(plugin.and);
							Attacker = plugin.whatsupwithlife.replace("%t", player.getName());
							Attacker = Attacker.replace("%v", ((Player) target).getName());
							
							Victim = plugin.omg_seriously.replace("%t", player.getName());
							Victim = Victim.replace("%v", ((Player) target).getName());
							
							player.sendMessage(blonde + Attacker);
							((Player) target).sendMessage(blonde + Victim);
							return;
						}
					} // return;
				}
			}
			}
		}