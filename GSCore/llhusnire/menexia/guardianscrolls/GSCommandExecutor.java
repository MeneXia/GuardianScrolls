package llhusnire.menexia.guardianscrolls;

import java.io.File;

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
	
	/*public Integer checkAmount(short s) {
		switch(s) {
		case 26: plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.AMOUNT_OF_USES");
		}
		return (int) s;
	} can work as a separate method... */
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String zhf, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be in-game to use GuardianScrolls.");
			return true; //return ends current code.
			}
		Player player = (Player)sender;
		if(sender.hasPermission("GuardianScrolls.use.Thief")
				|| sender.hasPermission("GuardianScrolls.use.Warrior")
				|| sender.hasPermission("GuardianScrolls.use.Mage")) {
			if (cmd.getName().equalsIgnoreCase("guardianscrolls")
					|| cmd.getName().equalsIgnoreCase("gs")) {
				
				if (args.length == 0) {
					player.sendMessage("Help is shown here!");
					return true;
				}
				
				
				int amtuses = plugin.getTypeScroll(player);
				switch (amtuses) {
				case 26: amtuses = plugin.getConfig().getInt("SCROLLS.THIEF_SCROLL.AMOUNT_OF_USES");
				break;
				}
				
				
				
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("use")) {
						if (!(player.getItemInHand().getDurability() > 0)) {
								 if (player.getItemInHand().getTypeId() != 339) {
							player.sendMessage("You can't read this.");
							return true;
							} else if (player.getItemInHand().getTypeId() == 339) {
								player.sendMessage("You can't read this.");
								return true;
							}
						}
						// checkAmount(plugin.getTypeScroll(player))
						if (plugin.getHM(player).containsKey(plugin.getTypeScroll(player))) {
						// needed?	GuardianScrolls.scrollHM.put(player.getName(), new HashMap<Short, Short>());
							plugin.getHM(player).put(plugin.getTypeScroll(player), plugin.getHMCount(player, plugin.getTypeScroll(player))
									+ (int)amtuses);
						// needed?	plugin.scrollHM.get(player.getName()).
						// needed?	put(player.getItemInHand().getDurability(), (short) (GuardianScrolls.scrollHM.get(player.getName()).get(player.getItemInHand().getDurability()) + checkAmount((short) player.getItemInHand().getDurability())));
							
							player.sendMessage(ChatColor.AQUA + "Added value to existing.");
							if (player.getItemInHand().getAmount() == 1) {
								player.setItemInHand(null);
								return true;
							}
							player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
							return true;
						} else {
							plugin.getHM(player).put(plugin.getTypeScroll(player), (int)amtuses);
						player.sendMessage(ChatColor.GREEN + "First time reading this scroll.");
						if (player.getItemInHand().getAmount() == 1) {
							player.setItemInHand(null);
							return true;
						} else {
							player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
							/*try {
								GSDataHandler.save(plugin.scrollHM, "plugins" + File.separator + "GuardianScrolls" + File.separator + "scrolls.dat");
							} catch (Exception e) {
								e.printStackTrace();
							}*/
							return true;
						}
								}
							}
				}
				try {
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("use")) {
						if (!(player.getItemInHand().getDurability() > 0)) {
							 if (player.getItemInHand().getTypeId() != 339) {
						player.sendMessage("You can't read this.");
						return true;
						} else if (player.getItemInHand().getTypeId() == 339) {
							player.sendMessage("You can't read this.");
							return true;
						}
					}
						int amt = Integer.parseInt(args[1]);
						// when typing this argument, value must be exact.
						if (amt > player.getItemInHand().getAmount()) {
							player.sendMessage(ChatColor.GOLD + "You don't have enough.");
							return true;
						}
						if (amt <= player.getItemInHand().getAmount()) {
							if (plugin.getHM(player).containsKey(plugin.getTypeScroll(player))) {
								
								plugin.getHM(player).put(plugin.getTypeScroll(player), plugin.getHMCount(player, plugin.getTypeScroll(player))
										+ (int)amtuses * amt);
								
								player.sendMessage(ChatColor.AQUA + "Added value to existing.");
								if (amt == player.getItemInHand().getAmount()) {
									player.setItemInHand(null);
									return true;
								}
								player.getItemInHand().setAmount(player.getItemInHand().getAmount() - amt);
								return true;
							} else /*if (plugin.scrollHM.get(player.getName()) == null)*/ {
								
								plugin.getHM(player).put(plugin.getTypeScroll(player), plugin.getHMCount(player, plugin.getTypeScroll(player))
										+ (int)amtuses * amt);
								
							player.sendMessage(ChatColor.GREEN + "First time reading this scroll.");
							if (amt == player.getItemInHand().getAmount()) {
								player.setItemInHand(null);
								return true;
							} else {
								player.getItemInHand().setAmount(player.getItemInHand().getAmount() - amt);
								return true;
							}
									}
						}
					}
				}
			} catch (NumberFormatException nFE) {
				player.sendMessage(ChatColor.RED + "MUST BE A NUMBER BITCH.");
				return true;
			}
				
				
					}
				}
		return false;
	}
}
