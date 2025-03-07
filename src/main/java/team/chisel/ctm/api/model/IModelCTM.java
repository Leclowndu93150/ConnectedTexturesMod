package team.chisel.ctm.api.model;

import java.util.Collection;

import java.util.Set;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import org.jetbrains.annotations.Nullable;
import team.chisel.ctm.api.texture.ICTMTexture;

public interface IModelCTM extends IUnbakedGeometry<IModelCTM> {

    void load();
    
    Collection<ICTMTexture<?>> getCTMTextures();
    
    ICTMTexture<?> getTexture(ResourceLocation loc);

    Set<RenderType> getExtraLayers(BlockState state);

    @Nullable
    TextureAtlasSprite getOverrideSprite(int tintIndex);

    @Nullable
    ICTMTexture<?> getOverrideTexture(int tintIndex, ResourceLocation loc);
}
