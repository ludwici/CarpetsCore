package com.ludwici.carpetscore.api.datagen;

import com.ludwici.carpetscore.api.block.CarpetVariantBlock;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Set;

public class LootTableHelper extends BlockLootSubProvider{
    protected LootTableHelper(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
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

    public void createSelfVariantItemTable(Block block) {
        createVariantItemTable(block, block.asItem());
    }
    public <T extends Block> void createSelfVariantItemTable(DeferredBlock<T> block) {
        createSelfVariantItemTable(block.get());
    }

    public <B extends Block, I extends Item> void createVariantItemTable(DeferredBlock<B> block, DeferredItem<I> item) {
        createVariantItemTable(block.get(), item.get());
    }

    public <I extends Item> void createVariantItemTable(Block block, DeferredItem<I> item) {
        createVariantItemTable(block, item.get());
    }

    public <B extends Block> void createVariantItemTable(DeferredBlock<B> block, Item item) {
        createVariantItemTable(block.get(), item);
    }

    @Override
    protected void generate() {

    }
}
