package github.faxundo.argentina.block;

import github.faxundo.argentina.ArgentineCulture;
import github.faxundo.argentina.block.custom.KettleBlock;
import github.faxundo.argentina.block.custom.YerbaCropBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import static github.faxundo.argentina.ArgentineCulture.MOD_NAME;
import static github.faxundo.argentina.ArgentineCulture.LOGGER;

public class ACBlock {

    public static final Block YERBA_CROP = registerBlock("yerba_crop",
            new YerbaCropBlock(AbstractBlock.Settings.copy(Blocks.BEETROOTS)
                    .breakInstantly().noCollision().ticksRandomly()));
    public static final Block KETTLE_BLOCK = registerBlock("kettle",
            new KettleBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, ArgentineCulture.identifier(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, ArgentineCulture.identifier(name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerOLBlocks() {
        LOGGER.info(">>> Registering " + MOD_NAME + " blocks.");
    }
}
