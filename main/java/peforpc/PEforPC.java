package peforpc;

import java.util.logging.Logger;

import peforpc.blocks.BlockBeetroot;
import peforpc.blocks.BlockGlowObsidian;
import peforpc.blocks.BlockNetherCore;
import peforpc.blocks.BlockCyanFlower;
import peforpc.blocks.BlockStoneCutter;
import peforpc.handler.GuiHandler;
import peforpc.items.ItemBeetroot;
import peforpc.items.ItemBeetrootSeed;
import peforpc.items.ItemBeetrootSoup;

import peforpc.proxy.ServerProxy;
import peforpc.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class PEforPC {

	public static final Logger log = Logger.getLogger(Reference.MOD_ID);

	@Instance(Reference.MOD_ID)
	public static PEforPC instance;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static ServerProxy proxy;

	// ~~~~~~~~~~~~~~~~~~~~~~~~
	// Items
	public static Item beetroot;
	public static String beetrootName = "Beetroot";
	public static Item beetrootSeed;
	public static String beetrootSeedName = "Beetroot Seeds";
	public static Item beetrootSoup;
	public static String beetrootSoupName = "Beetroot Soup";
	
	// blocks
	public static Block beetrootPlant;
	public static String beetrootPlantName = "Beetroot";
	public static Block netherReactorCore;
	public static String netherReactorCoreName = "Nether Reactor Core";
	public static Block glowObsidian;
	public static String glowObsidianName = "Glowing Obsidian";
	public static String stoneCutterName = "Stone Cutter"; 
	public static Block BlockStoneCutter;
	public static Block BlockCyanFlower;
	public static String BlockCyanFlowerName = "Cyan Flower";
	

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();

		config.save();
		
		
		// Physical Stuff
		proxy.registerThings();
		proxy.registerServerTickHandler();

		PEforPC.blocks();
		PEforPC.Itemss();
		PErecipes.craftSmelt();
		PEforPC.entity();
		 NetworkRegistry.INSTANCE.registerGuiHandler(this,
					new GuiHandler());

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		PEforPC.Register();
		MinecraftForge.addGrassSeed(new ItemStack(beetrootSeed), 10);
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}

	public static void Itemss() {
		beetroot = new ItemBeetroot(Reference.MOD_ID, PEforPC.beetrootName);
		beetroot.setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName("beetroot");

		beetrootSeed = new ItemBeetrootSeed(Reference.MOD_ID, PEforPC.beetrootSeedName);
		beetrootSeed.setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("beetrootSeed");

		beetrootSoup = new ItemBeetrootSoup(Reference.MOD_ID, PEforPC.beetrootSoupName);
		beetrootSoup.setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName("beetrootSoup");
		

	}

	public static void blocks() {
		PEforPC.beetrootPlant = new BlockBeetroot(Reference.MOD_ID, PEforPC.beetrootPlantName);
		PEforPC.beetrootPlant.setBlockName("beetrootPlant");
		PEforPC.netherReactorCore = new BlockNetherCore(
				PEforPC.netherReactorCore, Material.rock, Reference.MOD_ID_LOWERCASE,
				PEforPC.netherReactorCoreName);
		PEforPC.netherReactorCore.setCreativeTab(CreativeTabs.tabBlock).setBlockName("netherReactorCore");
		PEforPC.glowObsidian = new BlockGlowObsidian(Material.rock, Reference.MOD_ID, PEforPC.glowObsidianName);
		PEforPC.glowObsidian.setCreativeTab(CreativeTabs.tabBlock).setBlockName("glowObsidian");
		PEforPC.BlockStoneCutter = new BlockStoneCutter(Material.rock);
		PEforPC.BlockStoneCutter.setCreativeTab(CreativeTabs.tabBlock).setBlockName("stoneCutter");
		PEforPC.BlockCyanFlower = new BlockCyanFlower(Reference.MOD_ID_LOWERCASE, BlockCyanFlowerName, 0);
		PEforPC.BlockCyanFlower.setCreativeTab(CreativeTabs.tabBlock).setBlockName("cyanFlower"); 
	}
	
	public static void Register(){
		GameRegistry.registerBlock(netherReactorCore, Reference.MOD_ID_LOWERCASE + (netherReactorCore.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(BlockStoneCutter, Reference.MOD_ID_LOWERCASE + (BlockStoneCutter.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(glowObsidian,Reference.MOD_ID_LOWERCASE + glowObsidian.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(beetrootPlant,Reference.MOD_ID_LOWERCASE + beetrootPlant.getUnlocalizedName().substring(5)); 
		GameRegistry.registerBlock(BlockCyanFlower,Reference.MOD_ID_LOWERCASE + BlockCyanFlower.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(beetroot,Reference.MOD_ID_LOWERCASE + beetroot.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(beetrootSeed,Reference.MOD_ID_LOWERCASE + beetrootSeed.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(beetrootSoup,Reference.MOD_ID_LOWERCASE + beetrootSoup.getUnlocalizedName().substring(5));
	}
	public static void entity() {

	}
	
}
