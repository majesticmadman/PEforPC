package peforpc.items;

import peforpc.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBeetrootSoup extends ItemFood {

	public ItemBeetrootSoup(String modid, String name) {
		super(1, false);
		setTextureName(Reference.MOD_ID_LOWERCASE + ":" + "Beetroot_Soup");
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Items.bowl);
    }

}
