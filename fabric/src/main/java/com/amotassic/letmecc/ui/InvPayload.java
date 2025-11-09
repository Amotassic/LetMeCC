package com.amotassic.letmecc.ui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record InvPayload(int entityId, boolean editable) {
    public static final StreamCodec<FriendlyByteBuf, InvPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, InvPayload::entityId,
            ByteBufCodecs.BOOL, InvPayload::editable,
            InvPayload::new
    );
}
