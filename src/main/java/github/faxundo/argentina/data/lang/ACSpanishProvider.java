package github.faxundo.argentina.data.lang;

import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.item.ACItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ACSpanishProvider extends FabricLanguageProvider {

    public ACSpanishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "es_ar", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ACItem.ICON, "Cultura Argentina");
        translationBuilder.add(ACItem.MATE, "Mate");
        translationBuilder.add(ACItem.YERBA, "Yerba Mate");
        translationBuilder.add(ACItem.YERBA_SEED, "Semilla de Yerba");
        translationBuilder.add(ACBlock.KETTLE_BLOCK, "Pava");
        translationBuilder.add("item.mate.sweet", "Dulce");
        translationBuilder.add("item.mate.bitter", "Amargo");
        translationBuilder.add("item.mate.yerba", "Yerba");
        translationBuilder.add("item.mate.uses", "Usos");
    }
}
