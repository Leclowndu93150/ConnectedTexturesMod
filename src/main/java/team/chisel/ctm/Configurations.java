package team.chisel.ctm;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import team.chisel.ctm.client.model.AbstractCTMBakedModel;

@Mod.EventBusSubscriber(modid = CTM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Configurations {

    public static final Configurations INSTANCE = new Configurations();

    public static void register(ModContainer modContainer, IEventBus modBus) {
        modContainer.addConfig(new ModConfig(Type.CLIENT, INSTANCE.configSpec, modContainer, "ctm.toml"));
    }

    @SubscribeEvent
    public static void onConfigChange(ModConfigEvent.Reloading event) {
        AbstractCTMBakedModel.invalidateCaches();
        Minecraft.getInstance().levelRenderer.allChanged();
    }

    private final ForgeConfigSpec configSpec;

    public final ForgeConfigSpec.BooleanValue disableCTM;
    public final ForgeConfigSpec.BooleanValue connectInsideCTM;

    private Configurations() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        disableCTM = builder.comment("Disable connected textures entirely").define("disableCTM", false);
        connectInsideCTM = builder.comment("Choose whether the inside corner is disconnected on a CTM block - http://imgur.com/eUywLZ4")
              .define("connectInsideCTM", false);
        configSpec = builder.build();
    }

    public static boolean isDisabled() {
        return INSTANCE.disableCTM.get();
    }

    public static boolean connectInsideCTM() {
        if (INSTANCE.configSpec.isLoaded()) {
            return INSTANCE.disableCTM.get();
        }
        return INSTANCE.disableCTM.getDefault();
    }
}
