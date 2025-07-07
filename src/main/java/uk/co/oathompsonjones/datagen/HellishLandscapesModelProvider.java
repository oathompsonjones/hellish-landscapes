package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import uk.co.oathompsonjones.HellishLandscapesBlocks;
import uk.co.oathompsonjones.HellishLandscapesItems;

public class HellishLandscapesModelProvider extends FabricModelProvider {
    public HellishLandscapesModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(HellishLandscapesBlocks.FENRIR_ICE);
        blockStateModelGenerator.registerSimpleCubeAll(HellishLandscapesBlocks.FENRIR_PACKED_ICE);
        blockStateModelGenerator.registerTorch(HellishLandscapesBlocks.SCREAM_TORCH,
                                               HellishLandscapesBlocks.WALL_SCREAM_TORCH
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(HellishLandscapesItems.GOLDEN_SHEARS, Models.HANDHELD);
        itemModelGenerator.register(HellishLandscapesItems.BOTTLED_SCREAMS, Models.GENERATED);
    }

    @Override
    public String getName() {
        return "HellishLandscapes Model Provider";
    }
}
