package com.ludwici.carpetscore.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BlockStateHelper extends FabricModelProvider {
    public BlockModelGenerators generator;

    public BlockStateHelper(FabricDataOutput output) {
        super(output);
    }

    public void createVariantCarpet(Block variant, Block orig) {
        ResourceLocation resourceLocation = TexturedModel.CARPET.get(orig).create(variant, generator.modelOutput);
        generator.blockStateOutput.accept(MultiPartGenerator.multiPart(variant)
                .with(Condition.condition().term(BlockStateProperties.DOWN, true), Variant.variant().with(VariantProperties.MODEL, resourceLocation))
                .with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(BlockStateProperties.UP, true), Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
        );
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        this.generator = blockStateModelGenerator;
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }
}
