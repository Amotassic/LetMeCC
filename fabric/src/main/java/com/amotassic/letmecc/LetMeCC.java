package com.amotassic.letmecc;

import com.amotassic.letmecc.command.LetMeSeeCommand;
import com.amotassic.letmecc.register.Register;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class LetMeCC implements ModInitializer {

    @Override
    public void onInitialize() {
        Common.init();
        Register.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> LetMeSeeCommand.register(dispatcher));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(this::addToTab);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.OP_BLOCKS).register(this::addToTab);
    }

    private void addToTab(FabricItemGroupEntries entries) {
        entries.accept(Register.LET_ME_CC);
    }
}
