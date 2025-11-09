package com.amotassic.letmecc.register;

import com.amotassic.letmecc.Common;
import com.amotassic.letmecc.item.LetMeCCItem;
import com.amotassic.letmecc.ui.InvScreenHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

@SuppressWarnings("SameParameterValue")
public class Register {

    public static final Item LET_ME_CC = register("let_me_cc", new LetMeCCItem());

    public static MenuType<InvScreenHandler> INV_SCREEN_HANDLER = Registry.register(BuiltInRegistries.MENU, Common.id("full_inv"), IMenuTypeExtension.create(InvScreenHandler::new));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, Common.id(name), item);
    }

    public static void init() {}
}
