package github.faxundo.argentina.util;

import com.mojang.serialization.Codec;
import github.faxundo.argentina.ArgentineCulture;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static github.faxundo.argentina.ArgentineCulture.MOD_NAME;
import static github.faxundo.argentina.ArgentineCulture.LOGGER;

public class ACDataComponent {

    /*
    0 = Empty
    1 = Bitter
    2 = Sweet
     */
    public static final ComponentType<Integer> MATE_STATE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            ArgentineCulture.identifier("mate_state"),
            ComponentType.<Integer>builder().codec(Codec.INT).build());
    public static final ComponentType<Integer> YERBA_USES = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            ArgentineCulture.identifier("yerba_uses"),
            ComponentType.<Integer>builder().codec(Codec.INT).build());

    public static void registerDataComponents() {
        LOGGER.info(">>> Registering " + MOD_NAME + " data components.");
    }
}
