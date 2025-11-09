package com.amotassic.letmecc.platform;

import com.amotassic.letmecc.ui.InvPayload;
import com.amotassic.letmecc.ui.InvScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {return FabricLoader.getInstance().isModLoaded(modId);}

    @Override
    public void openFullInv(Player player, LivingEntity target, boolean editable) {
        if (player.level().isClientSide()) return;
        var payload = new InvPayload(target.getId(), editable);
        openScreen(player, target.getDisplayName(), p -> payload,
                ((syncId, inv, p) -> new InvScreenHandler(syncId, inv, payload)));
    }

    public static void openScreen(Player player, Component title, Function<ServerPlayer, Object> data, MenuConstructor factory) {
        player.openMenu(new ExtendedScreenHandlerFactory<>() {
            public @NotNull Component getDisplayName() {return title;}
            public Object getScreenOpeningData(ServerPlayer player) {return data.apply(player);}
            public @Nullable AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
                return factory.createMenu(i, inv, player);
            }
        });
    }
}
