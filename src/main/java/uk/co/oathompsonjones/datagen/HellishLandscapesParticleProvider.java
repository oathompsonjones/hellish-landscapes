package uk.co.oathompsonjones.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;
import uk.co.oathompsonjones.HellishLandscapes;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HellishLandscapesParticleProvider implements DataProvider {
    private final FabricDataOutput output;

    public HellishLandscapesParticleProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        JsonObject json = new JsonObject();
        JsonArray  textures = new JsonArray();
        textures.add(HellishLandscapes.MOD_ID + ":scream_flame");
        json.add("textures", textures);

        Path filePath = output
                .getResolver(FabricDataOutput.OutputType.RESOURCE_PACK, "particles")
                .resolve(Identifier.of(HellishLandscapes.MOD_ID, "scream_flame"), "json");

        futures.add(DataProvider.writeToPath(writer, json, filePath));

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "ParticleDataProvider";
    }
}