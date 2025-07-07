package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HellishLandscapesParticles {
    public static final DefaultParticleType SCREAM_FLAME = register("scream_flame");

    public static DefaultParticleType register(String name) {
        return Registry.register(Registries.PARTICLE_TYPE,
                                 new Identifier(HellishLandscapes.MOD_ID, name),
                                 FabricParticleTypes.simple()
        );
    }

    public static void initialize() {
    }
}
