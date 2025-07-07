package uk.co.oathompsonjones;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HellishLandscapes implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_NAME = "Hellish Landscapes";
    public static final String MOD_ID   = MOD_NAME.toLowerCase().replace(" ", "");
    public static final Logger LOGGER   = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("{} is initializing!", MOD_ID);

        // Register all particle types
        HellishLandscapesParticles.initialize();

        // Register all items and item groups
        HellishLandscapesItems.initialize();

        // Register all blocks
        HellishLandscapesBlocks.initialize();
    }
}