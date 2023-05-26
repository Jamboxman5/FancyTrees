package net.jahcraft.fancytrees.trees;

import java.util.HashMap;
import java.util.Stack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Leaves;

public class OakTree {

	final int baseX;
	final int baseY;
	final int baseZ;
	Location blockPointer;
	int type;
	HashMap<Location, Material> blockMap;
	HashMap<Location, BlockFace> orientations;
	Material wood = Material.OAK_WOOD;
	Material leaves = Material.OAK_LEAVES;
	Material slab = Material.SPRUCE_SLAB;
	Material stairs = Material.SPRUCE_STAIRS;
	
	Stack<Location> pointerHistory;
	
	public OakTree(Location base) {
		this((int) (Math.random() * 4), base);
	}
	
	public OakTree(int type, Location base) {
		
		this.baseX = base.getBlockX();
		this.baseY = base.getBlockY();
		this.baseZ = base.getBlockZ();
		this.blockPointer = base;
		this.type = type;
		blockMap = new HashMap<Location, Material>();
		orientations = new HashMap<Location, BlockFace>();
		pointerHistory = new Stack<>();
		collectBlocks();
		
	}
	
	private void collectBlocks() {
		
		if (type >= 1) {
			putWood(); north(); putWood(); north(); putSlab(); down(); putWood(); down(); north(); east(); putWood(); reset(); reset();
			north(); east(); putSlab(); down(); putWood(); east(); putWood(); down(); putWood(); reset();
			west(); putWood(); north(); putSlab(); down(); putWood(); west(); putWood(); north(); putWood(); down(); north(); putWood(); back(); west(); putWood(); reset();
			west(); south(); putSlab(); down(); putWood(); west(); putWood(); down(); putWood(); west(); south(); putWood(); reset(); 
			south(); east(); putWood(); south(); putSlab(); down(); putWood(); down(); east(); putWood(); north(); putWood(); reset();
			up(); putWood(); north(); putStairs(BlockFace.SOUTH); center();
			up(); putWood(); up(); putWood(); up(); putWood(); up();
			north(); putWood(); east(); south(2); putWood(); west(2); putWood(); center(); up();
			north(); putWood(); east(2); south(2); putWood(); south(); west(4); putWood(); center(); up();
			north(2); putWood(); south(3); west(2); putWood(); south(); east(4); putWood(); center();
			putLeaves();

		} else if (type >= 2) {
			// BUILD TREE 2
		} else {
			// BUILD TREE 3
		}
		
	}
	
	public void assemble() {
		
		for (Location l : blockMap.keySet()) {
			
			setBlock(l, blockMap.get(l));
			
		}
		
	}
	
	private void setBlock(Location loc, Material m) {
		
//		Material type = loc.getBlock().getType();
		Block b = loc.getBlock();
		
//		if (!(type == Material.AIR || b.getBlockData() instanceof Sapling)) return;
		
		if (b.getBlockData() instanceof Leaves) {   
            Leaves leavesData = (Leaves) b.getBlockData();
            leavesData.setPersistent(true);
            b.setBlockData(leavesData);
		}
		
		b.setType(m);
		
	}
	
	private void increment(int x, int y, int z) {
		pointerHistory.push(blockPointer.clone()); 
		blockPointer.setX(blockPointer.getX() + x);
		blockPointer.setY(blockPointer.getY() + y);
		blockPointer.setZ(blockPointer.getZ() + z);
	}
	
	private void reset() { 
		blockPointer.setX(baseX); blockPointer.setY(baseY); blockPointer.setZ(baseZ);
	}
	private void center() { pointerHistory.push(blockPointer.clone()); blockPointer.setX(baseX); blockPointer.setZ(baseZ); }
	private void putWood() { blockMap.put(blockPointer.clone(), wood); }
	private void putSlab() { blockMap.put(blockPointer.clone(), slab); }
	private void putLeaves() { blockMap.put(blockPointer.clone(), leaves); }
	private void up() { up(1); }
	private void down() { down(1); }
	private void north() { north(1); }
	private void south() { south(1); }
	private void east() { east(1); }
	private void west() { west(1); }
	private void up(int i) { increment(0,i,0); }
	private void down(int i) { increment(0,-i,0); }
	private void north(int i) { increment(0,0,-i); }
	private void south(int i) { increment(0,0,i); }
	private void east(int i) { increment(i,0,0); }
	private void west(int i) { increment(-i,0,0); }
	private void putStairs(BlockFace direction) { 
		blockMap.put(blockPointer.clone(), stairs); 
		orientations.put(blockPointer.clone(), direction);
	}
	private void back() { blockPointer = pointerHistory.pop(); }
}
