package net.jahcraft.fancytrees.fertilizer;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;

import net.jahcraft.fancytrees.trees.OakTree;

public class FertilizerListener implements Listener {
	
	boolean instantGrow = true;
	
	@EventHandler
	public void onGrow(StructureGrowEvent e) {
		if (e.getPlayer() == null) return;
		if (!e.isFromBonemeal()) return;
		if (e.getSpecies() == null) return;
		if (!isUsingFertilizer(e.getPlayer())) return;
		
		e.setCancelled(true);
		
		growTree(e.getWorld().getBlockAt(e.getLocation())); e.getPlayer().sendMessage("grow");
		
	}
	
	@EventHandler
	public void onFertilize(BlockFertilizeEvent e) {
		if (e.getPlayer() == null) return;
		if (!isUsingFertilizer(e.getPlayer())) return;
		if (!(e.getBlock().getBlockData() instanceof Sapling)) return;
		
		e.setCancelled(true);
		
		growTree(e.getBlock()); e.getPlayer().sendMessage("fertilize");
		
	}

	private void growTree(Block b) {
		
		new OakTree(1,b.getLocation()).assemble();
		
	}

	private boolean isUsingFertilizer(Player player) {
		
		if (player == null) return false;
		if (player.getInventory() == null) return false;
		if (player.getInventory().getItemInMainHand() == null) return false;
		
		ItemStack hand = player.getInventory().getItemInMainHand();
		
		if (hand.getType() != Material.BONE_MEAL) return false;
		if (!hand.getItemMeta().hasCustomModelData()) return false;
		if (hand.getItemMeta().getCustomModelData() != 1) return false;
		
		return true;
		
	}
	
	

}


