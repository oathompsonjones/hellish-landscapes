package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class HellishLandscapesItems {
    public static final Item GOLDEN_SHEARS = register(new Item(new Item.Settings().maxCount(1)), "golden_shears");

    public static final Item BOTTLED_SCREAMS = register(new Item(new Item.Settings().maxCount(16)), "bottled_screams");

    public static final List<Item> ITEMS = Arrays
            .stream(HellishLandscapesItems.class.getDeclaredFields())
            .filter(field -> field.getType() == Item.class)
            .map(field -> {
                try {
                    return (Item) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access item field: " + field.getName(), e);
                }
            })
            .toList();

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
                                                                           Identifier.of(HellishLandscapes.MOD_ID,
                                                                                         "item_group"
                                                                           )
    );

    public static <T extends Item> T register(T item, String id) {
        return Registry.register(Registries.ITEM, new Identifier(HellishLandscapes.MOD_ID, id), item);
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP,
                          ITEM_GROUP,
                          FabricItemGroup
                                  .builder()
                                  .icon(() -> new ItemStack(GOLDEN_SHEARS))
                                  .displayName(Text.of(HellishLandscapes.MOD_NAME))
                                  .build()
        );
        for (var item : ITEMS)
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(item));
    }
}
