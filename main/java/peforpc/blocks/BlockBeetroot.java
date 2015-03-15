package peforpc.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import peforpc.PEforPC;
import peforpc.lib.Reference;
import net.minecraft.block.BlockCarrot;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockBeetroot extends BlockCarrot {
	   protected IIcon[] iIcon;
	   private IIcon[] field_149868_a;
	   
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
    
    
    @Override
    public Item getItemDropped(int p_149650_1_, Random parRand, int parFortune)
    {
        return Item.getItemFromBlock(this);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 7)
        {
            if (par2 == 6)
            {
                par2 = 5;
            }

            return this.field_149868_a[par2 >> 1];
        }
        else
        {
            return this.field_149868_a[3];
        }
    }
    
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister)
    {
        this.field_149868_a = new IIcon[4];

        for (int i = 0; i < this.field_149868_a.length; ++i)
        {
            this.field_149868_a[i] = iconregister.registerIcon(Reference.MOD_ID_LOWERCASE +":"  +  "beetrootPlant_stage_" + i);
        }
    }
}
