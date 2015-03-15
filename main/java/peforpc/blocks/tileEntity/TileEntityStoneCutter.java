package peforpc.blocks.tileEntity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStoneCutter extends TileEntity implements ISidedInventory {

	public final int maxCookTime = 200 / 4;
	public int furnaceCookTime;
	public int furnaceBurnTime;
    public int numPlayersUsing;
    public int currentItemBurnTime;
    private String name;
	private ItemStack[] invStacks = new ItemStack[3];

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.invStacks[i] = itemstack;
	}

		
	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.invStacks[i] != null) {
			ItemStack itemstack;
			if (this.invStacks[i].stackSize <= j) {
				itemstack = this.invStacks[i];
				this.invStacks[i] = null;
				return itemstack;
			} else {
				itemstack = this.invStacks[i].splitStack(j);
				if (this.invStacks[i].stackSize == 0)
					this.invStacks[i] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.invStacks[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.invStacks[i] != null) {
			ItemStack retStack = this.invStacks[i];
			this.invStacks[i] = null;
			return retStack;
		}
		return null;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		int[] slotInput = new int[] { 0 };
		int[] slotOutput = new int[] { 1 };
		if (side == 1)
			return slotInput;
		else if (side == 0)
			return slotOutput;
		else
			return new int[] { 0, 1 };
	}

	@Override
	public int getSizeInventory() {
		return this.invStacks.length;
	}

	public String getInvName() {
		return "Forge";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return true;
	}

	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}


	@Override
	public void writeToNBT(NBTTagCompound tagCom) {
		super.writeToNBT(tagCom);
		tagCom.setShort("CookTime", (short) this.furnaceCookTime);
		NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < this.invStacks.length; ++i) {
			if (this.invStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.invStacks[i].writeToNBT(nbttagcompound1);
				tagList.appendTag(nbttagcompound1);
			}
		}

		tagCom.setTag("Items", tagList);

	}

	@Override
	public void readFromNBT(NBTTagCompound tagCom) {
		super.readFromNBT(tagCom);
		NBTTagList tagList = tagCom.getTagList("Items", furnaceCookTime);
		this.invStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.invStacks.length)
				this.invStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
		}

		this.furnaceCookTime = tagCom.getShort("CookTime");
		this.furnaceBurnTime = tagCom.getShort("BurnTime");
		this.currentItemBurnTime = getItemBurnTime(this.invStacks [1]);

	}

	public void openChest() {
	}

	public void closeChest() {
	}

	   public boolean isBurning()
	    {
	        return this.furnaceBurnTime > 0;
	    }
	
	
	private boolean canSmelt() {
		return this.invStacks[0] != null && this.invStacks[1] != null;
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = this.invStacks[1].copy();

			--this.invStacks[0].stackSize;
			if (this.invStacks[0].stackSize <= 0)
				this.invStacks[0] = null;

			int damage = itemstack.getItemDamage() + 1;
			itemstack.setItemDamage(damage);

			this.invStacks[1] = itemstack;
		}
	}

	 public void updateEntity()
	    {
	        boolean flag = this.furnaceBurnTime > 0;
	        boolean flag1 = false;

	        if (this.furnaceBurnTime > 0)
	        {
	            --this.furnaceBurnTime;
	        }

	        if (!this.worldObj.isRemote)
	        {
	            if (this.furnaceBurnTime != 0 || this.invStacks [1] != null && this.invStacks [0] != null)
	            {
	                if (this.furnaceBurnTime == 0 && this.canSmelt())
	                {
	                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.invStacks [1]);

	                    if (this.furnaceBurnTime > 0)
	                    {
	                        flag1 = true;

	                        if (this.invStacks [1] != null)
	                        {
	                            --this.invStacks [1].stackSize;

	                            if (this.invStacks [1].stackSize == 0)
	                            {
	                                this.invStacks [1] = invStacks [1].getItem().getContainerItem(invStacks [1]);
	                            }
	                        }
	                    }
	                }

	                if (this.isBurning() && this.canSmelt())
	                {
	                    ++this.furnaceCookTime;

	                    if (this.furnaceCookTime == 200)
	                    {
	                        this.furnaceCookTime = 0;
	                        this.smeltItem();
	                        flag1 = true;
	                    }
	                }
	                else
	                {
	                    this.furnaceCookTime = 0;
	                }
	            }

	            if (flag != this.furnaceBurnTime > 0)
	            {
	                flag1 = true;
	                BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	            }
	        }

	        if (flag1)
	        {
	            this.markDirty();
	        }
	    }
	

    public static int getItemBurnTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
            Item item = p_145952_0_.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {



            }

            if (item == Items.lava_bucket) return 20000;
            return GameRegistry.getFuelValue(p_145952_0_);
        }
    }
	
	
	@SideOnly(Side.CLIENT)
	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	public int getCookProgressScaled(int par1) {
		return this.furnaceCookTime * par1 / this.maxCookTime;
	}

	   public String getInventoryName()
	    {
	        return this.hasCustomInventoryName() ? this.name : "container.forge";
	    }

	   public boolean hasCustomInventoryName()
	    {
	        return this.name != null && this.name.length() > 0;
	    }

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {	
	}



    public static boolean isItemFuel(ItemStack itemstack)
    {
        return getItemBurnTime(itemstack) > 0;
    }


    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return p_94041_1_ == 2 ? false : (p_94041_1_ == 1 ? isItemFuel(p_94041_2_) : true);
    }

}
