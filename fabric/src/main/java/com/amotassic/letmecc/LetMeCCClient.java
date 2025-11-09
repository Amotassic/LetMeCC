package com.amotassic.letmecc;

import com.amotassic.letmecc.register.Register;
import com.amotassic.letmecc.ui.InvHandledScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class LetMeCCClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(Register.INV_SCREEN_HANDLER, InvHandledScreen::new);
    }
}
