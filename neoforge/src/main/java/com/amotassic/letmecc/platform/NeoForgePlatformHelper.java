package com.amotassic.letmecc.platform;

import com.amotassic.letmecc.ui.InvScreenHandler;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.ModList;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {return ModList.get().isLoaded(modId);}

    @Override
    public void openFullInv(Player player, LivingEntity target, boolean editable) {
        if (player.level().isClientSide) return;
        FriendlyByteBuf b = new FriendlyByteBuf(Unpooled.buffer());
        b.writeInt(target.getId()); b.writeBoolean(editable);
        player.openMenu(new SimpleMenuProvider(((i, inv, player1) -> new InvScreenHandler(i, inv, b)), target.getName()), (buf -> {buf.writeInt(target.getId()); buf.writeBoolean(editable);}));
    }
}
