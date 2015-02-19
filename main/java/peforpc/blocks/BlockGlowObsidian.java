package peforpc.blocks;

import java.util.Random;

import peforpc.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockGlowObsidian extends Block {

	public BlockGlowObsidian(Material mat, String modid, String name) {
		super(mat);
		// glowstone = 1.0F
		setLightLevel(0.7F);
		setHardness(50.0F);
		setResistance(2000.0F);
		setBlockTextureName(Reference.MOD_ID_LOWERCASE + ":" + "glowingObsidian");
	}

	public Block idDropped(int par1, Random par2Random, int par3) {
		return Blocks.obsidian;
	}

}
