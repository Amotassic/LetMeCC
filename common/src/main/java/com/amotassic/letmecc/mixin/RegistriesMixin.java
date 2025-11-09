package com.amotassic.letmecc.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BuiltInRegistries.class, priority = 1145)
public class RegistriesMixin {

    @Inject(method = "freeze", at = @At(value = "HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        ci.cancel();
    }
}
