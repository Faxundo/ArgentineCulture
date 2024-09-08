package github.faxundo.argentina.item;

import github.faxundo.argentina.ArgentineCulture;
import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.item.custom.MateItem;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static github.faxundo.argentina.ArgentineCulture.LOGGER;
import static github.faxundo.argentina.ArgentineCulture.MOD_NAME;

public class ACItem {

    public static final Item ICON = registerItem("icon", new Item(new Item.Settings()));

    public static final Item MATE = registerItem("mate", new MateItem(new Item.Settings()));
    public static final Item YERBA = registerItem("yerba", new Item(new Item.Settings()));
    public static final Item YERBA_SEED = registerItem("yerba_seed",
            new AliasedBlockItem(ACBlock.YERBA_CROP, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, ArgentineCulture.identifier(name), item);
    }

    public static void registerOLItems() {
        LOGGER.info(">>> Registering " + MOD_NAME + " items.");
    }
}
