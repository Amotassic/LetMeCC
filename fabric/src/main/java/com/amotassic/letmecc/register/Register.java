package com.amotassic.letmecc.register;

import com.amotassic.letmecc.Common;
import com.amotassic.letmecc.item.LetMeCCItem;
import com.amotassic.letmecc.ui.InvPayload;
import com.amotassic.letmecc.ui.InvScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;

@SuppressWarnings("SameParameterValue")
public class Register {

    public static final Item LET_ME_CC = register("let_me_cc", new LetMeCCItem());

    public static MenuType<InvScreenHandler> INV_SCREEN_HANDLER = Registry.register(BuiltInRegistries.MENU, Common.id("full_inv"), new ExtendedScreenHandlerType<>(InvScreenHandler::new, InvPayload.CODEC));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, Common.id(name), item);
    }

    public static void init() {}
}
