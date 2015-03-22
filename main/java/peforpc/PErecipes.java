package peforpc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class PErecipes {
	public static void craftSmelt() {
		GameRegistry.addRecipe(new ItemStack(PEforPC.beetrootSoup),
				new Object[] { "###", "###", " X ", '#', PEforPC.beetroot, 'X', Items.bowl});
		GameRegistry.addSmelting(PEforPC.beetroot, new ItemStack(
				Items.dye, 1, 1), 5F);
		
		GameRegistry.addRecipe(new ItemStack(PEforPC.netherReactorCore),
				new Object[] { "idi", "idi", "idi", 'i', Items.iron_ingot, 'd',
						Items.diamond, 'c', Blocks.quartz_block });
		
		GameRegistry.addRecipe(new ItemStack(PEforPC.BlockStoneCutter), new Object[] {"   ", " ##", " ##", '#', Blocks.cobblestone});;
		
		
	}
}
