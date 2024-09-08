package github.faxundo.argentina.item;

import github.faxundo.argentina.block.ACBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static github.faxundo.argentina.ArgentineCulture.MOD_ID;
import static github.faxundo.argentina.ArgentineCulture.LOGGER;
import static github.faxundo.argentina.ArgentineCulture.MOD_NAME;

public class ACItemGroup {

    private static final RegistryKey<ItemGroup> ARGENTINE_CULTURE = RegistryKey.of(RegistryKeys.ITEM_GROUP,
            Identifier.of(MOD_ID, MOD_ID));

    public static void registerItemGroup() {
        LOGGER.info(">>> Registering " + MOD_NAME + " item groups.");
        Registry.register(Registries.ITEM_GROUP, ARGENTINE_CULTURE, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ACItem.ICON))
                .displayName(Text.translatable("item.argentine_culture.icon"))
                .entries((context, entries) -> {
                    entries.add(ACItem.MATE);
                    entries.add(ACItem.YERBA);
                    entries.add(ACItem.YERBA_SEED);
                    entries.add(ACBlock.KETTLE_BLOCK);
                })
                .build()
        );
    }
}
