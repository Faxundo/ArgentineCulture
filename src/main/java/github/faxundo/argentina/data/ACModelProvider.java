package github.faxundo.argentina.data;

import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.block.custom.YerbaCropBlock;
import github.faxundo.argentina.item.ACItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ACModelProvider extends FabricModelProvider {

    public ACModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ACBlock.YERBA_CROP, YerbaCropBlock.AGE, 0, 1, 2, 3, 4);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ACItem.ICON, Models.GENERATED);
        itemModelGenerator.register(ACItem.MATE, Models.GENERATED);
        itemModelGenerator.register(ACItem.YERBA, Models.GENERATED);
    }
}
