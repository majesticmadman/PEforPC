package peforpc.items;

import peforpc.PEforPC;
import peforpc.lib.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemBeetrootSeed extends ItemSeeds {

	public ItemBeetrootSeed(String modid, String name) {
		super(PEforPC.beetrootPlant, Blocks.farmland);
		setTextureName(Reference.MOD_ID_LOWERCASE + ":" + "Beetroot_Seeds");
	}
	
	

}
