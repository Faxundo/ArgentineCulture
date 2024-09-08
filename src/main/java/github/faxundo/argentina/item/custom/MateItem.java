package github.faxundo.argentina.item.custom;

import github.faxundo.argentina.block.entity.KettleBlockEntity;
import github.faxundo.argentina.item.ACItem;
import github.faxundo.argentina.util.ACDataComponent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class MateItem extends Item {

    private boolean useOnBlock;

    public MateItem(Settings settings) {
        super(settings.maxCount(1)
                .component(ACDataComponent.MATE_STATE, 0)
                .component(ACDataComponent.YERBA_USES, 0));
        this.useOnBlock = false;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            ItemStack mate = context.getStack();

            if (!mate.contains(ACDataComponent.MATE_STATE)) mate.set(ACDataComponent.MATE_STATE, 0);
            if (!mate.contains(ACDataComponent.YERBA_USES)) mate.set(ACDataComponent.YERBA_USES, 0);

            BlockPos pos = context.getBlockPos();
            BlockEntity blockEntity = context.getWorld().getBlockEntity(pos);
            int yerbaUses = mate.get(ACDataComponent.YERBA_USES);

            if (blockEntity instanceof KettleBlockEntity kettleBlockEntity && yerbaUses > 0) {
                if (kettleBlockEntity.hasWater() && kettleBlockEntity.getCookingTime() == 0) {
                    kettleBlockEntity.setWaterCharges(kettleBlockEntity.getWaterCharges() - 1);
                    mate.set(ACDataComponent.MATE_STATE, 1);
                    this.useOnBlock = true;
                    return ActionResult.CONSUME;
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack mate = user.getMainHandStack();
        if (!world.isClient) {

            //Avoid immediate charge and use
            if (useOnBlock) {
                useOnBlock = false;
                return TypedActionResult.pass(mate);
            }

            if (!mate.contains(ACDataComponent.MATE_STATE)) mate.set(ACDataComponent.MATE_STATE, 0);
            if (!mate.contains(ACDataComponent.YERBA_USES)) mate.set(ACDataComponent.YERBA_USES, 0);

            //Yerba logic
            ItemStack offHandStack = user.getOffHandStack();
            if (offHandStack.getItem().equals(ACItem.YERBA) && mate.get(ACDataComponent.YERBA_USES) == 0) {
                offHandStack.decrement(1);
                mate.set(ACDataComponent.YERBA_USES, 6);
            }

            //Use logic
            if (mate.get(ACDataComponent.YERBA_USES) > 0) {
                int saturation = 1;
                if (mate.get(ACDataComponent.MATE_STATE) == 1 && offHandStack.getItem().equals(Items.SUGAR)) {
                    mate.set(ACDataComponent.MATE_STATE, 2);
                    offHandStack.decrement(1);
                    saturation = 3;
                } else if (mate.get(ACDataComponent.MATE_STATE) == 1 || mate.get(ACDataComponent.MATE_STATE) == 2) {
                    user.getHungerManager().add(2, saturation);
                    mate.set(ACDataComponent.YERBA_USES, mate.get(ACDataComponent.YERBA_USES) - 1);
                    mate.set(ACDataComponent.MATE_STATE, 0);
                    user.playSound(SoundEvents.ENTITY_GENERIC_DRINK,8.0f,0.0f);
                }
            }
        }
        return TypedActionResult.success(mate, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.mate.yerba").formatted(Formatting.GREEN)
                .append(": ")
                .append(String.valueOf(stack.get(ACDataComponent.YERBA_USES))).formatted(Formatting.GREEN)
                .append(" ")
                .append(Text.translatable("item.mate.uses")).formatted(Formatting.GREEN));
        if (stack.get(ACDataComponent.MATE_STATE) == 1) {
            tooltip.add(Text.translatable("item.mate.bitter").formatted(Formatting.YELLOW));
        }
        if (stack.get(ACDataComponent.MATE_STATE) == 2) {
            tooltip.add(Text.translatable("item.mate.sweet").formatted(Formatting.YELLOW));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
