package com.ludwici.carpetscore.api.datagen;

import com.ludwici.carpetscore.api.block.CarpetVariantBlock;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Set;

public class LootTableHelper extends BlockLootSubProvider{
    protected LootTableHelper(LootTableSubProvider.Context context) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), context);
    }

    public void createVariantItemTable(Block block, Item item) {
        this.add(block, lt -> LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(AlternativesEntry.alternatives(
                                AlternativesEntry.alternatives(
                                        CarpetVariantBlock.FACE_COUNT.getPossibleValues(),
                                        value -> LootItem.lootTableItem(item)
                                                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(CarpetVariantBlock.FACE_COUNT, value)))
                                                .apply(SetItemCountFunction.setCount(Holder.direct(new ConstantValue(value))))

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
