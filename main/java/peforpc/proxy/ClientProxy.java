package peforpc.proxy;

import peforpc.blocks.tileEntity.TileEntityReactor;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {

	@Override
	public void registerThings() {
		GameRegistry.registerTileEntity(TileEntityReactor.class, "TileEntityReactor");
	
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

}
