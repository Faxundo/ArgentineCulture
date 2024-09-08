package github.faxundo.argentina.block;

import github.faxundo.argentina.ArgentineCulture;
import github.faxundo.argentina.block.entity.KettleBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static github.faxundo.argentina.ArgentineCulture.MOD_NAME;
import static github.faxundo.argentina.ArgentineCulture.LOGGER;

public class ACBlockEntity {

    public static final BlockEntityType<KettleBlockEntity> KETTLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, ArgentineCulture.identifier("kettle"),
                    BlockEntityType.Builder.create(KettleBlockEntity::new,
                            ACBlock.KETTLE_BLOCK).build());

    public static void registerOLBlockEntities() {
        LOGGER.info(">>> Registering " + MOD_NAME + " blocks entities.");
    }
}
