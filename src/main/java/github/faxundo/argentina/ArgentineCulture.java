package github.faxundo.argentina;

import github.faxundo.argentina.block.ACBlock;
import github.faxundo.argentina.block.ACBlockEntity;
import github.faxundo.argentina.item.ACItem;
import github.faxundo.argentina.item.ACItemGroup;
import github.faxundo.argentina.util.ACDataComponent;
import github.faxundo.argentina.util.ACLootTableModifier;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgentineCulture implements ModInitializer {
	public static final String MOD_ID = "argentine_culture";
	public static final String MOD_NAME = "Argentine Culture";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ACItem.registerOLItems();
		ACItemGroup.registerItemGroup();
		ACBlock.registerOLBlocks();
		ACLootTableModifier.modifyLootTables();
		ACBlockEntity.registerOLBlockEntities();
		ACDataComponent.registerDataComponents();
	}

	public static Identifier identifier (String name) {
		return Identifier.of(MOD_ID, name);
	}
}