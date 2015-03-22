package peforpc.blocks;

import java.util.Random;

import peforpc.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockCyanFlower extends BlockBush {
	 @SideOnly(Side.CLIENT)
	  private IIcon blockIcon;
	   
	public BlockCyanFlower(String modid, String name, int iconindex) {
		super();
	}

	public int damageDropped(int damage)
    {
        return damage;
    }
    
    @Override
    public Item getItemDropped(int p_149650_1_, Random parRand, int parFortune)
    {
        return Item.getItemFromBlock(this);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {

        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister)
    {
    	blockIcon = iconregister.registerIcon(Reference.MOD_ID_LOWERCASE + ":" + "Cyan_Flower");
    }
    
}
