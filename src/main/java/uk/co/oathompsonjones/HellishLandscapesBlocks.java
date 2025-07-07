package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.List;

public class HellishLandscapesBlocks {
    public static final Block FENRIR_ICE = register(new Block(AbstractBlock.Settings.copy(Blocks.ICE)), "fenrir_ice");

    public static final Block FENRIR_PACKED_ICE = register(new Block(AbstractBlock.Settings.copy(Blocks.PACKED_ICE)),
                                                           "fenrir_packed_ice"
    );

    public static final Block SCREAM_TORCH = register(
            new TorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).nonOpaque(),
                           HellishLandscapesParticles.SCREAM_FLAME
            ),
            "scream_torch",
            false
    );

    public static final Block WALL_SCREAM_TORCH = register(new WallTorchBlock(
            AbstractBlock.Settings.copy(Blocks.WALL_TORCH).nonOpaque().dropsLike(SCREAM_TORCH),
            HellishLandscapesParticles.SCREAM_FLAME
    ), "wall_scream_torch", false);

    public static final List<Block> BLOCKS = Arrays.stream(HellishLandscapesBlocks.class.getDeclaredFields()).filter(
            field -> field.getType() == Block.class).map(field -> {
        try {
            return (Block) field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access block field: " + field.getName(), e);
        }
    }).toList();

    public static Block register(Block block, String name) {
        return register(block, name, true, new Item.Settings());
    }

    public static Block register(Block block, String name, Item.Settings itemSettings) {
        return register(block, name, true, itemSettings);
    }

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        return register(block, name, shouldRegisterItem, new Item.Settings());
    }

    public static Block register(Block block, String name, boolean shouldRegisterItem, Item.Settings itemSettings) {
        Identifier id = new Identifier(HellishLandscapes.MOD_ID, name);
        if (shouldRegisterItem)
            Registry.register(Registries.ITEM, id, new BlockItem(block, itemSettings));
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {
        for (var block : BLOCKS)
            ItemGroupEvents.modifyEntriesEvent(HellishLandscapesItems.ITEM_GROUP).register((group) -> {
                if (!block.asItem().getDefaultStack().isEmpty())
                    group.add(block.asItem());
            });

        // Register the torch item for the scream torch
        Registry.register(Registries.ITEM,
                          new Identifier(HellishLandscapes.MOD_ID, "scream_torch"),
                          new ScreamTorchItem(SCREAM_TORCH, WALL_SCREAM_TORCH, new Item.Settings())
        );
    }

    private static class ScreamTorchItem extends BlockItem {
        private final Block wallBlock;

        public ScreamTorchItem(Block standingBlock, Block wallBlock, Settings settings) {
            super(standingBlock, settings);
            this.wallBlock = wallBlock;
        }

        @Override
        protected BlockState getPlacementState(ItemPlacementContext context) {
            BlockState wallState = this.wallBlock.getPlacementState(context);
            return wallState != null
                   && wallState.canPlaceAt(context.getWorld(), context.getBlockPos())
                   && context.getSide() != Direction.UP
                   && context.getSide() != Direction.DOWN ? wallState : super.getPlacementState(context);
        }
    }
}
