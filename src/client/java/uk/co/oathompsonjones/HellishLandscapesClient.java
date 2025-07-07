package uk.co.oathompsonjones;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;

public class HellishLandscapesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        BlockRenderLayerMap.INSTANCE.putBlock(HellishLandscapesBlocks.FENRIR_ICE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(HellishLandscapesBlocks.SCREAM_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HellishLandscapesBlocks.WALL_SCREAM_TORCH, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(HellishLandscapesParticles.SCREAM_FLAME,
                                                       FlameParticle.Factory::new
        );
    }
}