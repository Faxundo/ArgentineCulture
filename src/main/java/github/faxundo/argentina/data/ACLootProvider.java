package github.faxundo.argentina.data;

import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.block.custom.YerbaCropBlock;
import github.faxundo.argentina.item.ACItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ACLootProvider extends FabricBlockLootTableProvider {

    public ACLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        LootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ACBlock.YERBA_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(YerbaCropBlock.AGE, 4));

        addDrop(ACBlock.YERBA_CROP, cropDrops(ACBlock.YERBA_CROP, ACItem.YERBA, ACItem.YERBA_SEED, builder));
    }
}
