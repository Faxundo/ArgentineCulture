package github.faxundo.argentina.data;

import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.item.ACItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ACRecipeProvider extends FabricRecipeProvider {
    public ACRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ACBlock.KETTLE_BLOCK, 1)
                .pattern(" F ")
                .pattern("F F")
                .pattern("FFF")
                .input('F', Items.IRON_INGOT)
                .showNotification(true)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, Identifier.of(getRecipeName(ACBlock.KETTLE_BLOCK)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ACItem.MATE, 1)
                .pattern(" X ")
                .pattern("AFA")
                .pattern(" A ")
                .input('F', ItemTags.LOGS)
                .input('A', Items.LEATHER)
                .input('X', Items.IRON_INGOT)
                .showNotification(true)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, Identifier.of(getRecipeName(ACItem.MATE)));
    }
}
