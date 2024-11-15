package com.ludwici.carpetscore.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class BlockStateHelper {

    protected BlockStateProvider provider;

    public BlockStateHelper(BlockStateProvider provider) {
        this.provider = provider;
    }

    public void createVariantCarpet(Block variant, Block orig) {
        ModelFile model = new ConfiguredModel(provider.models().singleTexture(name(variant), ResourceLocation.withDefaultNamespace( "block/carpet"), "wool", provider.blockTexture(orig))).model;

        provider.getMultipartBuilder(variant)
                .part().modelFile(model).addModel().condition(BlockStateProperties.DOWN, true).end()
                .part().modelFile(model).rotationX(90).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.EAST, true).end()
                .part().modelFile(model).rotationX(90).rotationY(0).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true).end()
                .part().modelFile(model).rotationX(90).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.WEST, true).end()
                .part().modelFile(model).rotationX(180).rotationY(0).uvLock(true).addModel().condition(BlockStateProperties.UP, true).end()
                .part().modelFile(model).rotationX(90).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.NORTH, true).end()
        ;
    }

    protected String name(Block block) {
        return key(block).getPath();
    }
    protected ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

}
