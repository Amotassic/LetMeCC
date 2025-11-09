package com.amotassic.letmecc;

import com.amotassic.letmecc.command.LetMeSeeCommand;
import com.amotassic.letmecc.register.Register;
import com.amotassic.letmecc.ui.InvHandledScreen;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(Common.MOD_ID)
public class LetMeCC {

    public LetMeCC(IEventBus eventBus, Dist dist) {
        Common.init();
        Register.init();
        NeoForge.EVENT_BUS.addListener(RegisterCommandsEvent.class, evt -> LetMeSeeCommand.register(evt.getDispatcher()));
        eventBus.addListener(this::addToTab);

        if (dist.isClient()) {
            eventBus.addListener(this::registerScreen);
        }
    }

    private void registerScreen(RegisterMenuScreensEvent event) {
        event.register(Register.INV_SCREEN_HANDLER, InvHandledScreen::new);
    }

    private void addToTab(BuildCreativeModeTabContentsEvent event) {
        var tabKey = event.getTabKey();
        if (tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES || tabKey == CreativeModeTabs.OP_BLOCKS) {
            event.accept(Register.LET_ME_CC);
        }
    }
}
