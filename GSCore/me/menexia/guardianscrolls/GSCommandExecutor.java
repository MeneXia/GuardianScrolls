package me.menexia.guardianscrolls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GSCommandExecutor implements CommandExecutor {
	private GuardianScrolls plugin;
	public GSCommandExecutor(GuardianScrolls instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String zhf, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be in-game to use GuardianScrolls.");
			return true;
			}
		
		Player player = (Player)sender;
		
			if (cmd.getName().equalsIgnoreCase("guardianscrolls")
					|| cmd.getName().equalsIgnoreCase("gs")) {
				
				if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
					player.sendMessage("Help is shown here!"); //TODO: Proper message
					return true;
				}
				
				int amtuses = VariableSwitcher.getScrollUses(player);
				String permVar = VariableSwitcher.getPermission(player);
				
				if (args.length == 1) {
					
					if (args[0].equalsIgnoreCase("use")) {
						if (plugin.notUsable.contains(amtuses)) {
							return true;
						}
						if ((player.getItemInHand().getTypeId() != 339)
								||(!(amtuses > 1) && player.getItemInHand().getTypeId() == 339)) {
							player.sendMessage("You can't read this."); //TODO: Color
							return true;
						}
						
						if (!player.hasPermission(permVar)) {
							player.sendMessage("Get a manual to decipher this scroll!"); //TODO: Color
							return true;
						}
						
						
						int input = 1;
						try {
							if (args.length == 2) {
								input = Integer.parseInt(args[1]);
							}
						
							if (plugin.getHM(player).containsKey(plugin.getTypeScroll(player))) {
								plugin.getHM(player).put(plugin.getTypeScroll(player), plugin.getHMCount(player, plugin.getTypeScroll(player)) + (int)amtuses*input);
								player.sendMessage(ChatColor.AQUA + "Added value to existing."); // TODO: Proper message here.
								
							} else {
								plugin.getHM(player).put(plugin.getTypeScroll(player), (int)amtuses*input);
								player.sendMessage(ChatColor.GREEN + "First time reading this scroll."); //TODO: Proper message here.
							}
						
							if (player.getItemInHand().getAmount() == 1) {
								player.setItemInHand(null);
								
							} else {
								player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
							}
						} catch (NumberFormatException nFE) {
							player.sendMessage(ChatColor.RED + "/gs use <number>");
						}
						return true;
							} // end of checking if argument is "use"
					
					if (args[0].equalsIgnoreCase("menu")) {
						try {
							Set<Entry<Short, Integer>> usesSet = plugin.getHM(player).entrySet();
							List<Entry<Short, Integer>> usesList = new ArrayList<Entry<Short, Integer>>(usesSet);
							int current = 1;
							int num = usesList.size();
							int needed = (int) Math.ceil((double)num/16.0);
							if (args.length == 2) {
								current = Integer.parseInt(args[1]);
							}
							if (current>needed) {
								current = 1;
							}
							
							// TODO: Check coloring
							player.sendMessage(ChatColor.DARK_GREEN + "--------- " + ChatColor.GOLD + "ScrollTracker Menu" + ChatColor.DARK_GREEN + " ---------");
							player.sendMessage("Equipped scroll: " + ChatColor.AQUA + plugin.activeHM.get(player.getName()) + ChatColor.WHITE + " scroll");
							String page = ChatColor.GRAY + "Scroll Library:" + ChatColor.WHITE + " - Page " + String.valueOf(current) + " of " + String.valueOf(needed);
							if (needed != 1) {
								player.sendMessage(ChatColor.RED + "You have no scrolls stored!");
							} else {
								player.sendMessage(page);
							}
							for (int a = (current-1)*16; a<Math.min(current*16, usesList.size()); a++) {
								String toSend = usesList.get(a).toString();
								toSend = toSend.replace("2=", ChatColor.GREEN + "Thief Scroll: " + ChatColor.YELLOW);
								//TODO: Add more replacements
								
								toSend = toSend +  " uses left";
								player.sendMessage(toSend);
							}
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED + "/gs menu <pagenumber>");
						}
						return true;
					}
				} // end of (args.length == 1)
				
				if (args.length == 2) { //TODO: needs rework.
					if (args[0].equalsIgnoreCase("equip")) {
						String toEquip = String.valueOf(args[1]);
						if (toEquip.equalsIgnoreCase("thief")) {
							plugin.activeHM.put(player.getName(), "Thief");
							player.sendMessage("Equipped thief scroll.");
							return true;
						}
					}
				}
					
			} // end of base command check
		return false;
	} // end of method
} // end of class
