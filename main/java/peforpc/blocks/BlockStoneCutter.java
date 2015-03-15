package peforpc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import peforpc.PEforPC;
import peforpc.blocks.tileEntity.TileEntityStoneCutter;
import peforpc.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStoneCutter extends BlockContainer {
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon bottomIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon backIcon;
	public BlockStoneCutter(Material mat, String modid, String name) {
		super(mat);
		 setHardness(2F);
		 setResistance(5F);
		 setStepSound(Block.soundTypeStone);
	}
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister IIconRegister) {
		topIcon = IIconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":" + "Stone_Cutter" + "_Top");
		bottomIcon = IIconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":" + "Stone_Cutter" + "_Bottom");
		sideIcon = IIconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":" + "Stone_Cutter" + "_Side");
		backIcon = IIconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":" + "Stone_Cutter" + "_Front_Back");
		}
	
		 @Override
	     public boolean onBlockActivated(World world, int x, int y, int z,
	                     EntityPlayer player, int metadata, float what, float these, float are) {
	             TileEntity tileEntity = world.getTileEntity(x, y, z);
	             if (tileEntity == null || player.isSneaking()) {
	                     return false;
	             }
	     player.openGui(PEforPC.instance, 0, world, x, y, z);
	             return true;
	     }
		
		
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
	if(side == 0) {
	return bottomIcon;
	} else if(side == 1) {
	return topIcon;
	}
	else if(side == 2){
		return backIcon; 
	}
	else if(side == 3){
		return backIcon;
	}
		else {
	return sideIcon;
	}
	}
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityStoneCutter(); 
	}
	
}