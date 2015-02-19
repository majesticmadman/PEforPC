package peforpc.items;

import peforpc.lib.Reference;
import net.minecraft.item.ItemFood;

public class ItemBeetroot extends ItemFood {

	public ItemBeetroot(String modid, String name) {
		super(1, false);
		setTextureName(Reference.MOD_ID_LOWERCASE + ":" + "Beetroot");
	}

}
