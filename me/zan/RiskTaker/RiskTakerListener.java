package me.zan.RiskTaker;

import org.bukkit.event.Listener;


public class RiskTakerListener implements Listener
{
	
	public static RiskTaker plugin;
	
	public RiskTakerListener(RiskTaker instance)
	{
		plugin = instance;
	}
	
//	public void onBlockDamage(BlockDamageEvent event)
//	{
//		if(plugin.enabled(event.getPlayer()))
//			event.getBlock().setTypeId(plugin.id);
//	}
	
}
