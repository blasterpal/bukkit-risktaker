package me.zan.RiskTaker;

import java.util.logging.Logger;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public final class RiskTaker extends JavaPlugin implements Listener {

	private static final Logger log = Logger.getLogger("Minecraft");

	public void onEnable() {
		log.info("[RiskTaker] enabled...");
		getServer().getPluginManager().registerEvents(this, this);
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("RiskTaker")){
			//Now let's get random
			Random ran = new Random();
			int x = ran.nextInt(10) + 1;
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player." + Integer.toString(x));
			} else {
				Player player = (Player) sender;
				//do something
				//Now let's get random
				((Player) sender).sendMessage(ChatColor.BLUE + "You have taken a risk and might be rewarded! ... [" + Integer.toString(x) + "]");
				PlayerInventory inventory = player.getInventory(); // The player's inventory

				switch (x) {
				case 1:
					setOnFire(player);
					break;
				case 2: 
					blowUp(player);
					break;

				default:
					giveSomething(player,inventory);
					break;
				}
			}
		}
		return true;
	}



	private void giveSomething(Player player, PlayerInventory inventory){
		ItemStack itemstack = new ItemStack(Material.DIAMOND, 12); // A stack of diamonds
		inventory.addItem(itemstack);
		log.info("Gave " + player.getDisplayName()  + " dimaonds!");
	}

	private void setOnFire(Player player){
		player.setFireTicks(10000);
		log.info("Setting " + player.getDisplayName()  + " on fire!!!");
	}

	private void blowUp(Player player){
		float explosionPower = 4F;
		player.getWorld().createExplosion(player.getLocation(), explosionPower);
		player.setHealth(1);
		log.info("Blowing " + player.getDisplayName() + " up!!!");
	}
}
