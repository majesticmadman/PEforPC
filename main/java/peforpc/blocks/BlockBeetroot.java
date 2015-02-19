package peforpc.blocks;

import peforpc.PEforPC;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockBeetroot extends BlockCrops {

	public BlockBeetroot(String modid, String name) {
		super();
	}

    protected Item func_149866_i()
    {
        return PEforPC.beetrootSeed;
    }

    protected Item func_149865_P()
    {
        return PEforPC.beetroot;
    }
    
    
}
