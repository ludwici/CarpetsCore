package com.ludwici.carpetscore.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class NoGravityVariantBlock extends CarpetVariantBlock{
    public NoGravityVariantBlock(Properties pProperties) {
        super(null, pProperties);
    }

    public NoGravityVariantBlock(Item replaceItem, Properties pProperties) {
        super(replaceItem, pProperties);
    }

    @Override
    protected BlockState getUpdatedState(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        int count = 0;

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BooleanProperty booleanproperty = getPropertyForFace(direction);
            if (pState.getValue(booleanproperty)) {
                count += 1;
            }
        }

        count = count + (pState.getValue(DOWN) ? 1 : 0) + (pState.getValue(UP) ? 1 : 0);
        pState = pState.setValue(FACE_COUNT, count);
        return pState;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = pContext.getLevel().getBlockState(blockpos);
        Direction clickedFace = pContext.getClickedFace();
        Player player = pContext.getPlayer();

        if (player != null && player.isShiftKeyDown() && blockstate.is(this)) {
            BooleanProperty property = getPropertyForFace(clickedFace);
            if (property != null && !blockstate.getValue(property)) {
                blockstate = blockstate.setValue(property, true).setValue(FACE_COUNT, blockstate.getValue(FACE_COUNT) + 1);
                return blockstate;
            }
        }
        return super.getStateForPlacement(pContext);
    }

    @Override
    protected BlockState updateShape(BlockState pState, LevelReader pLevel, ScheduledTickAccess pScheduledTickAccess, BlockPos pCurrentPos, Direction pDirection, BlockPos pDirectionPos, BlockState pDirectionState, RandomSource pRandomSource) {
        return !this.hasFaces(pState) ? Blocks.AIR.defaultBlockState() : pState;
    }
}
