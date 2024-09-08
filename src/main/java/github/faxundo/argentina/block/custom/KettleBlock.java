package github.faxundo.argentina.block.custom;

import com.mojang.serialization.MapCodec;
import github.faxundo.argentina.block.ACBlockEntity;
import github.faxundo.argentina.block.entity.KettleBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KettleBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<KettleBlock> CODEC = createCodec(KettleBlock::new);
    public static final VoxelShape SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(0.3125, 0.375, 0.3125, 0.6875, 0.4375, 0.6875),
            VoxelShapes.cuboid(0.4375, 0.4375, 0.4375, 0.5625, 0.5, 0.5625),
            VoxelShapes.cuboid(0.8125, 0.1875, 0.4375, 1, 0.3125, 0.5625),
            VoxelShapes.cuboid(0.0625, 0.25, 0.4375, 0.1875, 0.375, 0.5625),
            VoxelShapes.cuboid(-0.0625, 0.0625, 0.4375, 0.0625, 0.3125, 0.5625),
            VoxelShapes.cuboid(0.0625, 0, 0.4375, 0.1875, 0.125, 0.5625),
            VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.8125, 0.375, 0.8125));

    public KettleBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<KettleBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KettleBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ACBlockEntity.KETTLE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;

        if (world.getBlockEntity(pos) instanceof KettleBlockEntity kettleBlockEntity) {
            ItemStack waterBucket = player.getMainHandStack();
            if (waterBucket.getItem().equals(Items.WATER_BUCKET) && !kettleBlockEntity.hasWater()) {

                kettleBlockEntity.setCookingTime(200);
                kettleBlockEntity.setWater(true);
                kettleBlockEntity.markDirty();

                ItemStack bucket = new ItemStack(Items.BUCKET);
                waterBucket.decrement(1);
                player.setStackInHand(Hand.MAIN_HAND, bucket);

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}
