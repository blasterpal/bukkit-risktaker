package me.zan.RiskTaker;

import java.util.logging.Logger;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public final class RiskTaker extends JavaPlugin implements Listener {

	private static final Logger log = Logger.getLogger("Minecraft");
	public static final int RANDOM_RANGE = 14;
	
	public void onEnable() {
		log.info("[RiskTaker] enabled...");
		getServer().getPluginManager().registerEvents(this, this);
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("Risk")){
			int x = randomInt();
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player. " + Integer.toString(x));
			} else {
				Player player = (Player) sender;
				((Player) sender).sendMessage(ChatColor.BLUE + "You have taken a risk and might be rewarded! ... [" + Integer.toString(x) + "]");
				PlayerInventory inventory = player.getInventory(); // The player's inventory

				/* 
				 * 1 chance /15 diamond + strike 
				 * 3 chance /15 fire 
				 * 3 chance /15 explode
				 * 8 chance /15 random item
				 */
				
				if (x == 1) {
					givePrecious(player,inventory);
				}
				else if (x > 1 && x<5) {
					setOnFire(player);
				}
				else if (x > 4 && x < 8) {
					blowUp(player);
				}
				else {
					giveSomethingRandom(player,inventory);
				}

			}
		}
		return true;
	}


// This method will randomly give a material/block
	private void giveSomethingRandom(Player player, PlayerInventory inventory){
		log.info("Gave " + player.getDisplayName()  + " something random!");
		List <Material> list = Arrays.asList(Material.values());
		Collections.shuffle(list);
		Material item = list.get(0);
		log.info("Material is " + item.name());
		ItemStack itemstack = new ItemStack(item, 1);
		inventory.addItem(itemstack);
	}
	
// This method will give diamonds and lightning strike
	private void givePrecious(Player player, PlayerInventory inventory){
		ItemStack itemstack = new ItemStack(Material.DIAMOND, 3); // A stack of diamonds
		inventory.addItem(itemstack);
		log.info("Gave " + player.getDisplayName()  + " dimaonds!");
		player.getWorld().strikeLightning(player.getLocation());
	}

	private void setOnFire(Player player){
		player.setFireTicks(10000);
		player.setHealth(5);
		log.info("Setting " + player.getDisplayName()  + " on fire!!!");
	}

	private void blowUp(Player player){
		float explosionPower = 4F;
		player.getWorld().createExplosion(player.getLocation(), explosionPower);
		player.setHealth(1);
		log.info("Blowing " + player.getDisplayName() + " up!!!");
	}
	
	private int randomInt(){
		//Now let's get random
		Random ran = new Random();
		int x = ran.nextInt(RANDOM_RANGE ) + 1; //1-RANDOM_RANGE
		return x;
	}
}
