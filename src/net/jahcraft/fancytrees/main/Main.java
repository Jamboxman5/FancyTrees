package net.jahcraft.fancytrees.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.jahcraft.fancytrees.fertilizer.FertilizerListener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		try {
			
			//COMMANDS
			//TODO: Obtain fertilizer
			
			//LISTENERS
			getServer().getPluginManager().registerEvents(new FertilizerListener(), this);

			
		} catch (Exception e) {
			
			Bukkit.getLogger().warning("Failed to load FancyTrees!");
			e.printStackTrace();
		}
	}
	
	@Override 
	public void onDisable() {
		
		Bukkit.getLogger().info("FancyTrees Unloaded and Disabled!");
		
	}

}
