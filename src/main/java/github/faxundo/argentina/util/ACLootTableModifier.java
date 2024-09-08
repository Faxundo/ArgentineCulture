package github.faxundo.argentina.util;

import github.faxundo.argentina.item.ACItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class ACLootTableModifier {

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (Blocks.SHORT_GRASS.getLootTableKey() == key || Blocks.TALL_GRASS.getLootTableKey() == key) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(0.1f))
                        .with(ItemEntry.builder(ACItem.YERBA_SEED));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
