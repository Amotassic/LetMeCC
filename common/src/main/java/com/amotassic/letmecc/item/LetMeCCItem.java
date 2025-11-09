package com.amotassic.letmecc.item;

import com.amotassic.letmecc.Common;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

@SuppressWarnings("all")
public class LetMeCCItem extends Item {
    public LetMeCCItem() {super(new Properties().stacksTo(1));}

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("item.letmecc.let_me_cc.tooltip"));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand usedHand) {
        if (!user.level().isClientSide && usedHand == InteractionHand.MAIN_HAND) {
            Common.PLATFORM.openFullInv(user, entity, true);
            return InteractionResult.SUCCESS;
        }
        return super.interactLivingEntity(stack, user, entity, usedHand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand usedHand) {
        if (!world.isClientSide && usedHand == InteractionHand.MAIN_HAND) {
            if (!user.isShiftKeyDown()) {
                LivingEntity closest = Common.PLATFORM.getClosestEntity(user, LivingEntity.class, 10, LivingEntity::isAlive);
                if (closest != null) {
                    Common.PLATFORM.openFullInv(user, closest, true);
                    return InteractionResultHolder.success(user.getItemInHand(usedHand));
                }
            } else {
                Common.PLATFORM.openFullInv(user, user, true);
                return InteractionResultHolder.success(user.getItemInHand(usedHand));
            }
        }
        return super.use(world, user, usedHand);
    }
}
