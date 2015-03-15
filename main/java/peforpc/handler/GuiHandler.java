package peforpc.handler;

import peforpc.blocks.tileEntity.TileEntityStoneCutter;
import peforpc.gui.ContainerCutter;
import peforpc.gui.GuiCutter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEnt = world.getTileEntity(x, y, z);
		if (tileEnt instanceof TileEntityStoneCutter)
			return new ContainerCutter(
					(TileEntityStoneCutter) tileEnt);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEnt = world.getTileEntity(x, y, z);
		if (tileEnt instanceof TileEntityStoneCutter) {
			return new GuiCutter((TileEntityStoneCutter) tileEnt);
		}
		return null;
	}

}
