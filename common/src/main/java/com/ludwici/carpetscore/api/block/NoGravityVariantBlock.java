package com.ludwici.carpetscore.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class NoGravityVariantBlock extends CarpetVariantBlock {
    public NoGravityVariantBlock(Properties properties) {
        super(null, properties);
    }

    public NoGravityVariantBlock(Item replaceItem, Properties properties) {
        super(replaceItem, properties);
    }

    @Override
    protected BlockState getUpdatedState(BlockState state, BlockGetter level, BlockPos pos) {
        return state.setValue(FACE_COUNT, this.countFaces(state));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState existing = context.getLevel().getBlockState(context.getClickedPos());
        Player player = context.getPlayer();

        if (player != null && player.isShiftKeyDown() && existing.is(this)) {
            BooleanProperty face = getPropertyForFace(context.getClickedFace());

            if (face != null && !existing.getValue(face)) {
                return existing.setValue(face, true).setValue(FACE_COUNT, this.countFaces(existing) + 1);
            }
        }

        return super.getStateForPlacement(context);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return this.hasFaces(state) ? state : Blocks.AIR.defaultBlockState();
    }
}
