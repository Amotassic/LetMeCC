package com.amotassic.letmecc.command;

import com.amotassic.letmecc.Common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import static net.minecraft.commands.Commands.literal;

public class LetMeSeeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("letmecc")
                .then(literal("nearest")
                        .requires(source -> source.hasPermission(2))
                        .executes(c -> info(c, null, true))
                )
                .then(Commands.argument("target", EntityArgument.entity())
                        .executes(c -> info(c, EntityArgument.getEntity(c, "target"), false))
                        .then(Commands.argument("editable", BoolArgumentType.bool())
                                .requires(source -> source.hasPermission(2))
                                .executes(c -> info(c, EntityArgument.getEntity(c, "target"), BoolArgumentType.getBool(c, "editable")))
                        )
                )
        );
    }

    private static int info(CommandContext<CommandSourceStack> context, Entity entity, boolean editable) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        if (entity == null) entity = Common.PLATFORM.getClosestEntity(player, LivingEntity.class, 10, LivingEntity::isAlive);
        if (entity instanceof LivingEntity target) {
            Common.PLATFORM.openFullInv(player, target, editable);
            return 1;
        } else {
            context.getSource().sendFailure(Component.translatable("letmecc.fail").withStyle(ChatFormatting.RED));
            return 0;
        }
    }

}
