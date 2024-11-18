package com.ludwici.carpetscore.datagen;

import com.ludwici.carpetscore.api.block.CarpetVariantBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;

public class LootTableHelper extends FabricBlockLootTableProvider {
    protected LootTableHelper(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void createVariantItemTable(Block block, Item item) {
        this.add(block, lt -> LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(AlternativesEntry.alternatives(
                                AlternativesEntry.alternatives(
                                        CarpetVariantBlock.FACE_COUNT.getPossibleValues(),
                                        value -> LootItem.lootTableItem(item)
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CarpetVariantBlock.FACE_COUNT, value))
                                                )
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(value)))
                                )
                        ))
                )
        );
    }

    @Override
    public void generate() {

    }
}
