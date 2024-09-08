package github.faxundo.argentina;

import github.faxundo.argentina.data.ACLootProvider;
import github.faxundo.argentina.data.ACModelProvider;
import github.faxundo.argentina.data.ACRecipeProvider;
import github.faxundo.argentina.data.lang.ACEnglishProvider;
import github.faxundo.argentina.data.lang.ACSpanishProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ArgentineCultureDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ACEnglishProvider::new);
		pack.addProvider(ACSpanishProvider::new);
		pack.addProvider(ACModelProvider::new);
		pack.addProvider(ACRecipeProvider::new);
		pack.addProvider(ACLootProvider::new);
	}
}
