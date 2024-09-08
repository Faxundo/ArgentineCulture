package github.faxundo.argentina.data.lang;

import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.item.ACItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ACEnglishProvider extends FabricLanguageProvider {
    public ACEnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ACItem.ICON, "Argentine Culture");
        translationBuilder.add(ACItem.MATE, "Mate");
        translationBuilder.add(ACItem.YERBA, "Yerba Mate");
        translationBuilder.add(ACItem.YERBA_SEED, "Yerba Seed");
        translationBuilder.add(ACBlock.KETTLE_BLOCK, "Kettle");
        translationBuilder.add("item.mate.sweet", "Sweet");
        translationBuilder.add("item.mate.bitter", "Bitter");
        translationBuilder.add("item.mate.yerba", "Yerba");
        translationBuilder.add("item.mate.uses", "Uses");
    }
}
