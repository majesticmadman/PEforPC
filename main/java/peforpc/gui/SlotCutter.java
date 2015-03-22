package peforpc.gui;



import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

    public class SlotCutter extends Slot
    {
        private final Slot field_148332_b;

        public SlotCutter(Slot slot, int p_i1087_3_)
        {
            super(slot.inventory, p_i1087_3_, 0, 0);
            this.field_148332_b = slot;
        }

        public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
        {
            this.field_148332_b.onPickupFromSlot(p_82870_1_, p_82870_2_);
        }

        /**
         * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
         */
        public boolean isItemValid(ItemStack p_75214_1_)
        {
            return this.field_148332_b.isItemValid(p_75214_1_);
        }

        /**
         * Helper fnct to get the stack in the slot.
         */
        public ItemStack getStack()
        {
            return this.field_148332_b.getStack();
        }

        /**
         * Returns if this slot contains a stack.
         */
        public boolean getHasStack()
        {
            return this.field_148332_b.getHasStack();
        }

        /**
         * Helper method to put a stack in the slot.
         */
        public void putStack(ItemStack p_75215_1_)
        {
            this.field_148332_b.putStack(p_75215_1_);
        }

        /**
         * Called when the stack in a Slot changes
         */
        public void onSlotChanged()
        {
            this.field_148332_b.onSlotChanged();
        }

        /**
         * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
         * case of armor slots)
         */
        public int getSlotStackLimit()
        {
            return this.field_148332_b.getSlotStackLimit();
        }

        /**
         * Returns the icon index on items.png that is used as background image of the slot.
         */
        public IIcon getBackgroundIconIndex()
        {
            return this.field_148332_b.getBackgroundIconIndex();
        }

        /**
         * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
         * stack.
         */
        public ItemStack decrStackSize(int p_75209_1_)
        {
            return this.field_148332_b.decrStackSize(p_75209_1_);
        }

        /**
         * returns true if this slot is in par2 of par1
         */
        public boolean isSlotInInventory(IInventory p_75217_1_, int p_75217_2_)
        {
            return this.field_148332_b.isSlotInInventory(p_75217_1_, p_75217_2_);
        }
    }