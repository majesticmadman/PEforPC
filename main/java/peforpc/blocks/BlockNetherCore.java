package peforpc.blocks;

import java.util.Random;

import peforpc.PEforPC;
import peforpc.blocks.tileEntity.TileEntityReactor;
import peforpc.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockNetherCore extends BlockContainer {

	public static int maximumCharge = 5;// 45;

	public BlockNetherCore(Block netherReactorCore, Material mat, String modid, String name) {
		super(mat);
		this.setResistance(4000.0F);
		 setHarvestLevel("pickaxe", 1);
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.icons = new IIcon[3];

		String textureName = this.getUnlocalizedName().substring(
				this.getUnlocalizedName().indexOf(".") + 1);
		this.icons[0] = iconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":"
				+ textureName + "_Full");
		this.icons[2] = iconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":"
				+ textureName + "_Active");
		this.icons[1] = iconRegister.registerIcon(Reference.MOD_ID_LOWERCASE + ":"
				+ textureName + "_Drained");

	}

	@SideOnly(Side.CLIENT)
	/**
	 * @param side
	 * @param metadata
	 */
	public IIcon getIcon(int side, int metadata) {
		if (metadata < 0)
			metadata = 0;

		if (metadata == 0) // Drained
			return this.icons[0];
		else if (metadata == BlockNetherCore.maximumCharge + 1) // Active
			return this.icons[2];
		else
			// Between 0 & MaxCharge; Charged
			return this.icons[1];

	}

	public int damageDropped(int meta) {
		return 1;
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z,
			int meta) {
		TileEntity tEnt = world.getTileEntity(x, y, z);
		if (tEnt instanceof TileEntityReactor) {
			TileEntityReactor tileEnt = (TileEntityReactor) tEnt;
			if (meta > BlockNetherCore.maximumCharge) {
				super.onBlockDestroyedByPlayer(world, x, y, x, tileEnt.charge);
			}
		}

	}

	public TileEntity createNewTileEntity(World world) {
		return new TileEntityReactor();
	}

	private void setBlockNew(World world, int coreX, int coreY, int coreZ,
			int x, int y, int z, Block block) {
		this.setBlockMetaWithNotifyNew(world, coreX, coreY, coreZ, x, y, z,
				block, 0);
	}

	private void setBlockMetaWithNotifyNew(World world, int coreX, int coreY,
			int coreZ, int x, int y, int z , Block block, int flag) {
		Random rand = new Random();
		boolean degrate = rand.nextInt(100) % 5 == 0;
		if (degrate) {
			TileEntity tEnt = world.getTileEntity(coreX, coreY, coreZ);
			if (tEnt != null) {
				if (tEnt instanceof TileEntityReactor) {
					// PEforPC.log.info("Setting Degration");
					((TileEntityReactor) tEnt).setBlockDegrate(x, y, z);
				}
			} else {
				// PEforPC.log.info("No Tile Ent");
			}
		}

		world.setBlock(x, y, z, block);

	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float par7, float par8, float par9) {
		if (!world.isRemote) {
			if (player.isSneaking())
				return false;

			TileEntity tEnt = world.getTileEntity(x, y, z);
			if (tEnt instanceof TileEntityReactor) {
				TileEntityReactor tileEnt = (TileEntityReactor) tEnt;
				if (world.getBlockMetadata(x, y, z) <= 0)
					tileEnt.isExhausted = true;
				PEforPC.log.info("isActive: " + tileEnt.isActive);
				PEforPC.log.info("isExhausted: " + tileEnt.isExhausted);
				if (!tileEnt.isActive && !tileEnt.isExhausted) {
					if (this.structureCheck(world, x, y, z)) {
						PEforPC.log.info("Making Spire");
						this.createSpire(world, x, y, z);
						tileEnt.isActive = true;

						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean structureCheck(World world, int x, int y, int z) {
		Block cobble = Blocks.cobblestone;
		Block gold = Blocks.gold_block;

		boolean bottomGold = world.getBlock(x - 1, y - 1, z - 1) == gold
				&& world.getBlock(x + 1, y - 1, z - 1) == gold
				&& world.getBlock(x - 1, y - 1, z + 1) == gold
				&& world.getBlock(x + 1, y - 1, z + 1) == gold;
		boolean cobble1 = world.getBlock(x - 1, y - 1, z - 0) == cobble
				&& world.getBlock(x - 0, y - 1, z - 1) == cobble
				&& world.getBlock(x + 1, y - 1, z - 0) == cobble
				&& world.getBlock(x - 0, y - 1, z + 1) == cobble
				&& world.getBlock(x - 0, y - 1, z - 0) == cobble;
		boolean cobble2 = world.getBlock(x - 1, y - 0, z - 1) == cobble
				&& world.getBlock(x + 1, y - 0, z - 1) == cobble
				&& world.getBlock(x - 1, y - 0, z + 1) == cobble
				&& world.getBlock(x + 1, y - 0, z + 1) == cobble;
		boolean cobble3 = world.getBlock(x - 1, y + 1, z - 0) == cobble
				&& world.getBlock(x - 0, y + 1, z - 1) == cobble
				&& world.getBlock(x + 1, y + 1, z - 0) == cobble
				&& world.getBlock(x - 0, y + 1, z + 1) == cobble
				&& world.getBlock(x - 0, y + 1, z - 0) == cobble;

		return bottomGold && cobble1 && cobble2 && cobble3;

	}

	private void createSpire(World world, int x, int y, int z) {
		Block block = Blocks.netherrack;
		int radiusFromCore = 7;
		int baseofSpire = y - 2;
		int spireBoxHeight = 1 + 5 + 1 + 5;// floor+4+floor+4+spire
		// hollow it out
		this.spireHollowInterior(world, x, z, baseofSpire, radiusFromCore, y
				+ spireBoxHeight + 40);
		// floors of spire
		this.spireFloor(world, x, y, z, baseofSpire, block, radiusFromCore);
		this.spireFloor(world, x, y, z, baseofSpire + 6, block, radiusFromCore);
		this.spireFloor(world, x, y, z, baseofSpire + 12, block, radiusFromCore);
		// ~~~~~~~~~~~~~~~
		// Walls
		// +X
		int wallX = x + radiusFromCore + 1;
		for (int j = baseofSpire; j <= baseofSpire + spireBoxHeight; j++) {
			for (int k = z - radiusFromCore - 1; k <= z + radiusFromCore + 1; k++) {
				if (!this.isValidSpireWall(world.getBlock(wallX, j, k))) {
					// world.destroyBlock(wallX, j, k, true);
					this.setBlockNew(world, x, y, z, wallX, j, k, block);
				}
			}
		}
		// -X
		wallX = x - radiusFromCore - 1;
		for (int j = baseofSpire; j <= baseofSpire + spireBoxHeight; j++) {
			for (int k = z - radiusFromCore - 1; k <= z + radiusFromCore + 1; k++) {
				if (!this.isValidSpireWall(world.getBlock(wallX, j, k))) {
					// world.destroyBlock(wallX, j, k, true);
					this.setBlockNew(world, x, y, z, wallX, j, k, block);
				}
			}
		}
		// +Z
		int wallZ = z + radiusFromCore + 1;
		for (int j = baseofSpire; j <= baseofSpire + spireBoxHeight; j++) {
			for (int i = x - radiusFromCore - 1; i <= x + radiusFromCore + 1; i++) {
				if (!this.isValidSpireWall(world.getBlock(i, j, wallZ))) {
					// world.destroyBlock(i, j, wallZ, true);
					this.setBlockNew(world, x, y, z, i, j, wallZ, block);
				}
			}
		}
		// -Z
		wallZ = z - radiusFromCore - 1;
		for (int j = baseofSpire; j <= baseofSpire + spireBoxHeight; j++) {
			for (int i = x - radiusFromCore - 1; i <= x + radiusFromCore + 1; i++) {
				if (!this.isValidSpireWall(world.getBlock(i, j, wallZ))) {
					// world.destroyBlock(i, j, wallZ, true);
					this.setBlockNew(world, x, y, z, i, j, wallZ, block);
				}
			}
		}
		// ~~~~~~~~~~~~~~~
		// spire
		// this.spire(world, x, z, baseofSpire + 12, block, radiusFromCore);
		// ~~~~~~~~~~~~~~~
		// Lighting
		// this.spireLight(world, x, z, baseofSpire, Blocks.glowStone,
		// radiusFromCore);
		block = PEforPC.glowObsidian;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int k = z - 1; k <= z + 1; k++) {
				world.setBlock(i, y - 1, k, block);
			}
		}
		world.setBlock(x - 1, y - 0, z - 1, block);
		world.setBlock(x + 1, y - 0, z - 1, block);
		world.setBlock(x - 1, y - 0, z + 1, block);
		world.setBlock(x + 1, y - 0, z + 1, block);
		world.setBlock(x + 1, y + 1, z - 0, block);
		world.setBlock(x - 1, y + 1, z - 0, block);
		world.setBlock(x - 0, y + 1, z - 0, block);
		world.setBlock(x - 0, y + 1, z + 1, block);
		world.setBlock(x - 0, y + 1, z - 1, block);
		// ~~~~~~~~~~~~~~~
		// Spawner
		world.setBlock(x, y + 5, z, Blocks.mob_spawner, 0, 2);
		TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world
				.getTileEntity(x, y + 5, z);

		if (tileentitymobspawner != null) {
			tileentitymobspawner.func_145881_a().setEntityName("Blaze");
		}

	}

	private boolean isValidSpireWall(Block block) {
		return block == Blocks.netherrack
				|| block == Blocks.nether_brick
				|| block == Blocks.bedrock
				|| block == Blocks.obsidian
				|| block == Blocks.glowstone;
	}

	private void spireFloor(World world, int coreX, int coreY, int coreZ,
			int floorY, Block block, int radiusFromCore) {
		for (int i = coreX - radiusFromCore - 1; i <= coreX + radiusFromCore
				+ 1; i++) {
			for (int k = coreZ - radiusFromCore - 1; k <= coreZ
					+ radiusFromCore + 1; k++) {
				if (!this.isValidSpireWall(world.getBlock(i, floorY, k))) {
					// world.destroyBlock(i, floorY, k, true);
					this.setBlockNew(world, coreX, coreY, coreZ, i, floorY, k,
							block);
				}
			}
		}
	}

	private void spireLight(World world, int coreX, int coreZ, int floorY,
			Block blockiD, int radiusFromCore) {
		for (int layer = floorY; layer <= floorY + 5; layer += 5) {
			world.setBlock(coreX - 3, layer, coreZ - 3, blockiD);
			world.setBlock(coreX + 3, layer, coreZ - 3, blockiD);
			world.setBlock(coreX - 3, layer, coreZ + 3, blockiD);
			world.setBlock(coreX + 3, layer, coreZ + 3, blockiD);
		}

	}

	private void spireHollowInterior(World world, int coreX, int coreZ,
			int spireBase, int radiusFromCore, int height) {
		int minWallY = spireBase;
		int maxWallY = height;
		int minWallX = coreX - radiusFromCore - 1;
		int maxWallX = coreX + radiusFromCore + 1;
		int minWallZ = coreZ - radiusFromCore - 1;
		int maxWallZ = coreZ + radiusFromCore + 1;
		for (int j = maxWallY; j >= minWallY; j--) {
			for (int i = minWallX; i <= maxWallX; i++) {
				for (int k = minWallZ; k <= maxWallZ; k++) {
					if (world.getBlock(i, j, k) != this) {
						// world.destroyBlock(i, j, k, true);
						world.setBlock(i, j, k, null, 0, k);
					}
				}
			}
		}

	}

	private void spire(World world, int coreX, int coreZ, int floorY,
			Block blockiD, int radiusFromCore) {

		int maxWallX = coreX + radiusFromCore + 1;
		int maxWallZ = coreZ + radiusFromCore + 1;
		int minWallX = coreX - radiusFromCore - 1;
		int minWallZ = coreZ - radiusFromCore - 1;
		int yLayer = floorY;
		int i, k;

		k = minWallZ;
		while (k <= maxWallZ - 3) {
			yLayer++;
			k += 2;
			for (int i1 = maxWallX; i1 >= maxWallX - 2; i1--) {
				for (int k1 = k; k1 <= maxWallZ; k1++) {
					world.setBlock(i1, yLayer, k1, blockiD);
				}
			}
		}

		i = maxWallX;
		while (i >= minWallX + 3) {
			yLayer++;
			i -= 2;
			for (int k1 = maxWallZ; k1 >= maxWallZ - 2; k1--) {
				for (int i1 = i; i1 >= minWallX; i1--) {
					world.setBlock(i1, yLayer, k1, blockiD);
				}
			}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityReactor();
	}
}
