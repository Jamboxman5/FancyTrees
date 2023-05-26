package net.jahcraft.fancytrees.fertilizer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Fertilizer extends ItemStack {

	public Fertilizer() {
		
		super(Material.BONE_MEAL);
		
		ItemMeta meta = getItemMeta();
		meta.setCustomModelData(1);
		meta.setDisplayName(ChatColor.RESET + "Fertilizer");
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Grow fancy trees without");
		lore.add(ChatColor.GRAY + "all the hassle!");
		meta.setLore(lore);
		
		setItemMeta(meta);
		
	}
	
}
