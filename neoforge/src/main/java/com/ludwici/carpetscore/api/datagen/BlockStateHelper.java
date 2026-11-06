package com.ludwici.carpetscore.api.datagen;


import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class BlockStateHelper {
    protected BlockModelGenerators generator;

    public BlockStateHelper(BlockModelGenerators generator) {
        this.generator = generator;
    }

    public Identifier getModel(Block variant, Block orig) {
        return TexturedModel.CARPET.get(orig).create(variant, generator.modelOutput);
    }

    public Identifier getTranslucentModel(Block variant, Block orig) {
        var modelTemplate = TexturedModel.CARPET.get(orig).getTemplate().extend()
//                .renderType("translucent")
                .build();
        TextureMapping mapping = new TextureMapping().put(TextureSlot.WOOL, TextureMapping.getBlockTexture(orig));
        return modelTemplate.create(variant, mapping, generator.modelOutput);
    }

    public MultiVariant createVariantCarpet(Block variant, Block orig, MultiVariant multiVariant) {
        generator.blockStateOutput.accept(MultiPartGenerator.multiPart(variant)
                .with(condition().term(BlockStateProperties.DOWN, true), multiVariant)
                .with(condition().term(BlockStateProperties.EAST, true), multiVariant.with(X_ROT_90).with(Y_ROT_270).with(UV_LOCK))
                .with(condition().term(BlockStateProperties.SOUTH, true), multiVariant.with(X_ROT_90).with(UV_LOCK))
                .with(condition().term(BlockStateProperties.WEST, true), multiVariant.with(X_ROT_90).with(Y_ROT_90).with(UV_LOCK))
                .with(condition().term(BlockStateProperties.UP, true), multiVariant.with(X_ROT_180).with(UV_LOCK))
                .with(condition().term(BlockStateProperties.NORTH, true), multiVariant.with(X_ROT_90).with(Y_ROT_180).with(UV_LOCK))
        );
        return multiVariant;
    }

    public MultiVariant createVariantCarpet(Block variant, Block orig) {
        MultiVariant multiVariant = plainVariant(getModel(variant, orig));
        return createVariantCarpet(variant, orig, multiVariant);
    }

    public void createTranslucentVariantWithItem(Block variant, Block orig) {
        createTranslucentVariant(variant, orig);
        generator.createFlatItemModelWithBlockTexture(variant.asItem(), variant);
    }

    public MultiVariant createTranslucentVariant(Block variant, Block orig) {
        return createVariantCarpet(variant, orig, plainVariant(getTranslucentModel(variant, orig)));
    }

    public void createVariantCarpetWithItem(Block variant, Block orig, MultiVariant model) {
        createVariantCarpet(variant, orig, model);
        generator.registerSimpleItemModel(variant, key(variant));
    }

    public void createVariantCarpetWithItem(Block variant, Block orig) {
        createVariantCarpet(variant, orig);
        generator.createFlatItemModelWithBlockTexture(variant.asItem(), variant);
    }

    public <T extends Block> void createVariantCarpet(DeferredBlock<T> variant, Block orig) {
        createVariantCarpet(variant.get(), orig);
    }

    public <T extends Block> void createVariantCarpet(DeferredBlock<T> variant, DeferredBlock<T> orig) {
        createVariantCarpet(variant.get(), orig.get());
    }

    public <T extends Block> void createVariantCarpet(T variant, DeferredBlock<T> orig) {
        createVariantCarpet(variant, orig.get());
    }

    protected String name(Block block) {
        return key(block).getPath();
    }
    protected Identifier key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}