package github.faxundo.argentina.block.entity;

import github.faxundo.argentina.block.ACBlockEntity;
import github.faxundo.argentina.block.custom.KettleBlock;
import github.faxundo.argentina.util.ACHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KettleBlockEntity extends BlockEntity {

    private int cookingTime;
    private int waterCharges;
    private boolean hasWater;

    public KettleBlockEntity(BlockPos pos, BlockState state) {
        super(ACBlockEntity.KETTLE_BLOCK_ENTITY, pos, state);
        this.cookingTime = 0;
        this.waterCharges = 0;
        this.hasWater = false;
    }

    public int getWaterCharges() {
        return waterCharges;
    }

    public void setWaterCharges(int water_charges) {
        this.waterCharges = water_charges;
    }

    public void setCookingTime(int cooking_time) {
        this.cookingTime = cooking_time;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean hasWater() {
        return this.hasWater;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("cooking_time", cookingTime);
        nbt.putInt("water_changes", waterCharges);
        nbt.putBoolean("has_water", hasWater);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.cookingTime = nbt.getInt("cooking_time");
        this.waterCharges = nbt.getInt("water_changes");
        this.hasWater = nbt.getBoolean("has_water");
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof KettleBlock kettleBlock) {
            BlockState furnace = world.getBlockState(pos.down());
            if (hasWater && furnace.getBlock() instanceof FurnaceBlock furnaceBlock) {
                System.out.println(waterCharges);
                if (cookingTime > 0) {
                    cookingTime--;
                    if (cookingTime == 0) {
                        waterCharges = 6;
                    }
                } else if (waterCharges == 0) {
                    hasWater = false;
                }
                if (cookingTime == 0) {
                    ACHelper.spawnParticle(world, ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, 0, 0, 0, 1, 0.1f);
                }
            }
        }
    }
}