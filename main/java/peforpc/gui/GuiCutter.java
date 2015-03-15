package peforpc.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import peforpc.blocks.tileEntity.TileEntityStoneCutter;
import peforpc.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCutter extends GuiContainer {
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(
			Reference.MOD_ID_LOWERCASE + ":textures/gui/Stone Cutter Design.png");
	private TileEntityStoneCutter inventory;

	public GuiCutter(TileEntityStoneCutter tileEnt) {
		super(new ContainerCutter(tileEnt));
		this.inventory = tileEnt;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		

		i1 = this.inventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
	  public Object getServerGuiElement(int id, EntityPlayer player, World world,
                      int x, int y, int z) {
              TileEntity tileEntity = world.getTileEntity(x, y, z);
              if(tileEntity instanceof TileEntityStoneCutter){
                      return new ContainerCutter((TileEntityStoneCutter) tileEntity);
              }
              return null;
      }

      //returns an instance of the Gui you made earlier
      public Object getClientGuiElement(int id, EntityPlayer
    		  player, World world,
                      int x, int y, int z) {
              TileEntity tileEntity = world.getTileEntity(x, y, z);
              if(tileEntity instanceof TileEntityStoneCutter){
                      return new GuiCutter((TileEntityStoneCutter) tileEntity);
              }
              return null;

      }
	
}
