package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import uk.co.oathompsonjones.datagen.HellishLandscapesModelProvider;
import uk.co.oathompsonjones.datagen.HellishLandscapesParticleProvider;

public class HellishLandscapesDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(HellishLandscapesModelProvider::new);
        pack.addProvider(HellishLandscapesParticleProvider::new);
    }
}
